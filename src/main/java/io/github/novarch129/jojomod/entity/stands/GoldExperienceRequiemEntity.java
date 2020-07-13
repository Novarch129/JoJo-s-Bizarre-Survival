package io.github.novarch129.jojomod.entity.stands;

import io.github.novarch129.jojomod.entity.stands.attacks.GoldExperienceRequiemPunchEntity;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("ConstantConditions")
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GoldExperienceRequiemEntity extends AbstractStandEntity {
    private StringTextComponent truthname = new StringTextComponent("You will never reach the truth.");
    private boolean truth;

    public GoldExperienceRequiemEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_GER.get();
    }

    public GoldExperienceRequiemEntity(World world) {
        super(EntityInit.GOLD_EXPERIENCE_REQUIEM.get(), world);
        spawnSound = SoundInit.SPAWN_GER.get();
    }

    public void toggleFlight() {
        if (getMaster() != null)
            getMaster().setNoGravity(!getMaster().hasNoGravity());
    }

    public void toggleTruth() {
        truth = !truth;
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.MUDAGIORNO.get(), SoundCategory.NEUTRAL, 1, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                GoldExperienceRequiemPunchEntity goldExperienceRequiemPunchEntity = new GoldExperienceRequiemPunchEntity(world, this, getMaster());
                goldExperienceRequiemPunchEntity.shoot(getMaster(), rotationPitch, rotationYaw, 5, Float.MIN_VALUE);
                world.addEntity(goldExperienceRequiemPunchEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                ability = props.getAbility();

                if (props.getTransformed() > 1) {
                    props.subtractCooldown(1);
                }
                if (props.getCooldown() <= 0) {
                    props.setTransformed(0);
                    props.setCooldown(60);
                }
                player.getFoodStats().addStats(20, 20.0f);

                if (ability) {
                    if (player.getLastAttackedEntity() != null) {
                        if (truth)
                            player.getLastAttackedEntity().attackEntityFrom(DamageSource.OUT_OF_WORLD, 3.0f);

                        if (player.getLastAttackedEntity() instanceof PlayerEntity) {
                            props.setDiavolo(player.getLastAttackedEntity().getDisplayName().toString());
                        }
                    }
                    for (PlayerEntity playerEntity : world.getPlayers()) {
                        if (playerEntity != getMaster()) {
                            if (playerEntity.getLastAttackedEntity() == getMaster()) {
                                props.setDiavolo(playerEntity.getDisplayName().toString());
                            }
                        }
                    }

                    if (props.getDiavolo() != null && !props.getDiavolo().equals("")) {
                        for (PlayerEntity playerEntity : world.getPlayers()) {
                            if (playerEntity != getMaster()) {
                                if (playerEntity.getDisplayName().toString().equals(props.getDiavolo())) {
                                    if (playerEntity.isAlive()) {
                                        world.getServer().getWorld(dimension).getEntities()
                                                .filter(entity -> entity instanceof MobEntity)
                                                .forEach(entity -> ((MobEntity) entity).setAttackTarget(playerEntity));
                                        CreeperEntity truth = new CreeperEntity(EntityType.CREEPER, playerEntity.world);
                                        truth.setCustomName(truthname);
                                        truth.setPosition(playerEntity.getPosX(), playerEntity.getPosY(), playerEntity.getPosZ());
                                        truth.setAttackTarget(playerEntity);
                                        truth.setDropChance(EquipmentSlotType.MAINHAND, 0.0f);
                                        playerEntity.world.addEntity(truth);
                                        truth.setAttackTarget(playerEntity);
                                    } else {
                                        if (!world.isRemote) {
                                            world.getServer().getWorld(dimension).getEntities()
                                                    .filter(entity -> entity instanceof CreeperEntity)
                                                    .filter(entity -> entity.getCustomName().equals(truthname))
                                                    .forEach(Entity::remove);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });

            followMaster();
            setRotationYawHead(player.rotationYaw);
            setRotation(player.rotationYaw, player.rotationPitch);

            if (player.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                player.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        player.setSprinting(false);
                        GoldExperienceRequiemPunchEntity goldExperienceRequiem1 = new GoldExperienceRequiemPunchEntity(world, this, player);
                        goldExperienceRequiem1.setRandomPositions();
                        goldExperienceRequiem1.shoot(player, player.rotationPitch, player.rotationYaw, 4, Float.MIN_VALUE);
                        world.addEntity(goldExperienceRequiem1);
                        GoldExperienceRequiemPunchEntity goldExperienceRequiem2 = new GoldExperienceRequiemPunchEntity(world, this, player);
                        goldExperienceRequiem2.setRandomPositions();
                        goldExperienceRequiem2.shoot(player, player.rotationPitch, player.rotationYaw, 4, Float.MIN_VALUE);
                        world.addEntity(goldExperienceRequiem2);
                    }
                if (attackTicker >= 110) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
