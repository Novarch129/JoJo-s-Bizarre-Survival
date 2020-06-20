package com.novarch.jojomod.entities.stands.weatherReport;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.Util;
import com.novarch.jojomod.util.ValueTextComponent;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityWeatherReport extends EntityStandBase {
    private int oratick = 0;

    private int oratickr = 0;

    private int weatherTick = 0;

    public EntityWeatherReport(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        standID = Util.StandID.weatherReport;
    }

    public EntityWeatherReport(World worldIn) {
        super(EntityInit.WEATHER_REPORT.get(), worldIn);
        spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        standID = Util.StandID.weatherReport;
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
                            .filter(entity -> !(entity instanceof EntityStandBase))
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
                        EntityStandPunch.weatherReport weatherReportPunch = new EntityStandPunch.weatherReport(world, this, player);
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
                        EntityStandPunch.weatherReport weatherReportPunch1 = new EntityStandPunch.weatherReport(world, this, player);
                        weatherReportPunch1.setRandomPositions();
                        weatherReportPunch1.shoot(player, rotationPitch, rotationYaw, 1.8f, 0.2f);
                        world.addEntity(weatherReportPunch1);
                        EntityStandPunch.weatherReport weatherReportPunch2 = new EntityStandPunch.weatherReport(world, this, player);
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
