package MenuQuests;

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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Eqment implements Listener {

	
	
	public void Menu(Player p,String name){
		Inventory inv = Bukkit.createInventory(null, 9, "Equment-" + name);
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		int money = cfq.getInt("Eqment.money");
		boolean mr = cfq.getBoolean("Eqment.mr");
		int level = cfq.getInt("Eqment.level");
		boolean lr = cfq.getBoolean("Eqment.lr");
		
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SetMoney");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "Money : " + money);
			list.add(ChatColor.GREEN +"MoneyRemove : " + mr);
			list.add(ChatColor.BLUE + "คลิกขวาเพือตั้งค่าการนำเงินออก");
			list.add(ChatColor.BLUE + "คลิกซ้ายแก้ไขจำนวน");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(0, item);
		}
		
		if(a == 1){
			ItemStack item = new ItemStack(Material.EXP_BOTTLE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SetLevel");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "Level : " + level);
			list.add(ChatColor.GREEN +"LR : " + lr);
			list.add(ChatColor.BLUE + "คลิกขวาเพือตั้งค่าการนำเวลออก");
			list.add(ChatColor.BLUE + "คลิกซ้ายแก้ไขจำนวน");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		
		if(a == 1){
			ItemStack item = new ItemStack(Material.CHEST);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SetItem");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.BLUE + "คลิกเพือแก้ไข");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(2, item);
		}
		
		if(a == 1){
			ItemStack item = new ItemStack(Material.COMMAND_MINECART);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SetQuests");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.BLUE + "คลิกขวาเอา quests ออก");
			list.add(ChatColor.BLUE + "คลิกซ้ายเพิม Quests");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(3, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Back");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		
		
		
		p.openInventory(inv);
	}
	
	
	@SuppressWarnings("unchecked")
	public void Com(Player p,String name){
		Inventory inv = Bukkit.createInventory(null, 54, "CSQEQRemoveQuests-" + name);
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		List<String> list2 = (List<String>) cfq.getList("Eqment.QUESTS");
		if(list2 != null){
			for(int x = 0;x<list2.toArray().length;x++){
				Object[] ob = list2.toArray();
				if(ob[x] != null){
					String Command = ob[x].toString();
					ItemStack item = new ItemStack(Material.FLOWER_POT_ITEM);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN + "" + Command);
					item.setItemMeta(meta);
					inv.setItem(x, item);
				}
			}
		}
		
		
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Back");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(53, item);
			
		}
		p.openInventory(inv);
	}
	
	
	
	public void MenuAddItem(Player p,String name){
		Inventory inv = Bukkit.createInventory(null, 54, "CSQEQTITEM-" + name);
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		int a = 1;
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Eqment.ITEMSTACK." + x);
			if(item != null){
				
				inv.setItem(x, item);
			}
			
		}
		
		
		
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Back");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(53, item);
			
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.RED + "Save");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "บันถึงของรางวัล");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(52, item);
			
		}
		
		
		
		
		p.openInventory(inv);
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("Equment-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("Equment-", "");
			
			File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
			YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
			
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 8){
				CreateQuestsMenu cq = new CreateQuestsMenu();
				cq.MenuQuests(p, name);
			}
			if(e.getRawSlot() == 0){
				if(e.getClick() == ClickType.LEFT){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					cfd.set(p.getName() + ".CSASEM", true);
					cfd.set(p.getName() + ".name", name);
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมตัวเลขลงไป (ยกเลิกพิม cancel)");
					
				}
				if(e.getClick() == ClickType.RIGHT){
					ItemStack item = e.getInventory().getItem(0);
					ItemMeta meta = item.getItemMeta();
					List<String> list = meta.getLore();
					boolean ts = Boolean.parseBoolean(list.get(1).replaceAll(ChatColor.GREEN + "MoneyRemove : ", ""));
					if(ts){
						list.set(1, ChatColor.GREEN + "MoneyRemove : " + false);
						cfq.set("Eqment.mr", false);
					} else {
						list.set(1, ChatColor.GREEN + "MoneyRemove : " + true);
						cfq.set("Eqment.mr", true);
					}
					
					
					try {
						cfq.save(fq);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					meta.setLore(list);
					item.setItemMeta(meta);
					e.getInventory().setItem(0, item);
				}
			}
			
			
			if(e.getRawSlot() == 1){
				if(e.getClick() == ClickType.LEFT){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					cfd.set(p.getName() + ".CSASEL", true);
					cfd.set(p.getName() + ".name", name);
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมตัวเลขลงไป (ยกเลิกพิม cancel)");
					
				}
				if(e.getClick() == ClickType.RIGHT){
					ItemStack item = e.getInventory().getItem(1);
					ItemMeta meta = item.getItemMeta();
					List<String> list = meta.getLore();
					boolean ts = Boolean.parseBoolean(list.get(1).replaceAll(ChatColor.GREEN + "LR : ", ""));
					if(ts){
						list.set(1, ChatColor.GREEN + "LR : " + false);
						cfq.set("Eqment.lr", false);
					} else {
						list.set(1, ChatColor.GREEN + "LR : " + true);
						cfq.set("Eqment.lr", true);
					}
					
					
					try {
						cfq.save(fq);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					meta.setLore(list);
					item.setItemMeta(meta);
					e.getInventory().setItem(1, item);
				}
			}
			
			if(e.getRawSlot() == 2){
				this.MenuAddItem(p, name);
			}
			
			if(e.getRawSlot() == 3){
				if(e.getClick() == ClickType.LEFT){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					cfd.set(p.getName() + ".CSASEQ", true);
					cfd.set(p.getName() + ".name", name);
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมตัวเลขลงไป (ยกเลิกพิม cancel)");
				}
				if(e.getClick() == ClickType.RIGHT){
					this.Com(p, name);
				}
			}
			
		}
		
		if(e.getInventory().getName().contains("CSQEQRemoveQuests-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("CSQEQRemoveQuests-", "");
			
			File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
			YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
			
			Player p = (Player) e.getWhoClicked();
			
			if(e.getRawSlot() == 53){
				this.Menu(p, name);
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 45){
				List<String> list2 = (List<String>) cfq.getList("Eqment.QUESTS");
				
				list2.remove(e.getRawSlot());
				
				cfq.set("Eqment.QUESTS", list2);
				
				try {
					cfq.save(fq);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				this.Com(p, name);
				
			}
			
			
		}
		
		
		if(e.getInventory().getName().contains("CSQEQTITEM-")){
			
			String name = e.getInventory().getName().replaceAll("CSQEQTITEM-", "");
			
			File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
			YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
			
			Player p = (Player) e.getWhoClicked();
			
			if(e.getRawSlot() == 53){
				e.setCancelled(true);
				this.Menu(p, name);
			}
			if(e.getRawSlot() == 52){
				e.setCancelled(true);
				for(int x = 0;x<45;x++){
					ItemStack item = e.getInventory().getItem(x);
					cfq.set("Eqment.ITEMSTACK."+x, item);
				}
				
				
				try {
					cfq.save(fq);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.sendMessage("[CSQ] : save");
				
			}
			
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void As(AsyncPlayerChatEvent e){
		
		Player p = e.getPlayer();
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		int a = 1;
		if(a == 1){
			boolean te = cfd.getBoolean(p.getName() + ".CSASEM");
			if(te){
				String name = cfd.getString(p.getName() + ".name");
				if(e.getMessage().equalsIgnoreCase("cancel")){
					e.setCancelled(true);
					this.Menu(p, name);
					
					cfd.set(p.getName() + "", null);
					
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else {
					if(isInt(e.getMessage())){
						
						
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						
						cfq.set("Eqment.money", Integer.parseInt(e.getMessage()));
						
						cfd.set(p.getName() + "", null);
						
						
						try {
							cfq.save(fq);
							cfd.save(fd);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						e.setCancelled(true);
						this.Menu(p, name);
						
						
					} else {
						p.sendMessage("[CSQ] : ต้องเป้นตัวเลขเท่านั้น");
					}
				}
				
				
				
			}
		}
		
		if(a == 1){
			boolean te = cfd.getBoolean(p.getName() + ".CSASEL");
			if(te){
				String name = cfd.getString(p.getName() + ".name");
				if(e.getMessage().equalsIgnoreCase("cancel")){
					e.setCancelled(true);
					this.Menu(p, name);
					
					cfd.set(p.getName() + "", null);
					
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else {
					if(isInt(e.getMessage())){
						
						
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						
						
						cfq.set("Eqment.level", Integer.parseInt(e.getMessage()));
						
						cfd.set(p.getName() + "", null);
						
						
						try {
							cfq.save(fq);
							cfd.save(fd);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						e.setCancelled(true);
						this.Menu(p, name);
						
						
					} else {
						p.sendMessage("[CSQ] : ต้องเป้นตัวเลขเท่านั้น");
					}
				}
				
				
				
			}
		}
		
		
		if(a == 1){
			boolean te = cfd.getBoolean(p.getName() + ".CSASEQ");
			if(te){
				String name = cfd.getString(p.getName() + ".name");
				if(e.getMessage().equalsIgnoreCase("cancel")){
					e.setCancelled(true);
					this.Menu(p, name);
					
					cfd.set(p.getName() + "", null);
					
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				} else {
					
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					List<String> list = (List<String>) cfq.getList("Eqment.QUESTS");
					if(list != null){
						Object o = e.getMessage();
						if(list.contains(o)){
							p.sendMessage("[CSQ] : มีเควสนี้แล้ว");
						} else {
							
							list.add("" + e.getMessage());
							
							cfq.set("Eqment.QUESTS", list);
							
							
							cfd.set(p.getName() + "", null);
							
							
							try {
								cfq.save(fq);
								cfd.save(fd);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							e.setCancelled(true);
							this.Menu(p, name);
						}
						
						
						
					} else {
						ArrayList<String> li = new ArrayList<String>();
						
						li.add("" + e.getMessage());
						
						cfq.set("Eqment.QUESTS", li);
						
						cfd.set(p.getName() + "", null);
						
						
						try {
							cfq.save(fq);
							cfd.save(fd);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						e.setCancelled(true);
						this.Menu(p, name);
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
