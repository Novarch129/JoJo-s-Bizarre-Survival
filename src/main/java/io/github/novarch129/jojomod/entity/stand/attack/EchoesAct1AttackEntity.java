package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.client.entity.model.EchoesAct1AttackModel;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

public class EchoesAct1AttackEntity extends AbstractStandAttackEntity {
    public EchoesAct1AttackEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EchoesAct1AttackEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.ECHOES_ACT_1_ATTACK.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 0.5f);
        StandEffects.getLazyOptional(entity).ifPresent(props -> {
            switch (props.getSoundEffect()) {
                case 0: {
                    props.setSoundEffect((byte) rand.nextInt(3));
                    break;
                }
                case 1: {
                    props.setSoundEffect((byte) 7);
                    break;
                }
                case 2:
                case 3: {
                    props.setSoundEffect((byte) (props.getSoundEffect() * 2));
                    break;
                }
            }
        });
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 4) {
            world.removeBlock(pos, false);
            state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
        }
    }

    @Override
    protected int getRange() {
        return 4;
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.ECHOES_ACT_1_ATTACK;
    }

    @Override
    public <T extends AbstractStandAttackEntity> EntityModel<T> getEntityModel() {
        return Util.cast(new EchoesAct1AttackModel());
    }
}
