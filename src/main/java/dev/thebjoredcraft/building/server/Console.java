package dev.thebjoredcraft.building.server;

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

public class Console {
    public static void log(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <color:#40d1db>" + toLog));
    }
    public static void log(Integer toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <color:#40d1db>" + toLog));
    }
    public static void logError(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <red>" + toLog));
    }
    public static void logWarn(String toLog){
        Bukkit.getConsoleSender().sendMessage(MiniMessage.miniMessage().deserialize(MessageUtil.PREFIX + "3IX-Building <dark_gray>|</dark_gray> <yellow>" + toLog));
    }
}
