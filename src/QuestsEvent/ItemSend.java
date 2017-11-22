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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import MenuQuests.MenuQuests;

public class ItemSend implements Listener{
	
	
	
	
	public void selitem(Player p,String name){
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		p.closeInventory();
		
		cfd.set(p.getName()+".itemsend", true);
		cfd.set(p.getName()+".name", name);
		
		try {
			cfd.save(fd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p.sendMessage(ChatColor.RED + "ทิ้งของที่จะให้ส่งลงไป cancel");
	}
	
	public void Menuadditem(Player p,String name,ItemStack itemstack,int amo,Boolean send,Boolean npc,String idnpc){
		Inventory inv = Bukkit.createInventory(null, 9, "ItemsendAdd-"+name);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "BACK");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.BOOKSHELF);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Save Item");
			item.setItemMeta(meta);
			inv.setItem(7, item);
		}
		if(a == 1){
			inv.setItem(0, itemstack);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
			ItemMeta meta = item.getItemMeta();
			if(amo == 0){
				meta.setDisplayName(ChatColor.GOLD + "1");
			} else {
				meta.setDisplayName(ChatColor.GOLD + "" + amo);
			}
			
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +5000");
			list.add(ChatColor.GREEN + "กำหนดจำนวนของที่จะต้องส่ง");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		if(a == 1){
			if(send){
				ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "Item : " + send);
				ArrayList<String> list = new ArrayList<String>();
				list.add(ChatColor.RED + "กำหนดว่า ของทีส่งจะหายไปหรือไม่หาย");
				list.add(ChatColor.RED + "Item will the delivery be lost?");
				meta.setLore(list);
				item.setItemMeta(meta);
				inv.setItem(2, item);
			} else {
				ItemStack item = new ItemStack(Material.BARRIER);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "Item : " + send);
				ArrayList<String> list = new ArrayList<String>();
				list.add(ChatColor.RED + "กำหนดว่า ของทีส่งจะหายไปหรือไม่หาย");
				list.add(ChatColor.RED + "Item will the delivery be lost?");
				meta.setLore(list);
				item.setItemMeta(meta);
				inv.setItem(2, item);
			}
		}
		if(a == 1){
			if(npc){
				ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "sendnpc : " + npc);
				ArrayList<String> list = new ArrayList<String>();
				list.add(ChatColor.RED + "กำหนดว่า ของจะต้องส่งกับ Npc หรือไม่");
				list.add(ChatColor.RED + "Must be shipped with Npc?");
				meta.setLore(list);
				item.setItemMeta(meta);
				inv.setItem(3, item);
			} else {
				ItemStack item = new ItemStack(Material.BARRIER);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "sendnpc : " + npc);
				ArrayList<String> list = new ArrayList<String>();
				list.add(ChatColor.RED + "กำหนดว่า ของจะต้องส่งกับ Npc หรือไม่");
				list.add(ChatColor.RED + "Must be shipped with Npc?");
				meta.setLore(list);
				item.setItemMeta(meta);
				inv.setItem(3, item);
			}
		}
		if(npc){
			if(idnpc != null){
				if(a == 1){
					ItemStack item = new ItemStack(Material.NAME_TAG);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN + "IDNPC : " + idnpc);
					ArrayList<String> list = new ArrayList<String>();
					list.add(ChatColor.RED + "กำหนดว่า ของจะต้องส่งกับ Npc หรือไม่");
					list.add(ChatColor.RED + "Must be shipped with Npc?");
					meta.setLore(list);
					item.setItemMeta(meta);
					inv.setItem(4, item);
				}
			} else {
				if(a == 1){
					ItemStack item = new ItemStack(Material.BARRIER);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GREEN + "IDNPC : not have");
					ArrayList<String> list = new ArrayList<String>();
					list.add(ChatColor.RED + "กำหนดว่า ของจะต้องส่งกับ Npc หรือไม่");
					list.add(ChatColor.RED + "Must be shipped with Npc?");
					meta.setLore(list);
					item.setItemMeta(meta);
					inv.setItem(4, item);
				}
			}
		}
		
		
		p.openInventory(inv);
	}
	
	@SuppressWarnings("unchecked")
	public void MenuRemove(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		List<String> list = (List<String>) cfq.getList("itemsend");
		
		
		Inventory inv = Bukkit.createInventory(null, 54, "ItemsendRemove-"+name);
		int a = 1;
		if(a == 1){
			if(list != null){
				Object[] ob = list.toArray();
				for(int x = 0;x<list.toArray().length;x++){
					ItemStack item = cfq.getItemStack("itemsends." + ob[x] + ".item");
					ItemMeta meta = item.getItemMeta();
					ArrayList<String> l = new ArrayList<String>();
					
					boolean send = cfq.getBoolean("itemsends." + ob[x] + ".send"); 
					int amo = cfq.getInt("itemsends." + ob[x] + ".amo"); 
					boolean npc = cfq.getBoolean("itemsends." + ob[x] + ".npc"); 
					String idname = cfq.getString("itemsends." + ob[x] + ".idnpc"); 
					
					
					l.add(ChatColor.GREEN + "จำนวน : " + amo);
					l.add(ChatColor.GREEN + "ของหาย : " + send);
					l.add(ChatColor.GREEN + "ส่งกับNpc : " + npc);
					if(npc){
						l.add(ChatColor.GREEN + "Idnpc : " + idname);
					}
					meta.setLore(l);
					meta.setDisplayName(ChatColor.GREEN + "" + ob[x]);
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
			inv.setItem(53, item);
		}
		
		p.openInventory(inv);
		
		
	}
	
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent e){
		Player p = e.getPlayer();
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		Boolean t = cfd.getBoolean(p.getName()+".itemsend");
		if(t){
			String name = cfd.getString(p.getName()+".name");
			
			e.getItemDrop().remove();
			ItemStack item = e.getItemDrop().getItemStack();
			item.setAmount(1);
			
			this.Menuadditem(p, name, item, 0, true, false, null);
			
			cfd.set(p.getName() + "", null);
			
			try {
				cfd.save(fd);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
		}
		
		
	}
	
	@EventHandler
	public void as(AsyncPlayerChatEvent e){
		Player p = e.getPlayer();
		File fd = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
		
		int a = 1;
		if(a == 1){
			Boolean t = cfd.getBoolean(p.getName()+".itemsend");
			if(t){
				if(e.getMessage().equalsIgnoreCase("cancel")){
					String name = cfd.getString(p.getName()+".name");
					MenuQuests mq = new MenuQuests();
					mq.AddQuests(p, name);
					e.setCancelled(true);
					
					cfd.set(p.getName() + "", null);
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		
		
		if(a == 1){
			Boolean t = cfd.getBoolean(p.getName()+".setidnpcitemsend");
			if(t){
				if(e.getMessage().equalsIgnoreCase("cancel")){
					String name = cfd.getString(p.getName()+".name");
					
					ItemStack item = cfd.getItemStack(p.getName()+".item");
					int amo = cfd.getInt(p.getName()+".amo");
					boolean send = cfd.getBoolean(p.getName()+".send");
					boolean npc = cfd.getBoolean(p.getName()+".npc");
					String idnpc = cfd.getString(p.getName()+".idnpc");
					
					this.Menuadditem(p, name, item, amo, send, npc, idnpc);
					
					e.setCancelled(true);
					
					cfd.set(p.getName() + "", null);
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					String name = cfd.getString(p.getName()+".name");
					
					ItemStack item = cfd.getItemStack(p.getName()+".item");
					int amo = cfd.getInt(p.getName()+".amo");
					boolean send = cfd.getBoolean(p.getName()+".send");
					boolean npc = cfd.getBoolean(p.getName()+".npc");
					String idnpc = e.getMessage();
					
					this.Menuadditem(p, name, item, amo, send, npc, idnpc);
					
					e.setCancelled(true);
					
					cfd.set(p.getName() + "", null);
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("ItemsendAdd-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("ItemsendAdd-", "");
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 1){
				ItemStack item = e.getInventory().getItem(1);
				ItemMeta meta = item.getItemMeta();
				
				
				if(e.getClick() == ClickType.LEFT){
					
					
					int asd = Integer.parseInt(meta.getLore().get(0).replaceAll(ChatColor.GOLD + "Left = +", ""));
					
					int ds = Integer.parseInt(meta.getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
					
					int d = asd + ds;
					
					meta.setDisplayName(ChatColor.GOLD + "" + d);
					
					item.setItemMeta(meta);
					
					e.getInventory().setItem(1, item);
					
				} else if(e.getClick() == ClickType.RIGHT){
					int asd = Integer.parseInt(meta.getLore().get(0).replaceAll(ChatColor.GOLD + "Left = +", ""));
					
					int ds = Integer.parseInt(meta.getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
					
					if(ds != 1){
						int d = ds - asd;
						if(d > 0){
							meta.setDisplayName(ChatColor.GOLD + "" + d);
							
							item.setItemMeta(meta);
							
							e.getInventory().setItem(1, item);
						}
					}
					
					
					
				} else if(e.getClick() == ClickType.SHIFT_LEFT){
					int asd = Integer.parseInt(meta.getLore().get(0).replaceAll(ChatColor.GOLD + "Left = +", ""));
					
					int ddd = Check(asd);
					
					List<String> list = meta.getLore();
					list.set(0, ChatColor.GOLD + "Left = +" + ddd);
					list.set(1, ChatColor.GOLD + "Right = -" + ddd);
					
					int ds = Check(ddd);
					int ds2 = Check2(ddd);
					
					list.set(2, ChatColor.GOLD + "Shift + Left = เปลียนเป็น +" + ds);
					list.set(3, ChatColor.GOLD + "Shift + Right = เปลียนเป็น +" + ds2);
					
					meta.setLore(list);
					item.setItemMeta(meta);
					
					e.getInventory().setItem(1, item);
					
				} else if(e.getClick() == ClickType.SHIFT_RIGHT){
					
					int asd = Integer.parseInt(meta.getLore().get(0).replaceAll(ChatColor.GOLD + "Left = +", ""));
					
					int ddd = Check2(asd);
					
					List<String> list = meta.getLore();
					list.set(0, ChatColor.GOLD + "Left = +" + ddd);
					list.set(1, ChatColor.GOLD + "Right = -" + ddd);
					
					int ds = Check(ddd);
					int ds2 = Check2(ddd);
					
					list.set(2, ChatColor.GOLD + "Shift + Left = เปลียนเป็น +" + ds);
					list.set(3, ChatColor.GOLD + "Shift + Right = เปลียนเป็น +" + ds2);
					
					meta.setLore(list);
					item.setItemMeta(meta);
					
					e.getInventory().setItem(1, item);
					
					
				}
			}
			
			if(e.getRawSlot() == 2){
				ItemStack item = e.getInventory().getItem(2);
				ItemMeta meta = item.getItemMeta();
				
				boolean t = Boolean.parseBoolean(meta.getDisplayName().replaceAll(ChatColor.GREEN + "Item : ", ""));
				if(t){
					item.setType(Material.BARRIER);
					meta.setDisplayName(ChatColor.GREEN + "Item : " + false);
					item.setItemMeta(meta);
				} else {
					item.setType(Material.EMERALD_BLOCK);
					meta.setDisplayName(ChatColor.GREEN + "Item : " + true);
					item.setItemMeta(meta);
				}
				
				
				
			}
			
			if(e.getRawSlot() == 3){
				ItemStack item = e.getInventory().getItem(3);
				ItemMeta meta = item.getItemMeta();
				
				boolean t = Boolean.parseBoolean(meta.getDisplayName().replaceAll(ChatColor.GREEN + "sendnpc : ", ""));
				if(t){
					item.setType(Material.BARRIER);
					meta.setDisplayName(ChatColor.GREEN + "sendnpc : " + false);
					item.setItemMeta(meta);
					
					e.getInventory().setItem(4, new ItemStack(Material.AIR));
					
				} else {
					item.setType(Material.EMERALD_BLOCK);
					meta.setDisplayName(ChatColor.GREEN + "sendnpc : " + true);
					item.setItemMeta(meta);
					
					ItemStack item1 = new ItemStack(Material.BARRIER);
					ItemMeta meta1 = item1.getItemMeta();
					meta1.setDisplayName(ChatColor.GREEN + "IDNPC : not have");
					ArrayList<String> list = new ArrayList<String>();
					list.add(ChatColor.RED + "กำหนดว่า ของจะต้องส่งกับ Npc หรือไม่");
					list.add(ChatColor.RED + "Must be shipped with Npc?");
					meta1.setLore(list);
					item1.setItemMeta(meta1);
					e.getInventory().setItem(4, item1);
					
				}
				
				
				
			}
			
			if(e.getRawSlot() == 4){
				if(e.getCurrentItem().getType() != Material.AIR){
					File fd = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfd = YamlConfiguration.loadConfiguration(fd);
					
					p.closeInventory();
					
					
					ItemStack item2 = e.getInventory().getItem(0);
					boolean send = Boolean.parseBoolean(e.getInventory().getItem(2).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "Item : ", ""));
					int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
					boolean npc = Boolean.parseBoolean(e.getInventory().getItem(3).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "sendnpc : ", ""));
					String idname = e.getInventory().getItem(4).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "IDNPC : ", "");
					
					
					cfd.set(p.getName()+".setidnpcitemsend", true);
					cfd.set(p.getName()+".name", name);
					cfd.set(p.getName()+".item", item2);
					cfd.set(p.getName()+".amo", amo);
					cfd.set(p.getName()+".send", send);
					cfd.set(p.getName()+".npc", npc);
					cfd.set(p.getName()+".idnpc", idname);
					
					try {
						cfd.save(fd);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					p.sendMessage(ChatColor.RED + "พิม IDNCP ลงไป  (ยกเลิกพิม : cancel)");
					
					
					
				}
				
				
				
				
			}
			
			if(e.getRawSlot() == 7){
				File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				ItemStack item = e.getInventory().getItem(0);
				boolean send = Boolean.parseBoolean(e.getInventory().getItem(2).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "Item : ", ""));
				int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				boolean npc = Boolean.parseBoolean(e.getInventory().getItem(3).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "sendnpc : ", ""));
				
				
				boolean t = checksave(item,cfq);
				if(t){
					if(item.getItemMeta().hasDisplayName()){
						List<String> list = (List<String>) cfq.getList("itemsend");
						if(list != null){
							list.add("" + item.getItemMeta().getDisplayName());
							cfq.set("itemsend", list);
						} else {
							List<String> li = new ArrayList<String>();
							li.add("" + item.getItemMeta().getDisplayName());
							cfq.set("itemsend", li);
						}
						
						cfq.set("itemsends." + item.getItemMeta().getDisplayName()+".item", item);
						cfq.set("itemsends." + item.getItemMeta().getDisplayName()+".amo", amo);
						cfq.set("itemsends." + item.getItemMeta().getDisplayName()+".send", send);
						cfq.set("itemsends." + item.getItemMeta().getDisplayName()+".npc", npc);
						
						
						if(e.getInventory().getItem(4) != null){
							String idname = e.getInventory().getItem(4).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "IDNPC : ", "");
							cfq.set("itemsends." + item.getItemMeta().getDisplayName()+".idnpc", Integer.parseInt(idname));
						}
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					} else {
						
						List<String> list = (List<String>) cfq.getList("itemsend");
						if(list != null){
							list.add("" + item.getType());
							cfq.set("itemsend", list);
						} else {
							List<String> li = new ArrayList<String>();
							li.add("" + item.getType());
							cfq.set("itemsend", li);
						}
						
						cfq.set("itemsends." + item.getType()+".item", item);
						cfq.set("itemsends." + item.getType()+".amo", amo);
						cfq.set("itemsends." + item.getType()+".send", send);
						cfq.set("itemsends." + item.getType()+".npc", npc);
						
						
						if(e.getInventory().getItem(4) != null){
							String idname = e.getInventory().getItem(4).getItemMeta().getDisplayName().replaceAll(ChatColor.GREEN + "IDNPC : ", "");
							cfq.set("itemsends." + item.getType()+".idnpc", idname);
						}
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
					}
					
					this.MenuRemove(p, name);
					
				} else {
					
					p.sendMessage("[CSQ] : ไม่สามารถบันทึกได้อาจมีของชนิดนี้แล้วหรืออาจมีชื่อนี้แล้ว");
					
				}
			}
			
			
			if(e.getRawSlot() == 8){
				MenuQuests mq = new MenuQuests();
				mq.AddQuests(p, name);
			}
			
		} else if(e.getInventory().getName().contains("ItemsendRemove-")){
			String name = e.getInventory().getName().replaceAll("ItemsendRemove-", "");
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getRawSlot() == 53){
				
				MenuQuests mq= new MenuQuests();
				mq.AddQuests(p, name);
				
				
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 44){
				if(e.getCurrentItem().getType() != Material.AIR){
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					List<String> list = (List<String>) cfq.getList("itemsend");
					
					String id = list.get(e.getRawSlot());
					
					
					cfq.set("itemsends." + id, null);
					
					list.remove(e.getRawSlot());
					
					cfq.set("itemsend", list);
					
					try {
					cfq.save(fq);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					this.MenuRemove(p, name);
					
				}
			}
			
			
			
		}
		
		
		
		
		
		
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean checksave(ItemStack item, YamlConfiguration cfq) {
		if(item.getItemMeta().hasDisplayName()){
			List<String> list = (List<String>) cfq.getList("itemsend");
			if(list != null){
				Object[] o = list.toArray();
				for(int x= 0;x<o.length;x++){
					String ty = item.getItemMeta().getDisplayName();
					String ty2 = o[x].toString();
					if(ty.equalsIgnoreCase(ty2)){
						return false;
					}
				}
			}
		} else {
			List<String> list = (List<String>) cfq.getList("itemsend");
			if(list != null){
				Object[] o = list.toArray();
				for(int x= 0;x<o.length;x++){
					String ty = item.getType().toString();
					String ty2 = o[x].toString();
					if(ty.equalsIgnoreCase(ty2)){
						return false;
					}
				}
				
				
				
			}
		}
		
		
		
		return true;
	}

	public int Check(int asd) {
		if(asd == 1){
			return 5;
		} else if(asd == 5){
			return 10;
		} else if(asd == 10){
			return 50;
		} else if(asd == 50){
			return 100;
		} else if(asd == 100){
			return 500;
		} else if(asd == 500){
			return 1000;
		} else if(asd == 1000){
			return 5000;
		} else if(asd == 5000){
			return 1;
		}
		
		return 1;
	}
	
	public int Check2(int asd) {
		if(asd == 1){
			return 5000;
		} else if(asd == 5){
			return 1;
		} else if(asd == 10){
			return 5;
		} else if(asd == 50){
			return 10;
		} else if(asd == 100){
			return 50;
		} else if(asd == 500){
			return 100;
		} else if(asd == 1000){
			return 500;
		} else if(asd == 5000){
			return 1000;
		}
		
		return 0;
	}
	
	
}
