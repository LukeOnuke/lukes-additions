package com.lukeonuke.lukesadditions;

import com.lukeonuke.lukesadditions.additions.FreeCam;
import com.lukeonuke.lukesadditions.bot.AttackToggleableBotTarget;
import com.lukeonuke.lukesadditions.bot.UseToggleableBotTarget;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LukesAdditions implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("lukes-additions");

	public static KeyBinding toggleUse;
	public static KeyBinding toggleAttack;
	public static KeyBinding toggleFreeCam;
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		toggleUse = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lukesAdditions.toggleUseBot", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_KP_ADD, // The keycode of the key
				"category.lukesAdditions.bot" // The translation key of the keybinding's category.
		));

		toggleAttack = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lukesAdditions.toggleAttackBot", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_KP_ENTER, // The keycode of the key
				"category.lukesAdditions.bot" // The translation key of the keybinding's category.
		));

		toggleFreeCam = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.lukesAdditions.toggleFreeCam", // The translation key of the keybinding's name
				InputUtil.Type.MOUSE, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_MOUSE_BUTTON_1, // The keycode of the key
				"category.lukesAdditions.additions" // The translation key of the keybinding's category.
		));

		UseToggleableBotTarget useToggleableBotTarget = new UseToggleableBotTarget();
		AttackToggleableBotTarget attackToggleableBotTarget = new AttackToggleableBotTarget();
		FreeCam freeCam = new FreeCam();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if(toggleUse.wasPressed()) useToggleableBotTarget.toggle();
			if(toggleAttack.wasPressed()) attackToggleableBotTarget.toggle();
			if(toggleFreeCam.wasPressed()) freeCam.toggle();
		});

		ClientTickEvents.END_WORLD_TICK.register(world -> {
			useToggleableBotTarget.tick();
			attackToggleableBotTarget.tick();
		});
	}


}