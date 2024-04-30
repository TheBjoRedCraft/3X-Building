package dev.thebjoredcraft.building.announcement;

import org.bukkit.World;

import javax.annotation.Nullable;

public class Announcement {
    public String toAnnounce;
    public AnnouncementType type;
    public World world;

    public Announcement(String toAnnounce, AnnouncementType type){
        this.toAnnounce = toAnnounce;
        this.type = type;

        AnnouncementManager.announceToServer(toAnnounce);
    }
    public Announcement(String toAnnounce, AnnouncementType type, World world){
        this.toAnnounce = toAnnounce;
        this.type = type;
        this.world = world;

        AnnouncementManager.announceToWorld(toAnnounce, world);
    }

    public String getToAnnounce() {
        return toAnnounce;
    }

    public AnnouncementType getType() {
        return type;
    }

    public @Nullable World getWorld() {
        return world;
    }
}
