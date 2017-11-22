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
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import MenuQuests.MenuQuests;

public class BlockPlace implements Listener{

	

	
	public void addquests(Player p,String name){
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		p.closeInventory();
		
		cfd.set(p.getName()+".Chat", "abp");
		cfd.set(p.getName()+".Chatname", name);
		
		try {
			cfd.save(fd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "โยนบล็อกทิ้งออกไปเพือทำรายการ ยกเลิกพิม cancel");
		
		
	}
	
	@SuppressWarnings("unchecked")
	public void removequests(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		Inventory inv = Bukkit.createInventory(null, 18, "RemoveBP-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Exit");
			item.setItemMeta(meta);
			inv.setItem(17, item);
		}
		
		List<String> list = (List<String>) cfq.getList("BlockP");
		if(list != null){
			Object[] ob = list.toArray();
			for(int x = 0;x<ob.length;x++){
				String type = (String) ob[x];
				int data = cfq.getInt("Quests.BlockP." + type + ".data");
				int am = cfq.getInt("Quests.BlockP." + type + ".am");
				
				if(am > 64){
					ItemStack item = new ItemStack(Material.getMaterial(type),64,(short) data);
					ItemMeta meta = item.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("am : "+am);
					meta.setLore(lore);
					item.setItemMeta(meta);
					inv.setItem(x, item);
				} else {
					ItemStack item = new ItemStack(Material.getMaterial(type),am,(short) data);
					
					inv.setItem(x, item);
				}
			}
		}
		
		
		p.openInventory(inv);
	}
	
	@SuppressWarnings("unchecked")
	public void listquests(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		Inventory inv = Bukkit.createInventory(null, 18, "ListBP-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Exit");
			item.setItemMeta(meta);
			inv.setItem(17, item);
		}
		
		List<String> list = (List<String>) cfq.getList("BlockP");
		if(list != null){
			Object[] ob = list.toArray();
			for(int x = 0;x<ob.length;x++){
				String type = (String) ob[x];
				int data = cfq.getInt("Quests.BlockP." + type + ".data");
				int am = cfq.getInt("Quests.BlockP." + type + ".am");
				
				if(am > 64){
					ItemStack item = new ItemStack(Material.getMaterial(type),64,(short) data);
					ItemMeta meta = item.getItemMeta();
					ArrayList<String> lore = new ArrayList<String>();
					lore.add("am : "+am);
					meta.setLore(lore);
					item.setItemMeta(meta);
					inv.setItem(x, item);
				} else {
					ItemStack item = new ItemStack(Material.getMaterial(type),am,(short) data);
					
					inv.setItem(x, item);
				}
			}
		}
		
		
		p.openInventory(inv);
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
				if(type.equalsIgnoreCase("abp")){
					if(e.getMessage().equals("cancel")){
						MenuQuests mq = new MenuQuests();
						mq.AddQuests(p, name);
						e.setCancelled(true);
						
						cfg.set(p.getName() + "", null);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else if(type.equalsIgnoreCase("abp2")){
					if(e.getMessage().equals("cancel")){
						
						e.setCancelled(true);
						cfg.set(p.getName()+".Chat", "abp");
						cfg.set(p.getName()+".Chatname", name);
						
						try {
							cfg.save(file);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						p.sendMessage(ChatColor.RED + "โยนบล็อกทิ้งออกไปเพือทำรายการ ยกเลิกพิม cancel");
					} else {
						if(isInt(e.getMessage())){
							File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
							YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
							ItemStack item = cfg.getItemStack(p.getName()+".Item");
							cfq.set("Quests.BlockP." + item.getType() + ".am", Integer.parseInt(e.getMessage()));
							
							
							cfg.set(p.getName()+"", null);
							
							try {
								cfg.save(file);
								cfq.save(fq);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							e.setCancelled(true);
							
							this.listquests(p, name);
							
						} else {
							
							
						}
						
						
					}
				}
				
				
				
				
				
				
				
				
				
				
				
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void d(PlayerDropItemEvent e){
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		String name = cfg.getString(p.getName() + ".Chatname");
		String type = cfg.getString(p.getName() + ".Chat");
		if(name != null){
			if(type != null){
				if(type.equalsIgnoreCase("abp")){
					e.getItemDrop().remove();
					ItemStack item = e.getItemDrop().getItemStack();
					if(item.getType().isBlock()){
						
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						List<String> list = (List<String>) cfq.getList("BlockP");
						if(list != null){
							Object o = e.getItemDrop().getItemStack().getType();
							if(!list.contains(o)){
								
								
								list.add("" + item.getType());
								cfq.set("BlockP", list);
								
								cfq.set("Quests.BlockP." + item.getType() + ".data", item.getDurability());
								
								
								cfg.set(p.getName()+".Chat", "abp2");
								cfg.set(p.getName()+".Chatname", name);
								cfg.set(p.getName()+".Item", item);
								
								try {
									cfg.save(file);
									cfq.save(fq);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								p.sendMessage(ChatColor.RED + "พิมจำนวนลงไป ยกเลิกพิม cancel");
							} else {
								p.sendMessage("มีบล็อกนี้แล้ว");
							}
							
						} else {
							List<String> list2 = new ArrayList<String>();
							
							
							list2.add("" + item.getType());
							cfq.set("BlockP", list2);
							
							cfq.set("Quests.BlockP." + item.getType() + ".data", item.getDurability());
							
							
							cfg.set(p.getName()+".Chat", "abp2");
							cfg.set(p.getName()+".Chatname", name);
							cfg.set(p.getName()+".Item", item);
							
							try {
								cfg.save(file);
								cfq.save(fq);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							p.sendMessage(ChatColor.RED + "พิมจำนวนลงไป ยกเลิกพิม cancel");
						}
						
						
						
						
					} else {
						p.sendMessage("ไม่สามารถใช้ได้ ต้องเป็นบล็อกเท่านั้น");
					}
					
					
					
					
				}
				
				
				
				
				
				
				
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Click(InventoryClickEvent e){
		MenuQuests mq = new MenuQuests();
		Player p = (Player) e.getWhoClicked();
		if(e.getInventory().getName().contains("ListBP-")){
			String name = e.getInventory().getName().replaceAll("ListBP-", "");
			e.setCancelled(true);
			if(e.getRawSlot() == 17){
				mq.AddQuests(p, name);
			} else if(e.getRawSlot() >= 0 && e.getRawSlot() <= 16){
				p.sendMessage("CSQ : ลบแล้วทำใหม่ง่ายกว่านะ");
			}
			
		} else if(e.getInventory().getName().contains("RemoveBP-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("RemoveBP-", "");
			if(e.getRawSlot() == 17){
				mq.AddQuests(p, name);
			} else if(e.getRawSlot() >= 0 && e.getRawSlot() <= 16){
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getType() != Material.AIR){

						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						
						
						List<String> list = (List<String>) cfq.getList("BlockP");
						Object[] ob = list.toArray();
						String type = (String) ob[e.getRawSlot()];
						cfq.set("Quests.BlockP." + type + "", null);
						
						list.remove(ob[e.getRawSlot()]);
						
						cfq.set("BlockP", list);
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.removequests(p, name);
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
