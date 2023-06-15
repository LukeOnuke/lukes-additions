package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.LukesAdditions;
import com.lukeonuke.lukesadditions.additions.FreeCam;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    private final FreeCam freeCam = FreeCam.getInstance();
    @Inject(method = "doItemPick", at = @At("HEAD"), cancellable = true)
    private void doItemPick(CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        ci.cancel();
    }

    // Disable freecam on server disconnect or world change. If not done there are some interesting bugs.
    @Inject(method = "disconnect", at = @At("HEAD"))
    public void disconnect(CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        freeCam.toggle();
    }
}
