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


import dev.thebjoredcraft.building.message.MessageUtil;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI1;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI2;
import dev.thebjoredcraft.building.world.gui.member.BuildingWorldMemberGUI3;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class BuildingWorldGUIHandler implements Listener {
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

            if(!event.getMessage().isEmpty() || !event.getMessage().equals("cancel") || !event.getMessage().equals("abbrechen")){
                BuildingWorldCreateGUI.open(event.getPlayer(), event.getMessage().replace(" ", ""));
                BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
            }else{
                event.getPlayer().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Vorgang wurde abgebrochen!"));
                BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
            }
        }
    }
}
