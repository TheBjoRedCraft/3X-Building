package dev.thebjoredcraft.building.speed;

import dev.thebjoredcraft.building.message.MessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SpeedCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player) {
            if (args.length == 1) {
                 int speed = Integer.parseInt(args[0]);
                try {
                    switch (speed){
                        case 0:
                            player.setFlySpeed(0);
                            player.setWalkSpeed(0);
                        case 1:
                            player.setFlySpeed(0.1f);
                            player.setWalkSpeed(0.1f);
                        case 2:
                            player.setFlySpeed(0.2f);
                            player.setWalkSpeed(0.2f);
                        case 3:
                            player.setFlySpeed(0.3f);
                            player.setWalkSpeed(0.3f);
                        case 4:
                            player.setFlySpeed(0.4f);
                            player.setWalkSpeed(0.4f);
                        case 5:
                            player.setFlySpeed(0.5f);
                            player.setWalkSpeed(0.5f);
                        case 6:
                            player.setFlySpeed(0.6f);
                            player.setWalkSpeed(0.6f);
                        case 7:
                            player.setFlySpeed(0.7f);
                            player.setWalkSpeed(0.7f);
                        case 8:
                            player.setFlySpeed(0.8f);
                            player.setWalkSpeed(0.8f);
                        case 9:
                            player.setFlySpeed(0.9f);
                            player.setWalkSpeed(0.9f);
                        case 10:
                            player.setFlySpeed(1f);
                            player.setWalkSpeed(1f);
                    }
                }catch (NumberFormatException e){
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "/speed 1-10"));
                }
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "/speed 1-10"));
            }
        }
        return false;
    }
}
