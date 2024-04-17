package dev.thebjoredcraft.building.world.event;

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
