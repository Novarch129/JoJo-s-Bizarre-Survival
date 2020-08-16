package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.StickyFingersPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.network.message.server.SSyncStickyFingersDisguisePacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

@SuppressWarnings("ConstantConditions")
public class StickyFingersEntity extends AbstractStandEntity {
    public LivingEntity disguiseEntity;

    public StickyFingersEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_STICKY_FINGERS.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.ARRIVEDERCI.get(), SoundCategory.NEUTRAL, 1, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                StickyFingersPunchEntity stickyFingersPunchEntity = new StickyFingersPunchEntity(world, this, getMaster());
                stickyFingersPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.7f, 0.12f);
                world.addEntity(stickyFingersPunchEntity);
            }
    }

    public void disguise(int entityID) {
        if (getMaster() == null) return;
        Entity entity = world.getEntityByID(entityID);
        if (entity instanceof LivingEntity) {
            disguiseEntity = (LivingEntity) entity;
            master.setInvulnerable(true);
        } else if (entity == null) {
            disguiseEntity = null;
            master.setInvulnerable(false);
        }
        if (!world.isRemote)
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) master), new SSyncStickyFingersDisguisePacket(getEntityId(), entityID));
    }

    public void zipThroughWall() {
        if (getMaster() == null || world.isRemote) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            if (props.getCooldown() == 0) {
                Vec3d position = master.getLookVec().mul(3, 1, 3).add(master.getPositionVec());
                Vec3d position2 = master.getLookVec().add(master.getPositionVec());
                if (!world.getBlockState(new BlockPos(position2)).isAir(world, new BlockPos(position2)) &&
                        !world.getBlockState(new BlockPos(position2.add(0, 1, 0))).isAir(world, new BlockPos(position2.add(0, 1, 0))) &&
                        world.getBlockState(new BlockPos(position)).isAir(world, new BlockPos(position)) &&
                        world.getBlockState(new BlockPos(position.add(0, 1, 0))).isAir(world, new BlockPos(position.add(0, 1, 0)))) {
                    master.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());
                    world.playSound(null, getPosition(), SoundInit.ZIPPER.get(), SoundCategory.NEUTRAL, 1, 1);
                    props.setCooldown(60);
                }
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                props.setAbilityActive(props.getTimeLeft() > 601 && props.getCooldown() == 0 && props.getAbility() && disguiseEntity == null);
                ability = props.getAbility() && props.getTimeLeft() > 600;
                if (ability)
                    props.setTimeLeft(props.getTimeLeft() - 1);
                if (props.getTimeLeft() == 601)
                    props.setCooldown(200);
                if (props.getAbilityActive()) {
                    props.setNoClip(true);
                    master.setSwimming(true);
                    fallDistance = 0;
                    if (!world.getBlockState(master.getPosition()).isSolid())
                        master.setMotion(0, -0.5, 0);
                    if (master.getPosition().getY() < 1)
                        master.setMotion(0, 0.5, 0);
                } else {
                    if ((world.getBlockState(master.getPosition()).isSolid() && !world.getBlockState(master.getPosition()).isTransparent()) || master.getPosition().getY() < 1) {
                        master.setMotion(0, 2, 0);
                        fallDistance = 0;
                    } else {
                        if (props.getNoClip()) {
                            master.setMotion(0, 0, 0);
                            fallDistance = 0;
                            props.setNoClip(false);
                        }
                    }
                }
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (!world.isRemote) {
                    master.setSprinting(false);
                    StickyFingersPunchEntity stickyFingersPunchEntity = new StickyFingersPunchEntity(world, this, master);
                    stickyFingersPunchEntity.randomizePositions();
                    stickyFingersPunchEntity.shoot(master, master.rotationPitch, master.rotationYaw, 2.4f, 0.16f);
                    world.addEntity(stickyFingersPunchEntity);
                    StickyFingersPunchEntity stickyFingersPunchEntity1 = new StickyFingersPunchEntity(world, this, master);
                    stickyFingersPunchEntity1.randomizePositions();
                    stickyFingersPunchEntity1.shoot(master, master.rotationPitch, master.rotationYaw, 2.4f, 0.16f);
                    world.addEntity(stickyFingersPunchEntity1);
                }
                if (attackTicker >= 170) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }

    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> props.setNoClip(false));
        master.setInvulnerable(false);
    }
}
