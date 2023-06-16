package com.lukeonuke.lukesadditions.additions;

import com.lukeonuke.lukesadditions.LukesAdditions;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Objects;

public abstract class Toggleable {
    @Getter
    private boolean isActive = false;

    public boolean toggle() {
        isActive = !isActive;
        toggleEvent();

        MinecraftClient client = MinecraftClient.getInstance();
        if (!Objects.isNull(client.player))
            client.player.sendMessage(
                    Text.translatable("gui.lukesAdditions.toggle",
                            Text.translatable(getNameKey()).getString(),
                            isActive() ?
                                    Text.translatable("gui.lukesAdditions.toggleable.on").getString() :
                                    Text.translatable("gui.lukesAdditions.toggleable.off").getString()),
                    true);
        LukesAdditions.LOGGER.info("");

        return isActive;
    }

    abstract public void toggleEvent();

    public abstract String getNameKey();
}
