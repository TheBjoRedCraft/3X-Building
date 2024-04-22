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
import dev.thebjoredcraft.building.world.BuildingWorldData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class BuildingWorldVisitGUI {
    public static Inventory guiP1;
    public static void openPageOne(Player player){
        guiP1 = Bukkit.createInventory(null, 54, MiniMessage.miniMessage().deserialize("<red>Besuch eine Bau-Welt"));

        int count = 0;
        for(BuildingWorldData data : DataFile.getAllWorldData().values()){
            count ++;
            if(count != 54){
                guiP1.addItem(getWorldItem(data));
            }
        }
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

        if(event.getCurrentItem() != null && event.getCurrentItem().getType() == Material.PLAYER_HEAD){
            //String buildingWorldName = MiniMessage.miniMessage().serialize(event.getCurrentItem().displayName()).replace("<color:#40d1db>", "");
            BuildingWorldData data = DataFile.getAllWorldData().get(event.getCurrentItem().getItemMeta().getDisplayName());

            if(data != null) {
                event.getWhoClicked().teleport(data.getWorld().getSpawnLocation());
            }else{
                event.getCurrentItem().setAmount(-1);
                event.getWhoClicked().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Diese Bau-Welt exestiert nicht mehr!"));
            }
        }
    }
    public static ItemStack getWorldItem(BuildingWorldData data){
        ItemStack stack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta sMeta = (SkullMeta) stack.getItemMeta();
        List<Component> lore = new ArrayList<>();

        lore.add(MiniMessage.miniMessage().deserialize("<gray>Owner: " + data.getOwner().getName()));
        lore.add(MiniMessage.miniMessage().deserialize("<gray>Spieler: " + data.getWorld().getPlayerCount()));

        sMeta.setOwningPlayer(data.getOwner());
        //sMeta.displayName(MiniMessage.miniMessage().deserialize("<color:#40d1db>" + data.getDisplayName()));
        sMeta.setDisplayName(data.getDisplayName());
        sMeta.lore(lore);

        stack.setItemMeta(sMeta);


        return stack;
    }
}
