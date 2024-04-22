package dev.thebjoredcraft.building.logger;

import dev.thebjoredcraft.building.message.MessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class LoggerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 2 && args[0].equalsIgnoreCase("get") && !args[1].contains("-")){
            try {
                for (String log : DLogger.getLogs(Integer.parseInt(args[1]))) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + log));
                }
            }catch (NumberFormatException e){
                sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Bitte benutze eine richtige Zahl! 0-" + Integer.MAX_VALUE));
            }
        }else if(args.length > 1 ){
            StringBuilder toLog = new StringBuilder();
            LogType logType = LogType.valueOf(args[0].toUpperCase());

            try {
                for (int i = 1; i < args.length; i++) {
                    toLog.append(args[i]).append(" ");
                }

                DLogger.log(logType, toLog.toString());
            }catch (IllegalArgumentException e){
                sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Bitte benutze einen verfügbaren Log Type!: WARN, ERROR, MESSAGE, INFO, OTHER"));
            }
        }else{
            sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Usage: /logger <logType> <toLog>"));
            sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Usage: /logger get <lines> ------ 0-" + Integer.MAX_VALUE));
        }
        return false;
    }
}
