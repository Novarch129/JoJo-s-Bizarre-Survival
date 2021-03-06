package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.CMoonPunchEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class CMoonEntity extends AbstractStandEntity {
    public CMoonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_CMOON.get();
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
                CMoonPunchEntity cMoonPunchEntity = new CMoonPunchEntity(world, this, master);
                cMoonPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4, 0.15f);
                world.addEntity(cMoonPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                if (props.getAct() == props.getMaxAct() - 1) {
                    remove();
                    WhitesnakeEntity whitesnake = new WhitesnakeEntity(EntityInit.WHITESNAKE.get(), world);
                    Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                    whitesnake.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), master.rotationYaw, master.rotationPitch);
                    whitesnake.setMaster(master);
                    whitesnake.setMasterUUID(master.getUniqueID());
                    world.addEntity(whitesnake);
                }
            });

            if (ability) {
                master.setNoGravity(true);
                master.addPotionEffect(new EffectInstance(Effects.LEVITATION, 100, 1));
            } else {
                master.setNoGravity(false);
                master.removePotionEffect(Effects.LEVITATION);
                master.stopFallFlying();
                master.fallDistance = 0;
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
                        CMoonPunchEntity cMoon1 = new CMoonPunchEntity(world, this, master);
                        cMoon1.randomizePositions();
                        cMoon1.shoot(master, master.rotationPitch, master.rotationYaw, 2.15f, 0.2F);
                        world.addEntity(cMoon1);
                        CMoonPunchEntity cMoon2 = new CMoonPunchEntity(world, this, master);
                        cMoon2.randomizePositions();
                        cMoon2.shoot(master, master.rotationPitch, master.rotationYaw, 2.15f, 0.2F);
                        world.addEntity(cMoon2);
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
        if (getMaster() == null) return;
        if (master.isPotionActive(Effects.LEVITATION))
            master.removePotionEffect(Effects.LEVITATION);
        master.setNoGravity(false);
    }
}
