package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Commandsqs implements CommandExecutor {

	private static String nm = null;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("qs") && p.hasPermission("csq.ql")){
				if(args.length == 0){
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel <catalog/quests/command>");
					p.sendMessage(ChatColor.AQUA + "[CQ] : /qs remove <idnpc>");
				} else if(args[0].equalsIgnoreCase("sel")){
					if(args.length == 1){
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel <catalog/quests/command>");
						p.sendMessage(ChatColor.AQUA + "[CQ] : /qs remove <idnpc>");
					} else {
						if(args[1].equalsIgnoreCase("catalog")){
							if(args.length == 2){
								p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel catalog <idnpc> <namecatalog>");
							} else {
								if(args.length == 3){
									p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel catalog <idnpc> <namecatalog>");
								} else {
									File file = new File("plugins//Citizens//saves.yml");
									YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
									int asd = Integer.parseInt(args[2]);
									String nam = cfg.getString("npc."+asd);
									if(nam != null){
										File file1 = new File("plugins//Cushyquests//npc.yml");
										YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
										cfg1.set("npc."+asd+"."+args[1], args[3]);
										try {
											cfg1.save(file1);
										} catch (IOException e) {
											e.printStackTrace();
										}
										EventNPC en = new EventNPC();
										en.add(asd);
										
										p.sendMessage("set npc แล้ว");
									} else {
										p.sendMessage("ไม่มี NPC ตัวนี้");
									}
								}
							}
						} else if(args[1].equalsIgnoreCase("quests")){
							if(args.length == 2){
								p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel quests <idnpc> <namequests>");
							} else {
								if(args.length == 3){
									p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel quests <idnpc> <namequests>");
								} else {
									File file = new File("plugins//Citizens//saves.yml");
									YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
									int asd = Integer.parseInt(args[2]);
									String nam = cfg.getString("npc."+asd);
									if(nam != null){
										File file1 = new File("plugins//Cushyquests//npc.yml");
										YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
										cfg1.set("npc."+asd+"."+args[1], args[3]);
										try {
											cfg1.save(file1);
										} catch (IOException e) {
											e.printStackTrace();
										}
										EventNPC en = new EventNPC();
										en.add(asd);
										p.sendMessage("Set Quests");
									} else {
										p.sendMessage("ไม่มี NPC ตัวนี้");
									}
								}
							}
						} else if(args[1].equalsIgnoreCase("command")){
							if(args.length == 2){
								p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel command <idnpc> <command>");
							} else {
								if(args.length == 3){
									p.sendMessage(ChatColor.AQUA + "[CQ] : /qs sel command <idnpc> <command>");
								} else {
									File file = new File("plugins//Citizens//saves.yml");
									YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
									int asd = Integer.parseInt(args[2]);
									String nam = cfg.getString("npc."+asd);
									if(nam != null){
										File file1 = new File("plugins//Cushyquests//npc.yml");
										YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
										
										String com = check(args);
										
										cfg1.set("npc."+asd+"."+args[1], com);
										try {
											cfg1.save(file1);
										} catch (IOException e) {
											e.printStackTrace();
										}
										EventNPC en = new EventNPC();
										en.add(asd);
										p.sendMessage("Set Command");
									} else {
										p.sendMessage("ไม่มี NPC ตัวนี้");
									}
								}
							}
						}
					}
				} else if(args[0].equalsIgnoreCase("remove")){
					if(args.length == 1){
						
					} else {
						File file1 = new File("plugins//Cushyquests//npc.yml");
						YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
						int id = Integer.parseInt(args[1]);
						cfg1.set("npc."+id, null);
						
						
						try {
							cfg1.save(file1);
						} catch (IOException e) {
							e.printStackTrace();
						}
						EventNPC en = new EventNPC();
						en.remove(id);
						
						
						p.sendMessage("[CSQ] : remove qs");
					}
					
					
					
				}
				
				
			}
			
		} else {
			
			
			
		}
		
		
		return true;
	}

	private String check(String[] args) {
		nm = "";
		int max = args.length - 3;
		for(int x = 0;x<max;x++){
			int a = x + 3;
			
			nm = nm + args[a] + " ";
			
		}
		
		
		
		
		return nm;
	}

}
