package dev.thebjoredcraft.building.invsee;
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

import dev.thebjoredcraft.building.message.MessageUtil;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player player){
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target != null){
                    player.openInventory(target.getInventory());
                }else{
                    player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Der Spieler wurde nicht gefunden!"));
                }
            }else{
                player.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Usage: /invsee <player>"));
            }
        }else{
            sender.sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "Du musst ein Spieler sein um diesen Command auszuführen!"));
        }
        return false;
    }
}
