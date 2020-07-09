package io.github.novarch129.jojomod.entities.stands;

import io.github.novarch129.jojomod.capabilities.stand.Stand;
import io.github.novarch129.jojomod.entities.stands.attacks.PurpleHazePunchEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
public class PurpleHazeEntity extends AbstractStandEntity {
    public PurpleHazeEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
    }

    public PurpleHazeEntity(World world) {
        super(EntityInit.PURPLE_HAZE.get(), world);
        spawnSound = SoundInit.SPAWN_PURPLE_HAZE.get();
    }

    public void burstCapsule() {
        if (!world.isRemote)
            world.getServer().getWorld(dimension).getEntities()
                    .filter(entity -> entity instanceof LivingEntity)
                    .filter(entity -> !(entity instanceof AbstractStandEntity))
                    .filter(entity -> entity.getDistance(this) < 80)
                    .forEach(entity -> ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 200, 2)));
    }

    @Override
    public void playSpawnSound() {
        world.playSound(null, getMaster().getPosition(), getSpawnSound(), SoundCategory.NEUTRAL, 2, 1);
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.PURPLE_HAZE_RUSH.get(), SoundCategory.NEUTRAL, 4.5f, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                PurpleHazePunchEntity purpleHazePunchEntity = new PurpleHazePunchEntity(world, this, getMaster());
                purpleHazePunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.5f, 0.3f);
                world.addEntity(purpleHazePunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                ability = props.getAbility();

                if (props.getCooldown() > 0 && ability)
                    props.subtractCooldown(1);
            });

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
                        PurpleHazePunchEntity purpleHaze1 = new PurpleHazePunchEntity(world, this, player);
                        purpleHaze1.setRandomPositions();
                        purpleHaze1.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.4f);
                        world.addEntity(purpleHaze1);
                        PurpleHazePunchEntity purpleHaze2 = new PurpleHazePunchEntity(world, this, player);
                        purpleHaze2.setRandomPositions();
                        purpleHaze2.shoot(player, player.rotationPitch, player.rotationYaw, 2.2f, 0.4f);
                        world.addEntity(purpleHaze2);
                    }
                if (attackTicker >= 120) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
