package dev.thebjoredcraft.building.world.queue;

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


import dev.thebjoredcraft.building.world.BuildingWorld;
import dev.thebjoredcraft.building.world.BuildingWorldManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    public static List<BuildingWorld> queue = new ArrayList<>();

    public static void add(BuildingWorld data){
        queue.add(data);
        check();
        remove(data);
    }
    public static boolean isEmpty(){
        return queue.isEmpty();
    }
    public static void check(){
        if(!isEmpty()){
            BuildingWorldManager.create(queue.get(0).getData());
        }
    }
    public static void remove(BuildingWorld data){
        queue.remove(data);
    }
    public static void list(){
        for(BuildingWorld data : queue) {
            if (data.getData().getOwner().isOnline()) {
                data.getData().getOwner().getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<color:#3b92d1>>> <red>" + data.getData().getDisplayName()));
            }
        }
    }
}
