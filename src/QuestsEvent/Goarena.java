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

public class Goarena implements Listener{
	
	
	@SuppressWarnings("unchecked")
	public void addarena(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		List<String> list = (List<String>) cfq.getList("goarena");
		
		if(list != null){
			if(list.toArray().length < 5){
				p.closeInventory();
				File fd = new File("plugins//Cushyquests//chatdata.yml");
				YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
				
				cfd.set(p.getName() + ".setarena", true);
				cfd.set(p.getName() + ".name", name);
				
				try {
					cfd.save(fd);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				p.sendMessage(ChatColor.RED + "[CSQ] : พิมชื่อลงไป (cancel เพือยกเลิก)");
			} else {
				p.sendMessage("[CSQ] : เต็มแล้ว");
			}
		} else {
			p.closeInventory();
			File fd = new File("plugins//Cushyquests//chatdata.yml");
			YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
			
			cfd.set(p.getName() + ".setarena", true);
			cfd.set(p.getName() + ".name", name);
			
			try {
				cfd.save(fd);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			p.sendMessage(ChatColor.RED + "[CSQ] : พิมชื่อลงไป (cancel เพือยกเลิก)");
		}
		
	}
	

	@SuppressWarnings("unchecked")
	public void menuRemove(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		List<String> list = (List<String>) cfq.getList("goarena");
		
		
		Inventory inv = Bukkit.createInventory(null, 9, "goarenaremove-"+name);
		int a = 1;
		if(a == 1){
			if(list != null){
				Object[] ob = list.toArray();
				for(int x = 0;x<list.toArray().length;x++){
					ItemStack item = new ItemStack(Material.GLASS);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName("" + ob[x]);
					item.setItemMeta(meta);
					inv.setItem(x, item);
				}
			}
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "BACK");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		p.openInventory(inv);
		
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void As(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		
		boolean te = cfd.getBoolean(p.getName() + ".setarena");
		if(te){
			e.setCancelled(true);
			String name = cfd.getString(p.getName() + ".name");
			if(e.getMessage().equalsIgnoreCase("cancel")){
				MenuQuests mq = new MenuQuests();
				mq.AddQuests(p, name);
				
				
				cfd.set(p.getName() + "", null);
				
				try {
					cfd.save(fd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			} else {
				File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				List<String> li = (List<String>) cfq.getList("goarena");
				if(li != null){
					Object o = e.getMessage();
					if(li.contains(o)){
						p.sendMessage("[CSQ]มีชื่อนี้แล้ว");
					} else {
						
						li.add("" + e.getMessage());
						cfq.set("goarena", li);
						
						cfd.set(p.getName() + "", null);
						
						try {
							cfq.save(fq);
							cfd.save(fd);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.menuRemove(p, name);
					}
				} else {
					ArrayList<String> list = new ArrayList<String>();
					list.add("" + e.getMessage());
					
					cfq.set("goarena", list);
					
					cfd.set(p.getName() + "", null);
					
					try {
						cfq.save(fq);
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.menuRemove(p, name);
				}
				
				
				
				
			}
			
			
		}
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("goarenaremove-")){
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			String name = e.getInventory().getName().replaceAll("goarenaremove-", "");
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 5){
				if(e.getCurrentItem() != null){
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					List<String> list = (List<String>) cfq.getList("goarena");
					
					list.remove(e.getRawSlot());
					
					cfq.set("goarena", list);
					
					try {
						cfq.save(fq);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.menuRemove(p, name);
					
				}
			}
			if(e.getRawSlot() == 8){
				MenuQuests mq = new MenuQuests();
				mq.AddQuests(p, name);
			}
			
		}
	}
	
	
	
	
}
