package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.capability.StandEffects;
import io.github.novarch129.jojomod.entity.stand.attack.EchoesAct3PunchEntity;
import io.github.novarch129.jojomod.entity.stand.attack.EchoesSoundEntity;
import io.github.novarch129.jojomod.init.EntityInit;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class EchoesAct3Entity extends AbstractStandEntity {
    public EchoesAct3Entity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_ECHOES_ACT_3.get();
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1) {
            if (special)
                attackRush = true;
            else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                EchoesSoundEntity echoesSoundEntity = new EchoesSoundEntity(world, this, getMaster());
                echoesSoundEntity.shoot(getMaster(), rotationPitch, rotationYaw, 1.5f, 0.2f);
                world.addEntity(echoesSoundEntity);
            }
        }
    }

    public void barrier() {
        if (getMaster() == null) return;
        Stand.getLazyOptional(master).ifPresent(props -> {
            if (props.getCooldown() == 0) {
                world.addOptionalParticle(ParticleTypes.EXPLOSION, master.getPosX(), master.getPosY(), master.getPosZ(), 0, 0.1, 0);
                if (!world.isRemote) {
                    getServer().getWorld(dimension).getEntities()
                            .filter(entity -> entity instanceof LivingEntity && entity.isAlive())
                            .filter(entity -> !entity.equals(this) && !entity.equals(master))
                            .filter(entity -> entity.getDistance(this) < 7)
                            .forEach(entity -> {
                                ((LivingEntity) entity).knockBack(master, 3, master.getPosX() - entity.getPosX(), master.getPosZ() - entity.getPosZ());
                                if (!entity.isCrouching())
                                    StandEffects.getLazyOptional(entity).ifPresent(standEffects -> {
                                        standEffects.setThreeFreeze(true);
                                        standEffects.setStandUser(master.getUniqueID());
                                    });
                                ((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.RESISTANCE, 5, 100, false, false));
                                world.createExplosion(master, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 1, Explosion.Mode.DESTROY);
                                if (!master.isCreative()) {
                                    master.setHealth(master.getHealth() - (master.getMaxHealth() / 4));
                                    master.attackEntityFrom(DamageSource.causeMobDamage(this), 0.001f);
                                }
                                props.setCooldown(600);
                            });
                    if (getServer().getWorld(dimension).getEntities().filter(entity -> entity instanceof LivingEntity && entity.isAlive()).filter(entity -> !entity.equals(this) && !entity.equals(master)).anyMatch(entity -> entity.getDistance(this) < 7))
                        world.playSound(null, master.getPosition(), SoundInit.THREE_FREEZE.get(), SoundCategory.VOICE, 1, 1);
                }
            }
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> {
                ability = props.getAbility();
                StandEffects.getLazyOptional(master).ifPresent(standEffects -> standEffects.setThreeFreeze(false));

                if (props.getAct() == props.getMaxAct() - 2 && props.getStandOn()) {
                    remove();
                    EchoesAct2Entity echoesAct2Entity = new EchoesAct2Entity(EntityInit.ECHOES_ACT_2.get(), world);
                    Vec3d position = master.getLookVec().mul(0.5, 1, 0.5).add(master.getPositionVec()).add(0, 0.5, 0);
                    echoesAct2Entity.setLocationAndAngles(position.getX(), position.getY(), position.getZ(), master.rotationYaw, master.rotationPitch);
                    echoesAct2Entity.setMaster(master);
                    echoesAct2Entity.setMasterUUID(master.getUniqueID());
                    world.addEntity(echoesAct2Entity);
                }
            });

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (master.isCrouching())
                attackRush = false;
            if (attackRush) {
                attackTicker++;
                if (!world.isRemote) {
                    master.setSprinting(false);
                    EchoesAct3PunchEntity echoesAct3PunchEntity = new EchoesAct3PunchEntity(world, this, master);
                    echoesAct3PunchEntity.randomizePositions();
                    echoesAct3PunchEntity.shoot(master, master.rotationPitch, master.rotationYaw, 3.5f, 0.2f);
                    world.addEntity(echoesAct3PunchEntity);
                    EchoesAct3PunchEntity echoesAct3PunchEntity1 = new EchoesAct3PunchEntity(world, this, master);
                    echoesAct3PunchEntity1.randomizePositions();
                    echoesAct3PunchEntity1.shoot(master, master.rotationPitch, master.rotationYaw, 3.5f, 0.2f);
                    world.addEntity(echoesAct3PunchEntity1);
                }
                if (attackTicker >= 100) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
