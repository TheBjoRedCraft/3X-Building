package dev.thebjoredcraft.building.world;

import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.io.File;
import java.util.List;

public class BuildingWorldData {
    public World world;
    public @Nullable BuildingWorld buildingWorld;
    public Player owner;
    public List<Player> players;
    public String displayName;
    public int id;

    public BuildingWorldData(World world, @Nullable BuildingWorld buildingWorld, Player owner, List<Player> players, String displayName, int id){
        this.world = world;
        this.buildingWorld = buildingWorld;
        this.owner = owner;
        this.players = players;
        this.displayName = displayName;
        this.id = id;
    }
    public void save(){
        File datafile = new File("");
    }
}
