package MenuQuests;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import QuestsEvent.BlockBreak;
import QuestsEvent.BlockPlace;
import QuestsEvent.Cicknpc;
import QuestsEvent.Goarena;
import QuestsEvent.ItemSend;
import QuestsEvent.KillMod;

public class MenuQuests implements Listener{

	
	public void AddQuests(Player p,String namemenu){
		Inventory inv = Bukkit.createInventory(null, 18, "AddQuests-"+namemenu);
		int a = 1;
		if(a == 1){
			ItemStack item = new ItemStack(Material.COBBLESTONE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "BlockBreak");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			list.add(ChatColor.RED + "คลิกกลางแก้ไข");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(0, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.STONE);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "BlockPlace");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			list.add(ChatColor.RED + "คลิกกลางดู");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(1, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			item.setDurability((short) 3);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "CLICKNPC");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(2, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.SKULL_ITEM);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "KILLMOD");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(3, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.DIAMOND);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "ITEMSEND");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(4, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.GLASS);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "GOTOARENA");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(5, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.MOB_SPAWNER);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "KILLBOSS");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			list.add(ChatColor.RED + "Plugin : CushyBoss");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(6, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.FISHING_ROD);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "FISHING");
			ArrayList<String> list = new ArrayList<String>();
			list.add(ChatColor.RED + "คลิกขวาเพิม");
			list.add(ChatColor.RED + "คลิกซ้ายเอาออก");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(7, item);
		}
		if(a == 1){
			ItemStack item = new ItemStack(Material.REDSTONE_BLOCK);
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN + "Exit");
			List<String> list = new ArrayList<String>();
			list.add("");
			meta.setLore(list);
			item.setItemMeta(meta);
			inv.setItem(17, item);
		}
		
		
		
		
		p.openInventory(inv);
	}
	
	
	
	@EventHandler
	public void click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("AddQuests-")){
			e.setCancelled(true);
			String name = e.getInventory().getName().replaceAll("AddQuests-", "");
			Player p = (Player) e.getWhoClicked();
			if(e.getRawSlot() == 17){
				CreateQuestsMenu mm = new CreateQuestsMenu();
				mm.MenuQuests(p, name);
				
			} else if(e.getRawSlot() == 0){
				BlockBreak bb = new BlockBreak();
				if(e.getClick() == ClickType.LEFT){
					
					bb.addquests(p, name);
					
				} else if(e.getClick() == ClickType.RIGHT){
					bb.removequests(p,name);
				} else if(e.getClick() == ClickType.MIDDLE){
					bb.editquests(p,name);
				}
			} else if(e.getRawSlot() == 1){
				BlockPlace bb = new BlockPlace();
				if(e.getClick() == ClickType.LEFT){
					
					bb.addquests(p, name);
					
				} else if(e.getClick() == ClickType.RIGHT){
					bb.removequests(p,name);
				} else if(e.getClick() == ClickType.MIDDLE){
					bb.listquests(p,name);
				}
			} else if(e.getRawSlot() == 2){
				Cicknpc bb = new Cicknpc();
				if(e.getClick() == ClickType.LEFT){
					
					bb.add(p, name);
					
				} else if(e.getClick() == ClickType.RIGHT){
					bb.remove(p,name);
				} else if(e.getClick() == ClickType.MIDDLE){
					bb.remove(p,name);
				}
			} else if(e.getRawSlot() == 3){
				KillMod bb = new KillMod();
				if(e.getClick() == ClickType.LEFT){
					
					bb.menu(p,name,null, 0, null, null);
					
				} else if(e.getClick() == ClickType.RIGHT){
					
					bb.MenuRemove(p, name);
					
				}
			} else if(e.getRawSlot() == 4){
				ItemSend bb = new ItemSend();
				if(e.getClick() == ClickType.LEFT){
					
					bb.selitem(p, name);
					
				} else if(e.getClick() == ClickType.RIGHT){
					
					bb.MenuRemove(p, name);
					
				}
			} else if(e.getRawSlot() == 5){
				Goarena bb = new Goarena();
				if(e.getClick() == ClickType.LEFT){
					
					bb.addarena(p, name);
					
				} else if(e.getClick() == ClickType.RIGHT){
					
					bb.menuRemove(p, name);
					
				}
			}
			
			
			
		}
	}
	
	
	
	
}
