package com.lukeonuke.lukesadditions.additions.freecam;

import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.stat.StatHandler;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.time.Duration;
import java.util.UUID;

public class FreeCameraEntity extends ClientPlayerEntity {
    public FreeCameraEntity(MinecraftClient client) {
        super(
                client,
                client.world,
                new ClientPlayNetworkHandler(
                        client,
                        client.currentScreen,
                        client.getNetworkHandler().getConnection(),
                        client.getCurrentServerEntry(),
                        new GameProfile(UUID.randomUUID(), "FreeCam"),
                        client.getTelemetryManager().createWorldSession(false, Duration.ZERO, null)),

                client.player.getStatHandler(),
                client.player.getRecipeBook(),
                false,
                false
        );
    }

    public FreeCameraEntity(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting) {
        super(client, world, networkHandler, stats, recipeBook, lastSneaking, lastSprinting);
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    protected boolean hasCollidedSoftly(Vec3d adjustedMovement) {
        return false;
    }

    @Override
    public boolean collidesWithStateAtPos(BlockPos pos, BlockState state) {
        return false;
    }

    @Override
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return true;
    }

    @Override
    public boolean collidesWith(Entity other) {
        return false;
    }

    @Override
    protected boolean wouldPoseNotCollide(EntityPose pose) {
        return true;
    }
}
