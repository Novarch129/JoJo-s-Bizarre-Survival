package com.novarch.jojomod.capabilities;

import com.novarch.jojomod.StevesBizarreSurvival;
import com.novarch.jojomod.network.message.SyncStandCapability;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.lwjgl.system.CallbackI;

public class StandCapability implements IStand
{
	private String playername = "";

	private int playerStandID = 0;
	  
	private int playerStandAct = 0;
	  
	private boolean playerStandOn = false;
	  
	private boolean playerPowerSpawned = false;
	  
	private int playerJojoPower = 0;
	  
	private int cooldown = 0;

	private int timeleft = 0;

	private String diavolo = "";
	  
	private String playerStandName = "";

	private boolean ability = true;

	private int transformed = 0;

	@Override
	public String getPlayername() {
		return this.playername;
	}

	@Override
	public void setPlayername(String playername) {
		this.playername = playername;
		onDataUpdated();
	}

	public void setStandID(int value)
	{
	    this.playerStandID = value;
		onDataUpdated();
	}
	  
	public void setStandAct(int value)
	{
	    this.playerStandAct = value;
		onDataUpdated();
	}
	  
	public void setStandOn(boolean value)
	{
	    this.playerStandOn = value;
		onDataUpdated();
	}
	  
	  public void setJojoPower(int value)
	  {
	    this.playerJojoPower = value;
		  onDataUpdated();
	  }
	  
	  public void setPowerSpawned(boolean value)
	  {
	    this.playerPowerSpawned = value;
		  onDataUpdated();
	  }
	  
	  public void setPlayerStandName(String value)
	  {
	  	this.playerStandName = value;
		  onDataUpdated();
	  }
	  
	  public int getStandID()
	  {
	  	return this.playerStandID;
	  }
	  
	  public int getStandAct()
	  {
	    return this.playerStandAct;
	  }
	  
	  public boolean getStandOn() {
	    return this.playerStandOn;
	  }
	  
	  public int getJojoPower() {
	    return this.playerJojoPower;
	  }
	  
	  public boolean getPowerSpawned() {
	    return this.playerPowerSpawned;
	  }
	  
	  public String getPlayerStandName() {
	    return this.playerStandName;
	  }
	  
	  @Override
	  public void setCooldown(int new_cooldown)
	  {
		  this.cooldown = new_cooldown;
		  onDataUpdated();
	  }
	  
	  @Override
	  public int getCooldown()
	  {
		  return this.cooldown;
	  }

	@Override
	public void addCooldown(int addition)
	{
		this.cooldown += addition;
		onDataUpdated();
	}

	@Override
	public void subtractCooldown(int subtraction)
	{
		this.cooldown -= subtraction;
		onDataUpdated();
	}

	@Override
	public void setTimeLeft(int timeleft)
	{
		this.timeleft = timeleft;
		onDataUpdated();
	}

	@Override
	public int getTimeLeft()
	{
		return this.timeleft;
	}

	@Override
	public void addTimeLeft(int addition)
	{
		this.timeleft += addition;
		onDataUpdated();
	}

	@Override
	public void subtractTimeLeft(int subtraction) {
		this.timeleft -= subtraction;
		onDataUpdated();
	}

	@Override
	public void setDiavolo(String truth)
	{
		this.diavolo = truth;
		onDataUpdated();
	}

	@Override
	public String getDiavolo()
	{
		return this.diavolo;
	}

	@Override
	public boolean getAbility()
	{
		return this.ability;
	}

	@Override
	public void setAbility(boolean value)
	{
		this.ability = value;
		onDataUpdated();
	}

	@Override
	public int getTransformed()
	{
		return this.transformed;
	}

	@Override
	public void setTransformed(int value)
	{
		this.transformed = value;
		onDataUpdated();
	}

	@Override
	public void subtractTransformed(int subtraction)
	{
		this.transformed -= subtraction;
		onDataUpdated();
	}

	@Override
	public void addTransformed(int addition)
	{
		this.transformed += addition;
		onDataUpdated();
	}

	protected void onDataUpdated()
	{
		if(StevesBizarreSurvival.PROXY.getWorld()==null)
			return;
		if(StevesBizarreSurvival.PROXY.getWorld().isRemote)
			return;
		for(PlayerEntity playerEntity : StevesBizarreSurvival.PROXY.getWorld().getPlayers())
		{
			if(playerEntity==null)
				return;
			if(playerEntity.getDisplayName().toString().equals(this.getPlayername()))
				if(!(playerEntity instanceof ClientPlayerEntity)) {
					StevesBizarreSurvival.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), new SyncStandCapability(this));
					playerEntity.sendMessage(new StringTextComponent("Run"));
				}
		}
	}

	public void cloneSaveFunction(IStand props)
	{
	    setStandID(props.getStandID());
	    setStandAct(props.getStandAct());
	    setStandOn(false);
	    setJojoPower(props.getJojoPower());
	    setPowerSpawned(false);
	    setPlayerStandName(props.getPlayerStandName());
	    setCooldown(props.getCooldown());
	    setTimeLeft(props.getTimeLeft());
	    setTransformed(props.getTransformed());
	    setDiavolo(props.getDiavolo());
	    setAbility(props.getAbility());
	  }
	  
	  public void setStandRemoved()
	  {
	    setStandOn(false);
	    setStandAct(0);
	    setStandID(0);
	    setPlayerStandName("");
	    setCooldown(0);
	    setDiavolo("");
	    setAbility(true);
	  }
}
