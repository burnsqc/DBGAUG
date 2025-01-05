package com.dbgaug.listeners.mod;

import com.dbgaug.DBGAUG;
import com.dbgaug.client.gui.components.WorldgenScreenOverlay;

import net.minecraft.client.Minecraft;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public final class FMLClientSetupEventListener {
	@SubscribeEvent
	public final void onFMLClientSetupEvent(final FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			DBGAUG.worldgenScreenOverlay = new WorldgenScreenOverlay(Minecraft.getInstance());
		});
	}
}
