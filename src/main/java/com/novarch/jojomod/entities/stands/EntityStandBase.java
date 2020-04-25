package com.novarch.jojomod.entities.stands;

import java.util.UUID;

import com.novarch.jojomod.capabilities.IStand;
import com.novarch.jojomod.capabilities.IStandCapability;
import com.novarch.jojomod.capabilities.JojoProvider;

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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;

@SuppressWarnings("unused")
public abstract class EntityStandBase extends MobEntity
{
	private PlayerEntity master;
    protected boolean standOn;
    private UUID masterUUID;
    protected int tick;
    public boolean orarush;
    protected SoundEvent spawnSound;
    public int longTick;
    public int standID;
	boolean jumpCheck;
	boolean catchCheck;
	boolean attack;
	public boolean giveItem;
	public String mastername;
	public int hungerTimer;
	public boolean onheal;
	public boolean heavyweather;
	public boolean ger;
	public boolean life;
	public boolean timeSkipped;
	public boolean heaven;
    
	public EntityStandBase(EntityType<? extends MobEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.master = null;
        this.standOn = true;
        this.orarush = false;
        this.onheal = false;
        this.timeSkipped = true;
        this.heaven = false;
        this.life = false;
        this.spawnSound = null;
        this.ger = true;
        this.heavyweather = true;
        this.longTick = 2;
        this.tick = 0;
        this.hungerTimer = 0;
	}
	
	public SoundEvent getSpawnSound()
	{
		return spawnSound;
	}
	
	public void setMaster(final String name) 
	{
        if (!this.world.isRemote)
        {
            for(PlayerEntity player : this.world.getPlayers())
            {
            	if(player.getName().equals(name));
            	{
            		this.master = player;
            	}
            }
        }
    }
	
	public int getStandByID()
	{
        return this.standID;
    }
    public int getStandID()
    {
    	return this.standID;
    }
	
    public boolean attackSwing(final PlayerEntity player) {
        if (player.isSwingInProgress && this.getAttackTrue()) {
            this.setAttackTrue(false);
            return true;
        }
        return false;
    }
    
    public void hungerMessage() {
        if (this.hungerTimer == 1) {
            this.getMaster().sendMessage((ITextComponent)new TranslationTextComponent("msg.jojomod.hunger.txt", new Object[0]));
        }
        if (this.hungerTimer >= 40) {
            this.hungerTimer = 0;
        }
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
	
	public void setMasterUUID(final UUID masterUUID)
	{
		this.masterUUID = masterUUID;
	}
	
	public UUID getMasterUUID()
	{
		return this.masterUUID;
	}

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public void setMastername(final String par1Str) {
        if (!this.world.isRemote) {
            this.mastername = par1Str;
        }
	}
	
	public ITextComponent getMastername() {
        if (this.world.isRemote) {
            return (ITextComponent)new StringTextComponent("client");
        }
        if (this.getMaster() == null) {
            final ITextComponent name = (ITextComponent)new StringTextComponent(this.mastername);
            return name;
        }
        final ITextComponent name = this.getMaster().getDisplayName();
        return name;
    }
	
	protected void entityInit()
	{
		this.entityInit();
	}
	
	public void setGiveItems()
	{
        this.giveItem = true;
    }
    
    public void givePlayerItems(final PlayerEntity player)
    {
        this.giveItem = false;
    }
    
    public void resetAllChecks(final PlayerEntity player) {
        this.resetJumpBool(player);
    }
    
    public boolean getGiveItems()
    {
        return this.giveItem;
    }
	
    public void setDead()
    {
    	this.remove();
    }
    
    public void tick() {
        super.tick();
        this.fallDistance = 0.0f;
        if (!this.world.isRemote) {
            if (this.getMaster() == null) {
                this.setDead();
            }
            else {
                final PlayerEntity thePlayer = this.getMaster();
                try {
                    this.setHealth(1000.0f);
                }
                catch (ClassCastException ex) {}
                if (!thePlayer.isAlive()) {
                    this.setDead();
                }
                if (this.getGiveItems()) {
                    this.givePlayerItems(thePlayer);
                }
                if (this.getCatchPassive()) {
                    this.catchPassive();
                }
                /*if (this.getTimeContinue()) {
                    if (this.getTimeIsSkipped()) {
                        this.setTimeIsSkipped(false);
                    }
                    this.setTimeContinue(false);
                }*/
                this.clearActivePotions();
                if (this.getAir() < 20) {
                    this.setAir(60);
                }
                LazyOptional<IStand> power = thePlayer.getCapability(JojoProvider.STAND, null);
                IStand props = power.orElse(new IStandCapability());
                if (!props.getStandOn()) {
                    this.setDead();
                }
                else {
                    this.followMaster();
                    if (this.hungerTimer < 80) {
                        ++this.hungerTimer;
                    }
                }
                this.resetAllChecks(thePlayer);
            }
        }
    }
    
	public void spawnSound() 
	{
		this.world.playSound(this.getMaster(), new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), this.getSpawnSound(), this.getSoundCategory(), 1.0f, 1.0f);
	}
	
	protected void followMaster()
	{
        final Entity entity = (Entity)this.getMaster();
        final double distance = entity.getDistance((Entity)this);
        final double minimum = 0.5;
        final double maximum = 3.0;
        if (distance < minimum)
        {
            this.moveStand(distance, entity);
        }
        else if (distance > minimum) {
            if (distance < maximum && distance > minimum + 0.3)
            {
                this.moveStand(distance, entity);
            }
            else if (distance > maximum && !this.world.isRemote)
            {
                this.setPosition(entity.getPosX(), entity.getPosY(), entity.getPosZ());
            }
        }
    }
	
	public void moveStand(final double distance, final Entity entity)
	{
        final double distanceX = this.getPosX() - entity.getPosX();
        final double distanceY = this.getPosY() - entity.getPosY();
        final double distanceZ = this.getPosZ() - entity.getPosZ();
        float speed = (float)distance / 45.0f;
        if (distance < 0.5)
        {
            speed = -0.1f;
        }
        if (distanceX > 0.0)
        {
            this.moveForward += -speed;
        }
        if (distanceX < 0.0)
        {
            this.moveForward += speed;
        }
        if (distanceY > 0.0)
        {
            this.moveVertical += -speed;
        }
        if (distanceY < 0.0)
        {
            this.moveVertical += speed;
        }
        if (distanceZ > 0.0)
        {
            this.moveStrafing += -speed;
        }
        if (distanceZ < 0.0)
        {
            this.moveStrafing += speed;
        }
    }
	
	public boolean attackEntityFrom(final DamageSource damageSource, final float damage) {
        if (this.getMaster() == null || !this.standOn)
        {
            return false;
        }
        if (damageSource.getTrueSource() == this.getMaster())
        {
            return false;
        }
        if (damageSource == DamageSource.CACTUS)
        {
            return false;
        }
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
        {
            this.setJumped(true);
        }
    }



    public void setJumped(final boolean value)
    {
        this.jumpCheck = value;
    }
    
    public boolean getJumped()
    {
        return this.jumpCheck;
    }
    
    public boolean getCatchPassive()
    {
        return this.catchCheck;
    }
    
    public void setCatchPassive()
    {
        this.catchCheck = true;
    }
    
    public boolean getCanChangeAct()
    {
        return false;
    }
    
    public void changeAct() {}

    private void catchPassive() 
    {
        Entity entity = null;
            if(this.world != null)
            {
                for (Entity oneEntity : this.world.getEntitiesInAABBexcluding(getMaster(), this.getBoundingBox().expand(100.0, 400.0, 100.0), EntityPredicates.NOT_SPECTATING))
		        {
        	        Entity entityplayer = null;

        	        if(oneEntity != null)
        		        entityplayer = oneEntity;
        	
                    final double distance = entityplayer.getDistance(this.getMaster());
                    final double distance2 = 3.141592653589793 * 2 * 2 * 2;
            
                  if(entityplayer != null)
                      entity = entityplayer;
            
                   if (!this.world.isRemote && (entity instanceof TNTEntity || entity instanceof ArrowEntity || entity instanceof FallingBlockEntity) && distance <= distance2 && entity != null)
                   {
                  	if(entityplayer != null)
                  	    entity = entityplayer;
            	
                      final double distanceX = this.getPosX() - entity.getPosX();
                      final double distanceY = this.getPosY() - entity.getPosY();
                      final double distanceZ = this.getPosZ() - entity.getPosZ();
                      final float speed = 0.3f;
                
                if (distanceX > 0.0)
                {
                    this.moveForward += -speed;
                }
                if (distanceX < 0.0)
                {
                    this.moveForward += speed;
                }
                if (distanceY > 0.0)
                {
                    this.moveVertical += -speed;
                }
                if (distanceY < 0.0)
                {
                    this.moveVertical += speed;
                }
                if (distanceZ > 0.0)
                {
                    this.moveStrafing += -speed;
                }
                if (distanceZ < 0.0)
                {
                    this.moveStrafing += speed;
                }
            }
        }
        }
    }
}
