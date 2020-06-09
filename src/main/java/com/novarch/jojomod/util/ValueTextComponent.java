package com.novarch.jojomod.util;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

public class ValueTextComponent extends TextComponent {
    private final Object msg;

    public ValueTextComponent(Object msg) {
        this.msg = msg;
    }

    @Override
    public String getUnformattedComponentText() {
        return String.valueOf(this.msg);
    }

    @Override
    public ITextComponent shallowCopy() {
        return new StringTextComponent(String.valueOf(this.msg));
    }

    @Override
    public String toString() {
        return String.valueOf(msg);
    }
}
