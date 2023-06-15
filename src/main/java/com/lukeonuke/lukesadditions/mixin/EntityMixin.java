package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.LukesAdditions;
import com.lukeonuke.lukesadditions.additions.FreeCam;
import com.lukeonuke.lukesadditions.additions.freecam.FreeCameraEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {
    private final FreeCam freeCam = FreeCam.getInstance();
    private final MinecraftClient minecraftClient = MinecraftClient.getInstance();

    // Allow freecam to rotate, and block player rotation
    @Inject(at = @At("HEAD"), method = "changeLookDirection", cancellable = true)
    public void changeLookDirection(double cursorDeltaX, double cursorDeltaY, CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        if(!this.equals(minecraftClient.player)) return;

        freeCam.getFreeCameraEntity().changeLookDirection(cursorDeltaX, cursorDeltaY);
        ci.cancel();
    }

    //Disable player movement

    @Inject(at = @At("HEAD"), method = "setVelocity", cancellable = true)
    public void setVelocity(Vec3d velocity, CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        if(!this.equals(minecraftClient.player)) return;

        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "updateVelocity", cancellable = true)
    public void updateVelocity(float speed, Vec3d movementInput, CallbackInfo ci){
        if(!freeCam.isActive()) return;
        if(!this.equals(minecraftClient.player)) return;

        ci.cancel();
    }


    @Inject(method = "setPosition", at = @At("HEAD"), cancellable = true)
    private void setPosition(CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        if(!this.equals(minecraftClient.player)) return;

        ci.cancel();
    }


    @Inject(method = "setPos", at = @At("HEAD"), cancellable = true)
    private void setPos(CallbackInfo ci) {
        if(!freeCam.isActive()) return;
        if(!this.equals(minecraftClient.player)) return;

        ci.cancel();
    }
}
