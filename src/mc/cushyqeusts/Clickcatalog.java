
package mc.cushyqeusts;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import opquests.invitQuests;

public class Clickcatalog implements Listener {

	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void clickinv(InventoryClickEvent e){
		File file1 = new File("plugins//Cushyquests//catalog//");
		File[] fl = file1.listFiles();
		if(fl != null){
			for(int x =0;x<fl.length;x++){
				File file = new File("plugins//Cushyquests//catalog//" + fl[x].getName());
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String title = cfg.getString("title").replaceAll("(&([a-z0-9]))", "\u00A7$2");
				int size = cfg.getInt("sizeinv");
				Inventory inv = Bukkit.createInventory(null, size, title);
				for(int x2 =0;x2<size;x2++){
					ItemStack item = cfg.getItemStack("inv."+x2);
					inv.setItem(x2, item);
				}
				if(e.getInventory().getName().equals(inv.getName())){
					e.setCancelled(true);
					Player p = (Player) e.getWhoClicked();
					if(e.getAction() != InventoryAction.NOTHING){
						if(e.getCursor().getType() != null){
								String com = cfg.getString(e.getSlot() + ".com");
								if(com != null){
									List<String> list = (List<String>) cfg.getList(e.getSlot() + ".com");
									Object[] ob = list.toArray();
									for(int x2=0;x2<ob.length;x2++){
										String coms = ((String) ob[x2]).replaceAll("%player%", p.getName());
										if(coms.contains("console")){
											String coms2 = coms.replaceAll("<console>", "");
											Bukkit.dispatchCommand(Bukkit.getConsoleSender(), coms2);
										} else {
											p.performCommand(coms);
										}
									}
									
								}
								String catalog = cfg.getString(e.getSlot() + ".catalog");
								String quests = cfg.getString(e.getSlot() + ".quests");
								if(catalog != null){
									File file11 = new File("plugins//Cushyquests//catalog//" + catalog + ".yml");
									YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file11);
									String title1 = cfg1.getString("title").replaceAll("(&([a-z0-9]))", "\u00A7$2");
									int size1 = cfg1.getInt("sizeinv");
									
									Inventory inv1 = Bukkit.createInventory(null, size1, title1);
									for(int x1 =0;x1<size1;x1++){
										ItemStack item = cfg1.getItemStack("inv."+x1);
										inv1.setItem(x1, item);
									}
									p.openInventory(inv1);
								} else if(quests != null){
									invitQuests iq = new invitQuests();
									iq.assing(p, quests);
								}
								String send = cfg.getString(e.getSlot() + ".send");
								if(send != null){
									String send2 = cfg.getString(e.getSlot() + ".send").replaceAll("(&([a-z0-9]))", "\u00A7$2");
									p.sendMessage(send2);
								}
								
						}
					}
					
					
					
				}
				
			}
		}
		
		
		
	}
	
	
	
}
