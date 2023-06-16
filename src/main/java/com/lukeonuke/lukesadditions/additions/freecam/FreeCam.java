package com.lukeonuke.lukesadditions.additions.freecam;

import com.lukeonuke.lukesadditions.additions.Toggleable;
import com.lukeonuke.lukesadditions.mixin.PlayerEntityInvoker;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

import java.util.Objects;

public class FreeCam extends Toggleable {

    private FreeCam(){

    }

    private static FreeCam instance;

    @Getter
    private FreeCameraEntity freeCameraEntity;

    private Entity oldCamera;

    public static FreeCam getInstance() {
        if(instance == null) instance = new FreeCam();
        return instance;
    }

    @Override
    public void toggleEvent() {
        MinecraftClient client = MinecraftClient.getInstance();
        if(Objects.isNull(client.player)) return;

        if(isActive()){
            freeCameraEntity = new FreeCameraEntity(client);
            freeCameraEntity.spawn();
            oldCamera = client.getCameraEntity();
            client.setCameraEntity(freeCameraEntity);
            client.player.input = new Input();
        }else {
            client.setCameraEntity(oldCamera);
            freeCameraEntity.despawn();
            client.player.input = freeCameraEntity.input;
        }

        client.chunkCullingEnabled = !isActive();
        ((PlayerEntityInvoker) client.player).invokeGetAbilities().allowModifyWorld = !isActive();
    }

    @Override
    public String getNameKey() {
        return "gui.lukesAdditions.freeCamName";
    }
}
