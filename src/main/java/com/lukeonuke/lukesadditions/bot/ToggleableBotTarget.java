package com.lukeonuke.lukesadditions.bot;

import com.lukeonuke.lukesadditions.additions.Toggleable;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public abstract class ToggleableBotTarget extends Toggleable {

    @Getter
    @Setter
    private int tickCounter = 0;

    @Getter
    private MinecraftClient minecraftClient = MinecraftClient.getInstance();

    public void tick(){
        if(isActive()){
            tickCounter++;
            target();
        }
    }

    abstract void target();
}
