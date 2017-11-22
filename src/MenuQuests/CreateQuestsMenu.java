package MenuQuests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

public class CreateQuestsMenu implements Listener {

	
	public void MenuQuests(Player p,String name) {
		File file = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Inventory inv = Bukkit.createInventory(null, 9, "CreateQuests-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.NAME_TAG);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "ชื่อเควส");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "Name : " + cfg.getString("Name"));
			
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(0, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.SIGN);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "ข้อความเริ่มเควส");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "Message : " + cfg.getString("messstart"));
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.NETHER_STAR);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "ข้อความเควสตอนจบ");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "Message : " + cfg.getString("messfinish"));
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(2, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "เควส");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกเพือทำรายการ");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(3, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.IRON_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "รางวัล");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกเพือทำรายการ");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(4, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "สิ่งทีต้องมีก่อนทำเควส");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกเพือทำรายการ");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(5, item);
			
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.ANVIL);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "กำหนดเวลาเควส");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกเพือทำรายการ");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(6, item);
			
			
		}
		
		
		p.openInventory(inv);
	}
	
	
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("CreateQuests-")){
			Player p = (Player) e.getWhoClicked();
			String name = e.getInventory().getName().replaceAll("CreateQuests-", "");
			if(e.getRawSlot() == 0){
				
				Slot0(p,name);
			} else if(e.getRawSlot() == 1){
				
				Slot1(p,name);
			} else if(e.getRawSlot() == 2){
				
				Slot2(p,name);
			} else if(e.getRawSlot() == 3){
				MenuQuests maq = new MenuQuests();
				maq.AddQuests(p, name);
			} else if(e.getRawSlot() == 4){
				SetReward sr = new SetReward();
				sr.Menu(p, name);
			} else if(e.getRawSlot() == 5){
				Eqment eq = new Eqment();
				eq.Menu(p, name);
			} else if(e.getRawSlot() == 6){
				time t = new time();
				t.menu(p, name);
			}
			e.setCancelled(true);
		}
		
		
		
		
	}


	private void Slot2(Player p, String name) {
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		p.closeInventory();
		
		cfg.set(p.getName()+".Chat", "edmf");
		cfg.set(p.getName()+".Chatname", name);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "พิมชื่อลงไปได้เลย ยกเลิกพิม cancel");
		
	}
	
	
	private void Slot1(Player p, String name) {
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		p.closeInventory();
		
		cfg.set(p.getName()+".Chat", "edms");
		cfg.set(p.getName()+".Chatname", name);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "พิมชื่อลงไปได้เลย ยกเลิกพิม cancel");
		
	}


	private void Slot0(Player p,String name) {
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		
		p.closeInventory();
		
		cfg.set(p.getName()+".Chat", "ednq");
		cfg.set(p.getName()+".Chatname", name);
		
		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "พิมชื่อลงไปได้เลย ยกเลิกพิม cancel");
		
		
	}
	
	
	@EventHandler
	public void As(AsyncPlayerChatEvent e){
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		String name = cfg.getString(p.getName() + ".Chatname");
		String type = cfg.getString(p.getName() + ".Chat");
		if(name != null){
			if(type != null){
				if(type.equalsIgnoreCase("ednq")){
					if(e.getMessage().equals("cancel")){
						this.MenuQuests(p, name);
						e.setCancelled(true);
						cfg.set(p.getName() + "", null);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						cfq.set("Name", e.getMessage());
						
						cfg.set(p.getName() + "", null);
						
						try {
							cfq.save(fq);
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.MenuQuests(p, name);
						e.setCancelled(true);
					}
				} else if(type.equalsIgnoreCase("edms")){
					if(e.getMessage().equals("cancel")){
						this.MenuQuests(p, name);
						e.setCancelled(true);
						cfg.set(p.getName() + "", null);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						cfq.set("messstart", e.getMessage());
						
						cfg.set(p.getName() + "", null);
						
						try {
							cfq.save(fq);
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.MenuQuests(p, name);
						e.setCancelled(true);
					}
				} else if(type.equalsIgnoreCase("edmf")){
					if(e.getMessage().equals("cancel")){
						this.MenuQuests(p, name);
						e.setCancelled(true);
						cfg.set(p.getName() + "", null);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						cfq.set("messfinish", e.getMessage());

						cfg.set(p.getName() + "", null);
						
						try {
							cfq.save(fq);
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.MenuQuests(p, name);
						e.setCancelled(true);
					}
				}
				
				
				
			}
		}
		
	}
	
	
}
