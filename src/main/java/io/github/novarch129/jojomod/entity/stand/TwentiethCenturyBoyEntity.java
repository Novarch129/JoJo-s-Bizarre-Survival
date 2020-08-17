package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
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
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                props.setAbilityActive(props.getTimeLeft() > 801 && props.getCooldown() <= 0 && props.getAbility());
                ability = props.getTimeLeft() > 800 && props.getCooldown() <= 0 && props.getAbility();
                if (ability) {
                    master.setMotion(0, master.onGround && !world.getBlockState(master.getPosition().down()).isAir(world, master.getPosition().down()) ? 0 : master.getMotion().getY(), 0);
                    props.setTimeLeft(props.getTimeLeft() - 1);
                }
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
        }
    }
}
