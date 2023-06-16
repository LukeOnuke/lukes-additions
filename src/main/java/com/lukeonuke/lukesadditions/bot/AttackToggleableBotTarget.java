package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.mixin.MinecraftClientInvoker;

public class AttackToggleableBotTarget extends ToggleableBotTarget{
    @Override
    void target() {
        if (getTickCounter() == 30) {
            ((MinecraftClientInvoker) getMinecraftClient()).invokeDoAttack();
            setTickCounter(0);
        }
    }

    @Override
    public void toggleEvent() {

    }
    @Override
    public String getNameKey() {
        return "gui.lukesAdditions.attackBotName";
    }
}
