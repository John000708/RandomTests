package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Furnace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.TimeUnit;

public class MinerListener implements Listener {
    @EventHandler
    public void onCraftItem(PrepareItemCraftEvent e) {
        if (e.getInventory() instanceof CraftingInventory) {
            if (e.getInventory().getSize() == 10) {
                CraftingInventory inv = e.getInventory();
                if (e.getInventory().getResult().equals(Items.MINER)) {
                    ItemStack chip = inv.getMatrix()[4];
                    if (!Items.CHIP.isSimilar(chip)) {
                        System.out.print("Output air.");
                        inv.setResult(new ItemStack(Material.AIR));
                    }
                }
            }
        }
    }
    @EventHandler
    public void onItemDropEvent(ItemSpawnEvent e){
        if(e.getEntity().getItemStack().equals(Items.SILVER)){
            Bukkit.broadcastMessage("Silver has been dropped!");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException ex) {
                //Handle exception
            }
            Bukkit.broadcastMessage("Item reached the floor.");
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        if(e.getBlockPlaced() instanceof Furnace) {
            Furnace f = (Furnace) e.getBlockPlaced();
            if (f.getInventory().getName().contains("Solar")) {
                e.getBlock().getRelative(BlockFace.UP).setType(Material.DAYLIGHT_DETECTOR);
                e.getPlayer().sendMessage(ChatColor.AQUA + "A solar furnace has been placed!");
            }
        }
    }
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getBlock() instanceof Furnace){
            Furnace f = (Furnace)e.getBlock();
            if(f.getInventory().getName().contains("Solar Furnace")){
                f.setType(Material.AIR);
                f.getBlock().getRelative(BlockFace.UP).setType(Material.AIR);
                Bukkit.getWorld(f.getLocation().getWorld().toString()).dropItemNaturally(f.getLocation(), Items.SOLAR_FURNANCE);
                e.getPlayer().sendMessage(ChatColor.YELLOW + "A solar furnace has been removed!");
            }

        }
    }
}
