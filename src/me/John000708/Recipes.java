package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Recipes {

    public static ShapedRecipe MINER;
    public static ShapedRecipe CHIP;
    public static ShapedRecipe SOLAR_FURNANCE;

    protected static void load(){

        /* MINER */
        MINER = new ShapedRecipe(Items.MINER);
        MINER.shape("DDD", "DGD", "DDD");
        MINER.setIngredient('D', Material.DIAMOND_BLOCK);
        MINER.setIngredient('G', Material.CARPET); // <-- CHIP

        /* CHIP */
        CHIP = new ShapedRecipe(Items.CHIP);
        CHIP.shape("T T", "   ", "T T");
        CHIP.setIngredient('T', Material.GOLD_BLOCK);

        /* SOLAR_FURNANCE */
        SOLAR_FURNANCE = new ShapedRecipe(Items.SOLAR_FURNANCE);
        SOLAR_FURNANCE.shape(" X ", "BFB", "QQQ");
        SOLAR_FURNANCE.setIngredient('X', Material.DAYLIGHT_DETECTOR);
        SOLAR_FURNANCE.setIngredient('B', Material.REDSTONE_BLOCK);
        SOLAR_FURNANCE.setIngredient('F', Material.FURNACE);
        SOLAR_FURNANCE.setIngredient('Q', Material.QUARTZ);

        inject();
    }
    private static void inject(){
        Bukkit.addRecipe(MINER);
        Bukkit.addRecipe(CHIP);
        Bukkit.addRecipe(SOLAR_FURNANCE);
    }
}
