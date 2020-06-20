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
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.TNTEntity;
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

    public void shootBomb() {
        Stand.getLazyOptional(getMaster()).ifPresent(props -> {
            if (props.getCooldown() <= 0) {
                TNTEntity tnt = new TNTEntity(world, getPosX(), getPosY(), getPosZ(), getMaster());
                tnt.setFuse(20);
                tnt.setVelocity(getLookVec().getX(), getLookVec().getY(), getLookVec().getZ());
                if(!world.isRemote)
                    world.addEntity(tnt);
                props.setCooldown(200);
            }
        });
    }
    
    @Override
    public void tick() {
        super.tick();

        setMotion(getMotion().getX(), 0, getMotion().getZ());

        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                if(ability != props.getAbility()) {
                    if(!ability)
                        JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(CSyncAerosmithPacket.Action.RENDER));
                    ability = props.getAbility();
                }
                if(ability)
                    if(props.getCooldown() > 0)
                        props.subtractCooldown(1);
            });

            setRotation(yaw, pitch);
            if(getMotion().getX() == 0 && getMotion().getY() == 0 && getMotion().getZ() == 0)
                setRotationYawHead(yaw);

            if ((!player.isSprinting() && !ability) || (!isSprinting() && ability)) {
                if ((attackSwing(player) && !ability) || (swingProgressInt == 1 && ability)) {
                    oratick++;
                    if (oratick == 1) {
                        EntityStandPunch.aerosmith aerosmithBullet = new EntityStandPunch.aerosmith(world, this, player);
                        aerosmithBullet.shoot(player, rotationPitch, rotationYaw, 4.0f, 0.4f);
                        world.addEntity(aerosmithBullet);
                        JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(5, 1));
                    }
                }
            } else if ((player.isSprinting() && !ability) || (isSprinting() && ability)) {
                if ((attackSwing(player) && !ability) || (swingProgressInt == 1 && ability))
                    if (player.getFoodStats().getFoodLevel() > 6) {
                        oratick++;
                        if (oratick == 1) {
                            world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundInit.VOLARUSH.get(), getSoundCategory(), 4.05f, 1.0f);
                            if (!world.isRemote)
                                orarush = true;
                        }
                    }
            }
            if ((player.swingProgressInt == 0 && !ability) || (swingProgressInt == 0 && ability))
                oratick = 0;
            if (orarush) {
                if(!ability)
                    player.setSprinting(false);
                else
                    setSprinting(false);
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
                    JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(5, 1));
                }
            }
        }
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(getMaster() != null)
            Stand.getLazyOptional(getMaster()).ifPresent(props -> {
                if (props.getAbility())
                    JojoBizarreSurvival.INSTANCE.sendToServer(new CSyncAerosmithPacket(CSyncAerosmithPacket.Action.RENDER));
            });
    }
}
