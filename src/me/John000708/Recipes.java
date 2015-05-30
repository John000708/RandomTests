package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Recipes {

    public static ShapedRecipe MINER;
    public static ShapedRecipe CHIP;

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

        inject();
    }
    private static void inject(){
        Bukkit.addRecipe(MINER);
        Bukkit.addRecipe(CHIP);
    }
}
