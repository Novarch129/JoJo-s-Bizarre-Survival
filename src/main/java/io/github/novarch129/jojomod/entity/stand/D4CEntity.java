package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.D4CPunchEntity;
import io.github.novarch129.jojomod.event.EventD4CTeleportProcessor;
import io.github.novarch129.jojomod.init.DimensionInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.DimensionHopTeleporter;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
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

    public void teleport() {
        if (getMaster() == null) return;
        if ((master.getHeldItemMainhand().getItem() instanceof ShieldItem || master.getHeldItemOffhand().getItem() instanceof ShieldItem) || (master.getHeldItemMainhand().getItem() instanceof BannerItem || master.getHeldItemOffhand().getItem() instanceof BannerItem))
            Stand.getLazyOptional(master).ifPresent(props -> {
                if (props.getCooldown() == 0) {
                    Vec3d position = master.getLookVec().mul(7, 1, 7).add(master.getPositionVec());
                    master.setPositionAndUpdate(position.getX(), position.getY(), position.getZ());
                    props.setCooldown(80);
                }
            });
    }

    public void grabEntity() {
        if (getMaster() == null) return;
        if ((master.getHeldItemMainhand().getItem() instanceof ShieldItem || master.getHeldItemOffhand().getItem() instanceof ShieldItem) || (master.getHeldItemMainhand().getItem() instanceof BannerItem || master.getHeldItemOffhand().getItem() instanceof BannerItem))
            Stand.getLazyOptional(master).ifPresent(props -> {
                if (props.getCooldown() == 0) {
                    world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                    D4CPunchEntity d4CPunchEntity = new D4CPunchEntity(world, this, master);
                    d4CPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 4, 0.0001f);
                    d4CPunchEntity.setGrab(true);
                    world.addEntity(d4CPunchEntity);
                    props.setCooldown(200);
                }
            });
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

    public void transportEntity(Entity entity) {
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
        remove();
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
                master.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));

                if ((master.world.getBlockState(master.getPosition().add(0, 0, -1)).isSolid() && master.world.getBlockState(master.getPosition().add(0, 0, 1)).isSolid()) || (master.world.getBlockState(master.getPosition().add(-1, 0, 0)).isSolid() && master.world.getBlockState(master.getPosition().add(1, 0, 0)).isSolid())) {
                    if (master.isCrouching() || master.isAirBorne) {
                        if (!world.isRemote && ability && props.getCooldown() == 0) {
                            changePlayerDimension(master);
                            world.getServer().getWorld(dimension).getEntities()
                                    .filter(entity -> entity instanceof LivingEntity)
                                    .filter(entity -> !(entity instanceof PlayerEntity))
                                    .filter(entity -> !(entity instanceof AbstractStandEntity))
                                    .filter(entity -> entity.getDistance(master) < 3 || entity.getDistance(this) < 3)
                                    .forEach(this::transportEntity);

                            world.getPlayers()
                                    .stream()
                                    .filter(playerEntity -> Stand.getCapabilityFromPlayer(playerEntity).getStandID() != Util.StandID.GER)
                                    .filter(playerEntity -> master.getDistance(master) < 3 || playerEntity.getDistance(this) < 3)
                                    .forEach(this::changePlayerDimension);
                            if (!master.isCreative()) master.getFoodStats().addStats(-3, 0);
                            props.setStandOn(false);
                            props.setCooldown(200);
                        }
                    }
                }
            });
            followMaster();
            setRotationYawHead(master.rotationYawHead);
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
                        dirtyDeedsDoneDirtCheap1.randomizePositions();
                        dirtyDeedsDoneDirtCheap1.shoot(master, master.rotationPitch, master.rotationYaw, 2, 0.35f);
                        world.addEntity(dirtyDeedsDoneDirtCheap1);
                        D4CPunchEntity dirtyDeedsDoneDirtCheap2 = new D4CPunchEntity(world, this, master);
                        dirtyDeedsDoneDirtCheap2.randomizePositions();
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
