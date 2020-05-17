package com.novarch.jojomod.entities.stands.aerosmith;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class EntityAerosmith extends EntityStandBase
{
    @ObjectHolder(JojoBizarreSurvival.MOD_ID + ":aerosmith") public static EntityType<EntityAerosmith> TYPE;

    private int oratick = 0;

    private int oratickr = 0;

    @Override
    public boolean canDespawn(double p_213397_1_) {
        return false;
    }

    @Override
    public boolean isAIDisabled() {
        return false;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock() {
        return false;
    }

    @Override
    public void writeAdditional(CompoundNBT p_213281_1_) {
        super.writeAdditional(p_213281_1_);
    }

    @Override
    public void readAdditional(CompoundNBT p_70037_1_) {
        super.readAdditional(p_70037_1_);
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return super.createSpawnPacket();
    }

    public EntityAerosmith(EntityType<? extends MobEntity> type, World worldIn)
    {
        super(type, worldIn);
        this.spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        setCatchPassive();
        this.standID = JojoLibs.StandID.aerosmith;
    }

    public EntityAerosmith(World worldIn)
    {
        super(TYPE, worldIn);
        this.spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        setCatchPassive();
        this.standID = JojoLibs.StandID.aerosmith;
    }

    public void tick()
    {
        super.tick();
        this.fallDistance = 0.0f;

        if(getMaster() != null)
        {
            PlayerEntity player = getMaster();
            JojoProvider.getLazy(player).ifPresent(props -> this.aerosmith = props.getAbility());
            if(!player.isSprinting())
            {
                if(attackSwing(player))
                {
                    this.oratick++;
                    if(oratick == 1)
                    {
                        EntityStandPunch.aerosmith aerosmithBullet = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet.shoot(player, player.rotationPitch, player.rotationYaw, 0, 3.0f, 0.4f);
                        this.world.addEntity(aerosmithBullet);
                    }
                }
            }
            else
            {
                if(attackSwing(player))
                    if(player.getFoodStats().getFoodLevel() > 6)
                    {
                        this.oratick++;
                        if(this.oratick == 1)
                        {
                            //TODO Add bullet sound
                            if (!this.world.isRemote)
                                this.orarush = true;
                        }
                    }
            }
            if (player.swingProgressInt == 0)
                this.oratick = 0;
            if (this.orarush)
            {
                player.setSprinting(false);
                this.oratickr++;
                if (this.oratickr >= 10)
                    if (!this.world.isRemote)
                    {
                        player.setSprinting(false);
                        EntityStandPunch.aerosmith aerosmithBullet1 = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet1.setRandomPositions();
                        aerosmithBullet1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
                        this.world.addEntity(aerosmithBullet1);
                        EntityStandPunch.aerosmith aerosmithBullet2 = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet2.setRandomPositions();
                        aerosmithBullet2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 0.2F);
                        this.world.addEntity(aerosmithBullet2);
                    }
                if (this.oratickr >= 110)
                {
                    this.orarush = false;
                    this.oratickr = 0;
                }
            }
        }
    }
}
