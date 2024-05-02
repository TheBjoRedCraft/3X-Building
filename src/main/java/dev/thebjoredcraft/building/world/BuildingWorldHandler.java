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
import dev.thebjoredcraft.building.util.OnlinePlayers;
import dev.thebjoredcraft.building.world.gui.BuildingWorldCreateGUI;
import dev.thebjoredcraft.building.world.gui.BuildingWorldGUI;
import dev.thebjoredcraft.building.world.gui.BuildingWorldVisitGUI;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI1;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI2;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI3;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

public class BuildingWorldHandler implements Listener {
    @EventHandler
    public void onCLick(InventoryClickEvent event){
        BuildingWorldVisitGUI.handle(event);
        BuildingWorldCreateGUI.handle(event);
        BuildingWorldMemberGUI1.handle(event);
        BuildingWorldMemberGUI2.handle(event);
        BuildingWorldMemberGUI3.handle(event);
        BuildingWorldGUI.handle(event);
    }
    @EventHandler
    public void onChat(PlayerChatEvent event){
        if(BuildingWorldCreateGUI.chatInputPlayers.contains(event.getPlayer())){
            event.setCancelled(true);

            if(!event.getMessage().contains("cancel")){
                if(!event.getMessage().contains("abbrechen")){
                    BuildingWorldCreateGUI.open(event.getPlayer(), event.getMessage().replace(" ", ""));
                    BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
                }else{
                    event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Vorgang wurde abgebrochen!"));
                    BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
                }
            }else{
                event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Vorgang wurde abgebrochen!"));
                BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
            }
        }
    }
    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        List<World> buildingWorldsBukkitWorlds = new ArrayList<>();
        List<World> playerMemberBuildingWorlds = new ArrayList<>();

        if(!event.getPlayer().hasPermission("building.world.protection.bypass")){
            for(BuildingWorldData data : DataFile.getAllWorldData().values()){
                buildingWorldsBukkitWorlds.add(data.getWorld());

                if(data.getPlayers().contains(event.getPlayer())){
                    playerMemberBuildingWorlds.add(event.getBlock().getWorld());
                }
            }

            if(buildingWorldsBukkitWorlds.contains(event.getBlock().getWorld())) {
                if (!playerMemberBuildingWorlds.contains(event.getBlock().getWorld())) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Du kannst hier nicht bauen, da du kein Mitglied dieser Bau-Welt bist."));
                }
            }
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        List<World> buildingWorldsBukkitWorlds = new ArrayList<>();
        List<World> playerMemberBuildingWorlds = new ArrayList<>();

        if(!event.getPlayer().hasPermission("building.world.protection.bypass")){
            for(BuildingWorldData data : DataFile.getAllWorldData().values()){
                buildingWorldsBukkitWorlds.add(data.getWorld());

                if(data.getPlayers().contains(event.getPlayer())){
                    playerMemberBuildingWorlds.add(event.getBlock().getWorld());
                }
            }

            if(buildingWorldsBukkitWorlds.contains(event.getBlock().getWorld())) {
                if (!playerMemberBuildingWorlds.contains(event.getBlock().getWorld())) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Du kannst hier nicht abauen, da du kein Mitglied dieser Bau-Welt bist."));
                }
            }
        }
    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        List<World> buildingWorldsBukkitWorlds = new ArrayList<>();
        List<World> playerMemberBuildingWorlds = new ArrayList<>();

        if(!event.getPlayer().hasPermission("building.world.protection.bypass")){
            if(event.getClickedBlock() != null) {
                for (BuildingWorldData data : DataFile.getAllWorldData().values()) {
                    buildingWorldsBukkitWorlds.add(data.getWorld());

                    if (data.getPlayers().contains(event.getPlayer())) {
                        playerMemberBuildingWorlds.add(event.getClickedBlock().getWorld());
                    }
                }

                if (buildingWorldsBukkitWorlds.contains(event.getClickedBlock().getWorld())) {
                    if (!playerMemberBuildingWorlds.contains(event.getClickedBlock().getWorld())) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Du kannst hier nicht interagieren, da du kein Mitglied dieser Bau-Welt bist."));
                    }
                }
            }
        }
        if(event.getPlayer().getItemInHand().getItemMeta() != null) {
            if(event.getPlayer().getItemInHand().getItemMeta().displayName() != null) {
                if (event.getPlayer().getItemInHand().getItemMeta().displayName().equals(Compass.displayName)) {
                    BuildingWorldGUI.open(event.getPlayer());
                }
            }
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        World world = Bukkit.getWorld(Building3IX.getInstance().getConfig().getString("LobbyWorld", ""));
        if(world != null){
            if(event.getPlayer().getWorld().equals(world)){
                Compass.give(event.getPlayer());
            }
        }else{
            Bukkit.createWorld(new WorldCreator(Building3IX.getInstance().getConfig().getString("LobbyWorld", "")));
            Console.log("Loaded or created World (LobbyWorld) " + Building3IX.getInstance().getConfig().getString("LobbyWorld", ""));
        }
        OnlinePlayers.addOnlinePlayersEver(event.getPlayer());
    }
}
