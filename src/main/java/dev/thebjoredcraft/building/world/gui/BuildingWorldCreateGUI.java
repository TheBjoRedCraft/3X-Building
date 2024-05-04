package dev.thebjoredcraft.building.world.gui;

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
import dev.thebjoredcraft.building.server.Debugger;
import dev.thebjoredcraft.building.world.BuildingWorld;
import dev.thebjoredcraft.building.world.BuildingWorldData;
import dev.thebjoredcraft.building.world.queue.Queue;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BuildingWorldCreateGUI {
    public static List<Player> chatInputPlayers = new ArrayList<>();
    public static Inventory gui;
    public static void open(Player player, @Nullable String name){
        gui = Bukkit.createInventory(null, 27, MiniMessage.miniMessage().deserialize("<red>Erstell eine Bau-Welt"));

        ItemStack cancel = new ItemStack(Material.RED_CONCRETE_POWDER);
        ItemMeta cMeta = cancel.getItemMeta();
        cMeta.displayName(MiniMessage.miniMessage().deserialize("<color:#40d1db>Abbrechen"));
        cancel.setItemMeta(cMeta);

        ItemStack create = new ItemStack(Material.LIME_CONCRETE_POWDER);
        ItemMeta crMeta = create.getItemMeta();
        crMeta.displayName(MiniMessage.miniMessage().deserialize("<color:#40d1db>Erstellen"));
        create.setItemMeta(crMeta);

        ItemStack rename = new ItemStack(Material.NAME_TAG);
        ItemMeta rMeta = rename.getItemMeta();
        if(name == null) {
            rMeta.displayName(MiniMessage.miniMessage().deserialize("<color:#40d1db>Name (Clicken zum Eingeben)"));
        }else{
            rMeta.setDisplayName(name);
        }
        rename.setItemMeta(rMeta);

        gui.setItem(10, cancel);
        gui.setItem(13, rename);
        gui.setItem(16, create);
        player.openInventory(gui);
    }
    public static void handle(InventoryClickEvent event){
        if(event.getClickedInventory() == null){
            return;
        }
        if(!event.getClickedInventory().equals(gui)){
            return;
        }
        event.setCancelled(true);

        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.LIME_CONCRETE_POWDER){
            String name = event.getClickedInventory().getItem(13).getItemMeta().getDisplayName();
            if(!event.getClickedInventory().getItem(13).getItemMeta().displayName().equals(MiniMessage.miniMessage().deserialize("<color:#40d1db>Name (Clicken zum Eingeben)"))){
                event.getWhoClicked().closeInventory();
                DataFile.addBuildingWorldID();

                Queue.add(new BuildingWorld(new BuildingWorldData(null, null, (OfflinePlayer) event.getWhoClicked(), new ArrayList<>(), name, DataFile.getCurrentBuildingWorldID()), true));
            }else{
                event.getWhoClicked().closeInventory();
                chatInputPlayers.add((Player) event.getWhoClicked());
                event.getWhoClicked().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Bitte gebe den Namen der Bau-Welt ein! Gebe <bold>abbrechen</bold> ein, um abzubrechen!"));
            }
        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.NAME_TAG){
            event.getWhoClicked().closeInventory();
            chatInputPlayers.add((Player) event.getWhoClicked());
            event.getWhoClicked().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Bitte gebe den Namen der Bau-Welt ein! Gebe <bold>abbrechen</bold> ein, um abzubrechen!"));

        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.RED_CONCRETE_POWDER){
            event.getWhoClicked().closeInventory();
        }
    }
}
