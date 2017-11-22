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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class time implements Listener {

	
	
	
	public void menu(Player p,String name){
		Inventory inv = Bukkit.createInventory(null, 9, "CSQSETTIME-" + name);
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		int s = cfq.getInt("time.s");
		int m = cfq.getInt("time.m");
		int h = cfq.getInt("time.h");
		int d = cfq.getInt("time.d");
		int M = cfq.getInt("time.M");
		int y = cfq.getInt("time.y");
		
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.GOLD_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + s);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "วินาที");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(0, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.IRON_INGOT);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + m);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "นาที");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + h);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "ชั่วโมง");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(2, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.BRICK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + d);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "วัน");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(3, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.COAL);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + M);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "เดือน");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(4, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.NETHER_BRICK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "" + y);
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "ปี");
			list.add(ChatColor.GOLD + "Left = +1");
			list.add(ChatColor.GOLD + "Right = -1");
			list.add(ChatColor.GOLD + "Shift + Left = เปลียนเป็น +5");
			list.add(ChatColor.GOLD + "Shift + Right = เปลียนเป็น +10");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(5, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.EMERALD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "Save Time");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.GREEN + "ถ้าตั้งเป้น 0 หมด จะเป้นการตั้งไม่มีเวลา");
			list.add(ChatColor.GREEN + "นั้นคือทำเควสได้แค่ครังเดียว");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(7, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GOLD + "back");
			item.setItemMeta(meta);
			inv.setItem(8, item);
		}
		
		
		p.openInventory(inv);
		
	}
	
	
	
	
	
	
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("CSQSETTIME-")){
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			String name = e.getInventory().getName().replaceAll("CSQSETTIME-", "");
			if(e.getRawSlot() == 0){
				sel(e.getRawSlot(),e,p,59);
			}
			if(e.getRawSlot() == 1){
				sel(e.getRawSlot(),e,p,59);
			}
			if(e.getRawSlot() == 2){
				sel(e.getRawSlot(),e,p,23);
			}
			if(e.getRawSlot() == 3){
				sel(e.getRawSlot(),e,p,30);
			}
			if(e.getRawSlot() == 4){
				sel(e.getRawSlot(),e,p,11);
			}
			if(e.getRawSlot() == 5){
				sel(e.getRawSlot(),e,p,1000);
			}
			if(e.getRawSlot() == 7){
				File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				int s = Integer.parseInt(e.getInventory().getItem(0).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				int m = Integer.parseInt(e.getInventory().getItem(1).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				int h = Integer.parseInt(e.getInventory().getItem(2).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				int d = Integer.parseInt(e.getInventory().getItem(3).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				int M = Integer.parseInt(e.getInventory().getItem(4).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				int y = Integer.parseInt(e.getInventory().getItem(5).getItemMeta().getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
				
				
				cfq.set("time.s", s);
				cfq.set("time.m", m);
				cfq.set("time.h", h);
				cfq.set("time.d", d);
				cfq.set("time.M", M);
				cfq.set("time.y", y);
				
				try {
					cfq.save(fq);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				p.sendMessage("[CSQ] : save Time");
				
			}
			if(e.getRawSlot() == 8){
				CreateQuestsMenu mq = new CreateQuestsMenu();
				mq.MenuQuests(p, name);
				
				
				
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	public void sel(int a, InventoryClickEvent e, Player p,int max){
		ItemStack item = e.getInventory().getItem(a);
		ItemMeta meta = item.getItemMeta();
		
		if(e.getClick() == ClickType.LEFT){
			int asd = Integer.parseInt(meta.getLore().get(1).replaceAll(ChatColor.GOLD + "Left = +", ""));
			int ds = Integer.parseInt(meta.getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
			
			int d = asd + ds;
			
			if(d <= max){
				
				
				meta.setDisplayName(ChatColor.GOLD + "" + d);
				
				item.setItemMeta(meta);
				
				e.getInventory().setItem(a, item);
			} else {
				meta.setDisplayName(ChatColor.GOLD + "0");
				
				item.setItemMeta(meta);
				
				e.getInventory().setItem(a, item);
			}
			
		} else if(e.getClick() == ClickType.RIGHT){
			int asd = Integer.parseInt(meta.getLore().get(1).replaceAll(ChatColor.GOLD + "Left = +", ""));
			
			int ds = Integer.parseInt(meta.getDisplayName().replaceAll(ChatColor.GOLD + "", ""));
			int d = ds - asd;
			
			if(d >= 0){
				
				
				meta.setDisplayName(ChatColor.GOLD + "" + d);
				
				item.setItemMeta(meta);
				
				e.getInventory().setItem(a, item);
			} else {
				
				meta.setDisplayName(ChatColor.GOLD + "" + max);
				
				item.setItemMeta(meta);
				
				e.getInventory().setItem(a, item);
			}
			
			
			
		} else if(e.getClick() == ClickType.SHIFT_LEFT){
			int asd = Integer.parseInt(meta.getLore().get(1).replaceAll(ChatColor.GOLD + "Left = +", ""));
			
			int ddd = Check(asd);
			
			List<String> list = meta.getLore();
			list.set(1, ChatColor.GOLD + "Left = +" + ddd);
			list.set(2, ChatColor.GOLD + "Right = -" + ddd);
			
			int ds = Check(ddd);
			int ds2 = Check2(ddd);
			
			list.set(3, ChatColor.GOLD + "Shift + Left = เปลียนเป็น +" + ds);
			list.set(4, ChatColor.GOLD + "Shift + Right = เปลียนเป็น +" + ds2);
			
			meta.setLore(list);
			item.setItemMeta(meta);
			
			e.getInventory().setItem(a, item);
			
		} else if(e.getClick() == ClickType.SHIFT_RIGHT){
			
			int asd = Integer.parseInt(meta.getLore().get(1).replaceAll(ChatColor.GOLD + "Left = +", ""));
			
			int ddd = Check2(asd);
			
			List<String> list = meta.getLore();
			list.set(1, ChatColor.GOLD + "Left = +" + ddd);
			list.set(2, ChatColor.GOLD + "Right = -" + ddd);
			
			int ds = Check(ddd);
			int ds2 = Check2(ddd);
			
			list.set(3, ChatColor.GOLD + "Shift + Left = เปลียนเป็น +" + ds);
			list.set(4, ChatColor.GOLD + "Shift + Right = เปลียนเป็น +" + ds2);
			
			meta.setLore(list);
			item.setItemMeta(meta);
			
			e.getInventory().setItem(a, item);
			
			
		}
	}
	
	
	public int Check(int asd) {
		if(asd == 1){
			return 5;
		} else if(asd == 5){
			return 10;
		} else if(asd == 10){
			return 1;
		}
		
		return 1;
	}
	
	public int Check2(int asd) {
		if(asd == 1){
			return 10;
		} else if(asd == 5){
			return 1;
		} else if(asd == 10){
			return 5;
		}
		
		return 0;
	}
	
	
}
