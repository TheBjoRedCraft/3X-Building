package dev.thebjoredcraft.building.world;

import dev.thebjoredcraft.building.message.MessageUtil;
import dev.thebjoredcraft.building.world.queue.Queue;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class BuildingWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
                int id = BuildingWorld.getCurrentID();
                id ++;

                BuildingWorld.setCurrentID(id);
                Queue.add(new BuildingWorld(new BuildingWorldData(null, null, player, new ArrayList<>(), args[1], id)));

            }else if(args.length == 2 && args[1].equalsIgnoreCase("delete")){
                BuildingWorldManager.delete(args[0]);
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Die Bau-Welt wurde gelöscht!"));
            }else if(args.length == 2 && args[1].equalsIgnoreCase("visit") || args.length == 2 && args[1].equalsIgnoreCase("v")){
                BuildingWorldManager.visit(player, args[0]);
            }else if(args.length == 2 && args[1].equalsIgnoreCase("info")){
                BuildingWorldManager.info(player, args[0]);
            }else if(args.length == 4 && args[1].equalsIgnoreCase("members") && args[2].equalsIgnoreCase("add")){
                Player target = Bukkit.getPlayer(args[3]);
                if(target != null){
                    BuildingWorldManager.addMember(target, args[0]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde hinzugefügt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde nicht gefunden!"));
                }
            }else if(args.length == 4 && args[1].equalsIgnoreCase("members") && args[2].equalsIgnoreCase("remove")){
                Player target = Bukkit.getPlayer(args[3]);
                if(target != null){
                    BuildingWorldManager.removeMember(target, args[0]);
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde entfernt"));
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde nicht gefunden!"));
                }
            }
        }
        return false;
    }
}
