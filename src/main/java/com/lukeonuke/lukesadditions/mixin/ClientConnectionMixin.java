package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.FreeCam;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {
    private final FreeCam freeCam = FreeCam.getInstance();

    @Inject(method = "handleDisconnection", at = @At("HEAD"))
    private void handleDisconnection(CallbackInfo ci) {
        if(freeCam.isActive()) freeCam.toggle();
    }
}
