package dev.thebjoredcraft.building.announcement;

import dev.thebjoredcraft.building.message.MessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class AnnouncementManager {
    public static void announceToWorld(String toAnnounce, World world){
        for(Player player : world.getPlayers()){
            player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + toAnnounce));
        }
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + toAnnounce));
    }
    public static void announceToServer(String toAnnounce){
        Bukkit.broadcast(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + toAnnounce));
    }
}
