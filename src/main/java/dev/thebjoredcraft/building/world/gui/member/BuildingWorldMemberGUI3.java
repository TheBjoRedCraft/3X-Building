package dev.thebjoredcraft.building.world.gui.member;
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
import dev.thebjoredcraft.building.server.Debugger;
import dev.thebjoredcraft.building.world.BuildingWorldData;
import dev.thebjoredcraft.building.world.BuildingWorldManager;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class BuildingWorldMemberGUI3 {
    public static Inventory guiP1;
    public static void open(Player player, String displayName){
        guiP1 = Bukkit.createInventory(null, 54, MiniMessage.miniMessage().deserialize("<red>Mitglieder hinzufügen"));
        int count = 0;

        for(Player target : Bukkit.getOnlinePlayers()){
            count ++;
            if(count != 52 && target != player && !DataFile.getAllWorldData().get(displayName).getPlayers().contains(target)){
                guiP1.addItem(getPlayerItem(target, displayName));
            }
        }
        guiP1.setItem(53, getBackItem(displayName));
        player.openInventory(guiP1);
    }
    public static void handle(InventoryClickEvent event){
        if(event.getClickedInventory() == null){
            return;
        }
        if(!event.getClickedInventory().equals(guiP1)){
            return;
        }
        event.setCancelled(true);

        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
            BuildingWorldData current = BuildingWorldManager.getByID(event.getCurrentItem().getItemMeta().getCustomModelData()).getData();
            BuildingWorldManager.addMember(Bukkit.getOfflinePlayer(event.getCurrentItem().getItemMeta().getDisplayName()), current.getDisplayName());
            BuildingWorldMemberGUI3.open((Player) event.getWhoClicked(), current.getDisplayName());
        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BARRIER){
            BuildingWorldData current = BuildingWorldManager.getByID(event.getCurrentItem().getItemMeta().getCustomModelData()).getData();
            BuildingWorldMemberGUI2.open((Player) event.getWhoClicked(), current.getDisplayName());
        }
    }
    public static ItemStack getPlayerItem(OfflinePlayer player, String displayName){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sMeta = (SkullMeta) stack.getItemMeta();
        List<Component> lore = new ArrayList<>();

        lore.add(MiniMessage.miniMessage().deserialize("<gray>Clicke, um den Spieler hinzuzufügen!"));

        sMeta.setOwningPlayer(player);
        sMeta.setDisplayName(player.getName());
        sMeta.lore(lore);
        sMeta.setCustomModelData(DataFile.getAllWorldData().get(displayName).getId());

        stack.setItemMeta(sMeta);
        return stack;
    }
    public static ItemStack getBackItem(String displayName){
        ItemStack stack = new ItemStack(Material.BARRIER);
        ItemMeta sMeta = stack.getItemMeta();
        List<Component> lore = new ArrayList<>();

        lore.add(MiniMessage.miniMessage().deserialize("<red>Zurück"));
        sMeta.displayName(MiniMessage.miniMessage().deserialize("<red>Zurück"));
        sMeta.lore(lore);
        sMeta.setCustomModelData(DataFile.getAllWorldData().get(displayName).getId());

        stack.setItemMeta(sMeta);
        return stack;
    }
}
