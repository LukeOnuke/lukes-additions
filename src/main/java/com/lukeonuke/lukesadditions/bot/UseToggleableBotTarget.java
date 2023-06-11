package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.mixin.MinecraftClientMixin;
import net.minecraft.client.MinecraftClient;

public class UseToggleableBotTarget extends ToggleableBotTarget {

    @Override
    void target() {
        if (getTickCounter() == 2) {
            ((MinecraftClientMixin) getMinecraftClient()).invokeDoItemUse();
            setTickCounter(0);
        }
    }

    @Override
    String getName() {
        return "gui.lukesAdditions.useBotName";
    }
}
