package me.John000708;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class Recipes {

    public static ShapedRecipe MINER;
    public static ShapedRecipe CHIP;
    public static ShapelessRecipe RAGE_JUICE;

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


        /* RAGE_JUICE */
        RAGE_JUICE = new ShapelessRecipe(Items.RAGE_JUICE);
        RAGE_JUICE.addIngredient(1, Material.LAVA_BUCKET);
        RAGE_JUICE.addIngredient(1, Material.GLASS_BOTTLE);

        inject();
    }
    private static void inject(){
        Bukkit.addRecipe(MINER);
        Bukkit.addRecipe(CHIP);
        Bukkit.addRecipe(RAGE_JUICE);
    }
}
