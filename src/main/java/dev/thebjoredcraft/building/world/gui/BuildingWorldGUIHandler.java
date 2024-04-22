package dev.thebjoredcraft.building.world.gui;

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
