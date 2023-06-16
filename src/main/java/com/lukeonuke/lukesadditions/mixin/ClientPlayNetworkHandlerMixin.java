package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.freecam.FreeCam;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    private final MinecraftClient client = MinecraftClient.getInstance();
    private final FreeCam freeCam = FreeCam.getInstance();

    @Inject(method = "onPlayerRespawn", at = @At("HEAD"))
    private void onPlayerRespawn(CallbackInfo ci) {
        if(freeCam.isActive()) freeCam.toggle();
    }
}
