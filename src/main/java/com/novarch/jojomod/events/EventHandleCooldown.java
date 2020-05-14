package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.JojoProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameType;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandleCooldown
{
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;
        JojoProvider.getLazy(player).ifPresent(props -> {
            if(!props.getStandOn() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getCooldown() > 0)
                props.subtractCooldown(0.5);

            if(props.getCooldown() == 0.5)
                props.setTimeLeft(1000);

            if(!props.getStandOn() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);

            else if(props.getStandOn() && !props.getAbility() && props.getTimeLeft() < 1000)
                props.addTimeLeft(0.5);


            if(!props.getStandOn())
            {
                player.setInvulnerable(false);
                if(!player.isCreative() && !player.isSpectator())
                    player.setGameType(GameType.SURVIVAL);
            }
        });
    }
}
