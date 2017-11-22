package QuestsEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import MenuQuests.MenuQuests;

@SuppressWarnings("deprecation")
public class KillMod implements Listener{

	
	@SuppressWarnings("unchecked")
	public void MenuRemove(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		Inventory inv = Bukkit.createInventory(null, 27, "RemoveEntity-"+name);
		int a = 1;
		
		
		List<String> lien = (List<String>) cfq.getList("Killmod");
		if(lien != null){
			for(int x = 0;x<lien.toArray().length;x++){
				if(a == 1){
					Object[] ob = lien.toArray();
					String idname = ob[x].toString();
					
					String type = cfq.getString("Killmods." + idname + ".type");
					int amo = cfq.getInt("Killmods." + idname + ".AMO");
					String Cuname = cfq.getString("Killmods." + idname + ".NAME");
					String loc = cfq.getString("Killmods." + idname + ".LOC");
					ArrayList<String> list = new ArrayList<String>();
					list.add(ChatColor.GREEN + "จำนวน : " + amo);
					if(Cuname != null){
						list.add(ChatColor.GREEN + "Name : " + Cuname);
					}
					if(loc != null){
						list.add(ChatColor.GREEN + "Location : " + loc);
					}
					
					if(type.equalsIgnoreCase("MUSHROOM_COW")){
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.MUSHROOM_COW.getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else if(type.equalsIgnoreCase("EVOKER")){
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.EVOKER.getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else if(type.equalsIgnoreCase("SKELETON_HORSE")){
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SKELETON_HORSE.getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else if(type.equalsIgnoreCase("VINDICATOR")){
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.VINDICATOR.getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else if(type.equalsIgnoreCase("ZOMBIE_HORSE")){
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE_HORSE.getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else if(type.equalsIgnoreCase("PLAYER")){
						ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) 3);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					} else {
						ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.fromName(type).getTypeId());
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GOLD + ""+idname);
						meta.setLore(list);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					}
				}
			}
		}
		
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "BACK");
			item.setItemMeta(meta);
			inv.setItem(26, item);
		}
		
		p.openInventory(inv);
	}
	
	
	
	public void menus(Player p,String name,String type,int amo,String Cuname,String locs){
		List<String> enlist = new ArrayList<String>();
		enlist.add("" + EntityType.BAT);
		enlist.add("" + EntityType.BLAZE);
		enlist.add("" + EntityType.SPIDER);
		enlist.add("" + EntityType.CHICKEN);
		enlist.add("" + EntityType.COW);
		enlist.add("" + EntityType.CREEPER);
		enlist.add("" + EntityType.DONKEY);
		enlist.add("" + EntityType.ELDER_GUARDIAN);
		enlist.add("" + EntityType.ENDERMAN);
		enlist.add("" + EntityType.ENDERMITE);
		enlist.add("" + EntityType.GHAST);
		enlist.add("" + EntityType.GUARDIAN);
		enlist.add("" + EntityType.HORSE);
		enlist.add("" + EntityType.HUSK);
		enlist.add("" + EntityType.LLAMA);
		enlist.add("" + EntityType.MAGMA_CUBE);
		enlist.add("" + EntityType.MULE);
		enlist.add("" + EntityType.OCELOT);
		enlist.add("" + EntityType.PIG);
		enlist.add("" + EntityType.POLAR_BEAR);
		enlist.add("" + EntityType.RABBIT);
		enlist.add("" + EntityType.SHEEP);
		enlist.add("" + EntityType.SHULKER);
		enlist.add("" + EntityType.SILVERFISH);
		enlist.add("" + EntityType.SKELETON);
		enlist.add("" + EntityType.SKELETON_HORSE);
		enlist.add("" + EntityType.SLIME);
		enlist.add("" + EntityType.SPIDER);
		enlist.add("" + EntityType.SQUID);
		enlist.add("" + EntityType.STRAY);
		enlist.add("" + EntityType.VEX);
		enlist.add("" + EntityType.VILLAGER);
		enlist.add("" + EntityType.WITCH);
		enlist.add("" + EntityType.WITHER_SKELETON);
		enlist.add("" + EntityType.WOLF);
		enlist.add("" + EntityType.ZOMBIE);
		enlist.add("" + EntityType.ZOMBIE_HORSE);
		enlist.add("" + EntityType.ZOMBIE_VILLAGER);
		
		Inventory inv = Bukkit.createInventory(null, 54, "SELENTITY-"+name);
		int a = 1;
		for(int x = 0;x<enlist.toArray().length;x++){
			Object[] ob = enlist.toArray();
			String entity = ob[x].toString();
			if(a == 1){
				
				ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.fromName(entity).getTypeId());
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "" + EntityType.fromName(entity));
				item.setItemMeta(meta);
				inv.setItem(x, item);
			}
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.EVOKER.getTypeId());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + EntityType.EVOKER);
			item.setItemMeta(meta);
			inv.setItem(39, item);
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.MUSHROOM_COW.getTypeId());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + EntityType.MUSHROOM_COW);
			item.setItemMeta(meta);
			inv.setItem(40, item);
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.SKELETON_HORSE.getTypeId());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + EntityType.SKELETON_HORSE);
			item.setItemMeta(meta);
			inv.setItem(41, item);
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.VINDICATOR.getTypeId());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + EntityType.VINDICATOR);
			item.setItemMeta(meta);
			inv.setItem(42, item);
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.MONSTER_EGG,1, EntityType.ZOMBIE_HORSE.getTypeId());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + EntityType.ZOMBIE_HORSE);
			item.setItemMeta(meta);
			inv.setItem(43, item);
		}
		if(a == 1){
			
			ItemStack item = new ItemStack(Material.SKULL_ITEM,1, (short) 3);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "PLAYER");
			item.setItemMeta(meta);
			inv.setItem(44, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "BACK");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GOLD + "" + type);
			list.add(ChatColor.GOLD + "" + amo);
			list.add(ChatColor.GOLD + "" + Cuname);
			list.add(ChatColor.GOLD + "" + locs);
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(53, item);
		}
		
		p.openInventory(inv);
	}
	
	
	
	
	public void menu(Player p,String name,String type,int amo,String Cuname,String locs){
		Inventory inv = Bukkit.createInventory(null, 9, "CreateEntity-" + name);
		int a = 1;
		if(a == 1){
			if(type == null){
				ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE.getTypeId());
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "ZOMBIE");
				item.setItemMeta(meta);
				inv.setItem(0, item);
			} else {
				if(type.equalsIgnoreCase("MUSHROOM_COW")){
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.MUSHROOM_COW.getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.MUSHROOM_COW);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else if(type.equalsIgnoreCase("EVOKER")){
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.EVOKER.getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.EVOKER);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else if(type.equalsIgnoreCase("SKELETON_HORSE")){
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.SKELETON_HORSE.getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.SKELETON_HORSE);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else if(type.equalsIgnoreCase("VINDICATOR")){
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.VINDICATOR.getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.VINDICATOR);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else if(type.equalsIgnoreCase("ZOMBIE_HORSE")){
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.ZOMBIE_HORSE.getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.ZOMBIE_HORSE);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else if(type.equalsIgnoreCase("PLAYER")){
					ItemStack item = new ItemStack(Material.SKULL_ITEM, 1,(short) 3);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.PLAYER);
					item.setItemMeta(meta);
					inv.setItem(0, item);
				} else {
					ItemStack item = new ItemStack(Material.MONSTER_EGG, 1, EntityType.fromName(type).getTypeId());
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.GOLD + ""+EntityType.fromName(type));
					item.setItemMeta(meta);
					inv.setItem(0, item);
				}
			}
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
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		if(inv.getItem(0).getType() != Material.SKULL_ITEM){
			if(a == 1){
				ItemStack item = new ItemStack(Material.NAME_TAG);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.GOLD + "CustomName");
				if(Cuname != null){
					if(!Cuname.equalsIgnoreCase("null")){
						ArrayList<String> list = new ArrayList<String>();
						list.add(ChatColor.GOLD + "" + Cuname);
						meta.setLore(list);
					}
					
				}
				item.setItemMeta(meta);
				inv.setItem(2, item);
			}
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_ORE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Location");
			if(locs != null){
				if(!locs.equalsIgnoreCase("null")){
					ArrayList<String> list = new ArrayList<String>();
					list.add(ChatColor.GOLD + "" + locs);
					meta.setLore(list);
				}
				
			}
			item.setItemMeta(meta);
			inv.setItem(3, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "BACK");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.BEACON);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Save Entity");
			item.setItemMeta(meta);
			inv.setItem(7, item);
		}
		
		
		
		p.openInventory(inv);
	}
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("CreateEntity-")){
			String name = e.getInventory().getName().replaceAll("CreateEntity-", "");
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			if(e.getRawSlot() == 0){
				String type = e.getInventory().getItem(0).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", "");
				int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				List<String> list = e.getInventory().getItem(3).getItemMeta().getLore();
				
				if(e.getInventory().getItem(2) != null){
					List<String> list2 = e.getInventory().getItem(2).getItemMeta().getLore();
					if(list2 != null){
						String cn = e.getInventory().getItem(2).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
						if(list != null){
							String loc = e.getInventory().getItem(3).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
							this.menus(p, name, type, amo, cn, loc);
						} else {
							this.menus(p, name, type, amo, cn, null);
						}
					} else {
						if(list != null){
							String loc = e.getInventory().getItem(3).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
							this.menus(p, name, type, amo, null, loc);
						} else {
							this.menus(p, name, type, amo, null, null);
						}
					}
				} else {
					
					if(list != null){
						String loc = e.getInventory().getItem(3).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
						this.menus(p, name, type, amo, null, loc);
					} else {
						this.menus(p, name, type, amo, null, null);
					}
				}
				
				
				
				
			}
			
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
				File file = new File("plugins//Cushyquests//chatdata.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				
				String type = e.getInventory().getItem(0).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", "");
				int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				List<String> list = e.getInventory().getItem(3).getItemMeta().getLore();
				List<String> list2 = e.getInventory().getItem(2).getItemMeta().getLore();
				if(list2 != null){
					String cn = e.getInventory().getItem(2).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
					cfg.set(p.getName() + ".cn", cn);
				}
				if(list != null){
					String loc = e.getInventory().getItem(3).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
					cfg.set(p.getName() + ".loc", loc);
				}
				
				cfg.set(p.getName() + ".ecn", true);
				cfg.set(p.getName() + ".Entity", type);
				cfg.set(p.getName() + ".amo", amo);
				cfg.set(p.getName() + ".nameq", name);
				
				
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.closeInventory();
				p.sendMessage("[CSQ] : พิมชื่อลงไปได้เลย");
			}
			
			if(e.getRawSlot() == 3){
				if(e.getCurrentItem() != null){
					File file = new File("plugins//Cushyquests//chatdata.yml");
					YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
					
					String type = e.getInventory().getItem(0).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", "");
					int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
					if(e.getInventory().getItem(2) != null){
						List<String> list = e.getInventory().getItem(2).getItemMeta().getLore();
						if(list != null){
							String cn = e.getInventory().getItem(2).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
							cfg.set(p.getName() + ".cn", cn);
						}
					}
					List<String> list2 = e.getInventory().getItem(3).getItemMeta().getLore();
					if(list2 != null){
						String loc = e.getInventory().getItem(3).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
						cfg.set(p.getName() + ".loc", loc);
					}
					
					cfg.set(p.getName() + ".elt", true);
					cfg.set(p.getName() + ".Entity", type);
					cfg.set(p.getName() + ".amo", amo);
					cfg.set(p.getName() + ".nameq", name);
					
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					p.closeInventory();
					p.sendMessage("[CSQ] : พิมID LOCATION ที่ตั้งลงไปได้เลย");
				}
				
				
			}
			if(e.getRawSlot() == 7){
				File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String type = e.getInventory().getItem(0).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", "");
				int amo = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				List<String> list = checkitem2(e);
				List<String> list2 = e.getInventory().getItem(3).getItemMeta().getLore();
				String Cuname = Checkitem2(list);
				String locs = Checkitem3(list2);
				
				
				List<String> lien = (List<String>) cfq.getList("Killmod");
				Boolean t = Checksave(lien,Cuname,type);
				if(t){
					if(Cuname.equalsIgnoreCase("null")){
						if(lien != null){
							lien.add(type);
							cfq.set("Killmod", lien);
						} else {
							List<String> l = new ArrayList<String>();
							l.add(type);
							cfq.set("Killmod", l);
						}
						
						cfq.set("Killmods." + type + ".type", type);
						cfq.set("Killmods." + type + ".AMO", amo);
						if(!locs.equalsIgnoreCase("null")){
							cfq.set("Killmods." + type + ".LOC", locs);
						}
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					} else {
						if(lien != null){
							lien.add(Cuname);
							cfq.set("Killmod", lien);
						} else {
							List<String> l = new ArrayList<String>();
							l.add(Cuname);
							cfq.set("Killmod", l);
						}
						
						
						cfq.set("Killmods." + Cuname + ".type", type);
						cfq.set("Killmods." + Cuname + ".AMO", amo);
						cfq.set("Killmods." + Cuname + ".NAME", Cuname);
						if(!locs.equalsIgnoreCase("null")){
							cfq.set("Killmods." + Cuname + ".LOC", locs);
						}
						
						try {
							cfq.save(fq);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						
						
					}
					
					p.closeInventory();
					this.MenuRemove(p, name);
					
					
					
					
				} else {
					p.sendMessage(ChatColor.RED + "[CSQ] : ไม่สามารถ Save ได้เนื่องจากอาจมีชื่อหรือมอนชนิดนี้แล้ว");
				}
				
			}
			
			if(e.getRawSlot() == 8){
				MenuQuests mq = new MenuQuests();
				mq.AddQuests(p, name);
			}
			
		} else if(e.getInventory().getName().contains("SELENTITY-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("SELENTITY-", "");
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 53){
				String type = e.getInventory().getItem(53).getItemMeta().getLore().get(0).replaceAll(ChatColor.GOLD + "", "");
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 38){
				ItemStack item = e.getInventory().getItem(e.getRawSlot());
				ItemMeta meta = item.getItemMeta();
				
				String type = meta.getDisplayName().replaceAll(ChatColor.GOLD + "", "");

				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name, type, amo, Cuname, locs);
				
				
			}
			
			if(e.getRawSlot() == 39){
				
				String type = EntityType.EVOKER.getName();
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			if(e.getRawSlot() == 40){
				
				String type = EntityType.MUSHROOM_COW.getName();
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			if(e.getRawSlot() == 41){
				
				String type = EntityType.SKELETON_HORSE.getName();
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			if(e.getRawSlot() == 42){
				
				String type = EntityType.VINDICATOR.getName();
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			if(e.getRawSlot() == 43){
				
				String type = EntityType.SKELETON_HORSE.getName();
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			if(e.getRawSlot() == 44){
				
				String type = "PLAYER";
				
				int amo = Integer.parseInt(e.getInventory().getItem(53).getItemMeta().getLore().get(1).replaceAll(ChatColor.GOLD + "", ""));
				String Cuname = e.getInventory().getItem(53).getItemMeta().getLore().get(2).replaceAll(ChatColor.GOLD + "", "");
				String locs = e.getInventory().getItem(53).getItemMeta().getLore().get(3).replaceAll(ChatColor.GOLD + "", "");
				this.menu(p, name,type, amo, Cuname, locs);
				
				
			}
			
			
		} else if(e.getInventory().getName().contains("RemoveEntity-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("RemoveEntity-", "");
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 26){
				MenuQuests mq = new MenuQuests();
				mq.AddQuests(p, name);
			}
			if(e.getRawSlot() >= 0 && e.getRawSlot() <= 25){
				if(e.getCurrentItem().getType() != Material.AIR){
					File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					String idname = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", "");
					
					
					List<String> lien = (List<String>) cfq.getList("Killmod");
					Object o = idname;
					lien.remove(o);
					
					
					cfq.set("Killmods."+idname, null);
					
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
	
	private List<String> checkitem2(InventoryClickEvent e) {
		if(e.getInventory().getItem(2) != null){
			List<String> list = e.getInventory().getItem(2).getItemMeta().getLore();
			return list;
		} else {
			return null;
		}
	}



	private Boolean Checksave(List<String> lien, String Cuname, String type) {
		if(lien != null){
			if(Cuname.equalsIgnoreCase("null")){
				Object o = type;
				if(lien.contains(o)){
					
					return false;
				}
			} else {
				Object o = Cuname;
				if(lien.contains(o)){
					
					return false;
				}
			}
		}
		return true;
	}



	public String Checkitem3(List<String> list2) {
		if(list2 != null){
			String locs = list2.get(0).replaceAll(ChatColor.GOLD + "", "");
			
			return locs;
		}
		
		
		
		return "null";
	}



	public String Checkitem2(List<String> list) {
		if(list != null){
			String Cuname = list.get(0).replaceAll(ChatColor.GOLD + "", "");
			
			return Cuname;
		}
		
		
		
		return "null";
	}



	@EventHandler
	public void As(AsyncPlayerChatEvent e){
		File file = new File("plugins//Cushyquests//chatdata.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Player p = e.getPlayer();
		int a = 1;
		if(a == 1){
			Boolean t = cfg.getBoolean(p.getName() + ".ecn");
			if(t){
				if(e.getMessage().equalsIgnoreCase("cancel")){
					e.setCancelled(true);
					
					String type = cfg.getString(p.getName() + ".Entity");
					int amo = cfg.getInt(p.getName() + ".amo");
					String loc = cfg.getString(p.getName() + ".loc");
					String cn = cfg.getString(p.getName() + ".cn");
					String name = cfg.getString(p.getName() + ".nameq");
					
					this.menu(p, name, type, amo, cn, loc);
					
					
					cfg.set(p.getName() + "", null);
					
					
					
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} else {
					e.setCancelled(true);
					
					String type = cfg.getString(p.getName() + ".Entity");
					int amo = cfg.getInt(p.getName() + ".amo");
					String loc = cfg.getString(p.getName() + ".loc");
					String cn = e.getMessage();
					String name = cfg.getString(p.getName() + ".nameq");
					
					this.menu(p, name, type, amo, cn, loc);
					
					
					cfg.set(p.getName() + "", null);
					
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		}
		
		if(a == 1){
			Boolean t = cfg.getBoolean(p.getName() + ".elt");
			if(t){
				if(e.getMessage().equalsIgnoreCase("cancel")){
					
					e.setCancelled(true);
					
					String type = cfg.getString(p.getName() + ".Entity");
					int amo = cfg.getInt(p.getName() + ".amo");
					String loc = cfg.getString(p.getName() + ".loc");
					String cn = cfg.getString(p.getName() + ".cn");
					String name = cfg.getString(p.getName() + ".nameq");
					
					this.menu(p, name, type, amo, cn, loc);
					
					
					cfg.set(p.getName() + "", null);
					
					
					
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					
				} else {
					e.setCancelled(true);
					
					String type = cfg.getString(p.getName() + ".Entity");
					int amo = cfg.getInt(p.getName() + ".amo");
					String loc = e.getMessage();
					String cn = cfg.getString(p.getName() + ".cn");
					String name = cfg.getString(p.getName() + ".nameq");
					
					this.menu(p, name, type, amo, cn, loc);
					
					
					cfg.set(p.getName() + "", null);
					
					
					
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					
					
				}
				
				
				
			}
		}
		
		
		
		
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
