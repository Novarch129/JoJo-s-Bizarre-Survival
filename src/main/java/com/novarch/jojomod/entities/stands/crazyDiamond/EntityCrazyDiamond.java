package com.novarch.jojomod.entities.stands.crazyDiamond;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import net.minecraft.block.Block;
import net.minecraft.block.FallingBlock;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.ArrayList;
import java.util.List;

public class EntityCrazyDiamond extends EntityStandBase
{
	  private int oratick = 0;

	  private int oratickr = 0;

	  private KeyBinding repair = KeyHandler.keys[2];

	 private List<Block> blockList = new ArrayList<Block>();

	 private List<BlockPos> blockPosList = new ArrayList<BlockPos>();

	 public void addBlock(Block block) {
	 	blockList.add(block);
	 }

	public void addBlockPos(BlockPos blockPos) {
		blockPosList.add(blockPos);
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) { return false; }

	@Override
	public boolean isAIDisabled()
	  {
	    return false;
	  }

	  @Override
	  public void readAdditional(CompoundNBT compoundNBT)
	  {
	    super.readAdditional(compoundNBT);
	  }

	  @Override
	  public void writeAdditional(CompoundNBT compoundNBT)
	  {
	    super.writeAdditional(compoundNBT);
	  }

	  @Override
	  public IPacket<?> createSpawnPacket()
	{
		return super.createSpawnPacket();
	}

	public EntityCrazyDiamond(EntityType<? extends EntityStandBase> type, World world)
	{
		super(type, world);
	    this.spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
	    this.standID = JojoLibs.StandID.crazyDiamond;
	}

	public EntityCrazyDiamond(World world)
	{
		super(EntityInit.CRAZY_DIAMOND.get(), world);
	    this.spawnSound = SoundInit.SPAWN_CRAZY_DIAMOND.get();
	    this.standID = JojoLibs.StandID.crazyDiamond;
	}
	
	public void tick() {
		super.tick();
		this.fallDistance = 0.0f;
		if (getMaster() != null)
		{
			PlayerEntity player = getMaster();

			JojoProvider.getLazyOptional(player).ifPresent(props -> {
				this.ability = props.getAbility();

				//Crazy Diamond's ability
				List<Block> blockRepairedList = new ArrayList<Block>();
				List<BlockPos> blockPosRepairedList = new ArrayList<BlockPos>();

				if(blockList.size() > 20)
					blockList.remove(0);
				if(blockPosList.size() > 20)
					blockPosList.remove(0);

				if (repair.isPressed() && props.getCooldown() <= 0) {
					if (blockList.size() > 0 && blockPosList.size() > 0) {
						world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.SPAWN_CRAZY_DIAMOND.get(), getSoundCategory(), 1.0f, 1.0f);
						props.setCooldown(100);
						for (Block block : blockList) {
							for (BlockPos pos : blockPosList) {
								world.setBlockState(pos, block.getDefaultState());
								blockRepairedList.add(block);
								blockPosRepairedList.add(pos);
							}
						}
					}
				}

				if(blockRepairedList.size() > 0 && blockPosRepairedList.size() > 0)
				{
					for (Block repairedBlock : blockRepairedList) {
						if (blockList.contains(repairedBlock))
							blockList.remove(repairedBlock);
					}
					for (BlockPos repairedBlockPos : blockPosRepairedList) {
						if (blockPosList.contains(repairedBlockPos))
							blockPosList.remove(repairedBlockPos);
					}
					blockRepairedList.removeAll(blockRepairedList);
					blockPosRepairedList.removeAll(blockPosRepairedList);
				}

				if(props.getCooldown() > 0 && ability)
					props.subtractCooldown(1);
			});

			if (this.standOn)
			{
				followMaster();
				setRotationYawHead(player.rotationYaw);
				setRotation(player.rotationYaw, player.rotationPitch);

				//Orarush food check
				if (!player.isAlive())
					remove();
				if (player.isSprinting()) {
					if (attackSwing(player))
						if (player.getFoodStats().getFoodLevel() > 6) {
							this.oratick++;
							if (this.oratick == 1) {
								if(!world.isRemote)
									world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.DORARUSH.get(), getSoundCategory(), 1.0f, 1.0f);

								if (!this.world.isRemote)
									this.orarush = true;
							}
						} else
							hungerMessage();
				} else if (attackSwing(getMaster())) {
					if (!this.world.isRemote) {
						this.oratick++;
						if (this.oratick == 1) {
							this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.PUNCH_MISS.get(), getSoundCategory(), 1.0f, 0.8f / (this.rand.nextFloat() * 0.4f + 1.2f) + 0.5f);
							EntityStandPunch.crazyDiamond crazyDiamond = new EntityStandPunch.crazyDiamond(this.world, this, player);
							crazyDiamond.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(crazyDiamond);
						}
					}
				}
				if (player.swingProgressInt == 0)
					this.oratick = 0;
				if (this.orarush) {
					player.setSprinting(false);
					this.oratickr++;
					if (this.oratickr >= 10)
						if (!this.world.isRemote) {
							player.setSprinting(false);
							EntityStandPunch.crazyDiamond crazyDiamond1 = new EntityStandPunch.crazyDiamond(this.world, this, player);
							crazyDiamond1.setRandomPositions();
							crazyDiamond1.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(crazyDiamond1);
							EntityStandPunch.crazyDiamond crazyDiamond2 = new EntityStandPunch.crazyDiamond(this.world, this, player);
							crazyDiamond2.setRandomPositions();
							crazyDiamond2.shoot(player, player.rotationPitch, player.rotationYaw, 0.0f, 2.0f, 0.2f);
							this.world.addEntity(crazyDiamond2);
						}
					if (this.oratickr >= 80) {
						this.orarush = false;
						this.oratickr = 0;
					}
				}
			}
		}
	}


	  public boolean isEntityInsideOpaqueBlock()
	  {
	  	return false;
	  }

	@Override
	public boolean canBeCollidedWith()
	{
		return super.canBeCollidedWith();
	}
}
