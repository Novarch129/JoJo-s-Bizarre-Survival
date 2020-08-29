package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.NailBulletEntity;
import io.github.novarch129.jojomod.init.EntityInit;
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
public class TuskAct2Entity extends AbstractStandEntity implements IChargeable {
    private int bulletChargeTicks;
    private int prevBulletChargeTicks;

    public TuskAct2Entity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_TUSK_ACT_2.get();
    }

    @Override
    public void attack(boolean special) {
    }

    @Override
    public void chargeAttack(boolean isCharging) {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            if (props.getCooldown() > 0) return;
            props.setCharging(isCharging);
            if (isCharging && bulletChargeTicks <= 440) {
                setChargeTicks(bulletChargeTicks + 4);
                props.setStandDamage(3.6f + (bulletChargeTicks + 4) / 20f);
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

                if (props.getAct() == props.getMaxAct() - 1 && props.getStandOn()) {
                    remove();
                    TuskAct1Entity tuskAct1Entity = new TuskAct1Entity(EntityInit.TUSK_ACT_1.get(), world);
                    Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                    tuskAct1Entity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), master.rotationYaw, master.rotationPitch);
                    tuskAct1Entity.setMaster(master);
                    tuskAct1Entity.setMasterUUID(master.getUniqueID());
                    world.addEntity(tuskAct1Entity);
                }

                if (props.getAbilityUseCount() < 10 && getChargeTicks() == 0 && getChargeTicks() != getPrevChargeTicks()) {
                    world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                    NailBulletEntity nailBulletEntity = new NailBulletEntity(world, this, master, true);
                    nailBulletEntity.damage = 3.6f + (getPrevChargeTicks() + 4) / 20f;
                    if (nailBulletEntity.damage >= 9) {
                        for (int i = 0; i < (nailBulletEntity.damage / 5 - 1); i++) {
                            world.playSound(null, getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 2, (1 + (rand.nextFloat() - rand.nextFloat()) * 0.2f) * 0.7f);
                            if (world.isRemote) {
                                for (int k = 0; k < 20; k++) {
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
                    nailBulletEntity.shoot(getMaster(), 0, rotationYaw, 0.1f, 0.05f);
                    if (!world.isRemote) {
                        world.addEntity(nailBulletEntity);
                        props.setAbilityUseCount(props.getAbilityUseCount() + 1);
                    }
                }

                if (props.getAbilityUseCount() == 10 && props.getCooldown() == 0)
                    props.setCooldown(300);

                if (props.getCooldown() == 1)
                    props.setAbilityUseCount(0);
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
        }
    }
}
