package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.FreeCam;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    private final FreeCam freeCam = FreeCam.getInstance();
    @Inject(at = @At("HEAD"), method = "hasCollidedSoftly", cancellable = true)
    protected void hasCollidedSoftly(Vec3d adjustedMovement, CallbackInfoReturnable<Boolean> cir){
        if(!freeCam.isActive()) return;
        cir.setReturnValue(false);
    }

    @Inject(at = @At("HEAD"), method = "wouldCollideAt", cancellable = true)
    private void wouldCollideAt(BlockPos pos,  CallbackInfoReturnable<Boolean> cir) {
        if(!freeCam.isActive()) return;
        cir.setReturnValue(false);
    }
}
