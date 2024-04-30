package dev.thebjoredcraft.building.world;

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
import dev.thebjoredcraft.building.data.DataFile;
import dev.thebjoredcraft.building.message.MessageUtil;
import dev.thebjoredcraft.building.server.Console;
import dev.thebjoredcraft.building.server.Debugger;
import dev.thebjoredcraft.building.world.queue.Queue;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.codehaus.plexus.util.FileUtils;

import java.io.IOException;
import java.util.HashMap;

public class BuildingWorldManager {
    public static void create(BuildingWorldData data){
        Player owner = data.getOwner().getPlayer();
        Debugger.debug("1-title");

        owner.showTitle(Title.title(MiniMessage.miniMessage().deserialize("<red>Bitte warte einen Moment..."), MiniMessage.miniMessage().deserialize("...<green>Deine Welt wird erstellt!")));
        World world = Bukkit.createWorld(new WorldCreator(data.getDisplayName() + data.getId()).type(WorldType.FLAT));

        owner.showTitle(Title.title(MiniMessage.miniMessage().deserialize("<red>Bitte warte einen Moment..."), MiniMessage.miniMessage().deserialize("...<green>Du wirst teleportiert!")));
        Debugger.debug("2-title");
        owner.teleport(world.getSpawnLocation());

        Queue.remove(data.getBuildingWorld());

        data.setWorld(world);
        DataFile.writeWorldData(data);
    }
    public static void visit(Player player, String displayName){
        HashMap<String, BuildingWorldData> bWorlds = DataFile.getAllWorldData();

        if(bWorlds.containsKey(displayName)){
            BuildingWorldData data = bWorlds.get(displayName);

            player.teleport(data.getWorld().getSpawnLocation());
        }
    }
    public static void addMember(OfflinePlayer player, String displayName){
        HashMap<String, BuildingWorldData> bWorlds = DataFile.getAllWorldData();

        if(bWorlds.containsKey(displayName)){
            BuildingWorldData data = bWorlds.get(displayName);

            data.getPlayers().add(player);
            DataFile.writeWorldData(data);
        }
    }
    public static void removeMember(OfflinePlayer player, String displayName) {
        HashMap<String, BuildingWorldData> bWorlds = DataFile.getAllWorldData();

        if (bWorlds.containsKey(displayName)) {
            BuildingWorldData data = bWorlds.get(displayName);

            data.getPlayers().remove(player);
            DataFile.writeWorldData(data);

        }
    }
    public static void info(Player player, String displayName){
        HashMap<String, BuildingWorldData> bWorlds = DataFile.getAllWorldData();

        if(bWorlds.containsKey(displayName)){
            BuildingWorldData data = bWorlds.get(displayName);

            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Bau-Welt Information"));
            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + " "));
            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + data.getDisplayName()));
            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Besitzer: " + data.getOwner().getName()));
            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Mitglieder:"));

            for(OfflinePlayer member : data.getPlayers()){
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "- " + member.getName()));
            }
        }
    }
    public static void delete(String displayName) {
        HashMap<String, BuildingWorldData> bWorlds = DataFile.getAllWorldData();

        if (bWorlds.containsKey(displayName)) {
            BuildingWorldData data = bWorlds.get(displayName);

            for(Player target : data.getWorld().getPlayers()){
                World world = Bukkit.createWorld(new WorldCreator(Building3IX.getInstance().getConfig().getString("MainWorld", "")));
                target.teleport(world.getSpawnLocation());
            }

            Bukkit.getWorlds().remove(data.getWorld());
            Bukkit.unloadWorld(data.getWorld(), true);
            try {
                FileUtils.deleteDirectory(data.getWorld().getWorldFolder());
            } catch (IOException e) {
                Console.logError(e.getMessage());
            }
            DataFile.removeWorldData(data);
        }
    }
    public static BuildingWorld getByID(int id){
        BuildingWorld bWorld = null;
        for(BuildingWorldData data : DataFile.getAllWorldData().values()){
            if(data.getId() == id){
                bWorld = new BuildingWorld(new BuildingWorldData(data.getWorld(), data.getBuildingWorld(), data.getOwner(), data.getPlayers(), data.getDisplayName(), data.getId()), false);
            }
        }
        return bWorld;
    }
}
