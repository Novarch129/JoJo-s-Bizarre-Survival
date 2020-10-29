package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.SoftAndWetPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class SoftAndWetEntity extends AbstractStandEntity {
    public SoftAndWetEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_SOFT_AND_WET.get();
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
                SoftAndWetPunchEntity softAndWetPunchEntity = new SoftAndWetPunchEntity(world, this, getMaster());
                softAndWetPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4, 0.01f);
                world.addEntity(softAndWetPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> ability = props.getAbility());

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
                        SoftAndWetPunchEntity softAndWetPunchEntity = new SoftAndWetPunchEntity(world, this, master);
                        softAndWetPunchEntity.randomizePositions();
                        softAndWetPunchEntity.shoot(master, master.rotationPitch, master.rotationYaw, 3.5f, 0.1f);
                        world.addEntity(softAndWetPunchEntity);
                        SoftAndWetPunchEntity softAndWetPunchEntity1 = new SoftAndWetPunchEntity(world, this, master);
                        softAndWetPunchEntity1.randomizePositions();
                        softAndWetPunchEntity1.shoot(master, master.rotationPitch, master.rotationYaw, 3.5f, 0.1f);
                        world.addEntity(softAndWetPunchEntity1);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
