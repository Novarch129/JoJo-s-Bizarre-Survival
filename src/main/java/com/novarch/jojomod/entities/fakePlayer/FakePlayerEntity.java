package com.novarch.jojomod.entities.fakePlayer;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.events.EventHandleStandAbilities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.registries.ObjectHolder;

public class FakePlayerEntity extends MobEntity
{
    private PlayerEntity parent;

    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":fake_player_jojo") public static EntityType<FakePlayerEntity> TYPE;

    public FakePlayerEntity(EntityType<? extends MobEntity> p_i48576_1_, World p_i48576_2_)
    {
        super(p_i48576_1_, p_i48576_2_);
    }

    public FakePlayerEntity(World world)
    {
        super(TYPE, world);
    }

    public FakePlayerEntity(World p_i48576_2_, PlayerEntity parent)
    {
        super(TYPE, p_i48576_2_);
        this.parent = parent;
    }

    @Override
    public boolean canDespawn(double p_213397_1_)
    {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    @Override
    public boolean isAIDisabled()
    {
        return true;
    }

    @Override
    protected void registerData()
    {
        super.registerData();
    }

    @Override
    public void readAdditional(CompoundNBT compound)
    {
        super.readAdditional(compound);
    }

    @Override
    public void writeAdditional(CompoundNBT compound)
    {
        super.writeAdditional(compound);
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public PlayerEntity getParent()
    {
        return parent;
    }

    public void setParent(PlayerEntity parent)
    {
        this.parent = parent;
    }

    @Override
    public void tick()
    {
        super.tick();

        if(this.parent!=null) {
           // if(!this.parent.isAlive())
             //   EventHandleStandAbilities.removalQueue.add(this);

            JojoProvider.getLazyOptional(this.parent).ifPresent(props -> {
                if (!props.getAbility())
                    EventHandleStandAbilities.removalQueue.add(this);
                if(!props.getStandOn())
                    EventHandleStandAbilities.removalQueue.add(this);
            });
        }// else { EventHandleStandAbilities.removalQueue.add(this); }
    }
}
