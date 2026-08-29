package com.serversecurity;

import net.fabricmc.api.ModInitializer;

public class MainMod implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("[Security-System] Mod Loaded Successfully!");
        AntiOpFeature.register();
    }
}
