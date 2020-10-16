package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;

import java.util.Objects;

public class WeatherReportPunchEntity extends AbstractStandAttackEntity {
    private boolean lightning = world.rand.nextInt(10) == 1;

    public WeatherReportPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.WEATHER_REPORT_PUNCH.get(), worldIn, shooter, player);
    }

    public WeatherReportPunchEntity(EntityType<WeatherReportPunchEntity> weatherReportEntityType, World world) {
        super(weatherReportEntityType, world);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (!(entity instanceof LivingEntity)) return;
        entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 1);
        if (shootingStand.ability)
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.OXYGEN_POISONING.get(), 300, 15));
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3) {
            if (lightning && !shootingStand.attackRush) {
                LightningBoltEntity lightning = new LightningBoltEntity(world, pos.getX(), pos.getY(), pos.getZ(), false);
                world.addEntity(lightning);
                if (!world.isRemote)
                    Objects.requireNonNull(world.getServer()).getWorld(dimension).addLightningBolt(lightning);
            } else {
                world.removeBlock(pos, false);
                if (world.rand.nextBoolean())
                    state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
            }
        }
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.WEATHER_REPORT_PUNCH;
    }
}
