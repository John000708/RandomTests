package me.John000708;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by John on 31-May-15.
 */
public class JuiceListener implements Listener {
    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent e) {
        if(e.getItem().getType().equals(Material.POTION) && e.getItem().getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "RAGE JUIIIIIIIIIICE!"))
            e.getPlayer().sendMessage(ChatColor.GREEN + "Has");
            e.getPlayer().sendMessage(ChatColor.GREEN + "Has");
            e.getPlayer().sendMessage(ChatColor.GREEN + "Yas");
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 20*20, 5, true));
    }
}
