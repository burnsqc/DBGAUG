package com.dbgaug;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod(DBGAUG.MOD_ID)
public final class DBGAUG {
	public static final String MOD_ID = "dbgaug";
	private static final Logger LOGGER = LogManager.getLogger();

	public DBGAUG() {
		LOGGER.info("DBGAUG NOW LOADING FOR DISTRIBUTION - " + FMLEnvironment.dist.toString());
	}
}
