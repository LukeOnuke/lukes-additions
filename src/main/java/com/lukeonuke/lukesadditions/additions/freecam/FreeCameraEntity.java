package com.lukeonuke.lukesadditions.additions.freecam;

import com.lukeonuke.lukesadditions.mixin.PlayerEntityInvoker;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.network.packet.Packet;
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
                        client.getTelemetryManager().createWorldSession(false, Duration.ZERO, null)){
                    // Do not send packets to server as this entity.
                    // ----
                    // As such the server doesn't know it exists and as such it poses no problems in
                    // location duplication, etc.
                    @Override
                    public void sendPacket(Packet<?> packet) {

                    }
                },

                client.player.getStatHandler(),
                client.player.getRecipeBook(),
                false,
                false
        );
        input = client.player.input;

        PlayerEntityInvoker playerEntityInvoker = (PlayerEntityInvoker) this;
        PlayerAbilities playerAbilities = playerEntityInvoker.invokeGetAbilities();
        playerAbilities.flying = true;
        playerAbilities.allowFlying = true;
        playerAbilities.allowModifyWorld = false;
        playerAbilities.invulnerable = true;

        setPosition(client.player.getPos());
        setRotation(client.player.getYaw(), client.player.getPitch());
    }

    public FreeCameraEntity(MinecraftClient client, ClientWorld world, ClientPlayNetworkHandler networkHandler, StatHandler stats, ClientRecipeBook recipeBook, boolean lastSneaking, boolean lastSprinting) {
        super(client, world, networkHandler, stats, recipeBook, lastSneaking, lastSprinting);
    }

    public void spawn() {
        if (clientWorld != null) {
            clientWorld.addEntity(getId(), this);
        }
    }

    public void despawn() {
        if (clientWorld != null && clientWorld.getEntityById(getId()) != null) {
            clientWorld.removeEntity(getId(), RemovalReason.DISCARDED);
        }
    }

    @Override
    public boolean isSpectator(){
        return true;
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
