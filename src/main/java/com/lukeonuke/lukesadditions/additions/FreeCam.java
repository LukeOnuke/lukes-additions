package com.lukeonuke.lukesadditions.additions;

import com.lukeonuke.lukesadditions.LukesAdditions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

import java.util.Objects;

public class FreeCam extends Toggleable{
    private final MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void toggleEvent() {
        Entity entity = client.cameraEntity;
        if(Objects.isNull(entity)) return;

        //client.cameraEntity.noClip = isActive();

        LukesAdditions.LOGGER.info("client.cameraEntity.noClip={}", client.cameraEntity.noClip);

        client.player.sendMessage(Text.translatable("gui.lukesAdditions.toggleFreeCam"));
    }
}
