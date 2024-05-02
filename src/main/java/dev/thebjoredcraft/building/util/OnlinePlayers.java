package dev.thebjoredcraft.building.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OnlinePlayers {
    public static List<OfflinePlayer> allOnlinePlayersEver = new ArrayList<>();

    public static List<String> getCurrentOnlinePlayersNames(){
        List<String> currentOnlinePlayersNames = new ArrayList<>();

        for(Player player : Bukkit.getOnlinePlayers()){
            currentOnlinePlayersNames.add(player.getName());
        }
        return currentOnlinePlayersNames;
    }
    public static List<String> getAllOnlinePlayerNamesEver(){
        List<String> allOnlinePlayerNamesEver = new ArrayList<>();

        for(OfflinePlayer player : allOnlinePlayersEver){
            allOnlinePlayerNamesEver.add(player.getName());
        }
        return allOnlinePlayerNamesEver;
    }
    public static void addOnlinePlayersEver(OfflinePlayer player){
        if(!allOnlinePlayersEver.contains(player)) {
            allOnlinePlayersEver.add(player);
        }
    }
}
