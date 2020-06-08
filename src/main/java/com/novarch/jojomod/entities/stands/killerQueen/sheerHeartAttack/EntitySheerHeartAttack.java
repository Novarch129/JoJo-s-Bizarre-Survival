package com.novarch.jojomod.entities.stands.killerQueen.sheerHeartAttack;

import com.novarch.jojomod.ai.goals.SheerHeartAttackSwellGoal;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import com.novarch.jojomod.config.JojoBizarreSurvivalConfig;
import com.novarch.jojomod.entities.stands.goldExperienceRequiem.EntityGoldExperienceRequiem;
import com.novarch.jojomod.entities.stands.killerQueen.EntityKillerQueen;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.ItemInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import java.util.Optional;

/**
 * 99% same as {@link CreeperEntity}, but doesn't extend it due to some private methods getting in the way
 */
public class EntitySheerHeartAttack extends MonsterEntity implements IChargeableMob
{
    private EntityKillerQueen masterStand;
    private PlayerEntity master;
    private static final DataParameter<Integer> STATE = EntityDataManager.createKey(EntitySheerHeartAttack.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> POWERED = EntityDataManager.createKey(EntitySheerHeartAttack.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> IGNITED = EntityDataManager.createKey(EntitySheerHeartAttack.class, DataSerializers.BOOLEAN);
    private int timeSinceIgnited;
    private int fuseTime = 30;
    private int explosionRadius = 3;

    public EntitySheerHeartAttack(EntityType<? extends MonsterEntity> type, World worldIn)
    {
        super(type, worldIn);
    }

    public EntitySheerHeartAttack(World worldIn, EntityKillerQueen killerQueen)
    {
        super(EntityInit.SHEER_HEART_ATTACK.get(), worldIn);
        masterStand = killerQueen;
        if(killerQueen.getMaster() != null)
            master = killerQueen.getMaster();
    }

    public PlayerEntity getMaster() {
        return master;
    }

    public EntityKillerQueen getMasterStand() {
        return masterStand;
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, new SheerHeartAttackSwellGoal(this));
        this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, EntityGoldExperienceRequiem.class, 6.0f, 1.0f, 1.2f));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0f, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtGoal(this, VillagerEntity.class, 8.0f));
        this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, VillagerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
    }

    @Override
    public void tick()
    {
        super.tick();

        if (this.isAlive()) {
            if(masterStand != null) {
                master = masterStand.getMaster();
                if (master != null) {
                    JojoProvider.getLazyOptional(master).ifPresent(props -> {
                        if (!props.getStandOn()) {
                            if (getAttackTarget() == this && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                                remove();
                            else if (getAttackTarget() != this)
                                remove();
                        }
                        else
                            this.setAttackTarget(masterStand.getBombEntity());
                    });
                    if (!master.isAlive() && getAttackTarget() != this) {
                        if (getAttackTarget() == this && !JojoBizarreSurvivalConfig.COMMON.sheerHeartAttackDeathLoop.get())
                            remove();
                        else if (getAttackTarget() != this)
                            remove();
                    }

                }

                if(getAttackTarget() == masterStand || getAttackTarget() == master)
                    setAttackTarget(this);

                Optional<Entity> damageSource = Optional.empty();
                if (this.getLastDamageSource() != null)
                    damageSource = Optional.ofNullable(this.getLastDamageSource().getTrueSource());

                damageSource.ifPresent(damageSourceEntity -> {
                    if (damageSourceEntity instanceof PlayerEntity)
                        JojoProvider.getLazyOptional((PlayerEntity) damageSourceEntity).ifPresent(props -> {
                            if (props.getStandID() == JojoLibs.StandID.GER)
                                remove();
                        });
                });

                if (this.hasIgnited())
                    this.setSheerHeartAttackState(1);

                int i = this.getSheerHeartAttackState();
                if (i > 0 && this.timeSinceIgnited == 0) {
                    this.playSound(SoundInit.LOOK_HERE.get(), 1.0F, 1.0F);
                }

                this.timeSinceIgnited += i;
                if (this.timeSinceIgnited < 0) {
                    this.timeSinceIgnited = 0;
                }

                if (this.timeSinceIgnited >= this.fuseTime) {
                    this.timeSinceIgnited = this.fuseTime;
                    explode();
                }
            }
        }
    }

    @Override
    public float getHealth()
    {
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
    public boolean onLivingFall(float distance, float damageMultiplier) {
        boolean flag = super.onLivingFall(distance, damageMultiplier);
        this.timeSinceIgnited = (int)((float)this.timeSinceIgnited + distance * 1.5F);
        if (this.timeSinceIgnited > this.fuseTime - 5) {
            this.timeSinceIgnited = this.fuseTime - 5;
        }

        return flag;
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(STATE, -1);
        this.dataManager.register(POWERED, false);
        this.dataManager.register(IGNITED, false);
    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
        if (this.dataManager.get(POWERED)) {
            compound.putBoolean("powered", true);
        }

        compound.putShort("Fuse", (short)this.fuseTime);
        compound.putByte("ExplosionRadius", (byte)this.explosionRadius);
        compound.putBoolean("ignited", this.hasIgnited());
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
        this.dataManager.set(POWERED, compound.getBoolean("powered"));
        if (compound.contains("Fuse", 99)) {
            this.fuseTime = compound.getShort("Fuse");
        }

        if (compound.contains("ExplosionRadius", 99)) {
            this.explosionRadius = compound.getByte("ExplosionRadius");
        }

        if (compound.getBoolean("ignited")) {
            this.ignite();
        }

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
            CreeperEntity creeperentity = (CreeperEntity)entity;
            if (creeperentity.ableToCauseSkullDrop()) {
                creeperentity.incrementDroppedSkulls();
                this.entityDropItem(ItemInit.summon_killer_queen.get());
            }
        }

    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        return true;
    }

    @Override
    public boolean isCharged() {
        return this.dataManager.get(POWERED);
    }

    /**
     * Returns the current state of creeper, -1 is idle, 1 is 'in fuse'
     */
    public int getSheerHeartAttackState() {
        return this.dataManager.get(STATE);
    }

    /**
     * Sets the state of creeper, -1 to idle and 1 to be 'in fuse'
     */
    public void setSheerHeartAttackState(int state) {
        this.dataManager.set(STATE, state);
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    @Override
    public void onStruckByLightning(LightningBoltEntity lightningBolt) {
        super.onStruckByLightning(lightningBolt);
        this.dataManager.set(POWERED, true);
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
        } else {
            return super.processInteract(player, hand);
        }
    }

    /**
     * Creates an explosion as determined by this creeper's power and explosion radius.
     */
    private void explode() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isCharged() ? 2.0F : 1.0F;
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionRadius * f * 3, explosion$mode);
        }

    }

    /**
     * Creates an enormous explosion and kills the entity, fired on {@link net.minecraftforge.event.entity.EntityStruckByLightningEvent}.
     */
    private void destroy() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isCharged() ? 2.0F : 1.0F;
            this.dead = true;
            this.world.createExplosion(this, this.getPosX(), this.getPosY(), this.getPosZ(), (float)this.explosionRadius * f * 100, explosion$mode);
            remove();
        }

    }

    public boolean hasIgnited() {
        return this.dataManager.get(IGNITED);
    }

    public void ignite() {
        this.dataManager.set(IGNITED, true);
    }
}
