package me.John000708;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;


public class Main extends JavaPlugin{
    public static Main plugin;
    FileConfiguration c = this.getConfig();
    boolean drop = false;
    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    int x = c.getInt("droplocation.x");
    int y = c.getInt("droplocation.y");
    int z = c.getInt("droplocation.z");
    String world = c.getString("droplocation.world");


    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(new OreListener(), this);
        this.getServer().getPluginManager().registerEvents(new MinerListener(), this);
        this.getServer().getPluginManager().registerEvents(new FurnaceListener(), this);
        c.options().copyDefaults(true);
        plugin = this;
        Items.load();
        Recipes.load();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable(){
            @Override
        public void run(){
                if(drop == true){
                    reloadConfig();
                    Bukkit.getWorld(world).dropItem(new Location(Bukkit.getWorld(world), x, y, z), Items.SILVER);
                }else{

                }
            }
        }, 0L, 40L);

    }
    public void onDisable() {
        plugin = null;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if(cmd.equalsIgnoreCase("setdrop")){
            if(sender instanceof Player){
                Player p = (Player)sender;
                Double x = p.getLocation().getX();
                Double y = p.getLocation().getY();
                Double z = p.getLocation().getZ();

                String world = p.getWorld().getName();
                c.set("droplocation.world", world);
                c.set("droplocation.x", x.intValue());
                c.set("droplocation.y", y.intValue());
                c.set("droplocation.z", z.intValue());
                p.sendRawMessage(x.toString());
                p.sendRawMessage(y.toString());
                p.sendRawMessage(z.toString());
                this.saveConfig();
            }
        }
        //Starts the drops.
        if(cmd.equalsIgnoreCase("dropstart")){
            drop = true;
            sender.sendMessage(ChatColor.GREEN + "Dropping started");
        }

        //Stops the drops.
        if(cmd.equalsIgnoreCase("dropstop")){
            reloadConfig();
            drop = false;
            sender.sendMessage(ChatColor.GREEN + "Dropping stopped");
        }

        //Show a block at the setdrop location TODO: Make it refreshable without a reload needed
        if(cmd.equalsIgnoreCase("show")) {
            Bukkit.getWorld(world).getBlockAt(x,y,z).setType(Material.WOOL);
        }

        //Give Items to the player.
        if(cmd.equalsIgnoreCase("giveitem")){

            if(!(sender instanceof Player)){
                sender.sendMessage("You need to be a player to execute this command!");
            }

            if(args.length == 0){
                sender.sendMessage("You need to specify an item!");
                return false;
            }

            Player p = (Player)sender;
            if(args[0].equalsIgnoreCase("MINER")){
                p.getInventory().addItem(Items.MINER);
                p.updateInventory();
                return true;
            }
            else if(args[0].equalsIgnoreCase("CHIP")){
                p.getInventory().addItem(Items.CHIP);
                p.sendMessage("fgvaga");
                p.updateInventory();
                return true;
            }
            else if(args[0].equalsIgnoreCase("SILVER")){
                p.getInventory().addItem(Items.SILVER);
                p.updateInventory();
                return true;
            }
        }
        return true;

    }

}
