package dev.thebjoredcraft.building.world;

public class BuildingWorld {
    public BuildingWorldData data;

    public BuildingWorld(BuildingWorldData data){
        this.data = data;

        BuildingWorldManager.create(data);
    }

    public BuildingWorldData getData() {
        return data;
    }
}
