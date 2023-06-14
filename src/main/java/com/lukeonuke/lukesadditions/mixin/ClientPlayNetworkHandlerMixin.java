package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.FreeCam;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientConnection.class)
public class ClientPlayNetworkHandlerMixin {
    private final FreeCam freeCam = FreeCam.getInstance();

    @Inject(at = @At("HEAD"), method = "send", cancellable = true)
    public void send(CallbackInfo ci) {
        if(freeCam.isActive()) ci.cancel();
    }
}
