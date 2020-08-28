package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.EchoesSoundEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class EchoesAct1Entity extends AbstractStandEntity {
    public EchoesAct1Entity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_ECHOES_ACT_1.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1) {
            world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
            EchoesSoundEntity echoesSoundEntity = new EchoesSoundEntity(world, this, getMaster());
            echoesSoundEntity.shoot(getMaster(), rotationPitch, rotationYaw, 1.5f, 0.2f);
            world.addEntity(echoesSoundEntity);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                if (props.getAct() == 0 && props.getStandOn()) {
                    switch (props.getStandID()) {
                        case Util.StandID.ECHOES_ACT_2: {
                            remove();
                            EchoesAct2Entity echoesAct2Entity = new EchoesAct2Entity(EntityInit.ECHOES_ACT_2.get(), world);
                            Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                            echoesAct2Entity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), master.rotationYaw, master.rotationPitch);
                            echoesAct2Entity.setMaster(master);
                            echoesAct2Entity.setMasterUUID(master.getUniqueID());
                            world.addEntity(echoesAct2Entity);
                            break;
                        }
                        case Util.StandID.ECHOES_ACT_3: {
                            remove();
                            EchoesAct3Entity echoesAct3Entity = new EchoesAct3Entity(EntityInit.ECHOES_ACT_3.get(), world);
                            Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                            echoesAct3Entity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), master.rotationYaw, master.rotationPitch);
                            echoesAct3Entity.setMaster(master);
                            echoesAct3Entity.setMasterUUID(master.getUniqueID());
                            world.addEntity(echoesAct3Entity);
                            break;
                        }
                        default:
                            break;
                    }
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
