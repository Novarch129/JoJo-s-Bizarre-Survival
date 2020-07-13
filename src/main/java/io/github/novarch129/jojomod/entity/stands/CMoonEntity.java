package io.github.novarch129.jojomod.entity.stands;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stands.attacks.CMoonPunchEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class CMoonEntity extends AbstractStandEntity {
    public CMoonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_CMOON.get();
    }

    public CMoonEntity(World world) {
        super(EntityInit.CMOON.get(), world);
        spawnSound = SoundInit.SPAWN_CMOON.get();
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
                CMoonPunchEntity cMoonPunchEntity = new CMoonPunchEntity(world, this, getMaster());
                cMoonPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4, 0.15f);
                world.addEntity(cMoonPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();

            Stand.getLazyOptional(player).ifPresent(props -> {
                ability = props.getAbility();
                if ((props.getStandID() == Util.StandID.CMOON && props.getAct() == 1) || (props.getStandID() == Util.StandID.MADE_IN_HEAVEN && props.getAct() == 2)) {
                    player.setNoGravity(false);
                    remove();
                    WhitesnakeEntity whitesnake = new WhitesnakeEntity(world);
                    whitesnake.setLocationAndAngles(getMaster().getPosX() + 0.1, getMaster().getPosY(), getMaster().getPosZ(), getMaster().rotationYaw, getMaster().rotationPitch);
                    whitesnake.setMaster(getMaster());
                    world.addEntity(whitesnake);
                    whitesnake.playSpawnSound();
                }
            });

            if (ability) {
                player.setNoGravity(true);
                player.addPotionEffect(new EffectInstance(Effects.LEVITATION, 100, 1));
            } else {
                player.setNoGravity(false);
                player.removePotionEffect(Effects.LEVITATION);
                player.stopFallFlying();
                player.fallDistance = 0;
            }

            followMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        CMoonPunchEntity cMoon1 = new CMoonPunchEntity(world, this, player);
                        cMoon1.setRandomPositions();
                        cMoon1.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
                        world.addEntity(cMoon1);
                        CMoonPunchEntity cMoon2 = new CMoonPunchEntity(world, this, player);
                        cMoon2.setRandomPositions();
                        cMoon2.shoot(player, player.rotationPitch, player.rotationYaw, 2.15f, 0.2F);
                        world.addEntity(cMoon2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
