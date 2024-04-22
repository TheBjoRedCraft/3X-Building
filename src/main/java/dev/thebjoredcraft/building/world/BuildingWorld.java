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



import java.util.HashMap;

public class BuildingWorld {
    public BuildingWorldData data;
    public static int currentID;
    public static HashMap<Integer, BuildingWorld> buildingWorlds = new HashMap<>();

    public BuildingWorld(BuildingWorldData data){
        this.data = data;

        BuildingWorldManager.create(data);
    }

    public BuildingWorldData getData() {
        return data;
    }

    public static int getCurrentID() {
        return currentID;
    }

    public static void setCurrentID(int currentID) {
        BuildingWorld.currentID = currentID;
    }
}
