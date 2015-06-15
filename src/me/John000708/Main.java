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

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class Main extends JavaPlugin{

    public static Main plugin;

    FileConfiguration c = this.getConfig();

    public static String nmsver;

    private static boolean works = true;

    boolean drop = false;
    int x = c.getInt("droplocation.x");
    int y = c.getInt("droplocation.y");
    int z = c.getInt("droplocation.z");
    String world = c.getString("droplocation.world");


    public void onEnable(){
        nmsver = Bukkit.getServer().getClass().getPackage().getName();
        nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
        this.getServer().getPluginManager().registerEvents(new OreListener(), this);
        this.getServer().getPluginManager().registerEvents(new MinerListener(), this);
        this.getServer().getPluginManager().registerEvents(new FurnaceListener(), this);
        this.getServer().getPluginManager().registerEvents(new JuiceListener(), this);
        c.options().copyDefaults(true);
        plugin = this;
        Items.load();
        Recipes.load();

        //Drop Item start.
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                if (drop == true) {
                    reloadConfig();
                    Bukkit.getWorld(world).dropItem(new Location(Bukkit.getWorld(world), x, y, z), Items.SILVER);
                } else {

                }
            }
        }, 0L, 5L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

            }
        }, 0L, 1L);
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
            else if(args[0].equalsIgnoreCase("juice")){
                p.getInventory().addItem(Items.RAGE_JUICE);
                p.updateInventory();
                return true;
            }

        }
        if(cmd.equalsIgnoreCase("test")){
            sendActionBar((Player)sender, ChatColor.GREEN + "This is a test!");
        }
        return true;

    }
    public static void sendActionBar(Player player, String message){
        try {
            Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + nmsver + ".entity.CraftPlayer");
            Object p = c1.cast(player);
            Object ppoc = null;
            Class<?> c4 = Class.forName("net.minecraft.server." + nmsver + ".PacketPlayOutChat");
            Class<?> c5 = Class.forName("net.minecraft.server." + nmsver + ".Packet");
            if (nmsver.equalsIgnoreCase("v1_8_R1") || !nmsver.startsWith("v1_8_")) {
                Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatSerializer");
                Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                Method m3 = c2.getDeclaredMethod("a", new Class<?>[] {String.class});
                Object cbc = c3.cast(m3.invoke(c2, "{\"text\": \"" + message + "\"}"));
                ppoc = c4.getConstructor(new Class<?>[] {c3, byte.class}).newInstance(new Object[] {cbc, (byte) 2});
            } else {
                Class<?> c2 = Class.forName("net.minecraft.server." + nmsver + ".ChatComponentText");
                Class<?> c3 = Class.forName("net.minecraft.server." + nmsver + ".IChatBaseComponent");
                Object o = c2.getConstructor(new Class<?>[] {String.class}).newInstance(new Object[] {message});
                ppoc = c4.getConstructor(new Class<?>[] {c3, byte.class}).newInstance(new Object[] {o, (byte) 2});
            }
            Method m1 = c1.getDeclaredMethod("getHandle", new Class<?>[] {});
            Object h = m1.invoke(p);
            Field f1 = h.getClass().getDeclaredField("playerConnection");
            Object pc = f1.get(h);
            Method m5 = pc.getClass().getDeclaredMethod("sendPacket",new Class<?>[] {c5});
            m5.invoke(pc, ppoc);
        } catch (Exception ex) {
            ex.printStackTrace();
            works = false;
        }
    }

}
