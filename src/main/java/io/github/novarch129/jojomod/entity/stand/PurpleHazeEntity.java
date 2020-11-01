package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.PurpleHazePunchEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class PurpleHazeEntity extends AbstractStandEntity {
    private boolean shouldFollowMaster = true;

    public PurpleHazeEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_PURPLE_HAZE.get();
    }

    public void burstCapsule() {
        if (world.isRemote) return;
        getServer().getWorld(dimension).getEntities()
                .filter(entity -> entity instanceof LivingEntity)
                .filter(entity -> !(entity instanceof AbstractStandEntity))
                .filter(entity -> entity.getDistance(this) < 7.5f)
                .forEach(entity -> ((LivingEntity) entity).addPotionEffect(new EffectInstance(EffectInit.HAZE.get(), 200, 2)));
    }

    public void toggleShouldFollowMaster() {
        if (getMaster() == null) return;
        shouldFollowMaster = !shouldFollowMaster;
        master.sendStatusMessage(new StringTextComponent("Following master: " + shouldFollowMaster), true);
    }

    @Override
    public void playSpawnSound() {
        world.playSound(null, getMaster().getPosition(), getSpawnSound(), SoundCategory.NEUTRAL, 2, 1);
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.PURPLE_HAZE_RUSH.get(), SoundCategory.NEUTRAL, 4.5f, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                PurpleHazePunchEntity purpleHazePunchEntity = new PurpleHazePunchEntity(world, this, master);
                purpleHazePunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.5f, 0.3f);
                world.addEntity(purpleHazePunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> ability = props.getAbility());

            if (shouldFollowMaster)
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
                        PurpleHazePunchEntity purpleHaze1 = new PurpleHazePunchEntity(world, this, master);
                        purpleHaze1.randomizePositions();
                        purpleHaze1.shoot(master, master.rotationPitch, master.rotationYaw, 2.2f, 0.4f);
                        world.addEntity(purpleHaze1);
                        PurpleHazePunchEntity purpleHaze2 = new PurpleHazePunchEntity(world, this, master);
                        purpleHaze2.randomizePositions();
                        purpleHaze2.shoot(master, master.rotationPitch, master.rotationYaw, 2.2f, 0.4f);
                        world.addEntity(purpleHaze2);
                    }
                if (attackTicker >= 120) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
