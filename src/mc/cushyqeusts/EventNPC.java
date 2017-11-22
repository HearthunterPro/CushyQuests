package mc.cushyqeusts;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.citizensnpcs.api.event.NPCRightClickEvent;
import opquests.invitQuests;

public class EventNPC implements Listener {

	@EventHandler
	public void npc(NPCRightClickEvent e){
		File file1 = new File("plugins//Cushyquests//npc.yml");
		YamlConfiguration cfg1 = YamlConfiguration.loadConfiguration(file1);
		int asd = e.getNPC().getId();
		String a = cfg1.getString("npc."+asd);
		Player p = e.getClicker();
		if(a != null){
			String as = cfg1.getString("npc."+asd+".catalog");
			String as1 = cfg1.getString("npc."+asd+".quests");
			String as2 = cfg1.getString("npc."+asd+".command");
			if(as != null){
				File file = new File("plugins//Cushyquests//catalog//" + as + ".yml");
				YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
				String title = cfg.getString("title").replaceAll("(&([a-z0-9]))", "\u00A7$2");
				int size = cfg.getInt("sizeinv");
				
				Inventory inv = Bukkit.createInventory(null, size, title);
				for(int x =0;x<size;x++){
					ItemStack item = cfg.getItemStack("inv."+x);
					inv.setItem(x, item);
				}
				p.openInventory(inv);
				
			} else if(as1 != null){
				invitQuests iq = new invitQuests();
				iq.assing(p, as1);
				
			} else if(as2 != null){
				String com = as2.replaceAll("%player%", e.getClicker().getName());
				if(com.contains("<c>")){
					String com2 = as2.replaceAll("<c>", "");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), com2);
				} else {
					e.getClicker().performCommand(com);
				}
				
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void run(){
		File fn = new File("plugins//Cushyquests//npc.yml");
		YamlConfiguration cfn = YamlConfiguration.loadConfiguration(fn);
		
		File fcs = new File("plugins//Citizens//saves.yml");
		YamlConfiguration cfcs = YamlConfiguration.loadConfiguration(fcs);
		
		
		List<String> li = (List<String>) cfn.getList("npclist"); 
		
		
		if(li != null){

			int max = li.toArray().length;
			for(int x = 0;x<max;x++){
				
				String t = cfcs.getString("npc."+li.get(x) + ".name");
				if(t != null){
					String idnpc = li.get(x);
					
					String a = cfn.getString("npc."+idnpc);
					if(a != null){
						String as = cfn.getString("npc."+idnpc+".catalog");
						String as1 = cfn.getString("npc."+idnpc+".quests");
						String as2 = cfn.getString("npc."+idnpc+".command");
						
						String wn = cfcs.getString("npc."+idnpc+".traits.location.world");
						
						String xs = cfcs.getString("npc."+idnpc+".traits.location.x");
						String ys = cfcs.getString("npc."+idnpc+".traits.location.y");
						String zs = cfcs.getString("npc."+idnpc+".traits.location.z");
						
						double x2 = Double.parseDouble(xs);
						double y = Double.parseDouble(ys);
						double z = Double.parseDouble(zs);
						
						Location loc = new Location(Bukkit.getWorld(wn), x2, y+2.2, z);
						
						if(as != null){
							
							loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 0);
							
							
						} else if(as1 != null){
							
							loc.getWorld().spawnParticle(Particle.NOTE, loc, 0);
							
							
						} else if(as2 != null){
							
							loc.getWorld().spawnParticle(Particle.CLOUD, loc, 0, 0.1, 0.1, 0.1, 0.5);
							
							
						}
						
						
						
						
						
					}
					
					
					
				}
				
				
				
			}
		}
		
		
		
		
		
		
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void add(int id){
		File fn = new File("plugins//Cushyquests//npc.yml");
		YamlConfiguration cfn = YamlConfiguration.loadConfiguration(fn);
		
		
		List<String> li = (List<String>) cfn.getList("npclist"); 
		if(li != null){
			if(li.contains("" + id)){
				
			} else {
				li.add(""+id);
				
				cfn.set("npclist", li);
			}
		} else {
			ArrayList<String> lis = new ArrayList<String>();
			lis.add(""+id);
			
			cfn.set("npclist", lis);
		}
		
		
		
		try {
			cfn.save(fn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void remove(int id){
		File fn = new File("plugins//Cushyquests//npc.yml");
		YamlConfiguration cfn = YamlConfiguration.loadConfiguration(fn);
		
		
		List<String> li = (List<String>) cfn.getList("npclist"); 
		if(li != null){
			if(li.contains("" + id)){
				
				li.remove("" + id);
				
				cfn.set("npclist", li);
			}
		}
		
		try {
			cfn.save(fn);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
}
