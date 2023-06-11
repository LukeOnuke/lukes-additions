package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.mixin.MinecraftClientMixin;

public class AttackToggleableBotTarget extends ToggleableBotTarget{
    @Override
    void target() {
        if (getTickCounter() == 30) {
            ((MinecraftClientMixin) getMinecraftClient()).invokeDoAttack();
            setTickCounter(0);
        }
    }

    @Override
    String getName() {
        return "gui.lukesAdditions.attackBotName";
    }
}
