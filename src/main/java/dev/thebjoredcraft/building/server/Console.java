package dev.thebjoredcraft.building.server;

import dev.thebjoredcraft.building.message.MessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class Console {
    public static void log(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <color:#40d1db>"));
    }
    public static void logError(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <red>"));
    }
    public static void logWarn(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <yellow>"));
    }
}
