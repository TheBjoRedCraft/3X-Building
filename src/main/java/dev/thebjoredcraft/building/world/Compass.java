package dev.thebjoredcraft.building.world;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Compass {
    public static Component displayName = MiniMessage.miniMessage().deserialize("<red>Bau-Welten");
    public static void give(Player player){
        ItemStack item = new ItemStack(Material.COMPASS);
        ItemMeta iMeta = item.getItemMeta();

        iMeta.displayName(displayName);

        item.setItemMeta(iMeta);

        player.getInventory().setItem(4, item);
    }
}
