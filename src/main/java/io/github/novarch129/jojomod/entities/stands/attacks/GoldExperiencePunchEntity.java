package io.github.novarch129.jojomod.entities.stands.attacks;

import io.github.novarch129.jojomod.capabilities.stand.Stand;
import io.github.novarch129.jojomod.entities.stands.AbstractStandEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class GoldExperiencePunchEntity extends AbstractStandAttackEntity {
    public GoldExperiencePunchEntity(EntityType<? extends AbstractStandAttackEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public GoldExperiencePunchEntity(World worldIn, AbstractStandEntity shooter, PlayerEntity player) {
        super(EntityInit.GOLD_EXPERIENCE_PUNCH.get(), worldIn, shooter, player);
    }

    @Override
    protected void onEntityHit(EntityRayTraceResult result) {
        Entity entity = result.getEntity();
        if (shootingStand.ability && entity instanceof LivingEntity) {
            ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 1, 2));
            if (entity instanceof PlayerEntity) {
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 250, 0));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 250, 2));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.NAUSEA, 250, 1));
                ((PlayerEntity) entity).addPotionEffect(new EffectInstance(Effects.WEAKNESS, 250, 1));
                ((PlayerEntity) entity).jump();
            }
        } else
            entity.attackEntityFrom(DamageSource.causeMobDamage(standMaster), 1.5f);
        entity.hurtResistantTime = 0;
    }

    @Override
    protected void onBlockHit(BlockRayTraceResult result) {
        BlockPos pos = result.getPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Stand.getLazyOptional(standMaster).ifPresent(props -> {
            if (shootingStand.ability && block != Blocks.AIR) {
                if (state.getBlockHardness(world, pos) != -1) {
                    if (props.getTransformed() == 0) {
                        int transformed = props.getTransformed();
                        Entity entity = null;
                        if (block == Blocks.GRASS || block == Blocks.GRASS_BLOCK || block == Blocks.NETHERRACK) {
                            props.addTransformed(1);
                            entity = new BatEntity(EntityType.BAT, world);
                        }
                        if (block == Blocks.JUNGLE_LEAVES) {
                            props.addTransformed(1);
                            entity = new ParrotEntity(EntityType.PARROT, world);
                        }
                        if (block == Blocks.TNT) {
                            props.addTransformed(1);
                            entity = new CreeperEntity(EntityType.CREEPER, world);
                        }
                        if (block == Blocks.SLIME_BLOCK) {
                            props.addTransformed(1);
                            entity = new SlimeEntity(EntityType.SLIME, world);
                        }
                        if (block == Blocks.STONE) {
                            props.addTransformed(1);
                            entity = new SilverfishEntity(EntityType.SILVERFISH, world);
                        }
                        if (block == Blocks.POTATOES || block == Blocks.OAK_LOG) {
                            props.addTransformed(1);
                            entity = new PigEntity(EntityType.PIG, world);
                        }
                        if (block == Blocks.HAY_BLOCK) {
                            props.addTransformed(1);
                            entity = new HorseEntity(EntityType.HORSE, world);
                        }
                        if (block == Blocks.MYCELIUM) {
                            props.addTransformed(1);
                            entity = new MooshroomEntity(EntityType.MOOSHROOM, world);
                        }
                        if (block == Blocks.EMERALD_BLOCK || block == Blocks.EMERALD_ORE) {
                            props.addTransformed(1);
                            entity = new VillagerEntity(EntityType.VILLAGER, world);
                        }
                        if (block == Blocks.MOSSY_COBBLESTONE) {
                            props.addTransformed(1);
                            entity = new ZombieEntity(EntityType.ZOMBIE, world);
                        }
                        if (block == Blocks.BONE_BLOCK || block == Blocks.BOOKSHELF) {
                            props.addTransformed(1);
                            entity = new SkeletonEntity(EntityType.SKELETON, world);
                        }
                        if (block == Blocks.VINE) {
                            props.addTransformed(1);
                            entity = new SpiderEntity(EntityType.SPIDER, world);
                        }
                        if (block == Blocks.WHITE_WOOL || block == Blocks.WHEAT) {
                            props.addTransformed(1);
                            entity = new SheepEntity(EntityType.SHEEP, world);
                        }
                        if (block == Blocks.BEETROOTS) {
                            props.addTransformed(1);
                            entity = new CowEntity(EntityType.COW, world);
                        }
                        if (block == Blocks.CARROTS) {
                            props.addTransformed(1);
                            entity = new RabbitEntity(EntityType.RABBIT, world);
                        }
                        if (block == Blocks.FIRE) {
                            props.addTransformed(1);
                            entity = new BlazeEntity(EntityType.BLAZE, world);
                        }
                        if (block == Blocks.MAGMA_BLOCK) {
                            props.addTransformed(1);
                            entity = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
                        }
                        if (block == Blocks.NETHER_BRICKS || block == Blocks.NETHER_WART || block == Blocks.NETHER_WART_BLOCK) {
                            props.addTransformed(1);
                            entity = new ZombiePigmanEntity(EntityType.ZOMBIE_PIGMAN, world);
                        }
                        if (block == Blocks.BLACK_SHULKER_BOX || block == Blocks.BLUE_SHULKER_BOX || block == Blocks.BROWN_SHULKER_BOX || block == Blocks.CYAN_SHULKER_BOX || block == Blocks.GRAY_SHULKER_BOX || block == Blocks.GREEN_SHULKER_BOX || block == Blocks.LIGHT_BLUE_SHULKER_BOX || block == Blocks.LIME_SHULKER_BOX || block == Blocks.MAGENTA_SHULKER_BOX || block == Blocks.ORANGE_SHULKER_BOX || block == Blocks.PINK_SHULKER_BOX || block == Blocks.PURPLE_SHULKER_BOX || block == Blocks.RED_SHULKER_BOX || block == Blocks.WHITE_SHULKER_BOX || block == Blocks.YELLOW_SHULKER_BOX) {
                            props.addTransformed(1);
                            entity = new ShulkerEntity(EntityType.SHULKER, world);
                        }
                        if (block == Blocks.SAND || block == Blocks.SANDSTONE) {
                            props.addTransformed(1);
                            entity = new HuskEntity(EntityType.HUSK, world);
                        }
                        if (block == Blocks.SEA_PICKLE) {
                            props.addTransformed(1);
                            entity = new SalmonEntity(EntityType.SALMON, world);
                        }
                        if (block == Blocks.SEA_LANTERN || block == Blocks.PRISMARINE) {
                            props.addTransformed(1);
                            entity = new GuardianEntity(EntityType.GUARDIAN, world);
                        }
                        if (block == Blocks.DIAMOND_BLOCK) {
                            props.addTransformed(1);
                            entity = new DolphinEntity(EntityType.DOLPHIN, world);
                        }
                        if (block == Blocks.TURTLE_EGG) {
                            props.addTransformed(1);
                            entity = new TurtleEntity(EntityType.TURTLE, world);
                            entity.setCustomName(new StringTextComponent("Jean Pierre Polnareff"));
                        }
                        if (props.getTransformed() != transformed) {
                            if (entity != null) {
                                entity.setPosition(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
                                world.addEntity(entity);
                            }
                            world.playSound(null, getPosX(), getPosY(), getPosZ(), SoundInit.TRANSMUTE.get(), SoundCategory.NEUTRAL, 4.0f, 1.0f);
                            world.removeBlock(pos, false);
                            if (!standMaster.isCreative())
                                standMaster.getFoodStats().addStats(-2, 0.0f);
                        }
                        props.setCooldown(80);
                    }
                }
            }
        });
        if (state.getBlockHardness(world, pos) != -1 && state.getBlockHardness(world, pos) < 3) {
            world.removeBlock(pos, false);
            state.getBlock().harvestBlock(world, standMaster, pos, state, null, standMaster.getActiveItemStack());
        }
    }
}
