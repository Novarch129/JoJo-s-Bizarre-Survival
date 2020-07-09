package io.github.novarch129.jojomod.entities.stands;

import io.github.novarch129.jojomod.entities.stands.attacks.GoldExperiencePunchEntity;
import io.github.novarch129.jojomod.capabilities.stand.Stand;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
public class GoldExperienceEntity extends AbstractStandEntity {
    private boolean transforming;
    private int transformTick;

    public GoldExperienceEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_GOLD_EXPERIENCE.get();
    }

    public GoldExperienceEntity(World world) {
        super(EntityInit.GOLD_EXPERIENCE.get(), world);
        spawnSound = SoundInit.SPAWN_GOLD_EXPERIENCE.get();
    }

    public boolean isTransforming() {
        return transforming;
    }

    public void setTransforming(boolean transforming) {
        this.transforming = transforming;
    }

    public int getTransformTick() {
        return transformTick;
    }

    public void setTransformTick(int transformTick) {
        this.transformTick = transformTick;
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        if (world.isRemote) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.MUDAGIORNO.get(), getSoundCategory(), 1, 1);
                attackRush = true;
            } else {
                if (attackRush) return;
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                GoldExperiencePunchEntity goldExperience = new GoldExperiencePunchEntity(world, this, getMaster());
                goldExperience.shoot(getMaster(), getMaster().rotationPitch, getMaster().rotationYaw, 2, 0.2f);
                world.addEntity(goldExperience);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                ability = props.getAbility();

                if (props.getTransformed() > 0)
                    props.subtractCooldown(1);
                if (props.getCooldown() <= 0) {
                    props.setTransformed(0);
                    props.setCooldown(80);
                }
            });
            player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 2));

            followMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        GoldExperiencePunchEntity goldExperience1 = new GoldExperiencePunchEntity(world, this, player);
                        goldExperience1.setRandomPositions();
                        goldExperience1.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
                        world.addEntity(goldExperience1);
                        GoldExperiencePunchEntity goldExperience2 = new GoldExperiencePunchEntity(world, this, player);
                        goldExperience2.setRandomPositions();
                        goldExperience2.shoot(player, player.rotationPitch, player.rotationYaw, 2.0F, 0.2F);
                        world.addEntity(goldExperience2);
                    }
                if (attackTicker >= 110) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
