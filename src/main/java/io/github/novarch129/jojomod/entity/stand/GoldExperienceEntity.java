package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.GoldExperiencePunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings({"ConstantConditions", "unused"})
public class GoldExperienceEntity extends AbstractStandEntity {
    private boolean transforming;
    private int transformTick;

    public GoldExperienceEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_GOLD_EXPERIENCE.get();
    }

    //4 methods below are currently redundant, will be used in the future.
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
                GoldExperiencePunchEntity goldExperience = new GoldExperiencePunchEntity(world, this, master);
                goldExperience.shoot(getMaster(), getMaster().rotationPitch, getMaster().rotationYaw, 2, 0.2f);
                world.addEntity(goldExperience);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                if (props.getTransformed() > 0)
                    props.subtractCooldown(1);
                if (props.getCooldown() <= 0) {
                    props.setTransformed(0);
                    props.setCooldown(80);
                }
            });
            master.addPotionEffect(new EffectInstance(Effects.REGENERATION, 40, 2));

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        GoldExperiencePunchEntity goldExperience1 = new GoldExperiencePunchEntity(world, this, master);
                        goldExperience1.randomizePositions();
                        goldExperience1.shoot(master, master.rotationPitch, master.rotationYaw, 2.0F, 0.2F);
                        world.addEntity(goldExperience1);
                        GoldExperiencePunchEntity goldExperience2 = new GoldExperiencePunchEntity(world, this, master);
                        goldExperience2.randomizePositions();
                        goldExperience2.shoot(master, master.rotationPitch, master.rotationYaw, 2.0F, 0.2F);
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
