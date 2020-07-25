package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.effect.CrimsonEffect;
import io.github.novarch129.jojomod.effect.CrimsonEffectUser;
import io.github.novarch129.jojomod.entity.stand.attack.KingCrimsonPunchEntity;
import io.github.novarch129.jojomod.init.EffectInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.GameType;
import net.minecraft.world.World;

/**
 * You shouldn't be surprised if you're confused by this code, even I can barely read it now.
 */
@SuppressWarnings("ConstantConditions")
public class KingCrimsonEntity extends AbstractStandEntity {
    public KingCrimsonEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_KING_CRIMSON.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        if (!ability || !Stand.getCapabilityFromPlayer(getMaster()).getAbility()) {
            attackTick++;
            if (attackTick == 1)
                if (special)
                    attackRush = true;
                else {
                    world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                    KingCrimsonPunchEntity kingCrimsonPunchEntity = new KingCrimsonPunchEntity(world, this, getMaster());
                    kingCrimsonPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 3, 0.05f);
                    world.addEntity(kingCrimsonPunchEntity);
                }
        }
    }

    /**
     * Gets all entities in the {@link net.minecraft.world.server.ServerWorld} using {@link net.minecraft.world.server.ServerWorld}#getAllEntities,
     * then applies the {@link CrimsonEffect} to them to make them glow.
     * Also applies the {@link CrimsonEffectUser} to it's user, impairing his vision.
     */
    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {

            followMaster();
            setRotationYawHead(master.rotationYaw);
            setRotation(master.rotationYaw, master.rotationPitch);

            master.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 40, 2));
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                props.setAbilityActive(!(props.getCooldown() > 0));

                if (!props.getAbility()) {
                    if (!master.isCreative() && !master.isSpectator())
                        master.setGameType(GameType.SURVIVAL);
                    master.setInvulnerable(false);
                }

                if (props.getAbilityActive() && props.getAbility() && props.getStandOn()) {
                    if (props.getTimeLeft() > 800) {
                        attackRush = false;
                        getMaster().setInvulnerable(true);
                        master.addPotionEffect(new EffectInstance(EffectInit.CRIMSON_USER.get(), 10000, 255));
                        if (!master.isCreative() && !master.isSpectator())
                            master.setGameType(GameType.ADVENTURE);
                        props.subtractTimeLeft(1);

                        if (!world.isRemote) //Pretty much equal to world instanceof ServerWorld
                            getServer().getWorld(dimension).getEntities()
                                    .filter(entity -> entity instanceof LivingEntity)
                                    .filter(entity -> !(entity instanceof GoldExperienceEntity))
                                    .filter(entity -> entity != this)
                                    .filter(Entity::isAlive)
                                    .forEach(entity -> {
                                        if (entity instanceof MobEntity) {
                                            if (((MobEntity) entity).getAttackTarget() == master || ((MobEntity) entity).getRevengeTarget() == master) {
                                                ((MobEntity) entity).setAttackTarget(null);
                                                ((MobEntity) entity).setRevengeTarget(null);
                                            }
                                            ((MobEntity) entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
                                        }

                                        if (entity instanceof PlayerEntity)
                                            Stand.getLazyOptional((PlayerEntity) entity).ifPresent(prs -> {
                                                if (entity != master && prs.getStandID() != Util.StandID.GER) {
                                                    if (prs.getStandID() == Util.StandID.KING_CRIMSON && prs.getStandOn() && prs.getAbility() && prs.getTimeLeft() > 800)
                                                        return;
                                                    ((PlayerEntity) entity).addPotionEffect(new EffectInstance(EffectInit.CRIMSON.get(), 200, 255));
                                                }
                                            });
                                    });
                    } else {
                        if (!master.isCreative() && !master.isSpectator())
                            master.setGameType(GameType.SURVIVAL);
                        master.setInvulnerable(false);
                        props.setAbilityActive(false);
                        master.removePotionEffect(EffectInit.CRIMSON_USER.get());
                        props.setCooldown(200);
                    }
                }

                if (!props.getAbilityActive()) {
                    if (props.getCooldown() <= 0) {
                        props.setTimeLeft(1000);
                        props.setAbilityActive(true);
                    }
                }

                if (!props.getAbility()) {
                    if (master.isPotionActive(EffectInit.CRIMSON_USER.get()))
                        master.removePotionEffect(EffectInit.CRIMSON_USER.get());
                    if (props.getTimeLeft() < 1000)
                        props.addTimeLeft(1);
                }
                if (master.swingProgressInt == 0 && !attackRush)
                    attackTick = 0;
                if (attackRush) {
                    attackTicker++;
                    if (attackTicker >= 10)
                        if (!world.isRemote) {
                            master.setSprinting(false);
                            KingCrimsonPunchEntity kingCrimson1 = new KingCrimsonPunchEntity(world, this, master);
                            kingCrimson1.setRandomPositions();
                            kingCrimson1.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.2f);
                            world.addEntity(kingCrimson1);
                            KingCrimsonPunchEntity kingCrimson2 = new KingCrimsonPunchEntity(world, this, master);
                            kingCrimson2.setRandomPositions();
                            kingCrimson2.shoot(master, master.rotationPitch, master.rotationYaw, 2.5f, 0.2f);
                            world.addEntity(kingCrimson2);
                        }
                    if (attackTicker >= 80) {
                        attackRush = false;
                        attackTicker = 0;
                    }
                }
            });
        }
    }
}
