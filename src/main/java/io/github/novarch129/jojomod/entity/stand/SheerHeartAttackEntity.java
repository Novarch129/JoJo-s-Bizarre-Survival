package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
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

import java.util.Optional;

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
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new CreeperSwellGoal(this));
        this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, GoldExperienceRequiemEntity.class, 6.0f, 1.0f, 1.2f));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0f, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtGoal(this, VillagerEntity.class, 8.0f));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.isAlive()) {
            if (masterStand != null) {
                master = masterStand.getMaster();
                if (master != null) {
                    Stand.getLazyOptional(master).ifPresent(props -> {
                        if (!props.getStandOn()) {
                            if (getAttackTarget() == this && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                                remove();
                            else if (getAttackTarget() != this)
                                remove();
                        } else
                            this.setAttackTarget(masterStand.getBombEntity());
                    });
                    if (!master.isAlive() && getAttackTarget() != this) {
                        if (getAttackTarget() == this && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                            remove();
                        else if (getAttackTarget() != this)
                            remove();
                    }

                }

                if (getAttackTarget() == masterStand || getAttackTarget() == master)
                    setAttackTarget(this);

                Optional<Entity> damageSource = Optional.empty();
                if (this.getLastDamageSource() != null)
                    damageSource = Optional.ofNullable(this.getLastDamageSource().getTrueSource());

                damageSource.ifPresent(damageSourceEntity -> {
                    if (damageSourceEntity instanceof PlayerEntity)
                        Stand.getLazyOptional((PlayerEntity) damageSourceEntity).ifPresent(props -> {
                            if (props.getStandID() == Util.StandID.GER)
                                remove();
                        });
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
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
    }

    /**
     * The maximum height from where the entity is alowed to jump (used in pathfinder)
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
                this.entityDropItem(ItemInit.SUMMON_KILLER_QUEEN.get());
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
            this.world.playSound(player, this.getPosX(), this.getPosY(), this.getPosZ(), SoundEvents.ITEM_FLINTANDSTEEL_USE, this.getSoundCategory(), 1.0F, this.rand.nextFloat() * 0.4F + 0.8F);
            if (!this.world.isRemote) {
                explode();
                itemstack.damageItem(1, player, (p_213625_1_) -> p_213625_1_.sendBreakAnimation(hand));
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
            float f = isCharged() ? 2.0F : 1.0F;
            this.world.createExplosion(this, getPosX(), getPosY(), getPosZ(), (float) explosionRadius * f * 3, explosion$mode);
        }

    }

    /**
     * Creates an enormous explosion and kills the entity, fired on {@link net.minecraftforge.event.entity.EntityStruckByLightningEvent}.
     */
    private void destroy() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isCharged() ? 2.0F : 1.0F;
            world.createExplosion(this, getPosX(), getPosY(), getPosZ(), (float) explosionRadius * f * 100, explosion$mode);
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
