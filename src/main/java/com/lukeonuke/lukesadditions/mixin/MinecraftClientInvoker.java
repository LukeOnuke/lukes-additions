package com.lukeonuke.lukesadditions.mixin;

import com.lukeonuke.lukesadditions.additions.FreeCam;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public interface MinecraftClientInvoker {
    @Invoker("doAttack")
    public boolean invokeDoAttack();

    @Invoker("doItemUse")
    public void invokeDoItemUse();
}
