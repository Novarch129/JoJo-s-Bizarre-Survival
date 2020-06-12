package com.novarch.jojomod.gui.overlay;

import com.mojang.blaze3d.platform.GlStateManager;
import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.capabilities.stand.Stand;
import com.novarch.jojomod.entities.stands.aerosmith.EntityAerosmith;
import com.novarch.jojomod.util.JojoLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.List;

@SuppressWarnings("deprecation")
public class CarbonDioxideRadarGUI extends AbstractGui
{
    public static final Minecraft mc = Minecraft.getInstance();

    public void renderRadar(List<Entity> entities, EntityAerosmith stand)
    {
        PlayerEntity player = mc.player;
        int posX = mc.getMainWindow().getScaledWidth() - 180, posY = (mc.getMainWindow().getScaledHeight() - 256) / 2;

        assert player != null;
        Stand.getLazyOptional(player).ifPresent(props ->{
            if(props.getStandID() == JojoLibs.StandID.aerosmith && props.getStandOn())
            {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.enableAlphaTest();

                GlStateManager.translated(posX, posY, 0);

                GlStateManager.translated(128, 128, 128);
                GlStateManager.scaled(0.5, 0.5, 0);

                GlStateManager.translated(-128, -128, -128);

                Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/gui/aerosmith_radar.png"));
                GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);

               for(Entity entity : entities) {
                   int x = (int) entity.getPosX(), y = (int) entity.getPosY(), z = (int) entity.getPosZ();

                   GlStateManager.pushMatrix();
                   int xDistance = (stand.getPosition().getX() - x) * 5;
                   double yDistance = stand.getPosition().getY() - y;
                   int zDistance = (stand.getPosition().getZ() - z) * 5;

                   double size = 0.05;
                   if (yDistance >= 5)
                       size = 0.025;
                   else if ((yDistance > 0 && yDistance < 5) || (yDistance < 0 && yDistance > -5))
                       size = 0.05;
                   else if (yDistance <= -5)
                       size = 0.075;

                   GlStateManager.translated(115 + xDistance, 115 + zDistance, 100);
                   GlStateManager.scaled(size, size, 0);
                   Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/gui/aerosmith_target.png"));
                   GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);

                   GlStateManager.popMatrix();
               }
                GlStateManager.popMatrix();
            }
        });
    }
}
