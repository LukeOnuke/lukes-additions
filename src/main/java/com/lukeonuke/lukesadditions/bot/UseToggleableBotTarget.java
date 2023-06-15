package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.mixin.MinecraftClientInvoker;

public class UseToggleableBotTarget extends ToggleableBotTarget {

    @Override
    void target() {
        if (getTickCounter() == 2) {
            ((MinecraftClientInvoker) getMinecraftClient()).invokeDoItemUse();
            setTickCounter(0);
        }
    }

    @Override
    String getName() {
        return "gui.lukesAdditions.useBotName";
    }
}
