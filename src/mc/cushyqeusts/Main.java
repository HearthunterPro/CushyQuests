package mc.cushyqeusts;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import MenuQuests.CreateQuestsMenu;
import MenuQuests.Eqment;
import MenuQuests.MenuQuests;
import MenuQuests.SetReward;
import MenuQuests.time;
import QuestsEvent.BlockBreak;
import QuestsEvent.BlockPlace;
import QuestsEvent.Cicknpc;
import QuestsEvent.Goarena;
import QuestsEvent.ItemSend;
import QuestsEvent.KillMod;
import opquests.EventQuests;
import opquests.invitQuests;

public class Main extends JavaPlugin{
	
	
	public void onEnable(){
		getCommand("quests").setExecutor(new Commands());
		getCommand("q").setExecutor(new Commands());
		getCommand("qe").setExecutor(new Commandsqe());
		getCommand("qs").setExecutor(new Commandsqs());
		getCommand("ql").setExecutor(new Commandsql());
		getCommand("qc").setExecutor(new Commandsqc());
		getServer().getPluginManager().registerEvents(new EventNPC(), this);
		getServer().getPluginManager().registerEvents(new editcat(), this);
		getServer().getPluginManager().registerEvents(new Clickcatalog(), this);
		getServer().getPluginManager().registerEvents(new CreateQuestsMenu(), this);
		getServer().getPluginManager().registerEvents(new MenuQuests(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new BlockPlace(), this);
		getServer().getPluginManager().registerEvents(new Cicknpc(), this);
		getServer().getPluginManager().registerEvents(new KillMod(), this);
		getServer().getPluginManager().registerEvents(new ItemSend(), this);
		getServer().getPluginManager().registerEvents(new Goarena(), this);
		getServer().getPluginManager().registerEvents(new SetReward(), this);
		getServer().getPluginManager().registerEvents(new Eqment(), this);
		getServer().getPluginManager().registerEvents(new time(), this);
		getServer().getPluginManager().registerEvents(new invitQuests(), this);
		getServer().getPluginManager().registerEvents(new EventQuests(), this);
		run();
	}


	private void run() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				EventNPC np = new EventNPC();
				np.run();
				
			}
		}, 0, 20);
		
	}


	public void onDisable(){
		
	}
}
