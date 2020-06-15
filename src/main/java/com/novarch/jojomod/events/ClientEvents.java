package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void renderPlayer(RenderWorldLastEvent event) {
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        EntityRendererManager manager = mc.getRenderManager();
        if(player != null) {
            Stand.getLazyOptional(player).ifPresent(props -> {
                if(props.getStandID() == JojoLibs.StandID.aerosmith &&
                        props.getStandOn() &&
                        props.getAbility()) {
                    float partialTicks = event.getPartialTicks();
                    assert mc.player != null;
                    double x = mc.player.getPosX();
                    double y = mc.player.getPosX();
                    double z = mc.player.getPosX();
                    float yaw = mc.player.rotationYaw;
                    manager.renderEntityStatic(
                            player,
                            x, y, z,
                            yaw, partialTicks,
                            event.getMatrixStack(),
                            mc.getRenderTypeBuffers().getBufferSource(),
                            manager.getPackedLight(player, partialTicks)
                    );
                }
            });
        }
    }
}
