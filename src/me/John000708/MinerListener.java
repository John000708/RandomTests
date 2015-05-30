package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Furnace;

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
    public void onInventoryPlaceEvent(InventoryClickEvent e){
        if(e.getInventory() instanceof FurnaceInventory){
            Furnace f = (Furnace)e.getInventory().getHolder();
            if(f.getFacing())
        }
    }
}
