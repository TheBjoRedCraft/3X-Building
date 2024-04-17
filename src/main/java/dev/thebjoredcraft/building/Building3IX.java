package dev.thebjoredcraft.building;

import org.bukkit.plugin.java.JavaPlugin;

public final class Building3IX extends JavaPlugin {
    public static Building3IX instance;

    @Override
    public void onLoad() {
        instance = this;

    }

    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Building3IX getInstance() {
        return instance;
    }
}
