package com.novarch.jojomod.entities.stands.aerosmith;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.EntityStandBase;
import com.novarch.jojomod.entities.stands.EntityStandPunch;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.client.CSyncAerosmithPacket;
import com.novarch.jojomod.util.JojoLibs;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class EntityAerosmith extends EntityStandBase {
    private int oratick = 0;

    private int oratickr = 0;

    public float yaw = 0;

    public float pitch = 0;

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

        setMotion(getMotion().getX(), 0, getMotion().getZ());

        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                if(ability != props.getAbility())
                    ability = props.getAbility();
            });

            setRotation(yaw, pitch);
            if(getMotion().getX() == 0 && getMotion().getY() == 0 && getMotion().getZ() == 0)
                setRotationYawHead(yaw);

            if (!player.isSprinting()) {
                if (attackSwing(player)) {
                    oratick++;
                    if (oratick == 1) {
                        EntityStandPunch.aerosmith aerosmithBullet = new EntityStandPunch.aerosmith(world, this, player);
                        aerosmithBullet.shoot(player, rotationPitch, rotationYaw, 4.0f, 0.4f);
                        world.addEntity(aerosmithBullet);
                    }
                }
            } else if (player.isSprinting()) { //TODO Fix
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

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(getMaster() != null)
            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                if(props.getAbility())
                    JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(4));
            });
    }

    @Override
    public void setRotation(float yaw, float pitch) {
        super.setRotation(yaw, pitch);
    }
}
