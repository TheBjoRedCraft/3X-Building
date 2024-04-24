package dev.thebjoredcraft.building;

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


import dev.thebjoredcraft.building.logger.LoggerCommand;
import dev.thebjoredcraft.building.speed.SpeedCommand;
import dev.thebjoredcraft.building.world.BuildingWorldCommand;
import dev.thebjoredcraft.building.world.gui.BuildingWorldGUIHandler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Building3IX extends JavaPlugin {
    public static Building3IX instance;

    @Override
    public void onLoad() {
        instance = this;

    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        getCommand("bworld").setExecutor(new BuildingWorldCommand());
        getCommand("logger").setExecutor(new LoggerCommand());
        getCommand("speed").setExecutor(new SpeedCommand());

        Bukkit.getPluginManager().registerEvents(new BuildingWorldGUIHandler(), this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
        // Plugin shutdown logic
    }
    public static Building3IX getInstance() {
        return instance;
    }
}
