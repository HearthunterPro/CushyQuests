package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class Commandsql implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("ql") && p.hasPermission("csq.ql")){
				if(args.length == 0){
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /ql create <name> ук worldEdit");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /ql delete <name>");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /ql tp <name>");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /ql list");
				} else if(args[0].equalsIgnoreCase("create")){
					if(args.length == 1){
						
						
					} else {
						Selection s = getWorldEdit().getSelection(p);
						Location ad2 = s.getMinimumPoint();
						int hg = s.getHeight();
						int wg = s.getWidth();
						int lg = s.getLength();
						World wo = s.getWorld();
						File file = new File("plugins//Cushyquests//location//" + args[1]+".yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						if(s != null){
							if(!file.exists()){
								cfg.set("Location", ad2);
								cfg.set("hg", hg);
								cfg.set("wg", wg);
								cfg.set("lg", lg);
								cfg.set("world", wo.getName());
								
								try {
									cfg.save(file);
								} catch (IOException e) {
									e.printStackTrace();
								}
								p.sendMessage("Save location");
							} else {
								
							}
							
						}
						
					}
				} else if(args[0].equalsIgnoreCase("delete")){
					if(args.length == 1){
						
						
					} else {
						File file = new File("plugins//Cushyquests//location//" + args[1]+".yml");
						if(file.exists()){
							file.delete();
							p.sendMessage("Delete Location");
						} else {
							p.sendMessage("Location Exists");
						}
						
					}
					
					
					
				} else if(args[0].equalsIgnoreCase("list")){
					File file = new File("plugins//Cushyquests//location//");
					File[] io = file.listFiles();
					if(args.length == 1){
						
						p.sendMessage(ChatColor.RED + "=====================================");
						if(io.length > 10){
							for(int x = 0;x<10;x++){
								p.sendMessage(x + " - " + io[x].getName());
							}
						} else {
							for(int x = 0;x<io.length;x++){
								p.sendMessage(x + " - " + io[x].getName());
							}
						}
						p.sendMessage(ChatColor.RED + "=====================================");
					} else {
						
						int page = Integer.parseInt(args[1]);
						if(page < 0){
							int i = io.length / 10;
							
							int ix = page * 10;
							
							int st = ix + 10;
							
							if(page <= i){
								if(io.length > st){
									p.sendMessage(ChatColor.RED + "=====================================");
									for(int x = 0;x<10;x++){
										int x2 = x + (ix);
										p.sendMessage(x + " - " + io[x2].getName());
									}
									p.sendMessage(ChatColor.RED + "=====================================");
								} else {
									int str = io.length - ix;
									p.sendMessage(ChatColor.RED + "=====================================");
									for(int x = 0;x<str;x++){
										int x2 = x + (ix);
										p.sendMessage(x + " - " + io[x2].getName());
									}
									p.sendMessage(ChatColor.RED + "=====================================");
									
									
									
								}
								
								
							}
							
							
						}
						
					}
					
					
					
				} else if(args[0].equalsIgnoreCase("tp")){
					if(args.length == 1){
						
					} else {
						File file = new File("plugins//Cushyquests//location//" + args[1]+".yml");
						YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
						
						if(file.exists()){
							
							Location loc = (Location) cfg.get("Location");
							loc.setX(loc.getBlockX() + 0.5);
							loc.setY(loc.getBlockY() + 0.5);
							loc.setZ(loc.getBlockZ() + 0.5);
							p.teleport(loc);
							
						}
						
					}
					
				}
			}
			
			
			
		}
		return false;
	}

	
	
	public WorldEditPlugin getWorldEdit() {
        Plugin p = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (p instanceof WorldEditPlugin) return (WorldEditPlugin) p;
        else return null;
	}
}
