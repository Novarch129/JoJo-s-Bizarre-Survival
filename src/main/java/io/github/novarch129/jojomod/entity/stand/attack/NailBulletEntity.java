package io.github.novarch129.jojomod.entity.stand.attack;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class NailBulletEntity extends AbstractStandAttackEntity {
    public float damage = 4;
    public boolean isHoming;

    public NailBulletEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        this(EntityInit.NAIL_BULLET.get(), worldIn, shooter, player);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player, boolean isHoming) {
        super(EntityInit.NAIL_BULLET.get(), worldIn, player.getPosX(), isHoming ? player.getPosY() + 0.8 : player.getPosY() + 0.4, player.getPosZ());
        shootingEntity = shooter;
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
        this.isHoming = isHoming;
    }

    public NailBulletEntity(EntityType<? extends Entity> type, World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(type, worldIn, player.getPosX(), player.getPosY() + 0.4, player.getPosZ());
        shootingEntity = shooter;
        shootingStand = shooter;
        standMaster = player;
        movePunchInFrontOfStand(shooter);
    }

    @Override
    public void tick() {
        super.tick();
        if (isHoming) {
            LivingEntity target = world.getClosestEntityWithinAABB(LivingEntity.class, new EntityPredicate().setCustomPredicate(entity -> !entity.equals(standMaster) && !(entity instanceof AbstractStandEntity)), null, getPosX(), getPosY(), getPosZ(), new AxisAlignedBB(getPosition().add(20, 20, 20), getPosition().add(-20, -20, -20)));
            if (target == null) return;
            lookAt(EntityAnchorArgument.Type.EYES, target.getPositionVec());
            Vec3d motion = Util.getEntityForwardsMotion(this).mul(getDistance(target) < 6 ? new Vec3d(1.1, 1.1, 1.1) : new Vec3d(0.1, 0.1, 0.1));
            setMotion(motion);
            Vec3d distance = getPositionVec().subtract(target.getPositionVec());
            if ((distance.getX() > -2 && distance.getX() < 2) && (distance.getZ() > -2 && distance.getZ() < 2) && getPosY() > target.getPosY())
                setMotion(getMotion().add(0, -0.3, 0));
        }
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), damage);
        if (damage > 5 && entity instanceof LivingEntity)
            ((LivingEntity) entity).knockBack(this, damage / 7, getPosX() - entity.getPosX(), getPosZ() - entity.getPosZ());
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
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
