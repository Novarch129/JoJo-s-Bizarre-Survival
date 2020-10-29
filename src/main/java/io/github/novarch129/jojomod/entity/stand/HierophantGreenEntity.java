package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.EmeraldSplashEntity;
import io.github.novarch129.jojomod.entity.stand.attack.HierophantGreenTailEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.network.message.server.SSyncHierophantGreenPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

@SuppressWarnings("ConstantConditions")
public class HierophantGreenEntity extends AbstractStandEntity {
    public LivingEntity possessedEntity;
    public float yaw, pitch;

    public HierophantGreenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_HIEROPHANT_GREEN.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.EMERALD_SPLASH.get(), SoundCategory.NEUTRAL, 1, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                HierophantGreenTailEntity hierophantGreenTailEntity = new HierophantGreenTailEntity(world, this, master);
                hierophantGreenTailEntity.shoot(master, rotationPitch, rotationYaw, 3, 0.15f);
                world.addEntity(hierophantGreenTailEntity);
            }
    }

    public void setPossessedEntity(int entityID) {
        if (getMaster() == null) return;
        Entity entity = world.getEntityByID(entityID);
        if (entity instanceof LivingEntity) {
            possessedEntity = (LivingEntity) entity;
            master.setInvulnerable(true);
        } else if (entity == null)
            possessedEntity = null;
        if (!world.isRemote)
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) master), new SSyncHierophantGreenPacket(getEntityId(), entityID));
    }

    @Override
    public void tick() {
        super.tick();
        if (master != null) {
            Stand.getLazyOptional(master).ifPresent(props -> props.setAbilityActive(possessedEntity != null));

            if (possessedEntity != null) {
                possessedEntity.rotationYaw = master.rotationYaw;
                possessedEntity.rotationPitch = master.rotationPitch * 0.5f;
                possessedEntity.setRotation(rotationYaw, rotationPitch);
                possessedEntity.prevRotationYaw = rotationYaw;
                possessedEntity.renderYawOffset = rotationYaw;
                possessedEntity.rotationYawHead = renderYawOffset;
                if (possessedEntity instanceof MobEntity) {
                    ((MobEntity) possessedEntity).goalSelector.disableFlag(Goal.Flag.LOOK);
                    ((MobEntity) possessedEntity).goalSelector.disableFlag(Goal.Flag.MOVE);
                }
            }

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
                        EmeraldSplashEntity emeraldSplashEntity = new EmeraldSplashEntity(world, this, master);
                        emeraldSplashEntity.randomizePositions();
                        emeraldSplashEntity.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.25f);
                        world.addEntity(emeraldSplashEntity);
                        EmeraldSplashEntity emeraldSplashEntity1 = new EmeraldSplashEntity(world, this, master);
                        emeraldSplashEntity1.randomizePositions();
                        emeraldSplashEntity1.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.25f);
                        world.addEntity(emeraldSplashEntity1);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (possessedEntity instanceof MobEntity) {
            ((MobEntity) possessedEntity).goalSelector.enableFlag(Goal.Flag.LOOK);
            ((MobEntity) possessedEntity).goalSelector.enableFlag(Goal.Flag.MOVE);
        }
    }
}
