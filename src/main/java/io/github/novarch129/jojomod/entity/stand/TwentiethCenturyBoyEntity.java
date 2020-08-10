package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.stand.attack.TwentiethCenturyBoyPunchEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class TwentiethCenturyBoyEntity extends AbstractStandEntity {
    public TwentiethCenturyBoyEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundEvents.ENTITY_GENERIC_EXPLODE;
    }

    @Override
    public void playSpawnSound() {
        world.playSound(null, master.getPosition(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1, 1);
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
                TwentiethCenturyBoyPunchEntity twentiethCenturyBoyPunchEntity = new TwentiethCenturyBoyPunchEntity(world, this, getMaster());
                twentiethCenturyBoyPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 1, 0.3f);
                world.addEntity(twentiethCenturyBoyPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                props.setAbilityActive(props.getTimeLeft() > 801 && props.getCooldown() <= 0 && props.getAbility());
                if (props.getAbilityActive()) {
                    master.setMotion(0, master.onGround && !world.getBlockState(master.getPosition().down()).isAir(world, master.getPosition().down()) ? 0 : master.getMotion().getY(), 0);
                    props.subtractTimeLeft(1);
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
                        TwentiethCenturyBoyPunchEntity twentiethCenturyBoyPunchEntity1 = new TwentiethCenturyBoyPunchEntity(world, this, master);
                        twentiethCenturyBoyPunchEntity1.randomizePositions();
                        twentiethCenturyBoyPunchEntity1.shoot(master, master.rotationPitch, master.rotationYaw, 1, 0.25f);
                        world.addEntity(twentiethCenturyBoyPunchEntity1);
                        TwentiethCenturyBoyPunchEntity twentiethCenturyBoyPunchEntity = new TwentiethCenturyBoyPunchEntity(world, this, master);
                        twentiethCenturyBoyPunchEntity.randomizePositions();
                        twentiethCenturyBoyPunchEntity.shoot(master, master.rotationPitch, master.rotationYaw, 1, 0.25f);
                        world.addEntity(twentiethCenturyBoyPunchEntity);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
