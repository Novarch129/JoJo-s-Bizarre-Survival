package io.github.novarch129.jojomod.entity;

import io.github.novarch129.jojomod.init.EntityInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.util.HandSide;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.Collections;

public class CameraEntity extends LivingEntity {
    private PlayerEntity master;

    public CameraEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);

    }

    public CameraEntity(World world, PlayerEntity master) {
        super(EntityInit.CAMERA.get(), world);
        this.master = master;
    }

    public PlayerEntity getMaster() {
        return master;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
    }

    @Override
    public void tick() {
        super.tick();
        if (master != null) {
            rotationYaw = master.rotationYaw;
            rotationPitch = master.rotationPitch * 0.5f;
            setRotation(rotationYaw, rotationPitch);
            prevRotationYaw = rotationYaw;
            renderYawOffset = rotationYaw;
            rotationYawHead = renderYawOffset;
        }
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public float getYaw(float partialTicks) {
        return rotationYaw;
    }

    @Override
    public HandSide getPrimaryHand() {
        return HandSide.RIGHT;
    }
}
