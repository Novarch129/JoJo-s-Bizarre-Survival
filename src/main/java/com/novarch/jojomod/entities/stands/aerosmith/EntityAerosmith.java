package com.novarch.jojomod.entities.stands.aerosmith;

import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import com.novarch.jojomod.util.handlers.KeyHandler;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

/**
 * TODO, Fix this pile of junk.
 */
@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityAerosmith extends EntityStandBase {
    private int oratick = 0;

    private int oratickr = 0;

    public boolean shouldFall = false;

    public EntityAerosmith(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        standID = JojoLibs.StandID.aerosmith;
    }

    public EntityAerosmith(World worldIn) {
        super(EntityInit.AEROSMITH.get(), worldIn);
        spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        standID = JojoLibs.StandID.aerosmith;
    }

    @Override
    public void spawnSound() {
        world.playSound(null, new BlockPos(getMaster().getPosX(), getMaster().getPosY(), getMaster().getPosZ()), getSpawnSound(), getSoundCategory(), 3.0f, 1.0f);
    }

    @Override
    public void tick() {
        super.tick();

        if(!shouldFall)
            setMotion(getMotion().getX(), 0, getMotion().getZ());
        shouldFall = false;

        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> ability = props.getAbility());

            if (ability) {
                if(world.isRemote)
                    Minecraft.getInstance().setRenderViewEntity(this);
            }

            if (!player.isSprinting()) {
                if (attackSwing(player)) {
                    oratick++;
                    if (oratick == 1) {
                        EntityStandPunch.aerosmith aerosmithBullet = new EntityStandPunch.aerosmith(world, this, player);
                        aerosmithBullet.shoot(player, rotationPitch, rotationYaw, 4.0f, 0.4f);
                        world.addEntity(aerosmithBullet);
                    }
                }
            } else if (player.isSprinting() || Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown()) { //TODO Fix
                if (Minecraft.getInstance().gameSettings.keyBindAttack.isPressed())
                    if (player.getFoodStats().getFoodLevel() > 6) {
                        world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.VOLARUSH.get(), getSoundCategory(), 4.05f, 1.0f);
                        oratick++;
                        if (oratick == 1) {
                            if (!world.isRemote)
                                orarush = true;
                        }
                    }
            }
            if (player.swingProgressInt == 0)
                oratick = 0;
            if (orarush) {
                player.setSprinting(false);
                oratickr++;
                if (oratickr >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        EntityStandPunch.aerosmith aerosmithBullet1 = new EntityStandPunch.aerosmith(world, this, player);
                        aerosmithBullet1.setRandomPositions();
                        aerosmithBullet1.shoot(player, rotationPitch, rotationYaw, 4.0F, 0.3F);
                        world.addEntity(aerosmithBullet1);
                        EntityStandPunch.aerosmith aerosmithBullet2 = new EntityStandPunch.aerosmith(world, this, player);
                        aerosmithBullet2.setRandomPositions();
                        aerosmithBullet2.shoot(player, rotationPitch, rotationYaw, 4.0F, 0.3F);
                        world.addEntity(aerosmithBullet2);
                    }
                if (oratickr >= 110) {
                    orarush = false;
                    oratickr = 0;
                }
            }
        }
    }
}
