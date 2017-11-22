package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Commandsqc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("qc") && p.hasPermission("csq.qc")){
				if(args.length == 0){	
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qc create <name> <number> <nametitle>");
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qc edit <name>");
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qc delete <name>");
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qc rename <name> <title>");
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qc list");
				} else if(args[0].equalsIgnoreCase("create")){
					if(args.length == 1){
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qc create <name> <1-6> <nametitle>");
					} else {
						if(args.length == 2){
							p.sendMessage(ChatColor.AQUA + "[CQ] : /qc create <name> <1-6> <nametitle>");
						} else {
							File file = new File("plugins//Cushyquests//catalog//" + args[1] + ".yml");
							if(!file.exists()){
								YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
								int nums = Integer.parseInt(args[2]);
								if(nums > 0 && nums <= 6){
									int a = nums*9;
									cfg.set("sizeinv", a);
									cfg.set("title", args[3]);
									try {
										cfg.save(file);
									} catch (IOException e) {
										e.printStackTrace();
									}
									Inventory inv = Bukkit.createInventory(null, a, "invedit-" + args[1]);
									
									p.openInventory(inv);
									p.sendMessage(ChatColor.AQUA + "[CQ] : ");
								} else {
									p.sendMessage(ChatColor.AQUA + "[CQ] : ต้องเป็นตัวเลขเท่านั้น 1 - 6");
								}
								
							} else {
								p.sendMessage(ChatColor.AQUA + "[CQ] : มีชื่อนี้แล้ว");
							}
						}
					}
				} else if(args[0].equalsIgnoreCase("edit")){
					if(args.length == 1){
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qc edit <name>");
					} else {
						File file = new File("plugins//Cushyquests//catalog//" + args[1] + ".yml");
						if(file.exists()){
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);							
								int a = cfg.getInt("sizeinv");
								Inventory inv = Bukkit.createInventory(null, a, "invedit-" + args[1]);
								for(int x = 0;x<=a;x++){
									ItemStack ds = cfg.getItemStack("inv."+x);
									if(ds != null){
										inv.setItem(x, ds);
									}
								}
								p.openInventory(inv);
								p.sendMessage(ChatColor.AQUA + "[CQ] : Q = แก้ไข Item");
								p.sendMessage(ChatColor.AQUA + "[CQ] : SHITE+Click = Save Catalog");
							
						} else {
							p.sendMessage(ChatColor.AQUA + "[CQ] : ม่มีชื่อนี้");
						}
					}
				} else if(args[0].equalsIgnoreCase("delete")){
					if(args.length == 1){
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qc delete <name>");
					} else {
						
					}
				} else if(args[0].equalsIgnoreCase("rename")){
					if(args.length == 1){
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qc rename <name> <title>");
					} else {
						if(args.length == 2){
							p.sendMessage(ChatColor.AQUA + "[CQ] : /qc rename <name> <title>");
						} else {
							File file = new File("plugins//Cushyquests//catalog//" + args[1] + ".yml");
							if(file.exists()){
								YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);	
								
								cfg.set("title", args[2]);
								
								try {
									cfg.save(file);
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								p.sendMessage(ChatColor.AQUA + "[CQ] : ม่มีชื่อนี้");
							}
						}
					}
				} else if(args[0].equalsIgnoreCase("list")){
					File file = new File("plugins//Cushyquests//catalog//");
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
				} else {
					p.sendMessage(ChatColor.GREEN + "[CQ] : ไม่มีคำสั่ง ");
				}
			}
		} else {
			
			
		}
		return true;
	}

}
