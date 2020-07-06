package com.novarch.jojomod.entities.stands.attacks;

import com.novarch.jojomod.entities.stands.AbstractStandEntity;
import com.novarch.jojomod.init.EntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class MagiciansRedFlameEntity extends AbstractStandAttackEntity {
    private boolean explosive;

    public MagiciansRedFlameEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public MagiciansRedFlameEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.MAGICIANS_RED_FLAMES.get(), worldIn, shooter, player);
    }

    public boolean isExplosive() {
        return explosive;
    }

    public void setExplosive(boolean explosive) {
        this.explosive = explosive;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        super.writeSpawnData(buffer);
        buffer.writeBoolean(isExplosive());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        super.readSpawnData(additionalData);
        setExplosive(additionalData.readBoolean());
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.setFire(shootingStand.attackRush ? 2 : 5);
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), shootingStand.attackRush ? 0.5f : 0.75f);
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (explosive)
            world.createExplosion(this, DamageSource.IN_FIRE, pos.getX(), pos.getY(), pos.getZ(), 3.0f, true, Explosion.Mode.DESTROY);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3)
            world.setBlockState(pos, Blocks.FIRE.getDefaultState());
    }
}
