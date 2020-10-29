package io.github.novarch129.jojomod.entity.stand.attack;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Collections;

public class BubbleEntity extends LivingEntity {
    private BlockPos spawnPosition;

    public BubbleEntity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
        noClip = true;
    }

    @Override
    public Iterable<ItemStack> getArmorInventoryList() {
        return Collections.emptyList();
    }

    @Override
    public ItemStack getItemStackFromSlot(EquipmentSlotType slotIn) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemStackToSlot(EquipmentSlotType slotIn, ItemStack stack) {
    }

    @Override
    public HandSide getPrimaryHand() {
        return HandSide.RIGHT;
    }

    public void pop() {
        world.createExplosion(this, getPosX(), getPosY(), getPosZ(), 4, Explosion.Mode.DESTROY);
        remove();
    }

    @Override
    public void tick() {
        super.tick();
        if (spawnPosition != null && (!world.isAirBlock(spawnPosition) || spawnPosition.getY() < 1))
            spawnPosition = null;
        if (spawnPosition == null || rand.nextInt(30) == 0 || spawnPosition.withinDistance(getPositionVec(), 2))
            spawnPosition = new BlockPos(getPosX() + (double) rand.nextInt(7) - (double) rand.nextInt(7), getPosY() + (double) rand.nextInt(6) - 2, getPosZ() + (double) rand.nextInt(7) - (double) rand.nextInt(7));

        double lvt_3_1_ = (double) spawnPosition.getX() + 0.5 - getPosX();
        double lvt_5_1_ = (double) spawnPosition.getY() + 0.1 - getPosY();
        double lvt_7_1_ = (double) spawnPosition.getZ() + 0.5 - getPosZ();
        Vec3d lvt_9_1_ = getMotion();
        Vec3d lvt_10_1_ = lvt_9_1_.add((Math.signum(lvt_3_1_) * 0.5D - lvt_9_1_.x) * 0.10000000149011612, (Math.signum(lvt_5_1_) * 0.699999988079071 - lvt_9_1_.y) * 0.10000000149011612, (Math.signum(lvt_7_1_) * 0.5 - lvt_9_1_.z) * 0.10000000149011612);
        setMotion(lvt_10_1_);
        float lvt_11_1_ = (float) (MathHelper.atan2(lvt_10_1_.z, lvt_10_1_.x) * 57.2957763671875) - 90;
        float lvt_12_1_ = MathHelper.wrapDegrees(lvt_11_1_ - rotationYaw);
        moveForward = 0.3f;
        rotationYaw += lvt_12_1_;

        if (!world.isRemote && !world.getBlockState(getPosition()).isAir(world, getPosition()))
            pop();
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if (world != null && !world.isRemote)
            spawnPosition = getPosition();
    }
}
