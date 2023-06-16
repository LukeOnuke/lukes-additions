package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.freecam.FreeCam;
import com.lukeonuke.lukesadditions.additions.freecam.FreeCameraEntity;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    private final FreeCam freeCam = FreeCam.getInstance();

    // No shadows on iris.
    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void shouldRender(Entity entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> ci) {
        if(!(entity instanceof FreeCameraEntity)) return;
        ci.setReturnValue(false);
    }

}
