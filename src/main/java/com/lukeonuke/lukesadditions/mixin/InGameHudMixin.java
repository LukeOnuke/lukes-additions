package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.freecam.FreeCam;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private final FreeCam freeCam = FreeCam.getInstance();
    private final MinecraftClient minecraftClient = MinecraftClient.getInstance();
    @Inject(method = "getCameraPlayer", at = @At("HEAD"), cancellable = true)
    private void getCameraPlayer(CallbackInfoReturnable<PlayerEntity> cir) {
        if(freeCam.isActive()) cir.setReturnValue(minecraftClient.player);
    }
}
