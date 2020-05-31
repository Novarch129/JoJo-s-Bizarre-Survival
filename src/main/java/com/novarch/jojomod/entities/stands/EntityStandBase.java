package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import java.util.UUID;

@SuppressWarnings("unused")
public abstract class EntityStandBase extends MobEntity
{
	private PlayerEntity master;
    protected boolean standOn;
    private int act;
    protected int tick;
    public boolean orarush;
    protected SoundEvent spawnSound;
    public int longTick;
    public int standID;
	boolean jumpCheck;
	boolean attack;
	public UUID masterUUID;
	public int hungerTimer;
	public boolean ability;
    
	public EntityStandBase(EntityType<? extends MobEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.master = null;
        this.standOn = true;
        this.orarush = false;
        this.spawnSound = null;
        this.longTick = 2;
        this.tick = 0;
        this.hungerTimer = 0;
        this.ability = true;
	}
	
	public SoundEvent getSpawnSound()
	{
		return this.spawnSound;
	}

    public void setMaster(PlayerEntity playerEntity)
    {
        this.master = playerEntity;
    }

    public int getStandID()
    {
    	return this.standID;
    }
	
    public boolean attackSwing(final PlayerEntity player)
    {
        if (player.isSwingInProgress && this.getAttackTrue())
        {
            this.setAttackTrue(false);
            return true;
        }
        return false;
    }
    
    public void hungerMessage()
    {
        if (this.hungerTimer == 1)
            this.getMaster().sendMessage(new TranslationTextComponent("msg.jojomod.hunger.txt"));
        if (this.hungerTimer >= 40)
            this.hungerTimer = 0;
    }

    @Override
    public IPacket<?> createSpawnPacket()
    {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public void setAttackTrue(final boolean value)
    {
        this.attack = value;
    }
    
    public boolean getAttackTrue()
    {
        return this.attack;
    }
    
	public PlayerEntity getMaster()
	{
		return this.master;
	}

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public void setMasterUUID(final UUID masterUUID)
    {
        if (!this.world.isRemote)
            this.masterUUID = masterUUID;
	}

    public UUID getMasterUUID()
    {
        return masterUUID;
    }

    public ITextComponent getMastername() {
        return this.getMaster().getDisplayName();
    }
    
    public void resetAllChecks(final PlayerEntity player) {
        this.resetJumpBool(player);
    }

    @Override
    public void tick()
    {
        super.tick();
        this.fallDistance = 0.0f;
        if (!this.world.isRemote)
        {
            if(this.getMaster() == null) {
                this.remove();
                return;
            } catchPassive();

            if (!this.getMaster().isAlive())
                this.remove();

                if (this.getAir() < 20)
                    this.setAir(60);

                this.clearActivePotions();
            JojoProvider.getLazyOptional(this.getMaster()).ifPresent(props -> {
                this.standOn = props.getStandOn();

                if (!props.getStandOn())
                    this.remove();
                else
                    if (this.hungerTimer < 80)
                        ++this.hungerTimer;
            });
            this.resetAllChecks(this.getMaster());
        }
    }
    
	public void spawnSound() 
	{
	    this.world.playSound(null, new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), this.getSpawnSound(), this.getSoundCategory(), 1.0f, 1.0f);
    }
	
	protected void followMaster()
	{
        final PlayerEntity entity = this.getMaster();
        final double distance = entity.getDistance(this);
        final double minimum = 0.5;
        final double maximum = 3.0;

        if (distance < minimum)
            this.moveStand(distance, entity);

        else if (distance > minimum)
        {
            if (distance < maximum && distance > minimum + 0.3)
                this.moveStand(distance, entity);

            else if (distance > maximum && !this.world.isRemote)
                this.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
        }
    }
	
	public void moveStand(final double distance, final Entity entity)
	{
        final double distanceX = this.getPosX() - entity.getPosX();
        final double distanceY = this.getPosY() - entity.getPosY();
        final double distanceZ = this.getPosZ() - entity.getPosZ();
        float speed = (float)distance / 45.0f;

        if (distance < 0.5)
            speed = -0.1f;
        if (distanceX > 0.0)
            this.moveForward += -speed;
        if (distanceX < 0.0)
            this.moveForward += speed;
        if (distanceY > 0.0)
            this.moveVertical += -speed;
        if (distanceY < 0.0)
            this.moveVertical += speed;
        if (distanceZ > 0.0)
            this.moveStrafing += -speed;
        if (distanceZ < 0.0)
            this.moveStrafing += speed;
    }

    @Override
	public boolean attackEntityFrom(final DamageSource damageSource, final float damage) {
        if (!this.standOn)
            return false;
        if (damageSource.getTrueSource() == this.getMaster())
            return false;
        if (damageSource == DamageSource.CACTUS)
            return false;
        this.getMaster().attackEntityFrom(damageSource, damage * 0.5f);
        return false;
    }
	
	public boolean playerJump(final PlayerEntity player)
	{
        if (player.moveVertical > 0.20000000298023224 && this.getJumped() && !player.onGround)
        {
            this.setJumped(false);
            return true;
        }
        return false;
    }
    
    public void resetJumpBool(final PlayerEntity player)
    {
        if (player.onGround)
            this.setJumped(true);
    }

    public boolean hasAct() {
	    return false;
    }

    public void changeAct(){
	    act+=1;
    }

    public void setJumped(final boolean value)
    {
        this.jumpCheck = value;
    }
    
    public boolean getJumped()
    {
        return this.jumpCheck;
    }

    private void catchPassive()
    {
        if (this.world != null)
        {
            if (!world.isRemote)
                world.getServer().getWorld(dimension).getEntities().forEach(entity1 -> {
                    Entity entity;
                    Entity playerEntity = null;

                    if (entity1 != null)
                        playerEntity = entity1;

                    assert playerEntity != null;
                    final double distance = playerEntity.getDistance(this.getMaster());
                    final double distance2 = Math.PI * 2 * 2 * 2;

                    entity = playerEntity;

                    if (!this.world.isRemote && (entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity) && distance <= distance2) {

                        final double distanceX = this.getPosX() - entity.getPosX();
                        final double distanceY = this.getPosY() - entity.getPosY();
                        final double distanceZ = this.getPosZ() - entity.getPosZ();

                        if (distanceX > 0.0)
                            this.moveForward += -0.3;
                        if (distanceX < 0.0)
                            this.moveForward += 0.3;
                        if (distanceY > 0.0)
                            this.moveVertical += -0.3;
                        if (distanceY < 0.0)
                            this.moveVertical += 0.3;
                        if (distanceZ > 0.0)
                            this.moveStrafing += -0.3;
                        if (distanceZ < 0.0)
                            this.moveStrafing += 0.3;
                    }
                });
        }
    }
}
