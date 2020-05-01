package com.novarch.jojomod.gui;

import com.novarch.jojomod.entities.stands.madeInHeaven.EntityMadeInHeaven;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;

public class GUICounter extends AbstractGui
{
    private String s;
    private EntityMadeInHeaven stand;

    private Minecraft mc;

    public GUICounter(Minecraft mc, EntityMadeInHeaven madeInHeaven)
    {
        this.mc = mc;
        this.stand = madeInHeaven;
        s = "";
        s = String.valueOf(stand.getHeaventickr());
    }

    public void render()
    {
        s = String.valueOf(stand.getHeaventickr());
        if(!mc.world.isRemote)
            this.mc.fontRenderer.drawStringWithShadow(s, 2.0f, 4.0f, 16777215);
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
