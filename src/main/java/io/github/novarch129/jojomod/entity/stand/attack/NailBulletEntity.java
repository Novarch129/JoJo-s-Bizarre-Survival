package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.client.entity.model.NailBulletModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class NailBulletEntity extends AbstractStandAttackEntity {
    public float damage = 4;

    public NailBulletEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public NailBulletEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.NAIL_BULLET.get(), worldIn, shooter, player);
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
    protected int getRange() {
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
