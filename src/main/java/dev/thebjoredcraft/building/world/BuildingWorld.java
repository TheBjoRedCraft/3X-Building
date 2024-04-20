package dev.thebjoredcraft.building.world;


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
