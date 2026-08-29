package com.serversecurity;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainMod implements ModInitializer {
	public static final String MOD_ID = "antixray_antiop";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Anti-Xray and Anti-OP Guard mod loaded successfully!");
		// Initialize security features here
	}
}
