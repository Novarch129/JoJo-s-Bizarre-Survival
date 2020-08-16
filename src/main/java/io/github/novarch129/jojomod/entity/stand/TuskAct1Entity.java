package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.NailBulletEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.IChargeable;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class TuskAct1Entity extends AbstractStandEntity implements IChargeable {
    private int bulletChargeTicks;
    private int prevBulletChargeTicks;

    public TuskAct1Entity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_STICKY_FINGERS.get();
    }

    @Override
    public void attack(boolean special) {
    }

    @Override
    public void chargeAttack(boolean isCharging) {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            props.setCharging(isCharging);
            if (isCharging && bulletChargeTicks <= 220) {
                setChargeTicks(bulletChargeTicks + 1);
                props.setStandDamage(3.95f + bulletChargeTicks / 20f);
            } else if (!isCharging)
                setChargeTicks(0);
        });
    }

    @Override
    public int getChargeTicks() {
        return bulletChargeTicks;
    }

    @Override
    public void setChargeTicks(int chargeTicks) {
        this.prevBulletChargeTicks = this.bulletChargeTicks;
        this.bulletChargeTicks = chargeTicks;
    }

    @Override
    public int getPrevChargeTicks() {
        return prevBulletChargeTicks;
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();

                if (getChargeTicks() == 0 && getChargeTicks() != getPrevChargeTicks()) {
                    world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                    NailBulletEntity nailBulletEntity = new NailBulletEntity(world, this, master);
                    nailBulletEntity.damage = 3.95f + getPrevChargeTicks() / 20f;
                    if (nailBulletEntity.damage >= 7) {
                        for (int i = 0; i < nailBulletEntity.damage / 6; i++) {
                            world.playSound(null, getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4, (1 + (rand.nextFloat() - rand.nextFloat()) * 0.2f) * 0.7f);
                            if (world.isRemote) {
                                for (int k = 0; k < 20; ++k) {
                                    double d0 = rand.nextGaussian() * 0.02;
                                    double d1 = rand.nextGaussian() * 0.02;
                                    double d2 = rand.nextGaussian() * 0.02;
                                    Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                                    world.addParticle(ParticleTypes.POOF,
                                            position.getX() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            position.getY() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            position.getZ() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            d0, d1, d2);
                                }
                            } else
                                world.setEntityState(this, (byte) 20);
                        }
                    }
                    nailBulletEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4 + nailBulletEntity.damage / 7, 0.05f);
                    if (!world.isRemote)
                        world.addEntity(nailBulletEntity);
                }
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
        }
    }
}
