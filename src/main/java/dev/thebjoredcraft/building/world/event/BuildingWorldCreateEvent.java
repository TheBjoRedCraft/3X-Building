package dev.thebjoredcraft.building.world.event;

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


import dev.thebjoredcraft.building.world.BuildingWorldData;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BuildingWorldCreateEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    public Player player;
    public BuildingWorldData data;

    public BuildingWorldCreateEvent(Player player, BuildingWorldData data){
        this.player = player;
        this.data = data;
    }

    public HandlerList getHandlerList() {
        return HANDLERS;
    }
    public Player getPlayer(){
        return player;
    }
    public BuildingWorldData getData() {
        return data;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

}
