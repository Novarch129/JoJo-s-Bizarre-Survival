package com.novarch.jojomod.entities.stands.weatherReport;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EffectInit;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class EntityWeatherReport extends EntityStandBase {
    private int oratick = 0;

    private int oratickr = 0;

    private int weatherTick = 0;

    @Override
    public boolean canDespawn(double p_213397_1_) {
        return false;
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public void writeAdditional(CompoundNBT p_213281_1_) {
        super.writeAdditional(p_213281_1_);
    }

    @Override
    public void readAdditional(CompoundNBT p_70037_1_) {
        super.readAdditional(p_70037_1_);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return super.createSpawnPacket();
    }

    public EntityWeatherReport(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        this.standID = JojoLibs.StandID.weatherReport;
    }

    public EntityWeatherReport(World worldIn) {
        super(EntityInit.WEATHER_REPORT.get(), worldIn);
        this.spawnSound = SoundInit.SPAWN_WEATHER_REPORT.get();
        this.standID = JojoLibs.StandID.weatherReport;
    }

    @Override
    public void tick() {
        super.tick();
        this.fallDistance = 0.0f;
        if (this.world.rand.nextInt(35) == 1)
            this.spawnExplosionParticle();

        if (weatherTick > 2)
            weatherTick = 0;

        if (getMaster() != null) {
            followMaster();
            PlayerEntity player = getMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);
            JojoProvider.getLazyOptional(player).ifPresent(props -> this.ability = props.getAbility());

            if (KeyHandler.keys[2].isPressed())
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

            if (this.ability) {
                if (!world.isRemote)
                    world.getServer().getWorld(this.dimension).getEntities().filter(entity -> entity != player)
                            .filter(entity -> entity instanceof LivingEntity)
                            .filter(entity -> !(entity instanceof EntityStandBase))
                            .filter(entity -> entity.world.getBlockState(new BlockPos(entity.getPosX(), entity.getPosY() + 1, entity.getPosZ())).getMaterial() != Material.WATER)
                            .forEach(entity -> {
                                if (entity.getDistance(player) <= 10) {
                                    ((PlayerEntity) entity).addPotionEffect(new EffectInstance(EffectInit.OXYGEN_POISONING.get(), 150, 5));
                                    if (!player.isCreative()) {
                                        player.getFoodStats().addStats(0, -0.1f);
                                        player.getFoodStats().addExhaustion(0.05f);
                                    }
                                }
                            });
            }

            if (!player.isSprinting()) {
                if (attackSwing(player)) {
                    this.oratick++;
                    if (oratick == 1) {
                        EntityStandPunch.weatherReport weatherReportPunch = new EntityStandPunch.weatherReport(this.world, this, player);
                        weatherReportPunch.shoot(player, this.rotationPitch, this.rotationYaw, 0, 2.5f, 0.4f);
                        this.world.addEntity(weatherReportPunch);
                    }
                }
            } else if (player.isSprinting()) {
                if (attackSwing(player))
                    if (player.getFoodStats().getFoodLevel() > 6) {
                        this.oratick++;
                        if (this.oratick == 1) {
                            if (!this.world.isRemote)
                                this.orarush = true;
                        }
                    }
            }
            if (player.swingProgressInt == 0)
                this.oratick = 0;
            if (this.orarush) {
                player.setSprinting(false);
                this.oratickr++;
                if (this.oratickr >= 10)
                    if (!this.world.isRemote) {
                        player.setSprinting(false);
                        EntityStandPunch.weatherReport weatherReportPunch1 = new EntityStandPunch.weatherReport(this.world, this, player);
                        weatherReportPunch1.setRandomPositions();
                        weatherReportPunch1.shoot(player, this.rotationPitch, this.rotationYaw, 0.0F, 1.8f, 0.2f);
                        this.world.addEntity(weatherReportPunch1);
                        EntityStandPunch.weatherReport weatherReportPunch2 = new EntityStandPunch.weatherReport(this.world, this, player);
                        weatherReportPunch2.setRandomPositions();
                        weatherReportPunch2.shoot(player, this.rotationPitch, this.rotationYaw, 0.0F, 1.8f, 0.2f);
                        this.world.addEntity(weatherReportPunch2);
                    }
                if (this.oratickr >= 110) {
                    this.orarush = false;
                    this.oratickr = 0;
                }
            }
        }
    }
}
