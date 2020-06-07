package com.novarch.jojomod.entities.stands.aerosmith;

import com.novarch.jojomod.capabilities.stand.JojoProvider;
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
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

@MethodsReturnNonnullByDefault
public class EntityAerosmith extends EntityStandBase {
    private int oratick = 0;

    private int oratickr = 0;

    boolean shouldFall = false;

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

    @Override
    public void spawnSound() {
        this.world.playSound(null, new BlockPos(this.getMaster().getPosX(), this.getMaster().getPosY(), this.getMaster().getPosZ()), this.getSpawnSound(), this.getSoundCategory(), 3.0f, 1.0f);
    }

    public EntityAerosmith(EntityType<? extends MobEntity> type, World worldIn) {
        super(type, worldIn);
        this.spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        this.standID = JojoLibs.StandID.aerosmith;
    }

    public EntityAerosmith(World worldIn) {
        super(EntityInit.AEROSMITH.get(), worldIn);
        this.spawnSound = SoundInit.SPAWN_AEROSMITH.get();
        this.standID = JojoLibs.StandID.aerosmith;
    }

    public void tick() {
        super.tick();
        this.fallDistance = 0.0f;

        float yaw1 = (float) Minecraft.getInstance().mouseHelper.getMouseX();
        float pitch1 = (float) Minecraft.getInstance().mouseHelper.getMouseY();

        if (pitch1 > 89.0f)
            pitch1 = 89.0f;

        else if (pitch1 < -89.0f)
            pitch1 = -89.0f;

        this.setRotation(yaw1, pitch1);

        if (!this.shouldFall)
            this.setMotion(this.getMotion().getX(), 0, this.getMotion().getZ());

        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            JojoProvider.getLazyOptional(player).ifPresent(props -> this.ability = props.getAbility());

            if (this.ability) {
                if (!Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
                    this.shouldFall = false;

                float yaw = this.rotationYaw;
                float pitch = this.rotationPitch;
                float f = 1.0f;
                double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
                double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
                double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * f);

                //Motion X
                if (Minecraft.getInstance().gameSettings.keyBindRight.isKeyDown() && this.getMotion().getX() < 0.6)
                    this.setVelocity(-motionZ * 0.5, this.getMotion().getY(), motionX * 0.5);

                else if (Minecraft.getInstance().gameSettings.keyBindLeft.isKeyDown() && this.getMotion().getX() > -0.6)
                    this.setVelocity(motionZ * 0.5, this.getMotion().getY(), -motionX * 0.5);

                //Motion Y
                if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown() && this.getMotion().getY() < 0.6)
                    this.addVelocity(0, 0.35, 0);

                else if (Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown() && this.getMotion().getY() > -0.6) {
                    this.shouldFall = true;
                    this.addVelocity(0, -0.2, 0);
                }

                //Motion Z
                if (Minecraft.getInstance().gameSettings.keyBindForward.isKeyDown() && this.getMotion().getZ() < 0.6)
                    if (Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown())
                        this.setVelocity(motionX, motionY, motionZ);
                    else
                        this.setVelocity(motionX * 0.5, this.getMotion().getY(), motionZ * 0.5);

                else if (Minecraft.getInstance().gameSettings.keyBindBack.isKeyDown() && this.getMotion().getZ() > -0.6)
                    this.setVelocity(-motionX * 0.6, this.getMotion().getY(), -motionZ * 0.6);

                //Bomb
                JojoProvider.getLazyOptional(player).ifPresent(props -> {
                    if (props.getAbility()) {
                        if (props.getCooldown() > 0)
                            props.subtractCooldown(1);
                    }
                    if (props.getCooldown() <= 0) {
                        if (KeyHandler.keys[2].isPressed()) {
                            TNTEntity tnt = new TNTEntity(this.world, this.getPosX(), this.getPosY(), this.getPosZ(), player);
                            tnt.setVelocity(this.getLookVec().getX(), this.getLookVec().getY(), this.getLookVec().getZ());
                            this.world.addEntity(tnt);
                            props.setCooldown(200);
                        }
                    }
                });
            } else {
                if (!Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
                    this.shouldFall = false;

                if (KeyHandler.keys[9].isKeyDown())
                    this.setRotation(player.rotationYaw, player.rotationPitch);

                float yaw = this.rotationYaw;
                float pitch = this.rotationPitch;
                float f = 1.0f;
                double motionX = (-MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
                double motionZ = (MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f);
                double motionY = (-MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * f);

                //Motion X
                if (KeyHandler.keys[4].isKeyDown() && this.getMotion().getX() < 0.6)
                    this.setVelocity(-motionZ * 0.5, this.getMotion().getY(), motionX * 0.5);

                else if (KeyHandler.keys[5].isKeyDown() && this.getMotion().getX() > -0.6)
                    this.setVelocity(motionZ * 0.5, this.getMotion().getY(), -motionX * 0.5);

                //Motion Y
                if (KeyHandler.keys[7].isKeyDown() && this.getMotion().getY() < 0.6)
                    this.addVelocity(0, 0.35, 0);

                else if (KeyHandler.keys[8].isKeyDown() && this.getMotion().getY() > -0.6) {
                    this.shouldFall = true;
                    this.addVelocity(0, -0.2, 0);
                }

                //Motion Z
                if (KeyHandler.keys[3].isKeyDown() && this.getMotion().getZ() < 0.6)
                    if (Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown())
                        this.setVelocity(motionX, motionY, motionZ);
                    else
                        this.setVelocity(motionX * 0.5, this.getMotion().getY(), motionZ * 0.5);

                else if (KeyHandler.keys[6].isKeyDown() && this.getMotion().getZ() > -0.6)
                    this.setVelocity(-motionX * 0.6, this.getMotion().getY(), -motionZ * 0.6);
            }

            if (!player.isSprinting()) {
                if (Minecraft.getInstance().gameSettings.keyBindAttack.isPressed()) {
                    this.oratick++;
                    if (oratick == 1) {
                        EntityStandPunch.aerosmith aerosmithBullet = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet.shoot(player, this.rotationPitch, this.rotationYaw, 4.0f, 0.4f);
                        this.world.addEntity(aerosmithBullet);
                    }
                }
            } else if (player.isSprinting() || Minecraft.getInstance().gameSettings.keyBindSprint.isKeyDown()) {
                if (Minecraft.getInstance().gameSettings.keyBindAttack.isPressed())
                    if (player.getFoodStats().getFoodLevel() > 6) {
                        this.world.playSound(null, new BlockPos(this.getPosX(), this.getPosY(), this.getPosZ()), SoundInit.VOLARUSH.get(), getSoundCategory(), 4.05f, 1.0f);
                        this.oratick++;
                        if (this.oratick == 1) {
                            if (!this.world.isRemote)
                                this.orarush = true;
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
                        EntityStandPunch.aerosmith aerosmithBullet1 = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet1.setRandomPositions();
                        aerosmithBullet1.shoot(player, this.rotationPitch, this.rotationYaw, 4.0F, 0.3F);
                        this.world.addEntity(aerosmithBullet1);
                        EntityStandPunch.aerosmith aerosmithBullet2 = new EntityStandPunch.aerosmith(this.world, this, player);
                        aerosmithBullet2.setRandomPositions();
                        aerosmithBullet2.shoot(player, this.rotationPitch, this.rotationYaw, 4.0F, 0.3F);
                        this.world.addEntity(aerosmithBullet2);
                    }
                if (this.oratickr >= 110) {
                    this.orarush = false;
                    this.oratickr = 0;
                }
            }
        }
    }
}
