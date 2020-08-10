package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.KillerQueenPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class KillerQueenEntity extends AbstractStandEntity {
    protected int shaCount;
    private SheerHeartAttackEntity sheerHeartAttack;
    private LivingEntity bombEntity;

    public KillerQueenEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_KILLER_QUEEN.get();
    }

    public LivingEntity getBombEntity() {
        return bombEntity;
    }

    public void setBombEntity(LivingEntity bombEntity) {
        this.bombEntity = bombEntity;
    }

    public void detonate() {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            if (props.getCooldown() <= 0) {
                if (bombEntity != null) {
                    if (bombEntity.isAlive()) {
                        props.setCooldown(140);
                        if (bombEntity instanceof MobEntity) {
                            Explosion explosion = new Explosion(bombEntity.world, master, bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4, true, Explosion.Mode.NONE);
                            ((MobEntity) bombEntity).spawnExplosionParticle();
                            explosion.doExplosionB(true);
                            world.playSound(null, master.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1, 1);
                            bombEntity.remove();
                        } else if (bombEntity instanceof PlayerEntity) {
                            Stand.getLazyOptional((PlayerEntity) bombEntity).ifPresent(bombProps -> {
                                if (bombProps.getStandID() != Util.StandID.GER) {
                                    Explosion explosion = new Explosion(bombEntity.world, master, bombEntity.getPosX(), bombEntity.getPosY(), bombEntity.getPosZ(), 4, true, Explosion.Mode.NONE);
                                    ((PlayerEntity) bombEntity).spawnSweepParticles();
                                    explosion.doExplosionB(true);
                                    bombEntity.attackEntityFrom(DamageSource.FIREWORKS, 4.5f * bombEntity.getArmorCoverPercentage());
                                } else {
                                    Explosion explosion = new Explosion(master.world, master, master.getPosX(), master.getPosY(), master.getPosZ(), 4, true, Explosion.Mode.NONE);
                                    master.spawnSweepParticles();
                                    explosion.doExplosionB(true);
                                    master.setHealth(0);
                                }
                            });
                        }
                        if (!master.isCreative() && !master.isSpectator())
                            master.getFoodStats().addStats(-2, 0);
                    }
                }
            }
        });
    }

    public void toggleSheerHeartAttack() {
        if (getMaster() == null || world.isRemote) return;
        if (sheerHeartAttack == null)
            sheerHeartAttack = new SheerHeartAttackEntity(world, this);
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
                            .forEach(Entity::remove);
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
                KillerQueenPunchEntity killerQueenPunchEntity = new KillerQueenPunchEntity(world, this, master);
                killerQueenPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2, 0.3f);
                world.addEntity(killerQueenPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> props.setAbility(false));

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
                        KillerQueenPunchEntity killerQueen1 = new KillerQueenPunchEntity(world, this, master);
                        killerQueen1.randomizePositions();
                        killerQueen1.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.4f);
                        world.addEntity(killerQueen1);
                        KillerQueenPunchEntity killerQueen2 = new KillerQueenPunchEntity(world, this, master);
                        killerQueen2.randomizePositions();
                        killerQueen2.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.4f);
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
