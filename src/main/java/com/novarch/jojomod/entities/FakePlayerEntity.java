package com.novarch.jojomod.entities;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.events.EventHandleStandAbilities;
import com.novarch.jojomod.init.EntityInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

@MethodsReturnNonnullByDefault
public class FakePlayerEntity extends MobEntity {
    private final PlayerEntity parent;

    public FakePlayerEntity(EntityType<? extends MobEntity> p_i48576_1_, World p_i48576_2_, PlayerEntity parent) {
        super(p_i48576_1_, p_i48576_2_);
        this.parent = parent;
    }

    public FakePlayerEntity(World world, PlayerEntity parent) {
        super(EntityInit.FAKE_PLAYER.get(), world);
        this.parent = parent;
    }

    @Override
    public float getHealth() {
        return Float.MAX_VALUE;
    }

    @Override
    public boolean canDespawn(double p_213397_1_) {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public boolean isAIDisabled() {
        return true;
    }

    @Override
    protected void registerData() {
        super.registerData();
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public PlayerEntity getParent() {
        return parent;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.parent != null) {
            if (!this.parent.isAlive())
                EventHandleStandAbilities.removalQueue.add(this);

            Stand.getLazyOptional(this.parent).ifPresent(props -> {
                if (!props.getAbility())
                    EventHandleStandAbilities.removalQueue.add(this);
                if (!props.getStandOn())
                    EventHandleStandAbilities.removalQueue.add(this);
            });
        } else
            EventHandleStandAbilities.removalQueue.add(this);
    }
}
