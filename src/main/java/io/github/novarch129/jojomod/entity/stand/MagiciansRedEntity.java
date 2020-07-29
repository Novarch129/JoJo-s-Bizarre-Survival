package io.github.novarch129.jojomod.entity.stand;

import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.config.JojoBizarreSurvivalConfig;
import io.github.novarch129.jojomod.entity.stand.attack.MagiciansRedFlameEntity;
import io.github.novarch129.jojomod.init.SoundInit;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

@SuppressWarnings("ConstantConditions")
public class MagiciansRedEntity extends AbstractStandEntity {
    public MagiciansRedEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
    }

    @Override
    public SoundEvent getSpawnSound() {
        return SoundInit.SPAWN_MAGICIANS_RED.get();
    }

    public void crossfireHurricane() {
        if (getMaster() == null) return;
        MagiciansRedFlameEntity crossfireHurricane = new MagiciansRedFlameEntity(world, this, master);
        crossfireHurricane.shoot(master, master.rotationPitch, master.rotationYaw, 4.0f, 0.5f);
        crossfireHurricane.setExplosive(true);
        world.addEntity(crossfireHurricane);
        world.addParticle(ParticleTypes.LARGE_SMOKE, crossfireHurricane.getPosX(), crossfireHurricane.getPosY(), crossfireHurricane.getPosZ(), crossfireHurricane.getMotion().getX(), crossfireHurricane.getMotion().getY(), crossfireHurricane.getMotion().getZ());
    }

    /**
     * Prevents all fire damage from affecting Magician's Red.
     *
     * @param damageSource The {@link DamageSource} damaging the Stand.
     * @param damage       The amount of damage taken.
     * @return Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (damageSource.getTrueSource() == getMaster() || damageSource == DamageSource.CACTUS || damageSource == DamageSource.ON_FIRE || damageSource == DamageSource.IN_FIRE)
            return false;
        getMaster().attackEntityFrom(damageSource, damage * 0.5f);
        return false;
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.CROSSFIRE_HURRICANE_SPECIAL.get(), getSoundCategory(), 1.5f, 1.0f);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                MagiciansRedFlameEntity magiciansRedFlameEntity = new MagiciansRedFlameEntity(world, this, master);
                magiciansRedFlameEntity.shoot(getMaster(), rotationPitch, rotationYaw, 2.5f, 0.4f);
                world.addEntity(magiciansRedFlameEntity);
                world.addParticle(ParticleTypes.LARGE_SMOKE, magiciansRedFlameEntity.getPosX(), magiciansRedFlameEntity.getPosY(), magiciansRedFlameEntity.getPosZ(), magiciansRedFlameEntity.getMotion().getX(), magiciansRedFlameEntity.getMotion().getY(), magiciansRedFlameEntity.getMotion().getZ());
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            Stand.getLazyOptional(master).ifPresent(props -> ability = props.getAbility());
            if (world.rand.nextInt(3) == 1) {
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX() + 0.2, getPosY(), getPosZ(), 0, 0.05, -0.001);
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX() - 0.2, getPosY(), getPosZ() + 0.1, 0, 0.075, 0);
                world.addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, getPosX(), getPosY() + 0.1, getPosZ() - 0.02, 0, 0.07, 0.005);
            }

            followMaster();
            setRotationYawHead(master.rotationYawHead);
            setRotation(master.rotationYaw, master.rotationPitch);

            if (master.getFireTimer() > 0)
                master.extinguish();

            if (master.swingProgressInt == 0 && !attackRush)
                attackTick = 0;
            if (attackRush) {
                master.setSprinting(false);
                attackTicker++;
                if (attackTicker >= 10)
                    if (!world.isRemote) {
                        master.setSprinting(false);
                        MagiciansRedFlameEntity magiciansRed1 = new MagiciansRedFlameEntity(world, this, master);
                        magiciansRed1.randomizePositions();
                        magiciansRed1.shoot(master, master.rotationPitch, master.rotationYaw, 2.2f, 0.6f);
                        world.addEntity(magiciansRed1);
                        MagiciansRedFlameEntity magiciansRed2 = new MagiciansRedFlameEntity(world, this, master);
                        magiciansRed2.randomizePositions();
                        magiciansRed2.shoot(master, master.rotationPitch, master.rotationYaw, 2.2f, 0.6f);
                        world.addEntity(magiciansRed2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }

    @Override
    public void setFire(int seconds) {
    }

    @Override
    public boolean canRenderOnFire() {
        return JojoBizarreSurvivalConfig.CLIENT.renderMagiciansRedFire.get();
    }
}
