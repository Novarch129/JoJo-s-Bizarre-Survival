package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.WhitesnakePunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class WhitesnakeEntity extends AbstractStandEntity {
    public WhitesnakeEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_WHITESNAKE.get();
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
                WhitesnakePunchEntity whitesnakePunchEntity = new WhitesnakePunchEntity(world, this, getMaster());
                whitesnakePunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 1.5f, 0.2f);
                world.addEntity(whitesnakePunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> ability = props.getAbility());
            master.setNoGravity(false);

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
                        WhitesnakePunchEntity whitesnake1 = new WhitesnakePunchEntity(world, this, master);
                        whitesnake1.randomizePositions();
                        whitesnake1.shoot(master, master.rotationPitch, master.rotationYaw, 1, 0.25f);
                        world.addEntity(whitesnake1);
                        WhitesnakePunchEntity whitesnake2 = new WhitesnakePunchEntity(world, this, master);
                        whitesnake2.randomizePositions();
                        whitesnake2.shoot(master, master.rotationPitch, master.rotationYaw, 1, 0.25f);
                        world.addEntity(whitesnake2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
