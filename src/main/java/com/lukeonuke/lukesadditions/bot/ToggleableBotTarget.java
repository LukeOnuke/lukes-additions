package com.lukeonuke.lukesadditions.bot;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public abstract class ToggleableBotTarget {
    @Getter
    private MinecraftClient minecraftClient = MinecraftClient.getInstance();
    @Getter
    private boolean isActive = false;

    @Getter
    @Setter
    private int tickCounter = 0;

    public boolean toggle(){
        isActive = !isActive;

        if(minecraftClient.player != null) minecraftClient.player.sendMessage(Text.translatable("gui.lukesAdditions.toggleBot", Text.translatable(getName()).getString()));

        return isActive;
    }

    public void tick(){
        if(isActive){
            tickCounter++;
            target();
        }
    }

    abstract void target();

    abstract String getName();
}
