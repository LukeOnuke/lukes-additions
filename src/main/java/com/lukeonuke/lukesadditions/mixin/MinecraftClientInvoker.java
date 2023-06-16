package com.lukeonuke.lukesadditions.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MinecraftClient.class)
public interface MinecraftClientInvoker {
    @Invoker("doAttack")
    public boolean invokeDoAttack();

    @Invoker("doItemUse")
    public void invokeDoItemUse();
}
