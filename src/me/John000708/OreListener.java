package me.John000708;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by John on 28-May-15.
 */
public class OreListener implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        if(e.getBlock().getType().equals(Material.IRON_ORE)){
            double ran = Math.random();
            String msg = String.valueOf(ran);
            if(ran < 0.3){
                e.getPlayer().sendRawMessage(msg);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), Items.SILVER);
            }
        }
    }
}
