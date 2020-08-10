package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.AerosmithBulletEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class AerosmithEntity extends AbstractStandEntity {
    public AerosmithEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_AEROSMITH.get();
    }

    @Override
    public void playSpawnSound() {
        world.playSound(null, getMaster().getPosition(), getSpawnSound(), SoundCategory.NEUTRAL, 3, 1);
    }

    public void shootBomb() {
        Stand.getLazyOptional(getMaster()).ifPresent(props -> {
            if (props.getCooldown() <= 0) {
                TNTEntity tnt = new TNTEntity(world, getPosX(), getPosY(), getPosZ(), getMaster());
                tnt.setFuse(20);
                tnt.setVelocity(getLookVec().getX(), getLookVec().getY(), getLookVec().getZ());
                if (!world.isRemote)
                    world.addEntity(tnt);
                props.setCooldown(200);
            }
        });
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.VOLARUSH.get(), SoundCategory.NEUTRAL, 4.05f, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                AerosmithBulletEntity aerosmithBulletEntity = new AerosmithBulletEntity(world, this, getMaster());
                aerosmithBulletEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4, 0.4f);
                world.addEntity(aerosmithBulletEntity);
                attackTick = 0;
            }
    }

    @Override
    public void tick() {
        super.tick();
        setMotion(getMotion().getX(), 0, getMotion().getZ());
        if (master != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                if (ability != props.getAbility())
                    ability = props.getAbility();
                if (ability)
                    if (props.getCooldown() > 0)
                        props.subtractCooldown(1);
            });

            setRotation(master.rotationYaw, master.rotationPitch);
            if (getMotion() == Vec3d.ZERO)
                setRotationYawHead(master.rotationYawHead);

            if (master.swingProgressInt == 0 && !ability && !attackRush)
                attackTick = 0;
            if (attackRush) {
                if (!ability)
                    master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        AerosmithBulletEntity aerosmithBullet1 = new AerosmithBulletEntity(world, this, master);
                        aerosmithBullet1.randomizePositions();
                        aerosmithBullet1.shoot(master, rotationPitch, rotationYaw, 4, 0.3f);
                        world.addEntity(aerosmithBullet1);
                        AerosmithBulletEntity aerosmithBullet2 = new AerosmithBulletEntity(world, this, master);
                        aerosmithBullet2.randomizePositions();
                        aerosmithBullet2.shoot(master, rotationPitch, rotationYaw, 4, 0.3f);
                        world.addEntity(aerosmithBullet2);
                    }
                if (attackTicker >= 110) {
                    attackRush = false;
                    attackTicker = 0;
                    attackTick = 0;
                }
            }
        }
    }
}