package dev.thebjoredcraft.building;

import dev.thebjoredcraft.building.world.BuildingWorldCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Building3IX extends JavaPlugin {
    public static Building3IX instance;

    @Override
    public void onLoad() {
        instance = this;

    }

    @Override
    public void onEnable() {
        saveConfig();

        getCommand("bworld").setExecutor(new BuildingWorldCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        saveConfig();
        // Plugin shutdown logic
    }
    public static Building3IX getInstance() {
        return instance;
    }
}
