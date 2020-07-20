package io.github.novarch129.jojomod.event;

import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.world.ExplosionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventAbilityGER {
    @SubscribeEvent
    public static void cancelDamage(LivingHurtEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntityLiving();
            Stand.getLazyOptional(player).ifPresent(props -> {
                if (props.getStandID() == Util.StandID.GER) {
                    event.setCanceled(true);
                    if (event.getSource().getTrueSource() instanceof PlayerEntity)
                        props.setDiavolo(event.getSource().getTrueSource().getDisplayName().toString());
                    else if (event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension != DimensionType.THE_END)
                        player.setPositionAndUpdate(player.getPosX(), Util.getHighestBlockInXZ(player.world, player.getPosition()) + 1, player.getPosZ());
                    else if (event.getSource() == DamageSource.OUT_OF_WORLD && player.dimension == DimensionType.THE_END) {
                        BlockPos pos = Util.getNearestBlockEnd(player.world, player.getPosition());
                        player.setPositionAndUpdate(pos.getX(), pos.getY() + 1, pos.getZ());
                    }
                }
            });
        }
    }

    @SubscribeEvent
    public static void stopExplosion(ExplosionEvent.Start event) {
        PlayerEntity player = JojoBizarreSurvival.PROXY.getPlayer();
        if (player != null)
            Stand.getLazyOptional(player).ifPresent(props -> {
                if (props.getStandID() == Util.StandID.GER)
                    if (event.getExplosion().getPosition().distanceTo(player.getPositionVec()) < 30)
                        event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void stopKnockback(LivingKnockBackEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) event.getEntity()).ifPresent(props -> {
                if (props.getStandID() == Util.StandID.GER)
                    event.setCanceled(true);
            });
    }

    @SubscribeEvent
    public static void noClip(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntityLiving() instanceof PlayerEntity)
            Stand.getLazyOptional((PlayerEntity) event.getEntityLiving()).ifPresent(props -> {
                PlayerEntity player = (PlayerEntity) event.getEntityLiving();
                if (props.getNoClip()) {
                    player.noClip = true;
//                    if (props.getStandID() == Util.StandID.GER) { todo, deffer to packets
//                        player.setVelocity(player.getMotion().getX(), 0, player.getMotion().getZ());
//                        if (Minecraft.getInstance().gameSettings.keyBindJump.isKeyDown())
//                            player.setPosition(player.getPosX(), player.getPosY() + 1, player.getPosZ());
//                        else if (Minecraft.getInstance().gameSettings.keyBindSneak.isKeyDown())
//                            player.setPosition(player.getPosX(), player.getPosY() - 1, player.getPosZ());
//                    }
                }
            });
    }
}
