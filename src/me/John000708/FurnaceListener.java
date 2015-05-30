package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Created by John on 30-May-15.
 */
public class FurnaceListener implements Listener{
    @EventHandler
    public void onOpenInventory(InventoryOpenEvent e){
        if(e.getInventory().getName().equals(ChatColor.GOLD + "Solar Furnace")){
            e.getPlayer().sendMessage("galkg;lgs");
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlockPlaced().getType().equals(Material.FURNACE)){
            Furnace f = (Furnace)e.getBlockPlaced().getState();
            if(f.getInventory().getName().equals(ChatColor.GOLD + "Solar Furnace")) {
                e.getBlockPlaced().getRelative(BlockFace.UP).setType(Material.DAYLIGHT_DETECTOR);
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if (e.getBlock().getType().equals(Material.FURNACE)){
            Furnace f = (Furnace)e.getBlock().getState();
            if(f.getInventory().getName().equals(ChatColor.GOLD + "Solar Furnace")){
                Location loc = new Location(f.getWorld(), f.getX(), f.getY(), f.getZ());
                e.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
                loc.getBlock().setType(Material.AIR);
                Bukkit.getWorld(loc.getWorld().getName()).dropItemNaturally(loc, Items.SOLAR_FURNACE);

            }
        }
    }
}
