package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class BeachBoyBobberEntity extends FishingBobberEntity {
    private PlayerEntity master;
    private int act;
    private float yaw;
    private float pitch;

    public BeachBoyBobberEntity(World worldIn, PlayerEntity playerEntity, double x, double y, double z) {
        super(worldIn, playerEntity, x, y, z);
    }

    public BeachBoyBobberEntity(PlayerEntity playerEntity, World world) {
        super(playerEntity, world, 5, 5);
        master = playerEntity;
        act = Stand.getCapabilityFromPlayer(master).getAct();
        float f = master.rotationPitch;
        float f1 = master.rotationYaw;
        float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180) - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180) - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180));
        float f5 = MathHelper.sin(-f * ((float) Math.PI / 180));
        double d0 = master.getPosX() - (double) f3 * 0.3;
        double d1 = master.getPosYEye() + 0.5;
        double d2 = master.getPosZ() - (double) f2 * 0.3;
        this.setLocationAndAngles(d0, d1, d2, f1, f);
        Vec3d vec3d = new Vec3d(-f3, MathHelper.clamp(-(f5 / f4), -5, 5), -f2);
        double d3 = vec3d.length();
        vec3d = vec3d.mul(0.6 / d3 + 0.5 + rand.nextGaussian() * 0.0045, 0.6 / d3 + 0.5 + rand.nextGaussian() * 0.0045, 0.6 / d3 + 0.5 + rand.nextGaussian() * 0.0045);
        setMotion(vec3d.mul(2, 2, 2));
        rotationYaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * (double) (180 / (float) Math.PI));
        rotationPitch = (float) (MathHelper.atan2(vec3d.y, MathHelper.sqrt(horizontalMag(vec3d))) * (double) (180 / (float) Math.PI));
        prevRotationYaw = rotationYaw;
        prevRotationPitch = rotationPitch;
    }

    @Override
    public void tick() {
        if (act == 2 && !world.isRemote) {
            noClip = true;
            LivingEntity target = world.getClosestEntityWithinAABB(LivingEntity.class, new EntityPredicate().setCustomPredicate(entity -> !entity.equals(master) && !(entity instanceof AbstractStandEntity && !entity.equals(master.getRidingEntity())) && entity.isAlive()), null, getPosX(), getPosY(), getPosZ(), new AxisAlignedBB(getPosition().add(20, 20, 20), getPosition().add(-20, -20, -20)));
            if (target == null) return;
            Vec3d vec3d = EntityAnchorArgument.Type.EYES.apply(this);
            double d0 = target.getPositionVec().x - vec3d.x;
            double d1 = target.getPositionVec().y - vec3d.y;
            double d2 = target.getPositionVec().z - vec3d.z;
            double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
            pitch = MathHelper.wrapDegrees((float) (-(MathHelper.atan2(d1, d3) * (double) (180F / (float) Math.PI))));
            yaw = MathHelper.wrapDegrees((float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F);
            Vec3d motion = new Vec3d(
                    -MathHelper.sin(rotationYaw / 180 * (float) Math.PI) * MathHelper.cos(pitch / 180 * (float) Math.PI),
                    MathHelper.cos(yaw / 180 * (float) Math.PI) * MathHelper.cos(pitch / 180 * (float) Math.PI),
                    MathHelper.cos(yaw / 180 * (float) Math.PI) * MathHelper.cos(pitch / 180 * (float) Math.PI)
            ).mul(getDistance(target) < 10 ? new Vec3d(1.1, 1.1, 1.1) : new Vec3d(0.6, 0.6, 0.6));
            setMotion(motion);
            if (target.getDistance(this) < 1.5f)
                caughtEntity = target;
            onGround = false;
            collidedHorizontally = false;
        }
        super.tick();
    }

    @Override
    public void bringInHookedEntity() {
        if (master != null) {
            if (act == 1) {
                caughtEntity.attackEntityFrom(DamageSource.causePlayerDamage(master), 5);
                return;
            }
            Vec3d vec3d = (new Vec3d(master.getPosX() - getPosX(), master.getPosY() - getPosY(), master.getPosZ() - getPosZ())).mul(1.4, 1.4, 1.4).scale(0.1);
            caughtEntity.setMotion(caughtEntity.getMotion().add(vec3d));
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldStopFishing() {
        ItemStack heldItemMainhand = master.getHeldItemMainhand();
        ItemStack heldItemOffhand = master.getHeldItemOffhand();
        boolean flag = heldItemMainhand.getItem() instanceof net.minecraft.item.FishingRodItem;
        boolean flag1 = heldItemOffhand.getItem() instanceof net.minecraft.item.FishingRodItem;
        if (!master.removed && master.isAlive() && (flag || flag1) && !(getDistanceSq(master) > 2048))
            return false;
        else {
            remove();
            return true;
        }
    }

    @Override
    protected void doBlockCollisions() {
    }
}
