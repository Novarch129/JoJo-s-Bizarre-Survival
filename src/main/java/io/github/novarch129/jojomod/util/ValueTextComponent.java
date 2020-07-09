package io.github.novarch129.jojomod.util;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

@MethodsReturnNonnullByDefault
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
