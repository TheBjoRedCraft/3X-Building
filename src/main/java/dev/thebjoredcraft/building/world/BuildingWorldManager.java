package dev.thebjoredcraft.building.world;

import dev.thebjoredcraft.building.world.queue.Queue;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

public class BuildingWorldManager {
    public static void create(BuildingWorldData data){
        Player owner = data.getOwner();
        owner.showTitle(Title.title(MiniMessage.miniMessage().deserialize("Bitte warte einen Moment..."), MiniMessage.miniMessage().deserialize("...Deine Welt wird erstellt!")));

        World world = Bukkit.createWorld(new WorldCreator(data.getDisplayName() + data.getId()).type(WorldType.FLAT));

        owner.showTitle(Title.title(MiniMessage.miniMessage().deserialize("Bitte warte einen Moment..."), MiniMessage.miniMessage().deserialize("...Du wirst teleportiert!")));
        owner.teleport(world.getSpawnLocation());

        Queue.remove(data.getBuildingWorld());
    }

}
