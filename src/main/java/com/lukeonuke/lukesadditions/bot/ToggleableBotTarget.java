package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.additions.Toggleable;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public abstract class ToggleableBotTarget extends Toggleable {
    @Getter
    private final MinecraftClient minecraftClient = MinecraftClient.getInstance();

    @Getter
    @Setter
    private int tickCounter = 0;

    @Override
    public void toggleEvent(){
        if(minecraftClient.player != null) minecraftClient.player.sendMessage(Text.translatable("gui.lukesAdditions.toggleBot", Text.translatable(getName()).getString()));
    }


    public void tick(){
        if(isActive()){
            tickCounter++;
            target();
        }
    }

    abstract void target();

    abstract String getName();
}
