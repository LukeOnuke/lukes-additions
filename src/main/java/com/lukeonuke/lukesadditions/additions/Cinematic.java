package com.lukeonuke.lukesadditions.additions;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.util.Formatting;

public class Cinematic extends Toggleable{
    final MinecraftClient client = MinecraftClient.getInstance();
    private int oldFov;
    @Override
    public void toggleEvent() {
        SimpleOption<Integer> fov = client.options.getFov();
        if(isActive()){
            oldFov = fov.getValue();
            fov.setValue(30);
        }else {
            fov.setValue(oldFov);
        }
    }

    @Override
    public String getNameKey() {
        return "gui.lukesAdditions.cinematicName";
    }
}
