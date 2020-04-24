package com.novarch.jojomod.capabilities;

import net.minecraft.entity.player.PlayerEntity;

public class IStandCapability implements IStand
{
	private int playerStandID = 0;
	  
	private int playerStandAct = 0;
	  
	private boolean playerStandOn = false;
	  
	private int playerStandExp = 0;
	  
	private boolean playerPowerSpawned = false;
	  
	private int playerJojoPower = 0;
	  
	private int cooldown = 0;

	private int timeleft = 0;

	private PlayerEntity diavolo = null;
	  
	private String playerStandName = "";
	  
	public void addStandExp(int value)
	{
	    this.playerStandExp += value;
	}

	public void setStandID(int value)
	{
	    this.playerStandID = value;
	}
	  
	public void setStandExp(int value)
	{
	    this.playerStandExp = value;
	}
	  
	public void setStandAct(int value)
	{
	    this.playerStandAct = value;
	}
	  
	public void setStandOn(boolean value)
	{
	    this.playerStandOn = value;
	}
	  
	  public void setJojoPower(int value) {
	    this.playerJojoPower = value;
	  }
	  
	  public void setPowerSpawned(boolean value) {
	    this.playerPowerSpawned = value;
	  }
	  
	  public void setPlayerStandName(String value) {
	    this.playerStandName = value;
	  }
	  
	  public int getStandID() {
	    return this.playerStandID;
	  }
	  
	  public int getStandExp() {
	    return this.playerStandExp;
	  }
	  
	  public int getStandAct() {
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
	}

	@Override
	public void subtractCooldown(int subtraction)
	{
		this.cooldown -= subtraction;
	}

	@Override
	public void setTimeLeft(int new_time_left) {
		this.timeleft = new_time_left;
	}

	@Override
	public int getTimeLeft() {
		return this.timeleft;
	}

	@Override
	public void addTimeLeft(int addition) {
		this.timeleft += addition;
	}

	@Override
	public void subtractTimeLeft(int subtraction) {
		this.timeleft -= subtraction;
	}

	@Override
	public void setDiavolo(PlayerEntity truth)
	{
		this.diavolo = truth;
	}

	@Override
	public PlayerEntity getDiavolo()
	{
		return this.diavolo;
	}

	public void cloneSaveFunction(IStand props)
	{
	    setStandID(props.getStandID());
	    setStandAct(props.getStandAct());
	    setStandOn(props.getStandOn());
	    setJojoPower(props.getJojoPower());
	    setPowerSpawned(props.getPowerSpawned());
	    setPlayerStandName(props.getPlayerStandName());
	    setCooldown(props.getCooldown());
	    setDiavolo(props.getDiavolo());
	  }
	  
	  public void setStandRemoved()
	  {
	    setStandOn(false);
	    setStandAct(0);
	    setStandExp(0);
	    setStandID(0);
	    setPlayerStandName("");
	    setCooldown(0);
	    setDiavolo(null);
	  }
}
