package com.dbgaug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dbgaug.client.gui.components.WorldgenScreenOverlay;
import com.dbgaug.listeners.forge.InputEventListener;
import com.dbgaug.listeners.mod.FMLClientSetupEventListener;
import com.dbgaug.listeners.mod.RegisterGuiOverlaysEventListener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(DBGAUG.MOD_ID)
public final class DBGAUG {
	public static final String MOD_ID = "dbgaug";
	private static final Logger LOGGER = LogManager.getLogger();

	public static WorldgenScreenOverlay worldgenScreenOverlay;

	public DBGAUG() {
		LOGGER.info("DBGAUG NOW LOADING FOR DISTRIBUTION - " + FMLEnvironment.dist.toString());
		FMLJavaModLoadingContext.get().getModEventBus().register(new FMLClientSetupEventListener());
		FMLJavaModLoadingContext.get().getModEventBus().register(new RegisterGuiOverlaysEventListener());
		MinecraftForge.EVENT_BUS.register(new InputEventListener());
	}
}
