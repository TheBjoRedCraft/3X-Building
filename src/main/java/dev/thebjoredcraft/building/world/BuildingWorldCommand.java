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


import dev.thebjoredcraft.building.data.DataFile;
import dev.thebjoredcraft.building.message.MessageUtil;
import dev.thebjoredcraft.building.world.gui.BuildingWorldCreateGUI;
import dev.thebjoredcraft.building.world.gui.BuildingWorldGUI;
import dev.thebjoredcraft.building.world.gui.BuildingWorldVisitGUI;
import dev.thebjoredcraft.building.world.queue.Queue;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.*;

public class BuildingWorldCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
                if(player.hasPermission("building.world.command.create")) {

                    DataFile.addBuildingWorldID();
                    Queue.add(new BuildingWorld(new BuildingWorldData(null, null, player, new ArrayList<>(), args[1], DataFile.getCurrentBuildingWorldID()), true));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("create")){
                if(player.hasPermission("building.world.command.create")) {
                    BuildingWorldCreateGUI.open(player, null);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
                if(player.hasPermission("building.world.command.delete")) {
                    if (DataFile.getAllWorldData().containsKey(args[1])) {
                        BuildingWorldManager.delete(args[1]);
                        player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Die Bau-Welt wurde gelöscht!"));
                    } else {
                        player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Die Bau-Welt wurde nicht gefunden."));
                    }
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 2 && args[0].equalsIgnoreCase("visit") || args.length == 2 && args[0].equalsIgnoreCase("v")){
                if(player.hasPermission("building.world.command.visit")) {
                    BuildingWorldManager.visit(player, args[1]);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("visit") || args.length == 1 && args[0].equalsIgnoreCase("v")){
                if(player.hasPermission("building.world.command.visit")) {
                    BuildingWorldVisitGUI.openPageOne(player);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 2 && args[0].equalsIgnoreCase("info")){
                BuildingWorldManager.info(player, args[1]);
            }else if(args.length == 4 && args[0].equalsIgnoreCase("members") && args[1].equalsIgnoreCase("add")){
                if(player.hasPermission("building.world.command.members.add")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[3]);

                    BuildingWorldManager.addMember(target, args[2]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde hinzugefügt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 4 && args[0].equalsIgnoreCase("members") && args[1].equalsIgnoreCase("remove")) {
                if (player.hasPermission("building.world.command.members.remove")) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[3]);

                    BuildingWorldManager.removeMember(target, args[2]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde entfernt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else if(args.length == 1 && args[0].equalsIgnoreCase("compass") || args.length == 1 && args[0].equalsIgnoreCase("c") ){
                if(player.hasPermission("building.world.command.compass")) {
                    Compass.give(player);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            } else if(args.length == 0){
                if (player.hasPermission("building.world.command.gui")) {
                    BuildingWorldGUI.open(player);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            } else if(args.length == 2 && args[0].equalsIgnoreCase("import")){
                if (player.hasPermission("building.world.command.import")) {
                    BuildingWorldManager.importWorld(args[1], player);

                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Deine Welt wurde importiert."));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
            }else {
                if (player.hasPermission("building.world.command.gui")) {
                    BuildingWorldGUI.open(player);
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Dazu hast du leider keine Rechte."));
                }
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Falsche Argumente!"));
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> buildingWorldNames = new ArrayList<>();

        for(BuildingWorldData data : DataFile.getAllWorldData().values()){
            buildingWorldNames.add(data.getDisplayName());
        }

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], Arrays.asList("create", "delete", "visit", "info", "members", "compass", "import"), new ArrayList<>());
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("delete") || args[0].equalsIgnoreCase("visit") || args[0].equalsIgnoreCase("info")) {
                return StringUtil.copyPartialMatches(args[1], buildingWorldNames, new ArrayList<>());
            } else if (args[0].equalsIgnoreCase("members")) {
                return StringUtil.copyPartialMatches(args[1], Arrays.asList("add", "remove"), new ArrayList<>());
            }
        } else if (args.length == 3 && args[0].equalsIgnoreCase("members")) {
            return StringUtil.copyPartialMatches(args[2], buildingWorldNames, new ArrayList<>());
        } else if (args.length == 4 && args[0].equalsIgnoreCase("members")) {
            return StringUtil.copyPartialMatches(args[3], DataFile.getAllOnlinePlayerNamesEver(), new ArrayList<>());
        }
        Collections.sort(completions);
        return completions;
    }
}
