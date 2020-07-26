package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.D4CPunchEntity;
import io.github.novarch129.jojomod.event.EventD4CTeleportProcessor;
import io.github.novarch129.jojomod.init.DimensionInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.DimensionHopTeleporter;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.server.ServerWorld;

@SuppressWarnings({"ConstantConditions", "deprecation"})
public class D4CEntity extends AbstractStandEntity {
    public D4CEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_D4C.get();
    }

    /**
     * Used to shorten the code of the method below, removing the need to make a <code>new</code> {@link DimensionHopTeleporter} every time {@link Entity}# changeDimension is called.
     *
     * @param entity The {@link Entity} that will be transported.
     * @param type   The {@link DimensionType} the entity will be transported to.
     */
    private void changeDimension(DimensionType type, Entity entity) {
        if (!entity.world.isRemote)
            entity.changeDimension(type, new DimensionHopTeleporter((ServerWorld) entity.getEntityWorld()));
    }

    private void transportEntity(Entity entity) {
        if (entity.world.isRemote) return;
        if (entity.world.getDimension().getType() == DimensionType.OVERWORLD)
            changeDimension(DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE), entity);
        else if (entity.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE))
            changeDimension(DimensionType.OVERWORLD, entity);
        else if (entity.world.getDimension().getType() == DimensionType.THE_NETHER)
            changeDimension(DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_NETHER), entity);
        else if (entity.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_NETHER))
            changeDimension(DimensionType.THE_NETHER, entity);
        else if (entity.world.getDimension().getType() == DimensionType.THE_END)
            changeDimension(DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_END), entity);
        else if (entity.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_END))
            changeDimension(DimensionType.THE_END, entity);
    }

    private void changePlayerDimension(PlayerEntity player) {
        if (player.world.isRemote) return;
        if (player.world.getDimension().getType() == DimensionType.OVERWORLD)
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE));
        else if (player.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE))
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.OVERWORLD);
        else if (player.world.getDimension().getType() == DimensionType.THE_NETHER)
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_NETHER));
        else if (player.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_NETHER))
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.THE_NETHER);
        else if (player.world.getDimension().getType() == DimensionType.THE_END)
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_END));
        else if (player.world.getDimension().getType() == DimensionType.byName(DimensionInit.D4C_DIMENSION_TYPE_END))
            EventD4CTeleportProcessor.d4cPassengers.put(player, DimensionType.THE_END);
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
                D4CPunchEntity d4CPunchEntity = new D4CPunchEntity(world, this, master);
                d4CPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2, 0.3f);
                world.addEntity(d4CPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                if (props.getCooldown() > 0 && ability)
                    props.subtractCooldown(1);
                master.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));

                if ((master.world.getBlockState(master.getPosition().add(0, 0, -1)).getMaterial() != Material.AIR && master.world.getBlockState(master.getPosition().add(0, 0, 1)).getMaterial() != Material.AIR) || (master.world.getBlockState(master.getPosition().add(-1, 0, 0)).getMaterial() != Material.AIR && master.world.getBlockState(master.getPosition().add(1, 0, 0)).getMaterial() != Material.AIR)) {
                    if (master.isCrouching() || master.isAirBorne) {
                        if (ability && props.getCooldown() <= 0) {
                            changePlayerDimension(master);
                            if (!world.isRemote) {
                                world.getServer().getWorld(dimension).getEntities()
                                        .filter(entity -> entity instanceof LivingEntity)
                                        .filter(entity -> !(entity instanceof PlayerEntity))
                                        .filter(entity -> !(entity instanceof AbstractStandEntity))
                                        .filter(entity -> entity.getDistance(master) < 3.0f || entity.getDistance(this) < 3.0f)
                                        .forEach(this::transportEntity);

                                world.getPlayers()
                                        .stream()
                                        .filter(playerEntity -> Stand.getCapabilityFromPlayer(playerEntity).getStandID() != Util.StandID.GER)
                                        .filter(playerEntity -> master.getDistance(master) < 3.0f || playerEntity.getDistance(this) < 3.0f)
                                        .forEach(this::changePlayerDimension);
                            }
                            if (!master.isCreative()) master.getFoodStats().addStats(-3, 0.0f);
                            props.setStandOn(false);
                            props.setCooldown(200);
                            remove();
                        }
                    }
                }
            });
            followMaster();
            setRotationYawHead(master.rotationYaw);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        D4CPunchEntity dirtyDeedsDoneDirtCheap1 = new D4CPunchEntity(world, this, master);
                        dirtyDeedsDoneDirtCheap1.setRandomPositions();
                        dirtyDeedsDoneDirtCheap1.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.35f);
                        world.addEntity(dirtyDeedsDoneDirtCheap1);
                        D4CPunchEntity dirtyDeedsDoneDirtCheap2 = new D4CPunchEntity(world, this, master);
                        dirtyDeedsDoneDirtCheap2.setRandomPositions();
                        dirtyDeedsDoneDirtCheap2.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.35f);
                        world.addEntity(dirtyDeedsDoneDirtCheap2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
