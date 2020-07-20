package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.entity.stand.attack.EmeraldSplashEntity;
import io.github.novarch129.jojomod.entity.stand.attack.HierophantGreenTailEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class HierophantGreenEntity extends AbstractStandEntity {
    public HierophantGreenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_THE_HAND.get();
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
                HierophantGreenTailEntity hierophantGreenTailEntity = new HierophantGreenTailEntity(world, this, getMaster());
                hierophantGreenTailEntity.shoot(getMaster(), rotationPitch, rotationYaw, 3, 0.15f);
                world.addEntity(hierophantGreenTailEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();

            followMaster();
            setRotationYawHead(player.getRotationYawHead());
            setRotation(player.rotationYaw, player.rotationPitch);

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        EmeraldSplashEntity emeraldSplashEntity = new EmeraldSplashEntity(world, this, player);
                        emeraldSplashEntity.setRandomPositions();
                        emeraldSplashEntity.shoot(player, player.rotationPitch, player.rotationYaw, 2, 0.25f);
                        world.addEntity(emeraldSplashEntity);
                        EmeraldSplashEntity emeraldSplashEntity1 = new EmeraldSplashEntity(world, this, player);
                        emeraldSplashEntity1.setRandomPositions();
                        emeraldSplashEntity1.shoot(player, player.rotationPitch, player.rotationYaw, 2, 0.25f);
                        world.addEntity(emeraldSplashEntity1);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
