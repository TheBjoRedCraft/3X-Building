package dev.thebjoredcraft.building;

import dev.thebjoredcraft.building.data.DataFile;
import dev.thebjoredcraft.building.message.MessageUtil;
import dev.thebjoredcraft.building.world.BuildingWorldData;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DreixBuildingCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1 && args[0].equalsIgnoreCase("disable")){
            if(sender instanceof Player player){
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Möchtest du das Plugin wirklich deaktivieren? Das deaktivieren des Plugins kann zu Fehlern führen. <click:run_command:'/dreixbuilding disable confirm'>[Ja, möchte ich]</click>"));
            }else{
                sender.sendMessage(MiniMessage.miniMessage().deserialize("Möchtest du das Plugin wirklich deaktivieren? - /dreixbuilding disable confirm"));
            }
        }else if(args.length == 2 && args[0].equalsIgnoreCase("disable") && args[1].equalsIgnoreCase("confirm")){
            sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Das Plugin wird disabled...."));
            Bukkit.getPluginManager().disablePlugin(Building3IX.getInstance());
        }
        return false;
    }
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            return StringUtil.copyPartialMatches(args[0], List.of("disable"), new ArrayList<>());
        }
        Collections.sort(completions);
        return completions;
    }
}
