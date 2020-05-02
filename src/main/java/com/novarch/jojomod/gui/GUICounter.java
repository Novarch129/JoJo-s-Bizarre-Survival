package com.novarch.jojomod.gui;

import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GUICounter extends AbstractGui
{
    private String s;
    private EntityMadeInHeaven stand;

    private Minecraft mc;

    public GUICounter(Minecraft mc, EntityMadeInHeaven madeInHeaven)
    {
        super();
        this.mc = mc;
        this.stand = madeInHeaven;
        s = "";
        s = String.valueOf(stand.getHeaventickr());
    }

    public void render()
    {
        if(!mc.world.isRemote) {
            mc.player.sendMessage(new TranslationTextComponent("str", new Object[0]));
            this.mc.fontRenderer.drawStringWithShadow(s, 2.0f, 4.0f, 16777215);
            this.mc.fontRenderer.drawStringWithShadow("String", 4.0f, 4.0f, 1);
            this.mc.fontRenderer.drawStringWithShadow(s, (float) mc.mouseHelper.getMouseX(), (float) mc.mouseHelper.getMouseY(), 16777215);
        }
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
