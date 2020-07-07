package com.novarch.jojomod.entities;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.util.Util;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ALL")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class StandArrowEntity extends AbstractArrowEntity {

    public StandArrowEntity(EntityType<? extends AbstractArrowEntity> type, World world) {
        super(type, world);
    }

    @Deprecated //Use below constructor instead
    private StandArrowEntity(EntityType<? extends AbstractArrowEntity> type, World world, LivingEntity shooter) {
        super(type, shooter, world);
    }

    public StandArrowEntity(World world, LivingEntity shooter) {
        this(EntityInit.STAND_ARROW.get(), world, shooter);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (entity instanceof PlayerEntity) {
            Stand.getLazyOptional((PlayerEntity) entity).ifPresent(props -> {
                if (props.getStandID() == 0) {
                    props.setStandID(Util.StandID.STANDS[world.rand.nextInt(Util.StandID.STANDS.length)]);
                    world.playSound(null, getPosition(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.NEUTRAL, 1, 1);
                    remove();
                }
                else if (props.getStandID() == Util.StandID.GOLD_EXPERIENCE) {
                    props.setStandID(Util.StandID.GER);
                    world.playSound(null, getPosition(), SoundEvents.ITEM_SHIELD_BREAK, SoundCategory.NEUTRAL, 1, 1);
                    remove();
                }
            });
        }
        super.onEntityHit(result);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(ItemInit.STAND_ARROW.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
