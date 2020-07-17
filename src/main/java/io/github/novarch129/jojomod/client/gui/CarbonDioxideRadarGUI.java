package io.github.novarch129.jojomod.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import io.github.novarch129.jojomod.capability.stand.Stand;
import io.github.novarch129.jojomod.entity.FakePlayerEntity;
import io.github.novarch129.jojomod.entity.stand.AerosmithEntity;
import io.github.novarch129.jojomod.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.gui.GuiUtils;

@SuppressWarnings("deprecation")
public class CarbonDioxideRadarGUI extends AbstractGui {
    public static final Minecraft mc = Minecraft.getInstance();
    AerosmithEntity aerosmith = null;

    public void renderRadar() {
        PlayerEntity player = mc.player;
        int posX = mc.getMainWindow().getScaledWidth() - 180, posY = (mc.getMainWindow().getScaledHeight() - 256) / 2;

        if (mc.world == null) return;
        if (player == null) return;
        Stand.getLazyOptional(player).ifPresent(props -> {
            if (props.getStandID() == Util.StandID.AEROSMITH && props.getStandOn()) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.enableAlphaTest();

                GlStateManager.translated(posX, posY, 0);

                GlStateManager.translated(128, 128, 128);
                GlStateManager.scaled(0.5, 0.5, 0);

                GlStateManager.translated(-128, -128, -128);

                Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation(JojoBizarreSurvival.MOD_ID, "textures/gui/aerosmith_radar.png"));
                GuiUtils.drawTexturedModalRect(0, 0, 0, 0, 256, 256, 0);
                for (Entity entity : mc.world.getAllEntities()) {
                    if (entity instanceof AerosmithEntity && ((AerosmithEntity) entity).getMaster().getEntityId() == player.getEntityId())
                        setAerosmith((AerosmithEntity) entity);
                }

                if (aerosmith != null)
                    mc.world.getAllEntities().forEach(entity -> {
                        if (Util.Predicates.BREATHS.test(entity) && entity != player && entity != aerosmith && !(entity instanceof FakePlayerEntity) && entity.getDistance(aerosmith) < 16) {
                            int x = (int) entity.getPosX(), y = (int) entity.getPosY(), z = (int) entity.getPosZ();

                            if (aerosmith != null) {
                                GlStateManager.pushMatrix();
                                int xDistance = (aerosmith.getPosition().getX() - x) * 5;
                                double yDistance = aerosmith.getPosition().getY() - y;
                                int zDistance = (aerosmith.getPosition().getZ() - z) * 5;

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
                        }
                    });
                GlStateManager.popMatrix();
            }
        });
    }

    public void setAerosmith(AerosmithEntity aerosmith) {
        this.aerosmith = aerosmith;
    }
}
