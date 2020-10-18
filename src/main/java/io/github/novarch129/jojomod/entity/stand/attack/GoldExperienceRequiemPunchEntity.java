package io.github.novarch129.jojomod.entity.stand.attack;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.entity.stand.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class GoldExperienceRequiemPunchEntity extends AbstractStandAttackEntity {
    public GoldExperienceRequiemPunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public GoldExperienceRequiemPunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.GOLD_EXPERIENCE_REQUIEM_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksExisted > 5)
            remove();
        if (getMotion().getX() == 0 || getMotion().getY() == 0 || getMotion().getZ() == 0)
            remove();
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (shootingStand.ability) {
            if (entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
                ((PlayerEntity) entity).jump();
            } else
                entity.remove();
        } else
            entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 10);
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (shootingStand.ability) {
            Stand.getLazyOptional(standMaster).ifPresent(props -> {
                if (shootingStand.ability && block != Blocks.AIR) {
                    if (props.getTransformed() < 2) {
                        int transformed = props.getTransformed();
                        Entity entity1 = null, entity2 = null;
                        if (block == Blocks.GRASS || block == Blocks.GRASS_BLOCK || block == Blocks.NETHERRACK) {
                            props.addTransformed(1);
                            entity1 = new BatEntity(EntityType.BAT, world);
                            entity2 = new BatEntity(EntityType.BAT, world);
                        }
                        if (block == Blocks.JUNGLE_LEAVES) {
                            props.addTransformed(1);
                            entity1 = new ParrotEntity(EntityType.PARROT, world);
                            entity2 = new ParrotEntity(EntityType.PARROT, world);
                        }
                        if (block == Blocks.TNT) {
                            props.addTransformed(1);
                            entity1 = new CreeperEntity(EntityType.CREEPER, world);
                            entity2 = new CreeperEntity(EntityType.CREEPER, world);
                        }
                        if (block == Blocks.SLIME_BLOCK) {
                            props.addTransformed(1);
                            entity1 = new SlimeEntity(EntityType.SLIME, world);
                            entity2 = new SlimeEntity(EntityType.SLIME, world);
                        }
                        if (block == Blocks.STONE) {
                            props.addTransformed(1);
                            entity1 = new SilverfishEntity(EntityType.SILVERFISH, world);
                            entity2 = new SilverfishEntity(EntityType.SILVERFISH, world);
                        }
                        if (block == Blocks.POTATOES || block == Blocks.OAK_LOG) {
                            props.addTransformed(1);
                            entity1 = new PigEntity(EntityType.PIG, world);
                            entity2 = new PigEntity(EntityType.PIG, world);
                        }
                        if (block == Blocks.HAY_BLOCK) {
                            props.addTransformed(1);
                            entity1 = new HorseEntity(EntityType.HORSE, world);
                            entity2 = new HorseEntity(EntityType.HORSE, world);
                        }
                        if (block == Blocks.MYCELIUM) {
                            props.addTransformed(1);
                            entity1 = new MooshroomEntity(EntityType.MOOSHROOM, world);
                            entity2 = new MooshroomEntity(EntityType.MOOSHROOM, world);
                        }
                        if (block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
                            props.addTransformed(1);
                            entity1 = new VillagerEntity(EntityType.VILLAGER, world);
                            entity2 = new VillagerEntity(EntityType.VILLAGER, world);
                        }
                        if (block == Blocks.MOSSY_COBBLESTONE) {
                            props.addTransformed(1);
                            entity1 = new ZombieEntity(EntityType.ZOMBIE, world);
                            entity2 = new ZombieEntity(EntityType.ZOMBIE, world);
                        }
                        if (block == Blocks.BONE_BLOCK || block == Blocks.BOOKSHELF) {
                            props.addTransformed(1);
                            entity1 = new SkeletonEntity(EntityType.SKELETON, world);
                            entity2 = new SkeletonEntity(EntityType.SKELETON, world);
                        }
                        if (block == Blocks.VINE) {
                            props.addTransformed(1);
                            entity1 = new SpiderEntity(EntityType.SPIDER, world);
                            entity2 = new SpiderEntity(EntityType.SPIDER, world);
                        }
                        if (block == Blocks.WHITE_WOOL || block == Blocks.WHEAT) {
                            props.addTransformed(1);
                            entity1 = new SheepEntity(EntityType.SHEEP, world);
                            entity2 = new SheepEntity(EntityType.SHEEP, world);
                        }
                        if (block == Blocks.BEETROOTS) {
                            props.addTransformed(1);
                            entity1 = new CowEntity(EntityType.COW, world);
                            entity2 = new CowEntity(EntityType.COW, world);
                        }
                        if (block == Blocks.CARROTS) {
                            props.addTransformed(1);
                            entity1 = new RabbitEntity(EntityType.RABBIT, world);
                            entity2 = new RabbitEntity(EntityType.RABBIT, world);
                        }
                        if (block == Blocks.FIRE) {
                            props.addTransformed(1);
                            entity1 = new BlazeEntity(EntityType.BLAZE, world);
                            entity2 = new BlazeEntity(EntityType.BLAZE, world);
                        }
                        if (block == Blocks.MAGMA_BLOCK) {
                            props.addTransformed(1);
                            entity1 = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
                            entity2 = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
                        }
                        if (block == Blocks.NETHER_BRICKS || block == Blocks.NETHER_WART || block == Blocks.NETHER_WART_BLOCK) {
                            props.addTransformed(1);
                            entity1 = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, world);
                            entity2 = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, world);
                        }
                        if (block == Blocks.BLACK_SHULKER_BOX || block == Blocks.BLUE_SHULKER_BOX || block == Blocks.BROWN_SHULKER_BOX || block == Blocks.CYAN_SHULKER_BOX || block == Blocks.GRAY_SHULKER_BOX || block == Blocks.GREEN_SHULKER_BOX || block == Blocks.LIGHT_BLUE_SHULKER_BOX || block == Blocks.LIME_SHULKER_BOX || block == Blocks.MAGENTA_SHULKER_BOX || block == Blocks.ORANGE_SHULKER_BOX || block == Blocks.PINK_SHULKER_BOX || block == Blocks.PURPLE_SHULKER_BOX || block == Blocks.RED_SHULKER_BOX || block == Blocks.WHITE_SHULKER_BOX || block == Blocks.YELLOW_SHULKER_BOX) {
                            props.addTransformed(1);
                            entity1 = new ShulkerEntity(EntityType.SHULKER, world);
                            entity2 = new ShulkerEntity(EntityType.SHULKER, world);
                        }
                        if (block == Blocks.SAND || block == Blocks.SANDSTONE) {
                            props.addTransformed(1);
                            entity1 = new HuskEntity(EntityType.HUSK, world);
                            entity2 = new HuskEntity(EntityType.HUSK, world);
                        }
                        if (block == Blocks.BEACON) {
                            props.addTransformed(2);
                            entity1 = new WitherEntity(EntityType.WITHER, world);
                        }
                        if (block == Blocks.SPONGE || block == Blocks.WET_SPONGE) {
                            props.addTransformed(2);
                            entity1 = new ElderGuardianEntity(EntityType.ELDER_GUARDIAN, world);
                        }
                        if (block == Blocks.WITHER_SKELETON_SKULL || block == Blocks.WITHER_SKELETON_WALL_SKULL) {
                            props.addTransformed(1);
                            entity1 = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
                            entity2 = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
                        }
                        if (block == Blocks.SEA_PICKLE) {
                            props.addTransformed(1);
                            entity1 = new SalmonEntity(EntityType.SALMON, world);
                            entity2 = new SalmonEntity(EntityType.SALMON, world);
                        }
                        if (block == Blocks.SEA_LANTERN || block == Blocks.PRISMARINE) {
                            props.addTransformed(1);
                            entity1 = new GuardianEntity(EntityType.GUARDIAN, world);
                            entity2 = new GuardianEntity(EntityType.GUARDIAN, world);
                        }
                        if (block == Blocks.DIAMOND_BLOCK) {
                            props.addTransformed(1);
                            entity1 = new DolphinEntity(EntityType.DOLPHIN, world);
                            entity2 = new DolphinEntity(EntityType.DOLPHIN, world);
                        }
                        if (block == Blocks.TURTLE_EGG) {
                            props.addTransformed(1);
                            entity1 = new TurtleEntity(EntityType.TURTLE, world);
                            entity2 = new TurtleEntity(EntityType.TURTLE, world);
                            entity1.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
                            entity2.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
                        }
                        if (ticksExisted > 60)
                            remove();
                        if (props.getTransformed() != transformed) {
                            if (entity1 != null) {
                                entity1.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                                world.addEntity(entity1);
                            }
                            if (entity2 != null) {
                                entity2.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                                world.addEntity(entity2);
                            }
                            world.playSound(null, getPosX(), getPosY(), getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
                            world.removeBlock(pos, false);
                        }
                        props.setCooldown(60);
                    }
                }
            });
        }
        world.removeBlock(pos, false);
        block.harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
    }

    @Override
    public ResourceLocation getEntityTexture() {
        return Util.ResourceLocations.GER_PUNCH;
    }
}
