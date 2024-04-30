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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.xml.crypto.Data;
import java.util.*;

public class BuildingWorldCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 2 && args[0].equalsIgnoreCase("create")) {

                DataFile.addBuildingWorldID();
                Queue.add(new BuildingWorld(new BuildingWorldData(null, null, player, new ArrayList<>(), args[1], DataFile.getCurrentBuildingWorldID()), true));

            }else if(args.length == 1 && args[0].equalsIgnoreCase("create")){
                BuildingWorldCreateGUI.open(player, null);
            }else if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
                if(DataFile.getAllWorldData().containsKey(args[1])){
                    BuildingWorldManager.delete(args[1]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Die Bau-Welt wurde gelöscht!"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Die Bau-Welt wurde nicht gefunden."));
                }
            }else if(args.length == 2 && args[0].equalsIgnoreCase("visit") || args.length == 2 && args[0].equalsIgnoreCase("v")){
                BuildingWorldManager.visit(player, args[1]);
            }else if(args.length == 1 && args[0].equalsIgnoreCase("visit") || args.length == 1 && args[0].equalsIgnoreCase("v")){
                BuildingWorldVisitGUI.openPageOne(player);
            }else if(args.length == 2 && args[0].equalsIgnoreCase("info")){
                BuildingWorldManager.info(player, args[1]);
            }else if(args.length == 4 && args[0].equalsIgnoreCase("members") && args[1].equalsIgnoreCase("add")){
                Player target = Bukkit.getPlayer(args[3]);
                if(target != null){
                    BuildingWorldManager.addMember(target, args[2]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde hinzugefügt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde nicht gefunden!"));
                }
            }else if(args.length == 4 && args[0].equalsIgnoreCase("members") && args[1].equalsIgnoreCase("remove")){
                Player target = Bukkit.getPlayer(args[3]);
                if(target != null){
                    BuildingWorldManager.removeMember(target, args[2]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde entfernt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde nicht gefunden!"));
                }
            }else{
                BuildingWorldGUI.open(player);
            }
        }
        return false;
    }
    private final String[] defaultCompletions = new String[]{"delete", "create", "visit", "info", "members" };
    private final String[] membersCompletions = new String[]{"add", "remove"};

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        List<String> buildingWorldNames = new ArrayList<>();
        List<String> onlinePlayerNames = new ArrayList<>();

        for(BuildingWorldData data : DataFile.getAllWorldData().values()){
            buildingWorldNames.add(data.getDisplayName());
        }
        for(Player player : Bukkit.getOnlinePlayers()){
            onlinePlayerNames.add(player.getName());
        }
        //TODO
        Collections.sort(completions);
        return completions;
    }
}
