package io.github.novarch129.jojomod.util;

import com.google.common.annotations.VisibleForTesting;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;

/**
 * Used for testing, shorter than using {@link String#valueOf(Object)}.
 */
@VisibleForTesting
public class ValueTextComponent extends TextComponent {
    private final Object msg;

    public ValueTextComponent(Object msg) {
        this.msg = msg;
    }

    @Override
    public String getUnformattedComponentText() {
        return String.valueOf(msg);
    }

    @Override
    public ITextComponent shallowCopy() {
        return new StringTextComponent(String.valueOf(msg));
    }

    @Override
    public String toString() {
        return String.valueOf(msg);
    }
}
