package com.lukeonuke.lukesadditions.additions;

import com.lukeonuke.lukesadditions.LukesAdditions;
import com.lukeonuke.lukesadditions.additions.freecam.FreeCameraEntity;
import com.lukeonuke.lukesadditions.mixin.PlayerEntityInvoker;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import java.time.Duration;
import java.util.Objects;
import java.util.UUID;

public class FreeCam extends Toggleable{
    private final MinecraftClient client = MinecraftClient.getInstance();

    private FreeCam(){

    }

    private static FreeCam instance;

    public static FreeCam getInstance() {
        if(instance == null) instance = new FreeCam();
        return instance;
    }

    private ClientPlayNetworkHandler oldHandler;

    @Override
    public void toggleEvent() {
//        Entity entity = client.cameraEntity;
//        if(Objects.isNull(entity)) return;
        //client.cameraEntity.noClip = isActive();
        if(Objects.isNull(client.player)) return;

        switchNetworkHandler(client.player);

        client.setCameraEntity(new FreeCameraEntity(client));

        PlayerEntityInvoker playerEntityInvoker = (PlayerEntityInvoker) client.cameraEntity;
        PlayerAbilities playerAbilities = playerEntityInvoker.invokeGetAbilities();
        playerAbilities.flying = isActive();
        playerAbilities.allowFlying = isActive();
        playerAbilities.allowModifyWorld = !isActive();

        client.player.noClip = isActive();

//        BlockPos pos = client.player.getBlockPos();
//        client.cameraEntity.setPos(pos.getX() -5, pos.getY() -5, pos.getZ());

        LukesAdditions.LOGGER.info("{}", client.cameraEntity);

        client.player.sendMessage(Text.translatable("gui.lukesAdditions.toggleFreeCam"));
    }

    private void switchNetworkHandler(ClientPlayerEntity clientPlayer){
//        if(isActive()){
//            oldHandler = clientPlayer.networkHandler;
//            clientPlayer.networkHandler = new ClientPlayNetworkHandler(
//                    client,
//                    client.currentScreen,
//                    client.getNetworkHandler().getConnection(),
//                    client.getCurrentServerEntry(),
//                    new GameProfile(UUID.randomUUID(), "FreeCam"),
//                    client.getTelemetryManager().createWorldSession(false, Duration.ZERO, null));
//        }else {
//            clientPlayer.networkHandler = oldHandler;
//        }
    }
}
