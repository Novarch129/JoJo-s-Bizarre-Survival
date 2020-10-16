package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.StandChunkEffects;
import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.entity.stand.EchoesAct1Entity;
import io.github.novarch129.jojomod.entity.stand.EchoesAct2Entity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class EchoesSoundEntity extends AbstractStandAttackEntity {
    public EchoesSoundEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public EchoesSoundEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.ECHOES_SOUND.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 0.5f);
        if (shootingStand instanceof EchoesAct1Entity)
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
        if (shootingStand == null) return;
        if (!(shootingStand instanceof EchoesAct2Entity)) {
            if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 4) {
                world.removeBlock(pos, false);
                state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
            }
        } else {
            Stand.getLazyOptional(standMaster).ifPresent(stand -> {
                if (shootingStand.ability && stand.getAbilityUseCount() < 4) {
                    StandChunkEffects.getLazyOptional(world.getChunkAt(pos)).ifPresent(props -> {
                        if (props.getSoundEffects().containsKey(standMaster.getUniqueID()) && props.getSoundEffects().get(standMaster.getUniqueID()).contains(pos))
                            return;
                        props.addSoundEffect(standMaster, pos);
                        stand.setAbilityUseCount(stand.getAbilityUseCount() + 1);
                        standMaster.sendStatusMessage(new StringTextComponent("Echoes has added an effect to the block at X" + pos.getX() + " Y" + pos.getY() + " Z" + pos.getZ() + "."), true);
                    });
                } else {
                    if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 4) {
                        world.removeBlock(pos, false);
                        state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
                    }
                }
            });
        }
    }

    @Override
    protected int getRange() {
        return 4;
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.ECHOES_SOUND_WAVE;
    }
}
