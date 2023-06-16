package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.freecam.FreeCam;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    private final FreeCam freeCam = FreeCam.getInstance();

    // Disables third person in freecam.
    @Inject(method = "setPerspective", at = @At("HEAD"), cancellable = true)
    private void setPerspective(CallbackInfo ci) {
        if (!freeCam.isActive()) return;
        ci.cancel();
    }
}
