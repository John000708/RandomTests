package me.John000708;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class Items {

    public static ItemStack MINER;
    public static ItemStack CHIP;
    public static ItemStack SILVER;
    public static ItemStack SOLAR_FURNACE;
    public static ItemStack RAGE_JUICE;

    private static ItemMeta meta;


    protected static void load(){
        /* Miner */
        MINER = new ItemStack(Material.DIAMOND_BLOCK);
        meta = MINER.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Bitcoin Miner");
        meta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "A slow bitcoin miner!"));
        MINER.setItemMeta(meta);

        /* Chip */
        CHIP = new ItemStack((Material.CARPET));
        meta = CHIP.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "CHIP");
        meta.setLore(Arrays.asList(ChatColor.DARK_PURPLE + "A basic component used to many other recipes."));
        meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        CHIP.setItemMeta(meta);

        /* Silver */
        SILVER = new ItemStack(Material.IRON_INGOT, 1);
        meta = SILVER.getItemMeta();
        meta.setDisplayName(ChatColor.BLUE + "Silver");
        meta.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
        SILVER.setItemMeta(meta);

        /* SOLAR_FURNACE */
        SOLAR_FURNACE = new ItemStack(Material.FURNACE);
        meta = SOLAR_FURNACE.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "Solar Furnace");
        SOLAR_FURNACE.setItemMeta(meta);

        /* RAGE_JUICE */
        RAGE_JUICE = new ItemStack(Material.POTION);
        meta = RAGE_JUICE.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "RAGE JUIIIIIIIIIICE!");
        RAGE_JUICE.setItemMeta(meta);

    }


}
