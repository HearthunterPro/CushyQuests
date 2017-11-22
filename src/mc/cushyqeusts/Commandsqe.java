package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import MenuQuests.CreateQuestsMenu;

public class Commandsqe implements CommandExecutor {
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			CreateQuestsMenu qm = new CreateQuestsMenu();
			//Player
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("qe") && p.hasPermission("csq.qe")){
				if(args.length == 0){
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe create <name>");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe delete <name>");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe edit <name>");
					p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe list");
				} else if(args[0].equalsIgnoreCase("create")){
					if(args.length == 1){
						p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe create <name>");
					} else {
						File file = new File("plugins//Cushyquests//quests//" + args[1] + ".yml");
						if(file.exists()){
							p.sendMessage("มีชื่อนี้แล้ว");
						} else {
							YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
							cfg.set("Name", "");
							cfg.set("messstart", "");
							cfg.set("messfinish", "");
							try {
								cfg.save(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
							qm.MenuQuests(p,args[1]);
						}
						
					}
					
					
				} else if(args[0].equalsIgnoreCase("delete")){
					if(args.length == 1){
						p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe delete <name>");
					} else {
						File file = new File("plugins//Cushyquests//quests//" + args[1] + ".yml");
						if(file.exists()){
							file.delete();
							p.sendMessage("ลบ" + args[1]);
						} else {
							p.sendMessage("ไม่มีชื่อนี้");
						}
					}
					
					
				} else if(args[0].equalsIgnoreCase("edit")){
					if(args.length == 1){
						p.sendMessage(ChatColor.DARK_AQUA + "[CSQ] : /qe edit <name>");
					} else {
						File file = new File("plugins//Cushyquests//quests//" + args[1] + ".yml");
						if(file.exists()){
							
							qm.MenuQuests(p,args[1]);
						} else {
							p.sendMessage("ไม่มีชื่อนี้");
						}
					}
					
					
				} else if(args[0].equalsIgnoreCase("list")){
					File file = new File("plugins//Cushyquests//quests//");
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
					
				}
			}
			
			
		} else {
			//commander
			
			
		}
		
		
		return false;
	}


}
