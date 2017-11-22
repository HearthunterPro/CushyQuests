package QuestsEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import MenuQuests.MenuQuests;

public class Cicknpc implements Listener{

	
	public void add(Player p,String name){
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		p.closeInventory();
		
		cfd.set(p.getName()+".Chat", "acn");
		cfd.set(p.getName()+".Chatname", name);
		
		try {
			cfd.save(fd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "พิม IDNPC ลงไป cancel");
	}
	
	@SuppressWarnings("unchecked")
	public void remove(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		Inventory inv = Bukkit.createInventory(null, 9, "RemoveCN-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Exit");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		List<String> list = (List<String>) cfq.getList("CN");
		if(list != null){
			Object[] ob = list.toArray();
			for(int x = 0;x<ob.length;x++){
				String type = (String) ob[x];
				
				ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName("" + type);
				item.setItemMeta(meta);
				inv.setItem(x, item);
				
				
				
			}
		}
		
		
		p.openInventory(inv);
	}
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Click(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		MenuQuests mq = new MenuQuests();
		if(e.getInventory().getName().contains("RemoveCN-")){
			String name = e.getInventory().getName().replaceAll("RemoveCN-", "");
			
			e.setCancelled(true);
			if(e.getRawSlot() == 8){
				mq.AddQuests(p, name);
			} else if(e.getRawSlot() >= 0 && e.getRawSlot() <= 7){
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getType() != Material.AIR){

						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						
						
						List<String> list = (List<String>) cfq.getList("CN");
						Object[] ob = list.toArray();
						
						list.remove(ob[e.getRawSlot()]);
						
						cfq.set("CN", list);
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.remove(p, name);
					}
					
					
				}
				
			}
			
			
			
			
			
			
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void As(AsyncPlayerChatEvent e){
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		String name = cfg.getString(p.getName() + ".Chatname");
		String type = cfg.getString(p.getName() + ".Chat");
		if(name != null){
			if(type != null){
				if(type.equalsIgnoreCase("acn")){
					e.setCancelled(true);
					MenuQuests mq = new MenuQuests();
					if(e.getMessage().equals("cancel")){
						
						e.setCancelled(true);
						cfg.set(p.getName()+"", null);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						mq.AddQuests(p, name);
					} else {
						
						if(isInt(e.getMessage())){
							File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
							YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
							
							List<String> list = (List<String>) cfq.getList("CN");
							if(list != null){
								Object o = "NPC" + Integer.parseInt(e.getMessage());
								if(!list.contains(o)){
									
									
									list.add("" + o);
									cfq.set("CN", list);
									
									cfg.set(p.getName()+"", null);
									
									try {
										cfg.save(file);
										cfq.save(fq);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									
									this.remove(p, name);
								} else {
									p.sendMessage("มี NPC นี้แล้ว");
								}
							} else {
								ArrayList<String> l = new ArrayList<String>();
								Object o = "NPC" + Integer.parseInt(e.getMessage());
								l.add("" + o);
								cfq.set("CN", l);
								
								cfg.set(p.getName()+"", null);
								
								try {
									cfg.save(file);
									cfq.save(fq);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								this.remove(p, name);
							}
							
							
							
							
						} else {
							p.sendMessage("ใช่ไม่ได้ ต้องเป็น ID");
						}
						
					}
					
				}
			}
		}
		
		
		
	}
	
	
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	
}
