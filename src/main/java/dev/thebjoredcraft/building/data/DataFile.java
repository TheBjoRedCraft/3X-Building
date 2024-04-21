package dev.thebjoredcraft.building.data;

import dev.thebjoredcraft.building.Building3IX;
import dev.thebjoredcraft.building.server.Console;
import dev.thebjoredcraft.building.world.BuildingWorld;
import dev.thebjoredcraft.building.world.BuildingWorldData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataFile  {
    public static File dataFile = new File(Building3IX.getInstance().getDataFolder(), "data.yml");
    public static FileConfiguration fileConfig = YamlConfiguration.loadConfiguration(dataFile);

    public static void saveFile() throws IOException {
        fileConfig.save(dataFile);
    }
    public static void writeWorldData(BuildingWorldData data) {
        List<String> uuids = new ArrayList<>();
        String path = "worlds." + data.getDisplayName();

        for(Player player : data.getPlayers()){
            uuids.add(player.getUniqueId().toString());
        }

        fileConfig.set(path + ".bukkitWorld", data.getWorld().getName());
        fileConfig.set(path + ".owner", data.getOwner().getUniqueId().toString());
        fileConfig.set(path + ".displayName", data.getDisplayName());
        fileConfig.set(path + ".id", data.getId());
        fileConfig.set(path + ".players", uuids);

        try {
            saveFile();
        } catch (IOException e) {
            Console.logError(e.getMessage());
        }

    }
    public static void removeWorldData(BuildingWorldData data){
        String path = "worlds." + data.getDisplayName();

        if (fileConfig.contains(path)) {
            fileConfig.set(path, null);
            try {
                saveFile();
            } catch (IOException e) {
                Console.logError(e.getMessage());
            }
        }
    }
    public static HashMap<String, BuildingWorldData> getAllWorldData() {
        HashMap<String, BuildingWorldData> worldDataList = new HashMap<>();
        ConfigurationSection configSection = fileConfig.getConfigurationSection("worlds");

        if (configSection != null) {
            for (String key : configSection.getKeys(false)) {

                String path = "worlds." + key;
                String displayName = fileConfig.getString(path + ".displayName");
                World world = Bukkit.getWorld(fileConfig.getString(path + ".bukkitWorld"));
                OfflinePlayer owner = Bukkit.getPlayer(UUID.fromString(fileConfig.getString(path + ".owner")));
                List<String> playerUUIDs = fileConfig.getStringList(path + ".players");
                List<Player> players = new ArrayList<>();
                int id = fileConfig.getInt(path + ".id");

                for (String playerUUID : playerUUIDs) {
                    Player player = Bukkit.getPlayer(UUID.fromString(playerUUID));
                    if (player != null) {
                        players.add(player);
                    }
                }
                if(owner != null) {
                    if (world != null) {
                        BuildingWorldData worldData = new BuildingWorldData(world, null, owner, players, displayName, id);
                        worldDataList.put(worldData.getDisplayName(), worldData);
                    } else {
                        Console.logError("Fehler beim laden der BuildingWordData von data.yml (" + key + "): BukkitWorld (" + fileConfig.getString(path + ".bukkitWorld") + ") wurde nicht gefunden!");
                    }
                } else {
                    Console.logError("Fehler beim laden der BuildingWordData von data.yml (" + key + "): Owner (" + fileConfig.getString(path + ".bukkitWorld") + ") wurde nicht gefunden!");
                }
            }
        }

        return worldDataList;
    }
    public static void saveCurrentBuildingWorldID(){
        fileConfig.set("worlds.currentID", BuildingWorld.getCurrentID());
    }
    public static Integer getCurrentBuildingWorldID(){
        return fileConfig.getInt("worlds.currentID");
    }
}
