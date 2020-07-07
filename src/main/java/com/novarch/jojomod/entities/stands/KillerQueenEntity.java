package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.KillerQueenPunchEntity;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class KillerQueenEntity extends AbstractStandEntity {
    SheerHeartAttackEntity sheerHeartAttack = new SheerHeartAttackEntity(world, this);
    private LivingEntity bombEntity = null;
    private int shaCount;

    public KillerQueenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
    }

    public KillerQueenEntity(World world) {
        super(EntityInit.KILLER_QUEEN.get(), world);
        spawnSound = SoundInit.SPAWN_KILLER_QUEEN.get();
    }

    public LivingEntity getBombEntity() {
        return bombEntity;
    }

    public void setBombEntity(LivingEntity bombEntity) {
        this.bombEntity = bombEntity;
    }

    public void detonate() {
        if (getMaster() != null)
            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                if (props.getCooldown() <= 0) {
                    if (bombEntity != null) {
                        if (bombEntity.isAlive()) {
                            props.setCooldown(140);
                            if (bombEntity instanceof MobEntity) {
                                Explosion explosion = new Explosion(bombEntity.world, getMaster(), bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
                                ((MobEntity) bombEntity).spawnExplosionParticle();
                                explosion.doExplosionB(true);
                                world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                                bombEntity.remove();
                            } else if (bombEntity instanceof PlayerEntity) {
                                Stand.getLazyOptional((PlayerEntity) bombEntity).ifPresent(bombProps -> {
                                    if (bombProps.getStandID() != Util.StandID.GER) {
                                        Explosion explosion = new Explosion(bombEntity.world, getMaster(), bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4.0f, true, Explosion.Mode.NONE);
                                        ((PlayerEntity) bombEntity).spawnSweepParticles();
                                        explosion.doExplosionB(true);
                                        bombEntity.attackEntityFrom(DamageSource.FIREWORKS, 4.5f * bombEntity.getArmorCoverPercentage());
                                    } else {
                                        Explosion explosion = new Explosion(getMaster().world, getMaster(), getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ(), 4.0f, true, Explosion.Mode.NONE);
                                        getMaster().spawnSweepParticles();
                                        explosion.doExplosionB(true);
                                        getMaster().setHealth(0);
                                    }
                                });
                            }
                            if (!getMaster().isCreative() && !getMaster().isSpectator())
                                getMaster().getFoodStats().addStats(-2, 0);
                        }
                    }
                }
            });
    }

    public void toggleSheerHeartAttack() {
        if (getMaster() != null)
            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                if (shaCount <= 0) {
                    sheerHeartAttack.setPosition(getPosX(), getPosY(), getPosZ());
                    world.addEntity(sheerHeartAttack);
                    shaCount++;
                    props.setCooldown(300);
                } else {
                    if (!world.isRemote)
                        world.getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity instanceof SheerHeartAttackEntity)
                                .filter(entity -> ((SheerHeartAttackEntity) entity).getMaster().getEntityId() == getMaster().getEntityId())
                                .forEach(entity -> {
                                    entity.remove();
                                    shaCount--;
                                });
                }
            });
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
                KillerQueenPunchEntity killerQueenPunchEntity = new KillerQueenPunchEntity(world, this, getMaster());
                killerQueenPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2, 0.3f);
                world.addEntity(killerQueenPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> props.setAbility(false));

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
                        KillerQueenPunchEntity killerQueen1 = new KillerQueenPunchEntity(world, this, player);
                        killerQueen1.setRandomPositions();
                        killerQueen1.shoot(player, player.rotationPitch, player.rotationYaw, 2, 0.4f);
                        world.addEntity(killerQueen1);
                        KillerQueenPunchEntity killerQueen2 = new KillerQueenPunchEntity(world, this, player);
                        killerQueen2.setRandomPositions();
                        killerQueen2.shoot(player, player.rotationPitch, player.rotationYaw, 2, 0.4f);
                        world.addEntity(killerQueen2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
