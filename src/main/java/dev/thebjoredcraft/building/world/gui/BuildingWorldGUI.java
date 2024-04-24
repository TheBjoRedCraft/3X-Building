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

import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI1;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuildingWorldGUI {
    public static Inventory guiP1;
    public static void open(Player player){
        guiP1 = Bukkit.createInventory(null, 27, MiniMessage.miniMessage().deserialize("<red>Bau-Welten"));
        ItemStack visit = new ItemStack(Material.ELYTRA);
        ItemMeta vMeta = visit.getItemMeta();

        vMeta.displayName(MiniMessage.miniMessage().deserialize("<red>Besuche eine Bau-Welt"));
        visit.setItemMeta(vMeta);

        ItemStack bWorlds = new ItemStack(Material.GRASS_BLOCK);
        ItemMeta bMeta = bWorlds.getItemMeta();

        bMeta.displayName(MiniMessage.miniMessage().deserialize("<red>Erstell eine Bau-Welt"));
        bWorlds.setItemMeta(bMeta);

        ItemStack members = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta mMeta = members.getItemMeta();

        mMeta.displayName(MiniMessage.miniMessage().deserialize("<red>Mitlgieder deiner Bau-Welten hinzufügen und entfernen"));
        members.setItemMeta(mMeta);

        ItemStack cancel = new ItemStack(Material.BARRIER);
        ItemMeta cMeta = cancel.getItemMeta();

        cMeta.displayName(MiniMessage.miniMessage().deserialize("<red>Abbrechen"));
        cancel.setItemMeta(cMeta);

        guiP1.setItem(11, visit);
        guiP1.setItem(13, bWorlds);
        guiP1.setItem(15, members);
        guiP1.setItem(26, cancel);

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
            BuildingWorldMemberGUI1.open((Player) event.getWhoClicked());
        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.BARRIER){
            event.getWhoClicked().closeInventory();
        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.GRASS_BLOCK){
            BuildingWorldCreateGUI.open((Player) event.getWhoClicked(), null);
        }else if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.ELYTRA){
            BuildingWorldVisitGUI.openPageOne((Player) event.getWhoClicked());
        }
    }
}
