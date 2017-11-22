package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import opquests.Questsview;
import opquests.Questsview2;
import opquests.invitQuests;

public class Commands implements CommandExecutor {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("quests") && p.hasPermission("csq.use")){
				
				if(args.length == 0){
					String text = "&a[CSQ] :&b/&eqe   :   ���ҧ Quests".replaceAll("(&([a-z0-9]))", "\u00A7$2");
					String text2 = "&a[CSQ] :&b/&eqs  :   ��� Npc".replaceAll("(&([a-z0-9]))", "\u00A7$2");
					String text4 = "&a[CSQ] :&b/&eql :  �ش�ͧQuests".replaceAll("(&([a-z0-9]))", "\u00A7$2");
					String text6 = "&a[CSQ] :&b/&eqc  :  ���ҧ������͡".replaceAll("(&([a-z0-9]))", "\u00A7$2");
					p.sendMessage(ChatColor.GREEN + "/q : Chack my Quests");
					p.sendMessage(ChatColor.GREEN + "/quests help : all command ");
					p.sendMessage(text);
					p.sendMessage(text2);
					p.sendMessage(text4);
					p.sendMessage(text6);
					p.sendMessage(ChatColor.RED + "Plugin Version 1.0");
					p.sendMessage(ChatColor.RED + "Plugin SUPPORT Ver 1.12");
					p.sendMessage(ChatColor.RED + "Plugin CR. CushyPro");
					//end 
				} else if(args[0].equalsIgnoreCase("help")){
					p.sendMessage("/quests give : �Ѻ��ʷѹ��");
					p.sendMessage("/quests assign : ��˹��Ѻ���");
					p.sendMessage("/quests reset : ������������");
					p.sendMessage("/quests view [Player] : ��");
				} else if(args[0].equalsIgnoreCase("assign")){
					if(args.length == 1){
						p.sendMessage("/quests assign [Player] [Quests]");
					} else {
						if(args.length == 2){
							p.sendMessage("/quests assign [Player] [Quests]");
						} else {
							Player ps = Bukkit.getPlayer(args[1]);
							if(ps != null){
								String name = args[2];
								File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
								if(fq.exists()){
									invitQuests iq = new invitQuests();
									iq.assing(ps, name);
								} else {
									p.sendMessage(ChatColor.RED + "[csq] : ����ժ�����ʹ��");
								}
								
							} else {
								p.sendMessage(ChatColor.RED + "[CSQ] : �����蹹��������͹��������㹢�й��");
							}
							
							
						}
					}
				} else if(args[0].equalsIgnoreCase("give")){
					if(args.length == 1){
						p.sendMessage("/quests give [Player] [Quests]");
					} else {
						if(args.length == 2){
							p.sendMessage("/quests give [Player] [Quests]");
						} else {
							Player ps = Bukkit.getPlayer(args[1]);
							if(ps != null){
								invitQuests iq = new invitQuests();
								String name = args[2];
								File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
								if(fq.exists()){
									iq.Checkquestslist(ps, name, p);
								} else {
									p.sendMessage(ChatColor.RED + "[CSQ] : ���������������������");
								}
								
								
							}
							
						}
					}
					
					
					
				} else if(args[0].equalsIgnoreCase("reset")){
					if(args.length == 1){
						
						p.sendMessage(ChatColor.RED + "[CSQ] : /quests reset [Player] [Quests]");
						
						
					} else {
						if(args.length == 2){
							p.sendMessage(ChatColor.RED + "[CSQ] : /quests reset [Player] [Quests]");
						} else {
							
							Player ps = Bukkit.getPlayer(args[1]);
							
							String id = args[2];
							
							File fp = new File("plugins//Cushyquests//Player//" + ps.getName() + ".yml");
							YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
							
							if(fp.exists()){

								List<String> li = (List<String>) cfp.getList("Quests.complete");
								if(li != null){
									if(li.contains("" + id)){
										li.remove("" + id);
										
										cfp.set("Quests.complete", li);
										
										cfp.set("Quests.time." + id, null);
										
										
										try {
											cfp.save(fp);
										} catch (IOException e) {
											e.printStackTrace();
										}
										p.sendMessage(ChatColor.LIGHT_PURPLE + "[CSQ] : ź����");
									} else {
										p.sendMessage(ChatColor.LIGHT_PURPLE + "[CSQ] : ��������");
									}
								}
							} else {
								p.sendMessage(ChatColor.RED + "[CSQ] : ����բ�����");
							}
							
							
							
						}
						
						
						
					}
					
					
				} else if(args[0].equalsIgnoreCase("view")){
					if(args.length == 1){
						p.sendMessage(ChatColor.RED + "[CSQ] : /quests view [Player]");
					} else {
						Player ps = Bukkit.getPlayer(args[1]);
						
						File fp = new File("plugins//Cushyquests//Player//" + ps.getName() + ".yml");
						
						if(fp.exists()){
							Questsview2 qv = new Questsview2();
							qv.openq(p);
						} else {
							p.sendMessage(ChatColor.RED + "[CSQ] : ����բ�����");
						}
						
						
					}
					
					
				} else if(args[0].equalsIgnoreCase("test")){
					ItemStack item = p.getItemInHand();
					
					ItemStack item2 = p.getInventory().getItem(8);
					
					
					if(item2.equals(item)){
						p.sendMessage("true");
					} else {
						p.sendMessage("false");
					}
					
					
				} else {
					
					
				}
				
				
			} else if(cmd.getName().equalsIgnoreCase("q")){
				if(args.length == 0){
					Questsview qv = new Questsview();
					qv.openq(p);
				} else if(args[0].equalsIgnoreCase("menu")){
					invitQuests iq = new invitQuests();
					String name = args[1];
					String type = args[2];
					iq.menu(p, name, type);
				} else if(args[0].equalsIgnoreCase("add")){
					invitQuests iq = new invitQuests();
					String name = args[1];
					iq.Checkquestslist(p, name, null);
				} else if(args[0].equalsIgnoreCase("view")){
					invitQuests iq = new invitQuests();
					String name = args[1];
					iq.assing(p, name);
				} else if(args[0].equalsIgnoreCase("v")){
					Questsview qv = new Questsview();
					String name = args[1];
					String type = args[2];
					qv.v(p, name, type);
				} else if(args[0].equalsIgnoreCase("remove")){
					invitQuests iq = new invitQuests();
					int slot = Integer.parseInt(args[1]);
					iq.removequests(p, slot);
					
					Questsview qv = new Questsview();
					qv.openq(p);
					
				} else if(args[0].equalsIgnoreCase("reward")){
					invitQuests iq = new invitQuests();
					int slot = Integer.parseInt(args[1]);
					
					iq.givereward(p, slot);
					
					Questsview qv = new Questsview();
					qv.openq(p);
					
				} else if(args[0].equalsIgnoreCase("qcom")){
					Questsview qv = new Questsview();
					qv.openqquestscom(p);
					
				}
				
				
				
				
			}
			
			
			
		} else {
			if(cmd.getName().equalsIgnoreCase("quests")){
				if(args.length == 0){
					
				}  else if(args[0].equalsIgnoreCase("assign")){
					if(args.length == 1){
						Bukkit.getConsoleSender().sendMessage("/quests assign [Player] [Quests]");
					} else {
						if(args.length == 2){
							Bukkit.getConsoleSender().sendMessage("/quests assign [Player] [Quests]");
						} else {
							Player ps = Bukkit.getPlayer(args[1]);
							if(ps != null){
								String name = args[2];
								File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
								if(fq.exists()){
									invitQuests iq = new invitQuests();
									iq.assing(ps, name);
								} else {
									Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[csq] : ����ժ�����ʹ��");
								}
								
							} else {
								Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[CSQ] : �����蹹��������͹��������㹢�й��");
							}
							
							
						}
					}
				}
			}
		}
		
		
		return true;
	}

}
