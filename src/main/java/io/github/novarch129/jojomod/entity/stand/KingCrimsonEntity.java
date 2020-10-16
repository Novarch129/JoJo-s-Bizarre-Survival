package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.KingCrimsonAfterimageEntity;
import io.github.novarch129.jojomod.entity.stand.attack.KingCrimsonPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.IChargeable;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("ConstantConditions")
public class KingCrimsonEntity extends AbstractStandEntity implements IChargeable {
    private int punchChargeTicks, prevPunchChargeTicks;
    private KingCrimsonAfterimageEntity afterimage;

    public KingCrimsonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_KING_CRIMSON.get();
    }

    @Override
    public void attack(boolean special) {
    }

    @Override
    public void chargeAttack(boolean isCharging) {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            props.setCharging(isCharging);
            if (isCharging && punchChargeTicks <= 200) {
                setChargeTicks(punchChargeTicks + 1);
                props.setStandDamage(2.95f + punchChargeTicks / 20f);
            } else if (!isCharging)
                setChargeTicks(0);
        });
    }

    @Override
    public int getChargeTicks() {
        return punchChargeTicks;
    }

    @Override
    public void setChargeTicks(int chargeTicks) {
        this.prevPunchChargeTicks = this.punchChargeTicks;
        this.punchChargeTicks = chargeTicks;
    }

    @Override
    public int getPrevChargeTicks() {
        return prevPunchChargeTicks;
    }

    public void epitaph() {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(stand -> {
            if (stand.getCooldown() == 0 && stand.getInvulnerableTicks() == 0) {
                stand.setInvulnerableTicks(100);
                master.addPotionEffect(new EffectInstance(Effects.GLOWING, 100, 0));
            }
        });
    }

    public void teleport(double multiplier) {
        if (world.isRemote || master == null) return;
        Stand.getLazyOptional(master).ifPresent(stand -> {
            if (stand.getCooldown() == 0 && !stand.getAbilityActive()) {
                Vec3d position = master.getLookVec().mul(7 * multiplier, 1, 7 * multiplier).add(master.getPositionVec());
                for (double i = position.getY() - 0.5; world.getBlockState(new BlockPos(position.getX(), i, position.getZ())).isSolid(); i++)
                    position = position.add(0, 0.5, 0);
                if (world.getBlockState(new BlockPos(position)).isSolid())
                    return;
                BlockPos.getAllInBox(getPosition().add(10, 10, 10), getPosition().add(-10, -10, -10))
                        .filter(pos -> world.getBlockState(pos).getBlock() instanceof CropsBlock || world.getBlockState(pos).getBlock() instanceof StemBlock)
                        .forEach(pos -> {
                            BlockState state = world.getBlockState(pos);
                            if (state.getBlock() instanceof CropsBlock) {
                                if (state.get(((CropsBlock) state.getBlock()).getAgeProperty()) < ((CropsBlock) state.getBlock()).getMaxAge())
                                    world.setBlockState(pos, ((CropsBlock) state.getBlock()).withAge(state.get(((CropsBlock) state.getBlock()).getAgeProperty()) + 1), 2);
                            } else if (state.get(StemBlock.AGE) < 7)
                                world.setBlockState(pos, state.with(StemBlock.AGE, state.get(StemBlock.AGE) + 1), 2);
                        });
                master.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());
                world.setGameTime(world.getGameTime() + 200);
                world.setDayTime(world.getDayTime() + 200);
                stand.setCooldown(200);
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() == null) return;
        followMaster();
        master.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
        Stand.getLazyOptional(master).ifPresent(stand -> {
            if (!stand.getAbility() || stand.getTimeLeft() <= 800 || stand.getCooldown() > 0 || stand.getInvulnerableTicks() > 0) {
                setRotationYawHead(master.rotationYawHead);
                setRotation(master.rotationYaw, master.rotationPitch);
            }
            ability = stand.getAbility();
            stand.setAbilityActive(stand.getStandOn() && stand.getCooldown() <= 0 && stand.getTimeLeft() > 801 && stand.getAbility());

            if (!stand.getAbility() || (stand.getTimeLeft() <= 0 && stand.getCooldown() > 0)) {
                if (punchChargeTicks == 0 && punchChargeTicks != prevPunchChargeTicks) {
                    world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                    KingCrimsonPunchEntity kingCrimsonPunchEntity = new KingCrimsonPunchEntity(world, this, master);
                    kingCrimsonPunchEntity.damage = 2.95f + prevPunchChargeTicks / 20f;
                    if (kingCrimsonPunchEntity.damage >= 6) {
                        for (int i = 0; i < kingCrimsonPunchEntity.damage / 6; i++) {
                            world.playSound(null, getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 4, (1 + (rand.nextFloat() - rand.nextFloat()) * 0.2f) * 0.7f);
                            if (world.isRemote) {
                                for (int k = 0; k < 20; ++k) {
                                    double d0 = rand.nextGaussian() * 0.02;
                                    double d1 = rand.nextGaussian() * 0.02;
                                    double d2 = rand.nextGaussian() * 0.02;
                                    Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                                    world.addParticle(ParticleTypes.POOF,
                                            position.getX() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            position.getY() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            position.getZ() + ((rand.nextBoolean() ? rand.nextDouble() : -rand.nextDouble()) / 2),
                                            d0, d1, d2);
                                }
                            } else
                                world.setEntityState(this, (byte) 20);
                        }
                    }
                    kingCrimsonPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 3 + kingCrimsonPunchEntity.damage / 6.5f, 0.0001f);
                    if (!world.isRemote)
                        world.addEntity(kingCrimsonPunchEntity);
                }
            }

            if (!stand.getAbility()) {
                if (!master.isCreative() && !master.isSpectator())
                    master.setGameType(GameType.SURVIVAL);
                master.setInvulnerable(false);
                master.setInvisible(true);
            }

            if (stand.getCooldown() == 0 && stand.getAbility()) {
                if (stand.getTimeLeft() > 800) {
                    if (!world.isRemote && getServer().getWorld(dimension).getEntities().noneMatch(entity -> entity instanceof KingCrimsonAfterimageEntity && ((KingCrimsonAfterimageEntity) entity).getMaster().equals(master))) {
                        afterimage = new KingCrimsonAfterimageEntity(world, master);
                        world.addEntity(afterimage);
                        getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity instanceof AbstractArrowEntity)
                                .forEach(Entity::remove);
                    }
                    if (getAfterimage() != null) {
                        setRotationYawHead(afterimage.rotationYawHead);
                        setRotation(afterimage.rotationYaw, afterimage.rotationPitch);
                    }
                    if (!world.isRemote)
                        getServer().getWorld(dimension).getEntities()
                                .filter(entity -> entity instanceof MobEntity)
                                .filter(entity -> ((((MobEntity) entity).getAttackTarget() != null && ((MobEntity) entity).getAttackTarget().equals(master)) || (((MobEntity) entity).getRevengeTarget() != null && ((MobEntity) entity).getRevengeTarget().equals(master))))
                                .forEach(entity -> {
                                    ((MobEntity) entity).setAttackTarget(afterimage);
                                    ((MobEntity) entity).setRevengeTarget(afterimage);
                                });
                    getMaster().setInvulnerable(true);
                    if (!master.isCreative() && !master.isSpectator())
                        master.setGameType(GameType.ADVENTURE);
                    stand.setTimeLeft(stand.getTimeLeft() - 1);
                }
                if (stand.getTimeLeft() == 801) {
                    if (!master.isCreative() && !master.isSpectator())
                        master.setGameType(GameType.SURVIVAL);
                    master.setInvulnerable(false);
                    master.setInvisible(false);
                    stand.setAbilityActive(false);
                    afterimage = null;
                    stand.setCooldown(200);
                }
            }

            if (stand.getInvulnerableTicks() > 0) {
                if (!master.isCreative() && !master.isSpectator())
                    master.setGameType(GameType.SURVIVAL);
                master.setInvulnerable(false);
                master.setInvisible(false);
                stand.setAbilityActive(false);
            }
        });
    }

    @Override
    protected void followMaster() {
        if (this.master == null) return;
        Stand.getLazyOptional(this.master).ifPresent(stand -> {
            Entity master = (stand.getStandOn() && stand.getAbility() && stand.getTimeLeft() > 800 && stand.getCooldown() == 0 && stand.getInvulnerableTicks() == 0) ? getAfterimage() : this.master;
            if (master == null) return;
            double distance = master.getDistance(this);
            double minimum = 0.5; //The Stand's minimum and maximum distance from it's master.
            double maximum = 3;

            if (distance < minimum)
                moveStand();
            else if (distance > minimum)
                if (distance < maximum && distance > minimum + 0.3)
                    moveStand();
                else if (distance > maximum && !world.isRemote)
                    setPosition(master.getPosX(), master.getPosY(), master.getPosZ());
        });
    }

    @Override
    protected void moveStand() {
        if (this.master == null) return;
        Stand.getLazyOptional(this.master).ifPresent(stand -> {
            Entity master = (stand.getStandOn() && stand.getAbility() && stand.getTimeLeft() > 800 && stand.getCooldown() == 0 && stand.getInvulnerableTicks() == 0) ? getAfterimage() : this.master;
            if (master == null) return;
            double distanceFromMaster = master.getDistance(this);
            Vec3d distance = getPositionVec().subtract(master.getPositionVec());
            float speed = distanceFromMaster < 0.5 ? -0.1f : (float) distanceFromMaster / 45; //The speed at which the Stand should move towards it's master
            if (distance.getX() > 0)
                moveForward -= speed;
            else if (distance.getX() < 0)
                moveForward += speed;
            if (distance.getY() > 0)
                moveVertical -= speed;
            else if (distance.getY() < 0)
                moveVertical += speed;
            if (distance.getZ() > 0)
                moveStrafing -= speed;
            else if (distance.getZ() < 0)
                moveStrafing += speed;
        });
    }

    @Nullable
    private KingCrimsonAfterimageEntity getAfterimage() {
        return afterimage;
    }
}