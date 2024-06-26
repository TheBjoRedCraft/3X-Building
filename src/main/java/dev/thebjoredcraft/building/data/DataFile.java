package dev.thebjoredcraft.building.data;

/*
 * Copyright © 2024 TheBjoRedCraft. All rights reserved.
 *
 * This file contains proprietary information belonging to TheBjoRedCraft.
 * No part of this code may be reproduced, distributed, or transmitted in any form or by any means,
 * including photocopying, recording, or other electronic or mechanical methods, without the prior
 * written permission of TheBjoRedCraft, except in the case of brief quotations embodied in critical
 * reviews and certain other noncommercial uses permitted by copyright law.
 *
 * Unauthorized use, reproduction, or distribution of this code or any portion of it may result in severe
 * penalties, and will be prosecuted to the maximum extent possible under the law.
 */


import dev.thebjoredcraft.building.Building3IX;
import dev.thebjoredcraft.building.server.Console;
import dev.thebjoredcraft.building.server.Debugger;
import dev.thebjoredcraft.building.world.BuildingWorldData;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.WorldCreator;
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
    public static void save(){
        Building3IX.getInstance().saveResource("data.yml", false);
    }
    public static void writeWorldData(BuildingWorldData data) {
        List<String> uuids = new ArrayList<>();
        String path = "worlds." + data.getDisplayName();

        for(OfflinePlayer player : data.getPlayers()){
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
                OfflinePlayer owner = Bukkit.getOfflinePlayer(UUID.fromString(fileConfig.getString(path + ".owner")));
                List<String> playerUUIDs = fileConfig.getStringList(path + ".players");
                List<OfflinePlayer> players = new ArrayList<>();
                int id = fileConfig.getInt(path + ".id");

                for (String playerUUID : playerUUIDs) {
                    OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(playerUUID));
                    players.add(player);
                }
                if (world != null) {
                    BuildingWorldData worldData = new BuildingWorldData(world, null, owner, players, displayName, id);
                    worldDataList.put(worldData.getDisplayName(), worldData);
                } else {
                    Console.log("Loaded or created World " + fileConfig.getString(path + ".bukkitWorld") + "...");
                    Bukkit.createWorld(new WorldCreator(fileConfig.getString(path + ".bukkitWorld")));
                }
            }
        }

        return worldDataList;
    }
    public static void addBuildingWorldID(){
        Building3IX.getInstance().saveResource("data.yml", false);
        int configID = getCurrentBuildingWorldID();

        configID ++;

        fileConfig.set("currentID", configID);
    }
    public static Integer getCurrentBuildingWorldID(){
        return fileConfig.getInt("currentID");
    }
    public static List<String> getAllOnlinePlayerNamesEver(){
        return new ArrayList<>(fileConfig.getStringList("allEverOnlinePlayers"));
    }
    public static void addToOnlinePlayersEver(OfflinePlayer player){
        List<String> players = getAllOnlinePlayerNamesEver();
        Debugger.debug(players);
        Debugger.debug(1);

        if(!players.contains(player.getName())) {
            players.add(player.getName());
            Debugger.debug(players);
            Debugger.debug(2);
        }
        Debugger.debug(players);
        Debugger.debug(3);
        fileConfig.set("allEverOnlinePlayers", players);

        try {
            saveFile();
        } catch (IOException e) {
            Console.logError(e.getMessage());
        }

        Debugger.debug(getAllOnlinePlayerNamesEver());
    }
}
