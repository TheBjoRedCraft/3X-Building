package dev.thebjoredcraft.building.world.queue;

import dev.thebjoredcraft.building.world.BuildingWorld;
import dev.thebjoredcraft.building.world.BuildingWorldManager;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    public static List<BuildingWorld> queue = new ArrayList<>();

    public static void add(BuildingWorld data){
        queue.add(data);
        check();
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
        check();
    }
    public static void list(){
        for(BuildingWorld data : queue) {
            if (data.getData().getOwner().isOnline()) {
                data.getData().getOwner().getPlayer().sendMessage(MiniMessage.miniMessage().deserialize("<color:#3b92d1>>> <red>" + data.getData().getDisplayName()));
            }
        }
    }
}
