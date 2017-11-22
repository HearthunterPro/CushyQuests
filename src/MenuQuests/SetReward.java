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

public class SetReward implements Listener {

	
	public void Menu(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		
		Inventory inv = Bukkit.createInventory(null, 9, "setReward-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "BACK");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		int price = cfq.getInt("Reward.Price");
		int level = cfq.getInt("Reward.Level");
		int exp = cfq.getInt("Reward.EXP");
		String message = cfq.getString("Reward.message");
		if(a == 1){
			ItemStack item = new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET Price");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "เซ็ตจำนวนเงินที่จะได้รับ");
			if(price != 0){
				list.add(ChatColor.RED + "Price : " + price);
			}
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(0, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.EXP_BOTTLE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET Level");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "เลเวลของตัวละครที่จะได้");
			if(price != 0){
				list.add(ChatColor.RED + "Level : " + level);
			}
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(1, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_ORE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET EXP");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "EXP ของตัวละครที่จะได้");
			if(price != 0){
				list.add(ChatColor.RED + "Exp : " + exp);
			}
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(2, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.EMERALD_ORE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET Item");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "Item ที่จะได้");
			list.add(ChatColor.GREEN + "คลิกเพือแก้ไข");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(3, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.COMMAND);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET COMMAND");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "Command ที่จะรันคำสั่ง");
			list.add(ChatColor.GREEN + "คลิกขวาเพิม");
			list.add(ChatColor.GREEN + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(4, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.APPLE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET Quests");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "เควสต่อไป");
			list.add(ChatColor.GREEN + "คลิกขวาเพิม");
			list.add(ChatColor.GREEN + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(5, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.APPLE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "SET Message");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "ระบุข้อความในเควสว่าได้อะไรบ้าง(ลายละเอียดของรางวัล)");
			list.add(ChatColor.RED + "คลิกขวาแก้ไข");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			list.add(ChatColor.GREEN + "Message : " + message);
			meta.setLore(list);
			item.setItemMeta(meta);
			
			inv.setItem(6, item);
		}
		
		
		p.openInventory(inv);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void RemoveQuests(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		
		Inventory inv = Bukkit.createInventory(null, 9, "CSQREREMOVEQUESTS-"+name);
		
		List<String> list2 = (List<String>) cfq.getList("Reward.QUESTS");
		if(list2 != null){
			for(int x = 0;x<list2.toArray().length;x++){
				Object[] ob = list2.toArray();
				if(ob[x] != null){
					String Command = ob[x].toString();
					ItemStack item = new ItemStack(Material.GOLDEN_APPLE);
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
			meta.setDisplayName(ChatColor.GREEN + "BACK");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		p.openInventory(inv);
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void Com(Player p,String name){
		Inventory inv = Bukkit.createInventory(null, 54, "CSQRemoveCommand-" + name);
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		List<String> list2 = (List<String>) cfq.getList("Reward.COMMAND");
		if(list2 != null){
			for(int x = 0;x<list2.toArray().length;x++){
				Object[] ob = list2.toArray();
				if(ob[x] != null){
					String Command = ob[x].toString();
					ItemStack item = new ItemStack(Material.COMMAND);
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
		Inventory inv = Bukkit.createInventory(null, 54, "CSQSETITEM-" + name);
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		int a = 1;
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Reward.ITEM." + x);
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
	public void Click(InventoryClickEvent e){
		
		
		if(e.getInventory().getName().contains("setReward-")){
			String name = e.getInventory().getName().replaceAll("setReward-", "");
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getRawSlot() == 8){
				CreateQuestsMenu cqm = new CreateQuestsMenu();
				cqm.MenuQuests(p, name);
			}
			if(e.getRawSlot() == 0){
				File fd = new File("plugins//Cushyquests//chatdata.yml");
				YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
				
				cfd.set(p.getName() + ".setREP", true);
				cfd.set(p.getName() + ".name", name);
				
				
				try {
					cfd.save(fd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage("[CSQ] : พิมจำนวนเงินลงไป");
			}
			if(e.getRawSlot() == 1){
				File fd = new File("plugins//Cushyquests//chatdata.yml");
				YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
				
				cfd.set(p.getName() + ".setRELVL", true);
				cfd.set(p.getName() + ".name", name);
				
				
				try {
					cfd.save(fd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage("[CSQ] : พิมจำนวนเวลลงไป");
			}
			if(e.getRawSlot() == 2){
				File fd = new File("plugins//Cushyquests//chatdata.yml");
				YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
				
				cfd.set(p.getName() + ".setREEXP", true);
				cfd.set(p.getName() + ".name", name);
				
				
				try {
					cfd.save(fd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage("[CSQ] : พิมจำนวนexpลงไป");
			}
			if(e.getRawSlot() == 3){
				this.MenuAddItem(p, name);
			}
			if(e.getRawSlot() == 4){
				if(e.getClick() == ClickType.LEFT){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					cfd.set(p.getName() + ".setRECOM", true);
					cfd.set(p.getName() + ".name", name);
					
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมคำสั่งลงไปม่ต้องใส / : %player% ใช้แทนผู้เล่น  : (ยกเลิกพิม cancel)");
				}
				if(e.getClick() == ClickType.RIGHT){
					this.Com(p, name);
				}
				
			}
			if(e.getRawSlot() == 5){
				if(e.getClick() == ClickType.LEFT){
					
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					List<String> list2 = (List<String>) cfq.getList("Reward.QUESTS");
					if(list2 != null){
						if(list2.toArray().length < 3){
							File fd = new File("plugins//Cushyquests//chatdata.yml");
							YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
							
							
							
							cfd.set(p.getName() + ".setREQUESTS", true);
							cfd.set(p.getName() + ".name", name);
							
							
							try {
								cfd.save(fd);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							p.closeInventory();
							p.sendMessage("[CSQ] : พิมชื่อ Quests ลงไปให้แน่ใจว่าสร้าง Quests นี้แล้ว : (ยกเลิกพิม cancel)");
						} else {
							p.sendMessage("[CSQ] : จำกัด 3 อันเท่านั้น");
						}
					} else {
						File fd = new File("plugins//Cushyquests//chatdata.yml");
						YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
						
						cfd.set(p.getName() + ".setREQUESTS", true);
						cfd.set(p.getName() + ".name", name);
						
						
						try {
							cfd.save(fd);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						p.closeInventory();
						p.sendMessage("[CSQ] : พิมชื่อ Quests ลงไปให้แน่ใจว่าสร้าง Quests นี้แล้ว : (ยกเลิกพิม cancel)");
						
					}
					
					
				}
				if(e.getClick() == ClickType.RIGHT){
					this.RemoveQuests(p, name);
				}
				
			}
			if(e.getRawSlot() == 6){
				if(e.getClick() == ClickType.LEFT){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					
					
					cfd.set(p.getName() + ".setREMESSAGE", true);
					cfd.set(p.getName() + ".name", name);
					
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมข้อความ ลงไป : (ยกเลิกพิม cancel)");
				} else if(e.getClick() == ClickType.RIGHT){
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					cfq.set("Reward.message", null);
					
					try {
						cfq.save(fq);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.Menu(p, name);
					
				}
				
			}
			
			
			
		}
		
		
		if(e.getInventory().getName().contains("CSQSETITEM-")){
			String name = e.getInventory().getName().replaceAll("CSQSETITEM-", "");
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 53){
				e.setCancelled(true);
				this.Menu(p, name);
			}
			if(e.getRawSlot() == 52){
				e.setCancelled(true);
				
				File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				for(int x = 0;x<45;x++){
					ItemStack item = e.getInventory().getItem(x);
					if(item != null){
						
						cfq.set("Reward.ITEM." + x, item);
					} else {
						cfq.set("Reward.ITEM." + x, null);
					}
					
				}
				
				try {
					cfq.save(fq);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.sendMessage("[CSQ] :บันทึก Item แล้ว");
				
			}
		}
		
		if(e.getInventory().getName().contains("CSQRemoveCommand-")){
			String name = e.getInventory().getName().replaceAll("CSQRemoveCommand-", "");
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getRawSlot() == 53){
				
				this.Menu(p, name);
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 45){
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getType() != Material.AIR){
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						List<String> list2 = (List<String>) cfq.getList("Reward.COMMAND");
						list2.remove(e.getRawSlot());
						
						cfq.set("Reward.COMMAND", list2);
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.Com(p, name);
					}
					
				}
			}
		}
		
		if(e.getInventory().getName().contains("CSQREREMOVEQUESTS-")){
			String name = e.getInventory().getName().replaceAll("CSQREREMOVEQUESTS-", "");
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getRawSlot() == 8){
				
				this.Menu(p, name);
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 7){
				if(e.getCurrentItem() != null){
					if(e.getCurrentItem().getType() != Material.AIR){
						File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
						YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
						List<String> list2 = (List<String>) cfq.getList("Reward.QUESTS");
						list2.remove(e.getRawSlot());
						
						cfq.set("Reward.QUESTS", list2);
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						this.RemoveQuests(p, name);
					}
					
				}
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
			boolean te = cfd.getBoolean(p.getName() + ".setREP");
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
						
						
						cfq.set("Reward.Price", Integer.parseInt(e.getMessage()));
						
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
			boolean te = cfd.getBoolean(p.getName() + ".setRELVL");
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
						
						
						cfq.set("Reward.Level", Integer.parseInt(e.getMessage()));
						
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
			boolean te = cfd.getBoolean(p.getName() + ".setREEXP");
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
						
						
						cfq.set("Reward.EXP", Integer.parseInt(e.getMessage()));
						
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
			boolean te = cfd.getBoolean(p.getName() + ".setRECOM");
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
					
					
					List<String> list2 = (List<String>) cfq.getList("Reward.COMMAND");
					if(list2 != null){
						list2.add("" + e.getMessage());
						
						cfq.set("Reward.COMMAND", list2);
					} else {
						ArrayList<String> al = new ArrayList<String>();
						al.add("" + e.getMessage());
						
						cfq.set("Reward.COMMAND", al);
					}
					
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
		
		
		if(a == 1){
			boolean te = cfd.getBoolean(p.getName() + ".setREQUESTS");
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
					
					
					List<String> list2 = (List<String>) cfq.getList("Reward.QUESTS");
					if(list2 != null){
						list2.add("" + e.getMessage());
						
						cfq.set("Reward.QUESTS", list2);
					} else {
						ArrayList<String> al = new ArrayList<String>();
						al.add("" + e.getMessage());
						
						cfq.set("Reward.QUESTS", al);
					}
					
					cfd.set(p.getName() + "", null);
					
					
					try {
						cfq.save(fq);
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					e.setCancelled(true);
					this.RemoveQuests(p, name);
				}
				
				
				
			}
		}
		
		if(a == 1){
			boolean te = cfd.getBoolean(p.getName() + ".setREMESSAGE");
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
					
					
					cfq.set("Reward.message", e.getMessage());
					
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
	
	
	
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	
	
	
	
}
