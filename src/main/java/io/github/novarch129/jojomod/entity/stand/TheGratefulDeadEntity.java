package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.TheGratefulDeadPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class TheGratefulDeadEntity extends AbstractStandEntity {
    public TheGratefulDeadEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_GREEN_DAY.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special)
                attackRush = true;
            else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                TheGratefulDeadPunchEntity theGratefulDeadPunchEntity = new TheGratefulDeadPunchEntity(world, this, getMaster());
                theGratefulDeadPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.3f, 0.2f);
                world.addEntity(theGratefulDeadPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                props.setAbilityActive(props.getTimeLeft() > 801 && props.getCooldown() <= 0 && props.getAbility());
                ability = props.getTimeLeft() > 800 && props.getCooldown() <= 0 && props.getAbility();
                if (ability)
                    props.subtractTimeLeft(1);
            });

            if (ability && !world.isRemote)
                getServer().getWorld(dimension).getEntities()
                        .filter(entity -> !entity.equals(this) && !entity.equals(master))
                        .filter(entity -> entity instanceof LivingEntity)
                        .filter(entity -> entity.getDistance(this) < 20)
                        .forEach(entity -> {

                        });

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
                        TheGratefulDeadPunchEntity theGratefulDead1 = new TheGratefulDeadPunchEntity(world, this, master);
                        theGratefulDead1.randomizePositions();
                        theGratefulDead1.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.25f);
                        world.addEntity(theGratefulDead1);
                        TheGratefulDeadPunchEntity theGratefulDead2 = new TheGratefulDeadPunchEntity(world, this, master);
                        theGratefulDead2.randomizePositions();
                        theGratefulDead2.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.25f);
                        world.addEntity(theGratefulDead2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
