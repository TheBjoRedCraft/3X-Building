package dev.thebjoredcraft.building.world;

import java.util.ArrayList;
import java.util.List;

public class BuildingWorld {
    public BuildingWorldData data;
    public static int currentID;

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
