package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.client.entity.model.NailBulletModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public class NailBulletEntity extends AbstractStandAttackEntity {
    public float damage = 4;
    public boolean isHoming;
    public boolean isInfinite;

    public NailBulletEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        this(EntityInit.NAIL_BULLET.get(), worldIn, shooter, player);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player, boolean isHoming) {
        super(EntityInit.NAIL_BULLET.get(), worldIn, player.getPosX(), isHoming ? player.getPosY() + 1 : player.getPosY() + 0.4, player.getPosZ());
        setNoGravity(true);
        shootingEntity = shooter;
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
        shootingEntity = shooter;
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
    }

    @Override
    public void tick() {
        super.tick();
        if (isInfinite)
            ticksInAir = 0;
        if (isHoming) {
            LivingEntity target = world.getClosestEntityWithinAABB(LivingEntity.class, new EntityPredicate().setCustomPredicate(entity -> !entity.equals(standMaster) && !(entity instanceof AbstractStandEntity) && entity.isAlive()), null, getPosX(), getPosY(), getPosZ(), new AxisAlignedBB(getPosition().add(20, 20, 20), getPosition().add(-20, -20, -20)));
            if (target == null) return;
            lookAt(EntityAnchorArgument.Type.EYES, target.getPositionVec());
            Vec3d motion = Util.getEntityForwardsMotion(this).mul(getDistance(target) < 6 ? new Vec3d(1.1, 1.1, 1.1) : new Vec3d(0.1, 0.1, 0.1));
            setMotion(motion);
            Vec3d distance = getPositionVec().subtract(target.getPositionVec());
            if ((distance.getX() > -1 && distance.getX() < 1) && (distance.getZ() > -1 && distance.getZ() < 1) && getPosY() > target.getPosY())
                setMotion(getMotion().add(0, -0.1, 0));
            if (ticksInAir > 9800)
                setMotion(getMotion().add(0, -0.3, 0));
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

    @Override
    public <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel() {
        return Util.cast(new NailBulletModel());
    }
}
