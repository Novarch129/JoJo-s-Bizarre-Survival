package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class NailBulletEntity extends AbstractStandAttackEntity {
    public float damage = 4;
    public boolean isHoming;
    public boolean isInfinite;
    private PlayerEntity master;

    public NailBulletEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        this(EntityInit.NAIL_BULLET.get(), worldIn, shooter, player);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player, boolean isHoming) {
        super(EntityInit.NAIL_BULLET.get(), worldIn, player.getPosX(), isHoming ? player.getPosY() + 1 : player.getPosY() + 0.4, player.getPosZ());
        setNoGravity(true);
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
        this.isHoming = isHoming;
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player, boolean isHoming, boolean isInfinite) {
        this(worldIn, shooter, player, isHoming);
        this.isInfinite = isInfinite;
    }

    public NailBulletEntity(EntityType<? extends Entity> type, World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(type, worldIn, player.getPosX(), player.getPosY() + 0.4, player.getPosZ());
        setNoGravity(true);
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void tick() {
        super.tick();
        if (shootingStand != null && shootingStand.getMaster() != null)
            master = shootingStand.getMaster();
        if (isInfinite)
            ticksInAir = 0;
        if (isHoming) {
            LivingEntity target = world.getClosestEntityWithinAABB(LivingEntity.class, new EntityPredicate().setCustomPredicate(entity -> !entity.equals(standMaster) && !(entity instanceof AbstractStandEntity && !entity.equals(master.getRidingEntity())) && !(entity instanceof AbstractHorseEntity) && entity.isAlive()), null, getPosX(), getPosY(), getPosZ(), new AxisAlignedBB(getPosition().add(20, 20, 20), getPosition().add(-20, -20, -20)));
            if (target == null) return;
            double x = (target.getBoundingBox().minX + (target.getBoundingBox().maxX - target.getBoundingBox().minX) / 2) - getPosX();
            double y = (target.getBoundingBox().minY + (target.getBoundingBox().maxY - target.getBoundingBox().minY) / 2) - getPosY();
            double z = (target.getBoundingBox().minZ + (target.getBoundingBox().maxZ - target.getBoundingBox().minZ) / 2) - getPosZ();
            Vec3d vec3d = (new Vec3d(x, y, z)).normalize().add(rand.nextGaussian() * (double) 0.0075f * (double) 0, rand.nextGaussian() * (double) 0.0075f * 0, rand.nextGaussian() * (double) 0.0075F * 0).scale(1);
            setMotion(vec3d.scale(0.6));
            float f = MathHelper.sqrt(horizontalMag(vec3d));
            rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180 / (float) Math.PI));
            rotationPitch = (float) (MathHelper.atan2(vec3d.y, f) * (double) (180 / (float) Math.PI));
            prevRotationYaw = rotationYaw;
            prevRotationPitch = rotationPitch;
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), damage);
        if (damage > 5 && entity instanceof LivingEntity)
            ((LivingEntity) entity).knockBack(this, damage / 7, getPosX() - entity.getPosX(), getPosZ() - entity.getPosZ());
        if (isInfinite && !entity.equals(standMaster))
            StandEffects.getLazyOptional(entity).ifPresent(props -> props.setRotating(!props.isRotating()));
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (isHoming) {
            if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3) {
                world.removeBlock(pos, false);
                state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
            }
        }
        if (state.isSolid())
            remove();
    }

    @Override
    protected boolean shouldMatchMaster() {
        return false;
    }

    @Override
    protected boolean shouldBeDestroyedByBlocks(BlockState state) {
        return !isHoming && !state.isSolid();
    }

    @Override
    protected int getRange() {
        if (isHoming)
            return 10000;
        else
            return (int) (4 + damage / 7);
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.NAIL_BULLET;
    }
}
