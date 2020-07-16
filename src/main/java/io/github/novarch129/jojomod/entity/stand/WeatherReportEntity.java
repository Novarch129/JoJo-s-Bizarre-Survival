package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.WeatherReportPunchEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class WeatherReportEntity extends AbstractStandEntity {
    private int weatherTick;

    public WeatherReportEntity(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_WEATHER_REPORT.get();
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
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special)
                attackRush = true;
            else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                WeatherReportPunchEntity weatherReportPunch = new WeatherReportPunchEntity(world, this, getMaster());
                weatherReportPunch.shoot(getMaster(), rotationPitch, rotationYaw, 2.5f, 0.4f);
                world.addEntity(weatherReportPunch);
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
                            .filter(entity -> !entity.areEyesInFluid(FluidTags.WATER))
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
            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        WeatherReportPunchEntity weatherReportPunch1 = new WeatherReportPunchEntity(world, this, player);
                        weatherReportPunch1.setRandomPositions();
                        weatherReportPunch1.shoot(player, rotationPitch, rotationYaw, 1.8f, 0.2f);
                        world.addEntity(weatherReportPunch1);
                        WeatherReportPunchEntity weatherReportPunch2 = new WeatherReportPunchEntity(world, this, player);
                        weatherReportPunch2.setRandomPositions();
                        weatherReportPunch2.shoot(player, rotationPitch, rotationYaw, 1.8f, 0.2f);
                        world.addEntity(weatherReportPunch2);
                    }
                if (attackTicker >= 110) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
