package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.ItemInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * 99% same as {@link CreeperEntity}, had to add some ATs to extend it.
 */
@SuppressWarnings("ConstantConditions")
public class SheerHeartAttackEntity extends CreeperEntity {
    private KillerQueenEntity masterStand;
    private PlayerEntity master;

    public SheerHeartAttackEntity(EntityType<? extends CreeperEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public SheerHeartAttackEntity(World worldIn, KillerQueenEntity killerQueen) {
        super(EntityInit.SHEER_HEART_ATTACK.get(), worldIn);
        masterStand = killerQueen;
        if (killerQueen.getMaster() != null)
            master = killerQueen.getMaster();
    }

    public PlayerEntity getMaster() {
        return master;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(2, new CreeperSwellGoal(this));
        goalSelector.addGoal(11, new AvoidEntityGoal<>(this, GoldExperienceRequiemEntity.class, 6, 1, 1.2f));
        goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, false));
        goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8));
        goalSelector.addGoal(6, new LookAtGoal(this, VillagerEntity.class, 8));
        goalSelector.addGoal(6, new LookRandomlyGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {
            if (masterStand != null) {
                master = masterStand.getMaster();
                if (master != null) {
                    if (getAttackTarget() == null) return;
                    Stand.getLazyOptional(master).ifPresent(props -> {
                        if (!props.getStandOn()) {
                            if (getAttackTarget().equals(this) && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                                remove();
                            else if (!getAttackTarget().equals(this))
                                remove();
                        } else
                            setAttackTarget(masterStand.bombEntity);
                    });
                    if (!master.isAlive() && !getAttackTarget().equals(this)) {
                        if (getAttackTarget() == this && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                            remove();
                        else if (!getAttackTarget().equals(this))
                            remove();
                    }

                }

                if (getAttackTarget() != null && getAttackTarget().equals(masterStand) || getAttackTarget().equals(master))
                    setAttackTarget(this);

                if (getLastDamageSource() != null && getLastDamageSource().getTrueSource() instanceof PlayerEntity)
                    Stand.getLazyOptional((PlayerEntity) getLastDamageSource().getTrueSource()).ifPresent(props -> {
                        if (props.getStandID() == Util.StandID.GER)
                            remove();
                    });
            }
        }
    }

    @Override
    public float getHealth() {
        return Float.POSITIVE_INFINITY;
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
    }

    /**
     * The maximum height from where the entity is allowed to jump (used in pathfinder)
     */
    @Override
    public int getMaxFallHeight() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundInit.LOOK_HERE.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.LOOK_HERE.get();
    }

    @Override
    protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropSpecialItems(source, looting, recentlyHitIn);
        Entity entity = source.getTrueSource();
        if (entity != this && entity instanceof CreeperEntity) {
            CreeperEntity creeperentity = (CreeperEntity) entity;
            if (creeperentity.ableToCauseSkullDrop()) {
                creeperentity.incrementDroppedSkulls();
                entityDropItem(ItemInit.SUMMON_KILLER_QUEEN.get());
            }
        }

    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    @Override
    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
        super.onStruckByLightning(lightningBolt);
        destroy();
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        ItemStack itemstack = player.getHeldItem(hand);
        if (itemstack.getItem() == Items.FLINT_AND_STEEL) {
            this.world.playSound(player, getPosX(), getPosY(), getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, getSoundCategory(), 1, rand.nextFloat() * 0.4f + 0.8f);
            if (!world.isRemote) {
                explode();
                itemstack.damageItem(1, player, (playerEntity) -> playerEntity.sendBreakAnimation(hand));
            }
            return true;
        } else
            return super.processInteract(player, hand);
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    @Override
    public void explode() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float multiplier = isCharged() ? 2 : 1;
            world.createExplosion(this, getPosX(), getPosY(), getPosZ(), (float) explosionRadius * multiplier * 3, explosion$mode);
        }

    }

    /**
     * Creates an enormous explosion and kills the entity, fired on {@link net.minecraftforge.event.entity.EntityStruckByLightningEvent}.
     */
    private void destroy() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float multiplier = this.isCharged() ? 2 : 1;
            world.createExplosion(this, getPosX(), getPosY(), getPosZ(), (float) explosionRadius * multiplier * 100, explosion$mode);
            remove();
        }
    }

    @Override
    public void remove() {
        super.remove();
        if (masterStand != null)
            masterStand.shaCount--;
    }
}
