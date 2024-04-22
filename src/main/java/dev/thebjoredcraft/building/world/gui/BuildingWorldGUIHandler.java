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


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class BuildingWorldGUIHandler implements Listener {
    @EventHandler
    public void onCLick(InventoryClickEvent event){
        BuildingWorldVisitGUI.handle(event);
        BuildingWorldCreateGUI.handle(event);
    }
    @EventHandler
    public void onChat(PlayerChatEvent event){
        if(BuildingWorldCreateGUI.chatInputPlayers.contains(event.getPlayer())){
            event.setCancelled(true);

            if(!event.getMessage().isEmpty()){
                BuildingWorldCreateGUI.open(event.getPlayer(), event.getMessage().replace(" ", ""));
                BuildingWorldCreateGUI.chatInputPlayers.remove(event.getPlayer());
            }else{
                //TODO MESSAGE
            }
        }
    }
}
