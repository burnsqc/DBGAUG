package com.dbgaug.listeners.mod;

import com.dbgaug.DBGAUG;

import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterGuiOverlaysEventListener {

	@SubscribeEvent
	public final void onRegisterGuiOverlaysEvent(final RegisterGuiOverlaysEvent event) {
		event.registerAbove(VanillaGuiOverlay.DEBUG_TEXT.id(), "worldgen", (gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
			DBGAUG.worldgenScreenOverlay.render(guiGraphics);
		});
	}
}
