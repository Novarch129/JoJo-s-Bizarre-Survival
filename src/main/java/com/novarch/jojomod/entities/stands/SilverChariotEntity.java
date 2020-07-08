package com.novarch.jojomod.entities.stands;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.attacks.SilverChariotSwordEntity;
import com.novarch.jojomod.init.EntityInit;
import com.novarch.jojomod.init.SoundInit;
import com.novarch.jojomod.network.message.server.SSyncSilverChariotArmorPacket;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings({"ConstantConditions", "unused"})
@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class SilverChariotEntity extends AbstractStandEntity {
    private boolean hasArmor;

    public SilverChariotEntity(EntityType<? extends AbstractStandEntity> type, World world) {
        super(type, world);
        spawnSound = SoundInit.SPAWN_SILVER_CHARIOT.get();
    }

    public SilverChariotEntity(World world) {
        super(EntityInit.SILVER_CHARIOT.get(), world);
        spawnSound = SoundInit.SPAWN_SILVER_CHARIOT.get();
    }

    public boolean hasArmor() {
        return hasArmor;
    }

    /**
     * Sets whether Silver Chariot has his armor on and passes the information to the client.
     *
     * @param hasArmor Simply says if Silver Chariot should have his armor or not.
     */
    public void setHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
        if (!world.isRemote) //Packet is necessary because hasArmor can change after the entity has spawned, after IEntityAdditionalSpawnData#writeSpawnData has already fired.
            JojoBizarreSurvival.INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> this), new SSyncSilverChariotArmorPacket(getEntityId(), hasArmor()));
    }

    public void putHasArmor(boolean hasArmor) {
        this.hasArmor = hasArmor;
    }

    /**
     * Redirects attacks from Silver Chariot to it's master, doubling damage if it's armor is off.
     *
     * @param damageSource The {@link DamageSource} damaging the Stand.
     * @param damage       The amount of damage taken.
     * @return Always returns <code>false</code> to prevent the Stand from taking damage, and because I'm paranoid.
     */
    @Override
    public boolean attackEntityFrom(DamageSource damageSource, float damage) {
        if (!standOn || damageSource.getTrueSource() == getMaster() || damageSource == DamageSource.CACTUS || damageSource == DamageSource.FALL)
            return false;
        getMaster().attackEntityFrom(damageSource, hasArmor() ? damage * 0.5f : damage * 2);
        return false;
    }

    /**
     * Removes the speed {@link net.minecraft.potion.Effect} from the Stand's master when it's unsummoned.
     */
    @Override
    public void onRemovedFromWorld() {
        super.onRemovedFromWorld();
        if (ability)
            if (getMaster() != null)
                if (getMaster().isPotionActive(Effects.SPEED))
                    getMaster().removePotionEffect(Effects.SPEED);
    }

    @Override
    public void attack(boolean special) {
        if (getMaster() == null) return;
        attackTick++;
        if (attackTick == 1)
            if (special) {
                world.playSound(null, getPosition(), SoundInit.SILVER_CHARIOT_RUSH.get(), SoundCategory.NEUTRAL, 1, 1);
                attackRush = true;
            } else {
                world.playSound(null, getPosition(), SoundInit.PUNCH_MISS.get(), SoundCategory.NEUTRAL, 1, 0.6f / (rand.nextFloat() * 0.3f + 1) * 2);
                SilverChariotSwordEntity silverChariotSwordEntity = new SilverChariotSwordEntity(world, this, getMaster());
                silverChariotSwordEntity.shoot(getMaster(), rotationPitch, rotationYaw, hasArmor() ? 4 : 10, hasArmor() ? 0.001f : Float.MIN_VALUE);
                world.addEntity(silverChariotSwordEntity);
            }
    }

    @Override
    public void tick() {
        super.tick();
        if (getMaster() != null) {
            PlayerEntity player = getMaster();
            Stand.getLazyOptional(player).ifPresent(props -> {
                ability = props.getAbility();
                if (props.getTimeLeft() > 800 && props.getCooldown() <= 0) {
                    if (ability == hasArmor()) {
                        setHasArmor(!ability);

                        if (!hasArmor()) {
                            if (props.getTimeLeft() % 20 == 0 && !player.isCreative())
                                player.getFoodStats().addStats(-1, 0);
                            world.playSound(null, new BlockPos(getPosX(), getPosY(), getPosZ()), SoundEvents.ENTITY_GENERIC_EXPLODE, getSoundCategory(), 2.0f, 1.0f);
                            for (int i = 0; i < 5; i++)
                                spawnExplosionParticle();
                            player.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 4));
                        } else {
                            if (player.isPotionActive(Effects.SPEED))
                                player.removePotionEffect(Effects.SPEED);
                        }
                    }
                }

                if (!hasArmor())
                    props.subtractTimeLeft(1);
                if (props.getCooldown() > 0)
                    setHasArmor(true);
                if (props.getTimeLeft() == 800) {
                    setHasArmor(true);
                    props.setCooldown(200);
                }
                if (props.getCooldown() > 0 && ability)
                    props.subtractCooldown(1);
                if (props.getCooldown() == 1)
                    props.setTimeLeft(1000);
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
                        SilverChariotSwordEntity silverChariot1 = new SilverChariotSwordEntity(world, this, player);
                        silverChariot1.setRandomPositions();
                        silverChariot1.shoot(player, player.rotationPitch, player.rotationYaw, hasArmor() ? 3 : 6, hasArmor() ? 0.05f : 0.0001f);
                        world.addEntity(silverChariot1);
                        SilverChariotSwordEntity silverChariot2 = new SilverChariotSwordEntity(world, this, player);
                        silverChariot2.setRandomPositions();
                        silverChariot2.shoot(player, player.rotationPitch, player.rotationYaw, hasArmor() ? 3 : 6, hasArmor() ? 0.05f : 0.0001f);
                        world.addEntity(silverChariot2);
                    }
                if (attackTicker >= 80) {
                    attackRush = false;
                    attackTicker = 0;
                }
            }
        }
    }
}
