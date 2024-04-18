package dev.thebjoredcraft.building.world;

import dev.thebjoredcraft.building.world.queue.Queue;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BuildingWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 2 && args[0].equalsIgnoreCase("create")) {
                int id = BuildingWorld.getCurrentID();
                id ++;

                BuildingWorld.setCurrentID(id);
                Queue.add(new BuildingWorld(new BuildingWorldData(null, null, player, new ArrayList<>(), args[1], id)));
            }
        }
        return false;
    }
}
