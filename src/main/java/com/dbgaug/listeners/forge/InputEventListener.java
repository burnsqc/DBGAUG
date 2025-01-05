package com.dbgaug.listeners.forge;

import com.dbgaug.client.gui.components.WorldgenScreenOverlay;
import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class InputEventListener {
	@SubscribeEvent
	public void onInputEvent(final InputEvent.Key event) {
		if (InputConstants.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), 292) && (event.getKey() == 88) && event.getAction() == 1) {
			Minecraft minecraft = Minecraft.getInstance();
			minecraft.keyboardHandler.handledDebugKey = true;
			minecraft.gui.getChat().addMessage(Component.empty().append(Component.translatable("debug.prefix").withStyle(ChatFormatting.YELLOW, ChatFormatting.BOLD)).append(CommonComponents.SPACE).append(Component.translatable("debug.position.message")));
			InputConstants.Key inputconstants$key = InputConstants.getKey(event.getKey(), event.getScanCode());
			KeyMapping.set(inputconstants$key, false);
			WorldgenScreenOverlay.shouldRender = !WorldgenScreenOverlay.shouldRender;
		}
	}
}
