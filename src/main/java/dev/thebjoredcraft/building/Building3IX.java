package dev.thebjoredcraft.building;

import dev.thebjoredcraft.building.data.DataFile;
import dev.thebjoredcraft.building.logger.LoggerCommand;
import dev.thebjoredcraft.building.speed.SpeedCommand;
import dev.thebjoredcraft.building.world.BuildingWorld;
import dev.thebjoredcraft.building.world.BuildingWorldCommand;
import dev.thebjoredcraft.building.world.gui.BuildingWorldGUIHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Building3IX extends JavaPlugin {
    public static Building3IX instance;

    @Override
    public void onLoad() {
        instance = this;

    }

    @Override
    public void onEnable() {
        BuildingWorld.setCurrentID(DataFile.getCurrentBuildingWorldID());
        saveDefaultConfig();

        getCommand("bworld").setExecutor(new BuildingWorldCommand());
        getCommand("logger").setExecutor(new LoggerCommand());
        getCommand("speed").setExecutor(new SpeedCommand());

        Bukkit.getPluginManager().registerEvents(new BuildingWorldGUIHandler(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        DataFile.saveCurrentBuildingWorldID();
        saveDefaultConfig();
        // Plugin shutdown logic
    }
    public static Building3IX getInstance() {
        return instance;
    }
}
