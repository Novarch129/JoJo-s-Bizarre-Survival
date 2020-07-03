package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class WeatherReportEntity extends AbstractStandEntity {
    private int oratick = 0;

    private int oratickr = 0;

    private int weatherTick = 0;

    public WeatherReportEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        standID = Util.StandID.WEATHER_REPORT;
    }

    public WeatherReportEntity(World worldIn) {
        super(EntityInit.WEATHER_REPORT.get(), worldIn);
        spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        standID = Util.StandID.WEATHER_REPORT;
    }

    public void changeWeather() {
        if (weatherTick == 2)
            weatherTick = 0;
        else
            weatherTick++;

        switch (weatherTick) {
            case 0: {
                world.getWorldInfo().setRaining(false);
                world.setRainStrength(-1);
                world.getWorldInfo().setThundering(false);
                world.setThunderStrength(-1);
                break;
            }
            case 1: {
                world.getWorldInfo().setRaining(true);
                world.setRainStrength(5);
                world.getWorldInfo().setThundering(false);
                world.setThunderStrength(-1);
                break;
            }
            case 2: {
                world.getWorldInfo().setRaining(true);
                world.setRainStrength(5);
                world.getWorldInfo().setThundering(true);
                world.setThunderStrength(5);
                break;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (world.rand.nextInt(35) == 1)
            spawnExplosionParticle();

        if (getMaster() != null) {
            followMaster();
            PlayerEntity player = getMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);
            Stand.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());

            if (ability) {
                if (!world.isRemote)
                    world.getServer().getWorld(dimension).getEntities().filter(entity -> entity != player)
                            .filter(entity -> entity instanceof LivingEntity)
                            .filter(entity -> !(entity instanceof AbstractStandEntity))
                            .filter(entity -> entity.world.getBlockState(new BlockPos(entity.getPosX(), entity.getPosY() + 1, entity.getPosZ())).getMaterial() != Material.WATER)
                            .forEach(entity -> {
                                if (entity.getDistance(player) <= 10) {
                                    ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.OXYGEN_POISONING.get(), 150, 5));
                                    if (!player.isCreative()) {
                                        player.getFoodStats().addStats(0, -0.1f);
                                        player.getFoodStats().addExhaustion(0.05f);
                                    }
                                }
                            });
            }

            if (!player.isSprinting()) {
                if (attackSwing(player)) {
                    oratick++;
                    if (oratick == 1) {
                        AbstractStandPunchEntity.WeatherReport weatherReportPunch = new AbstractStandPunchEntity.WeatherReport(world, this, player);
                        weatherReportPunch.setLightning(world.rand.nextInt(10) == 1);
                        weatherReportPunch.shoot(player, rotationPitch, rotationYaw, 2.5f, 0.4f);
                        world.addEntity(weatherReportPunch);
                    }
                }
            } else if (player.isSprinting()) {
                if (attackSwing(player))
                    if (player.getFoodStats().getFoodLevel() > 6) {
                        oratick++;
                        if (oratick == 1)
                            if (!world.isRemote)
                                orarush = true;
                    }
            }
            if (player.swingProgressInt == 0)
                oratick = 0;
            if (orarush) {
                player.setSprinting(false);
                oratickr++;
                if (oratickr >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        AbstractStandPunchEntity.WeatherReport weatherReportPunch1 = new AbstractStandPunchEntity.WeatherReport(world, this, player);
                        weatherReportPunch1.setRandomPositions();
                        weatherReportPunch1.shoot(player, rotationPitch, rotationYaw, 1.8f, 0.2f);
                        world.addEntity(weatherReportPunch1);
                        AbstractStandPunchEntity.WeatherReport weatherReportPunch2 = new AbstractStandPunchEntity.WeatherReport(world, this, player);
                        weatherReportPunch2.setRandomPositions();
                        weatherReportPunch2.shoot(player, rotationPitch, rotationYaw, 1.8f, 0.2f);
                        world.addEntity(weatherReportPunch2);
                    }
                if (oratickr >= 110) {
                    orarush = false;
                    oratickr = 0;
                }
            }
        }
    }
}
