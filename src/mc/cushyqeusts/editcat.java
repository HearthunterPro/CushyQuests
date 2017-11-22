package mc.cushyqeusts;

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
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class editcat implements Listener {

	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Invent(InventoryClickEvent e){
		if(e.getInventory().getName().contains("invedit-")){
			Player p = (Player) e.getWhoClicked();
			if(e.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY){
				e.setCancelled(true);
				String names = e.getInventory().getName().replaceAll("invedit-", "");
				File file = new File("plugins//Cushyquests//catalog//"+names+".yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				int a = cfg.getInt("sizeinv");
				for(int x =0;x<a;x++){
					ItemStack item = e.getInventory().getItem(x);
					if(item != null){
						cfg.set("inv."+x, item);
					} else {
						cfg.set("inv."+x, null);
					}
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				p.sendMessage(ChatColor.AQUA + "[CQ] : save inventory");
			} else if(e.getAction() == InventoryAction.DROP_ONE_SLOT){
				e.setCancelled(true);
				String name = e.getInventory().getName().replaceAll("invedit-", "");
				File file = new File("plugins//Cushyquests//catalog//"+name+".yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				
				int a = cfg.getInt("sizeinv");
				for(int x =0;x<a;x++){
					ItemStack item = e.getInventory().getItem(x);
					if(item != null){
						cfg.set("inv."+x, item);
					} else {
						cfg.set("inv."+x, null);
					}
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
				String names = e.getInventory().getName().replaceAll("invedit-", "");
				Inventory inv = Bukkit.createInventory(null, 18, "invedits-"+names);
				
				ItemStack i = new ItemStack(Material.NAME_TAG, 1,(short) 0);
				ItemMeta me = i.getItemMeta();
				me.setDisplayName(ChatColor.GREEN + "ตั้งชื่อใหม่");
				i.setItemMeta(me);
				inv.setItem(0, i);
				
				ItemStack i1 = new ItemStack(Material.BONE, 1,(short) 0);
				ItemMeta me1 = i1.getItemMeta();
				me1.setDisplayName(ChatColor.GREEN + "เพิม lore ลงไป");
				i1.setItemMeta(me1);
				inv.setItem(1, i1);
				
				ItemStack i2 = new ItemStack(Material.CHEST, 1,(short) 0);
				ItemMeta me2 = i2.getItemMeta();
				me2.setDisplayName(ChatColor.GREEN + "เอา lore ออก");
				i2.setItemMeta(me2);
				inv.setItem(2, i2);
				
				ItemStack i11 = new ItemStack(Material.DIAMOND_CHESTPLATE, 1,(short) 0);
				ItemMeta me11 = i11.getItemMeta();
				me11.setDisplayName(ChatColor.GREEN + "เพิมคำสั่งลงไป");
				List<String> list2 = (List<String>) cfg.getList(e.getSlot() + ".com");
				if(list2 != null){
					me11.setLore(list2);
				}
				i11.setItemMeta(me11);
				inv.setItem(3, i11);
				
				ItemStack i111 = new ItemStack(Material.ENDER_CHEST, 1,(short) 0);
				ItemMeta me111 = i111.getItemMeta();
				me111.setDisplayName(ChatColor.GREEN + "ลบคำสั่ง");
				if(list2 != null){
					me111.setLore(list2);
				}
				i111.setItemMeta(me111);
				inv.setItem(4, i111);
				
				ItemStack i1111 = new ItemStack(Material.APPLE, 1,(short) 0);
				ItemMeta me1111 = i1111.getItemMeta();
				me1111.setDisplayName(ChatColor.GREEN + "เพิม Catalog");
				i1111.setItemMeta(me1111);
				inv.setItem(5, i1111);

				ItemStack i11111 = new ItemStack(Material.EMERALD, 1,(short) 0);
				ItemMeta me11111 = i11111.getItemMeta();
				me11111.setDisplayName(ChatColor.GREEN + "เพิม Quests");
				i11111.setItemMeta(me11111);
				inv.setItem(6, i11111);
				
				ItemStack i111111 = new ItemStack(Material.DIAMOND, 1,(short) 0);
				ItemMeta me111111 = i111111.getItemMeta();
				ArrayList<String> list = new ArrayList<String>();
				list.add(ChatColor.GREEN + "ลบ Catalog และ  Quests ออก");
				me111111.setLore(list);
				me111111.setDisplayName("" + e.getSlot());
				i111111.setItemMeta(me111111);
				inv.setItem(7, i111111);
				
				ItemStack i1111111 = new ItemStack(Material.GOLD_INGOT, 1,(short) 0);
				ItemMeta me1111111 = i1111111.getItemMeta();
				me1111111.setDisplayName(ChatColor.GREEN + "ตั่งข้อความเมือกด");
				i1111111.setItemMeta(me1111111);
				inv.setItem(8, i1111111);
				
				ItemStack i11111111 = new ItemStack(Material.IRON_INGOT, 1,(short) 0);
				ItemMeta me11111111 = i11111111.getItemMeta();
				me11111111.setDisplayName(ChatColor.GREEN + "ลบ Command quests catalog ออก”");
				i11111111.setItemMeta(me11111111);
				inv.setItem(9, i11111111);
				
				ItemStack i21 = new ItemStack(Material.REDSTONE_BLOCK, 1,(short) 0);
				ItemMeta me21 = i21.getItemMeta();
				me21.setDisplayName(ChatColor.RED + "กลับ");
				i21.setItemMeta(me21);
				inv.setItem(17, i21);
				
				p.openInventory(inv);
			} else {
				
			}
		} else if(e.getInventory().getName().contains("invedits")){
			e.setCancelled(true);
			Player p = (Player) e.getWhoClicked();
			if(e.getSlot() == 0){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 1);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมชื่อลงไป");
			} else if(e.getSlot() == 1){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 2);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมคำลงไป");
			} else if(e.getSlot() == 2){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 3);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมตัวเลขลงไป”");
			} else if(e.getSlot() == 3){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 4);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมคำสั่งลงไปไม่ต้องใส่ / และใช้ %player% แทนผู้เล่น");
			} else if(e.getSlot() == 4){ 
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 5);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมตัวเลขลงไป");
			} else if(e.getSlot() == 5){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				File file2 = new File("plugins//Cushyquests//catalog//"+names+".yml");
				YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
				int slot = cfg.getInt(p.getName()+".SLOT");
				String na = cfg2.getString(slot+".catalog");
				String naq = cfg2.getString(slot+".quests");
				if(na == null && naq == null){
					cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
					cfg.set(p.getName()+".Name", names);
					cfg.set(p.getName()+".ChatName", 6);
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					File file21 = new File("plugins//Cushyquests//catalog//");
					File[] li = file21.listFiles();
					for(int x =0;x<li.length;x++){
							p.sendMessage("" + x + " " +li[x].getName());
					}
					p.sendMessage(ChatColor.AQUA + "[CQ] : พิ ID Catalog ลงไป");
				} else {
					p.sendMessage("...");
				}
				
			} else if(e.getSlot() == 6){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				File file2 = new File("plugins//Cushyquests//catalog//"+names+".yml");
				YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
				int slot = cfg.getInt(p.getName()+".SLOT");
				String na = cfg2.getString(slot+".catalog");
				String naq = cfg2.getString(slot+".quests");
				if(na == null && naq == null){
					cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
					cfg.set(p.getName()+".Name", names);
					cfg.set(p.getName()+".ChatName", 7);
					try {
						cfg.save(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					p.closeInventory();
					File file21 = new File("plugins//Cushyquests//quests//");
					File[] li = file21.listFiles();
					for(int x =0;x<li.length;x++){
							p.sendMessage("" + x + " " +li[x].getName());
					}
					p.sendMessage(ChatColor.AQUA + "[CQ] : พิม ID Quests ลงไป");
				} else {
					p.sendMessage("....");
				}
				
			} else if(e.getSlot() == 7){
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				File file = new File("plugins//Cushyquests//catalog//"+names+".yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				int d = Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName());
				String cal = cfg.getString(d + "");
				if(cal != null){
					cfg.set(d + ".catalog", null);
					cfg.set(d + ".quests", null);
				}
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.AQUA + "[CQ] : ลบ Quests และ Catalog แล้ว");
			} else if(e.getSlot() == 8){
				File file = new File("plugins//Cushyquests//plaq.yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				cfg.set(p.getName()+".SLOT", Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName()));
				cfg.set(p.getName()+".Name", names);
				cfg.set(p.getName()+".ChatName", 8);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.closeInventory();
				p.sendMessage(ChatColor.AQUA + "[CQ] : พิมข้อความลงไป");
			} else if(e.getSlot() == 9){
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				File file = new File("plugins//Cushyquests//catalog//"+names+".yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				int d = Integer.parseInt(e.getInventory().getItem(7).getItemMeta().getDisplayName());
				String cal = cfg.getString(d + "");
				if(cal != null){
					cfg.set(d + "", null);
				}
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				p.sendMessage(ChatColor.AQUA + "[CQ] : ลบหใดแล้ว");
			} else if(e.getSlot() == 17){
				String names = e.getInventory().getName().replaceAll("invedits-", "");
				p.performCommand("qc edit " + names);
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void Chats(AsyncPlayerChatEvent e){
		File file = new File("plugins//Cushyquests//plaq.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int name = cfg.getInt(e.getPlayer().getName()+".ChatName");
		if(name > 0){
			if(e.getMessage().equalsIgnoreCase("cancel")){
				cfg.set(e.getPlayer().getName(), null);
				try {
					cfg.save(file);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(name == 1){
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			
			String n = e.getMessage().replaceAll("(&([a-z0-9]))", "\u00A7$2");
			
			ItemStack item = cfg2.getItemStack("inv." + slot);
			ItemMeta me = item.getItemMeta();
			
			me.setDisplayName(n);
			item.setItemMeta(me);
			
			cfg2.set("inv."+slot, item);
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(name == 2){
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			
			String n = e.getMessage().replaceAll("(&([a-z0-9]))", "\u00A7$2");
			
			ItemStack item = cfg2.getItemStack("inv." + slot);
			ItemMeta me = item.getItemMeta();
			List<String> li = me.getLore();
			if(li != null){
				li.add(n);
				me.setLore(li);
				item.setItemMeta(me);
			} else {
				ArrayList<String> l = new ArrayList<String>();
				l.add(n);
				me.setLore(l);
				item.setItemMeta(me);
			}
			
			cfg2.set("inv."+slot, item);
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if(name == 3){
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			
			int n = Integer.parseInt(e.getMessage());
			
			ItemStack item = cfg2.getItemStack("inv." + slot);
			ItemMeta me = item.getItemMeta();
			List<String> li = me.getLore();
			if(li != null){
				li.remove(n);
				me.setLore(li);
				item.setItemMeta(me);
			} else {
				
			}
			
			cfg2.set("inv."+slot, item);
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(name == 4){
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			
			String n = e.getMessage();
			List<String> list = (List<String>) cfg2.getList(slot+".com");
			if(list != null){
				list.add(n);
				cfg2.set(slot+".com", list);
			} else {
				ArrayList<String> li = new ArrayList<String>();
				li.add(n);
				cfg2.set(slot+".com", li);
			}
			
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if(name == 5){
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			
			int n = Integer.parseInt(e.getMessage());
			List<String> list = (List<String>) cfg2.getList(slot+".com");
			if(list != null){
				list.remove(n);
				cfg2.set(slot+".com", list);
			} else {
				
			}
			
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else if(name == 6){
			//===================================================================
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			String text = e.getMessage();
			String na = cfg2.getString(slot+".catalog");
			if(na == null){
				cfg2.set(slot+".catalog", text);
				try {
					cfg2.save(file2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				e.getPlayer().sendMessage("ลบ Catalog แล้ว");
			}
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else if(name == 7){
			//===================================================================
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			String na = cfg2.getString(slot+".quests");
			if(na == null){
				String text = e.getMessage();
				cfg2.set(slot+".quests", text);
				try {
					cfg2.save(file2);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} else {
				e.getPlayer().sendMessage("ลบ Quests แล้ว");
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		} else if(name == 8){
			//===================================================================
			e.setCancelled(true);
			
			String name2 = cfg.getString(e.getPlayer().getName()+".Name");
			
			File file2 = new File("plugins//Cushyquests//catalog//"+name2+".yml");
			YamlConfiguration cfg2 = YamlConfiguration.loadConfiguration(file2);
			
			int slot = cfg.getInt(e.getPlayer().getName()+".SLOT");
			String text = e.getMessage();
			cfg2.set(slot+".send", text);
			try {
				cfg2.save(file2);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			e.getPlayer().performCommand("qc edit " + name2);
			
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
		}
		
		
	}
	
	//=============================================================================================
	
	
	
	
	@EventHandler
	public void Quests(PlayerQuitEvent e){
		File file = new File("plugins//Cushyquests//plaq.yml");
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		int name = cfg.getInt(e.getPlayer().getName()+".ChatName");
		if(name > 0){
			cfg.set(e.getPlayer().getName(), null);
			try {
				cfg.save(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	
}
