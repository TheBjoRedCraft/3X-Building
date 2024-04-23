package dev.thebjoredcraft.building.world;

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


import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class BuildingWorldData {
    public World world;
    public @Nullable BuildingWorld buildingWorld;
    public OfflinePlayer owner;
    public List<OfflinePlayer> players;
    public String displayName;
    public Integer id;

    public BuildingWorldData(@Nullable World world, @Nullable BuildingWorld buildingWorld, @Nullable OfflinePlayer owner, @Nullable List<OfflinePlayer> players, @Nullable String displayName, @Nullable Integer id){
        this.world = world;
        this.buildingWorld = buildingWorld;
        this.owner = owner;
        this.players = players;
        this.displayName = displayName;
        this.id = id;
    }

    @Nullable
    public BuildingWorld getBuildingWorld() {
        return buildingWorld;
    }

    public int getId() {
        return id;
    }

    public World getWorld() {
        return world;
    }

    public OfflinePlayer getOwner() {
        return owner;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<OfflinePlayer> getPlayers() {
        return players;
    }

    public void setBuildingWorld(@Nullable BuildingWorld buildingWorld) {
        this.buildingWorld = buildingWorld;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setPlayers(List<OfflinePlayer> players) {
        this.players = players;
    }
}
