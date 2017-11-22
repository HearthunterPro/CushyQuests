package opquests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.RegisteredServiceProvider;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.milkbowl.vault.economy.Economy;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;

public class invitQuests implements Listener {

	
	public static Economy economy = null;
	
	private static int ite = 0;
	private static int itec = 0;
	
	
	
	@SuppressWarnings("unchecked")
	public void Checkquestslist(Player p,String name,Player ps){
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		invitQuests iq = new invitQuests();
		List<String> list = (List<String>) cfp.getList("Questslist");
		if(list != null){
			
			Object o = name;
			if(list.contains(o)){
				if(ps != null){
					ps.sendMessage(ChatColor.RED + "[CSQ] : เควสนี้มีอยู่แล้ว");
				} else {
					p.sendMessage(ChatColor.RED + "[CSQ] : เควสนี้มีอยู่แล้ว");
				}
				
			} else {
				if(list.toArray().length < 3){
					if(ps != null){
						iq.invitquests(p, name);
					} else {
						iq.ChecEqment(p, name);
					}
				} else {
					if(ps != null){
						ps.sendMessage(ChatColor.RED + "[CSQ] : เควสรับเต็มแล้วไม่สามารถรับได้");
					} else {
						p.sendMessage(ChatColor.RED + "[CSQ] : เควสรับเต็มแล้วไม่สามารถรับได้");
					}
					
				}
			}
		} else {
			 
			if(ps != null){
				iq.invitquests(p, name);
			} else {
				iq.ChecEqment(p, name);
			}
		}
		
		
		
		
	}
	
	
	
	public void ChecEqment(Player p,String name){
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		
		boolean ta = checkquestscom(cfp,name);
		if(ta == false){
			
			boolean a = ChecEqment2(p,name);
			if(a){
				this.invitquests(p, name);
			} else {
				p.sendMessage(ChatColor.GREEN + "[CSQ] : คุณมีบ้างอย่างไม่ครบที่จะได้รับเควสนี้โปรดดูให้แน่ใจว่าคุณมีสิทธิ");
			}
		} else {
			String chcktime = cfp.getString("Quests.time."+name+".s");
			if(chcktime != null){
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat ss2 = new SimpleDateFormat("ss");
				int ss = Integer.parseInt(ss2.format(cal.getTime()));
				
				SimpleDateFormat mm2 = new SimpleDateFormat("mm");
				int mm = Integer.parseInt(mm2.format(cal.getTime()));
				
				SimpleDateFormat hh2 = new SimpleDateFormat("HH");
				int hh = Integer.parseInt(hh2.format(cal.getTime()));
				
				SimpleDateFormat dd2 = new SimpleDateFormat("dd");
				int dd = Integer.parseInt(dd2.format(cal.getTime()));
				
				SimpleDateFormat MM2 = new SimpleDateFormat("MM");
				int MM = Integer.parseInt(MM2.format(cal.getTime()));
				
				SimpleDateFormat yy2 = new SimpleDateFormat("yyyy");
				int yy = Integer.parseInt(yy2.format(cal.getTime()));
				
				int s = cfp.getInt("Quests.time."+name+".s");
				int m = cfp.getInt("Quests.time."+name+".m");
				int h = cfp.getInt("Quests.time."+name+".h");
				int d = cfp.getInt("Quests.time."+name+".d");
				int M = cfp.getInt("Quests.time."+name+".M");
				int y = cfp.getInt("Quests.time."+name+".y");
				
				boolean times = chectime(cfp,name);
				
				if(times){
					
					boolean a = ChecEqment2(p,name);
					if(a){
						this.invitquests(p, name);
					} else {
						p.sendMessage(ChatColor.GREEN + "[CSQ] : คุณมีบ้างอย่างไม่ครบที่จะได้รับเควสนี้โปรดดูให้แน่ใจว่าคุณมีสิทธิ");
					}
					
				} else {
					int ns = ch(ss,s,60);
					int nm = ch(mm,m,60);
					int nh = ch(hh,h,24);
					int nd = chd(dd,d,M);
					int nM = ch(MM,M,12);
					int ny = y - yy;
					
					
					p.sendMessage(ChatColor.RED + "[CSQ] : เหลือเวลาในการรับเควส " + ns + "s " + nm + "m " + nh + "h " + nd + "d " + nM + "M " + ny + "y ");
				}
				
				
				
			} else {
				p.sendMessage(ChatColor.RED + "[CSQ] : คุณได้ทำเควสนี้แล้ว เควสนี้ไม่มีการทำซ้ำได้");
			}
		}
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	private boolean checkquestscom(YamlConfiguration cfp,String name) {
		List<String> list = (List<String>) cfp.getList("Quests.complete");
		if(list != null){
			if(list.contains("" + name)){
				return true;
			}
		}
		
		
		return false;
	}



	private boolean chectime(YamlConfiguration cfp, String name) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat ss2 = new SimpleDateFormat("ss");
		int ss = Integer.parseInt(ss2.format(cal.getTime()));
		
		SimpleDateFormat mm2 = new SimpleDateFormat("mm");
		int mm = Integer.parseInt(mm2.format(cal.getTime()));
		
		SimpleDateFormat hh2 = new SimpleDateFormat("HH");
		int hh = Integer.parseInt(hh2.format(cal.getTime()));
		
		SimpleDateFormat dd2 = new SimpleDateFormat("dd");
		int dd = Integer.parseInt(dd2.format(cal.getTime()));
		
		SimpleDateFormat MM2 = new SimpleDateFormat("MM");
		int MM = Integer.parseInt(MM2.format(cal.getTime()));
		
		SimpleDateFormat yy2 = new SimpleDateFormat("yyyy");
		int yy = Integer.parseInt(yy2.format(cal.getTime()));
		
		int s = cfp.getInt("Quests.time."+name+".s");
		int m = cfp.getInt("Quests.time."+name+".m");
		int h = cfp.getInt("Quests.time."+name+".h");
		int d = cfp.getInt("Quests.time."+name+".d");
		int M = cfp.getInt("Quests.time."+name+".M");
		int y = cfp.getInt("Quests.time."+name+".y");
		
		
		if(yy == y){
			if(MM == M){
				if(dd == d){
					if(hh == h){
						if(mm == m){
							if(ss >= s){
								return true;
							}
						} else if(mm > m){
							return true;
						}
					} else if(hh > h){
						return true;
					}
				} else if(dd > d){
					return true;
				}
			} else if(MM > M){
				return true;
			}
		} else if(yy > y){
			return true;
		}
		
		
		
		return false;
	}



	@SuppressWarnings({ "unchecked" })
	private Boolean ChecEqment2(Player p, String name) {
		setupEconomy();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		int price = cfq.getInt("Eqment.money");
		boolean mr = cfq.getBoolean("Eqment.mr");
		if(price != 0){
			int money = (int) economy.getBalance(p);
			if(money >= price){
				if(mr){
					economy.withdrawPlayer(p, price);
				}
			} else {
				return false;
			}
			
		}
		int level = cfq.getInt("Eqment.level");
		boolean lr = cfq.getBoolean("Eqment.lr");
		if(level != 0){
			int level2 = p.getLevel();
			if(level2 >= level){
				if(lr){
					p.setLevel(level2 - level);
				}
			} else {
				return false;
			}
		}
		
		List<String> li = (List<String>) cfq.getList("Eqment.QUESTS");
		
		if(li != null){
			if(li.toArray().length != 0){
				for(int x = 0;x<li.toArray().length;x++){
					String qname = li.get(x);
					
					List<String> list = (List<String>) cfp.getList("Quests.complete");
					if(list != null){
						if(!list.contains("" + qname)){
							return false;
						}
					}
					
				}
			}
		}
		
		boolean chitem = checkitem(p,cfq);
		if(chitem){
			return false;
		}
		
		
		
		
		return true;
		
		
		
	}



	private boolean checkitem(Player p, YamlConfiguration cfq) {
		
		ite = 0;
		itec = 0;
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Eqment.ITEMSTACK."+x);
			if(item != null){
				itec = itec + 1;
				for(int x2 = 0;x2<p.getInventory().getSize();x2++){
					ItemStack item2 = p.getInventory().getItem(x2);
					if(item.equals(item2)){
						ite = ite + 1;
						break;
					}
					
				}
			}
			
			
			
		}
		if(itec == ite){
			return false;
		} else {
			return true;
		}
		
		
	}



	private int chd(int dd, int d, int m) {
		if(m == 1){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 2){
			int nh = ch(dd,d,28);
			return nh;
		} else if(m == 3){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 4){
			int nh = ch(dd,d,30);
			return nh;
		} else if(m == 5){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 6){
			int nh = ch(dd,d,30);
			return nh;
		} else if(m == 7){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 8){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 9){
			int nh = ch(dd,d,30);
			return nh;
		} else if(m == 10){
			int nh = ch(dd,d,31);
			return nh;
		} else if(m == 11){
			int nh = ch(dd,d,30);
			return nh;
		} else if(m == 12){
			int nh = ch(dd,d,31);
			return nh;
		}
		return 0;
		
	}



	private int ch(int ss, int s, int i) {
		if(s >= ss){
			int ns = s - ss;
			return ns;
		} else {
			int ns = (i + s) - ss;
			return ns;
		}
	}



	public static ItemStack book(String title, String author) {
		
		File fq = new File("plugins//Cushyquests//quests//" + title + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		String nameq = cfq.getString("Name");
		String textstart = cfq.getString("messstart");
		
		int s = cfq.getInt("time.s");
		int m = cfq.getInt("time.m");
		int h = cfq.getInt("time.h");
		int d = cfq.getInt("time.d");
		int M = cfq.getInt("time.M");
		int y = cfq.getInt("time.y");
		
		String bbb = checktime(s,m,h,d,M,y);
		
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_12_R1.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        
        bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
        		+ "{\"text\":\"\nName : \",\"color\":\"green\"},{\"text\":\""+nameq+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"IDQuests : "+title+"\"}},"
        		+ "{\"text\":\"\n\"},{\"text\":\"[ลายระเอียดเควส]\",\"color\":\"dark_blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+textstart+"\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[ภาระกิจทีต้องทำ]\",\"color\":\"dark_aqua\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"2\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[รางวัลทีได้]\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"3\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[สิ่งที่ต้องมีก่อนทำเควส]\",\"color\":\"dark_purple\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"4\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[เวลาในการทำเควสอีกรอบ]\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+bbb+"\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},{\"text\":\">>>Hover mouse in text\",\"color\":\"dark_red\"},"
        		+ "{\"text\":\"\n\"},"+"{\"text\":\"[รับเควส]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับเควส\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q add "+title+"\"}}," + "{\"text\":\"[ดูเควสของตนเอง]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือเปิด\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
        		+ "{\"text\":\"\"}"
        		+ "]}"));
        
    	
        Quests(bp,title);
        Reward(bp,title);
        Eqment(bp,title);
        
        bd.set("pages", bp);
        
        
        
        nmsis.setTag(bd);
        is = CraftItemStack.asBukkitCopy(nmsis);
        return is;
    }
	
	
	
	
	@SuppressWarnings("unchecked")
	private static void Eqment(NBTTagList bp, String title) {
		File fq = new File("plugins//Cushyquests//quests//" + title + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		String nameq = cfq.getString("Name");
		
		List<String> lb = (List<String>) cfq.getList("Eqment.QUESTS");
		
		String pri = checkprice(cfq);
		
		String lvl = checklvl(cfq);
		
		String ste = checkQu(lb);
		
		String naitem = chackitemeq(cfq);
		
		bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
        		+ "{\"text\":\"\nName : \",\"color\":\"green\"},{\"text\":\""+nameq+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[กลับไปหน้าแรก]\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิก\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"1\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + pri
        		+ "{\"text\":\"\n\"}," + lvl
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+ste+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" eqq\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+naitem+"\",\"color\":\"dark_purple\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" eqi\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},{\"text\":\">>>Hover mouse in text\",\"color\":\"dark_red\"},"
        		+ "{\"text\":\"\n\"},"+"{\"text\":\"[รับเควส]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับเควส\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q add "+title+"\"}}," + "{\"text\":\"[ดูเควสของตนเอง]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือเปิด\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
        		+ "{\"text\":\"\"}"
        		+ "]}"));
		
	}




	private static String checklvl(YamlConfiguration cfq) {
		int price = cfq.getInt("Eqment.level");
		boolean t = cfq.getBoolean("Eqment.lr");
		if(price != 0){
			if(t){
				return "{\"text\":\"ต้องใช้เลเวล : \",\"color\":\"dark_green\"}," + "{\"text\":\""+price+"\",\"color\":\"red\"},";
			} else {
				return "{\"text\":\"ต้องมีเลเวล : \",\"color\":\"dark_green\"}," + "{\"text\":\""+price+"\",\"color\":\"red\"},";
			}
		}
		
		return "";
	}



	private static String checkprice(YamlConfiguration cfq) {
		int price = cfq.getInt("Eqment.money");
		boolean t = cfq.getBoolean("Eqment.mr");
		if(price != 0){
			if(t){
				return "{\"text\":\"ต้องจ่ายเงิน : \",\"color\":\"dark_green\"}," + "{\"text\":\""+price+"\",\"color\":\"red\"},";
			} else {
				return "{\"text\":\"ต้องมีเงิน : \",\"color\":\"dark_green\"}," + "{\"text\":\""+price+"\",\"color\":\"red\"},";
			}
		}
		
		return "";
	}



	private static String chackitemeq(YamlConfiguration cfq) {
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Eqment.ITEMSTACK." + x);
			if(item != null){
				return "[Item]";
			}
		}
		return "";
	}




	private static String checkQu(List<String> lb) {
		if(lb != null){
			if(lb.toArray().length != 0){
				return "[Quests]";
			} else {
				return "";
			}
		}
		return "";
	}




	public static void Reward(NBTTagList bp, String title) {
		File fq = new File("plugins//Cushyquests//quests//" + title + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		String nameq = cfq.getString("Name");
		
		String message = cfq.getString("Reward.message");
		int price = cfq.getInt("Reward.Price");
		int Level = cfq.getInt("Reward.Level");
		int EXP = cfq.getInt("Reward.EXP");
		
		String naitem = chackitem(cfq);
		
		bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
        		+ "{\"text\":\"\nName : \",\"color\":\"green\"},{\"text\":\""+nameq+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[กลับไปหน้าแรก]\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิก\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"1\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[ลายละเอียดรางวัล]\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+message+"\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"เงินทีได้จากเควส : \",\"color\":\"dark_green\"}," + "{\"text\":\""+price+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"Level ทีได้จากเควส : \",\"color\":\"dark_green\"}," + "{\"text\":\""+Level+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"EXP ทีได้จากเควส : \",\"color\":\"dark_green\"}," + "{\"text\":\""+EXP+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+naitem+"\",\"color\":\"dark_purple\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" rwi\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},{\"text\":\">>>Hover mouse in text\",\"color\":\"dark_red\"},"
        		+ "{\"text\":\"\n\"},"+"{\"text\":\"[รับเควส]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับเควส\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q add "+title+"\"}}," + "{\"text\":\"[ดูเควสของตนเอง]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือเปิด\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
        		+ "{\"text\":\"\"}"
        		+ "]}"));
		
	}




	private static String chackitem(YamlConfiguration cfq) {
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Reward.ITEM." + x);
			if(item != null){
				return "[Item]";
			}
		}
		return "";
		
		
	}




	@SuppressWarnings("unchecked")
	public static void Quests(NBTTagList bp, String title) {
		invitQuests iq = new invitQuests();
		
		File fq = new File("plugins//Cushyquests//quests//" + title + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		String nameq = cfq.getString("Name");
		
		
		List<String> lb = (List<String>) cfq.getList("Block");
		List<String> lbp = (List<String>) cfq.getList("BlockP");
		List<String> lis = (List<String>) cfq.getList("itemsend");
		List<String> lkm = (List<String>) cfq.getList("Killmod");
		List<String> lcn = (List<String>) cfq.getList("CN");
		List<String> lga = (List<String>) cfq.getList("goarena");
		
		String lbc = iq.chacklb(lb);
		
		
		String lbpc = iq.chacklbp(lbp);
		
		String lisc = iq.chacklis(lis);
		
		String lkmc = iq.chacklkm(lkm);
		
		String lcnc = iq.chacklcn(lcn);
		
		String lgac = iq.chacklga(lga);
		
		
		bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
        		+ "{\"text\":\"\nName : \",\"color\":\"green\"},{\"text\":\""+nameq+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[กลับไปหน้าแรก]\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิก\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"1\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lbc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" bb\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lbpc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" bp\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lisc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" is\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lkmc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" km\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lcnc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" cn\"}},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lgac+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q menu "+title+" ga\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},{\"text\":\">>>Hover mouse in text\",\"color\":\"dark_red\"},"
        		+ "{\"text\":\"\n\"},"+"{\"text\":\"[รับเควส]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับเควส\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q add "+title+"\"}}," + "{\"text\":\"[ดูเควสของตนเอง]\",\"color\":\"blue\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือเปิด\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
        		+ "{\"text\":\"\"}"
        		+ "]}"));
		
		
	}




	public String chacklga(List<String> lga) {
		if(lga != null){
			if(lga.toArray().length != 0){
				return "[หาพื่นที่]";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}




	public String chacklcn(List<String> lcn) {
		if(lcn != null){
			if(lcn.toArray().length != 0){
				return "[คุยกับNpc]";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}




	public String chacklkm(List<String> lkm) {
		if(lkm != null){
			if(lkm.toArray().length != 0){
				return "[ฆ่ามอน]";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}




	public String chacklis(List<String> lis) {
		if(lis != null){
			if(lis.toArray().length != 0){
				return "[หาของ]";
			} else {
				return "";
			}
		} else {
			return "";
		}
	}




	public String chacklbp(List<String> lbp) {
		if(lbp != null){
			if(lbp.toArray().length != 0){
				return "[วางบล็อก]";
			} else {
				return "";
			}
		} else {
			return "";
		}
		
	}




	public String chacklb(List<String> lb) {
		if(lb != null){
			if(lb.toArray().length != 0){
				return "[ถุบบล็อก]";
			} else {
				return "";
			}
		} else {
			return "";
		}
		
		
	}




	public static String checktime(int s, int m, int h, int d, int M, int y) {
		if(s == 0 && m == 0 && h == 0 && d == 0 && M == 0 && y == 0){
			return "ไม่มีเวลา ทำเสร็จแล้วไม่สามารถทำได้อีก";
		} else {
			return "" + s + "s " + m + "m " + h + "h " + d + "d " + M + "M " + y + "y ";
		}
	}

	
	
	@SuppressWarnings("unchecked")
	public void invitquests(Player p,String name){
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		
		List<String> list = (List<String>) cfp.getList("Questslist");
		if(list != null){
			if(list.toArray().length < 3){
				list.add("" + name);
				cfp.set("Questslist", list);
			}
		} else {
			List<String> li = new ArrayList<String>();
			li.add(""+ name);
			cfp.set("Questslist", li);
		}
		
		
		List<String> lb = (List<String>) cfq.getList("Block");
		//blockbreak
		if(lb != null){
			if(lb.toArray().length != 0){
				for(int x = 0;x<lb.toArray().length;x++){
					Object[] ob = lb.toArray();
					String id = ob[x].toString();
					int data = cfq.getInt("Quests.Block."+id+".data");
					cfp.set("quests."+name+".bb."+id+"."+data, 0);
					
				}
				
				
			}
		}
		
		
		List<String> lbp = (List<String>) cfq.getList("BlockP");
		//blockplace
		if(lbp != null){
			if(lbp.toArray().length != 0){
				for(int x = 0;x<lbp.toArray().length;x++){
					Object[] ob = lbp.toArray();
					String id = ob[x].toString();
					int data = cfq.getInt("Quests.BlockP."+id+".data");
					cfp.set("quests."+name+".bp."+id+"."+data, 0);
					
				}
				
				
			}
		}
		List<String> lis = (List<String>) cfq.getList("itemsend");
		//itemsend
		if(lis != null){
			if(lis.toArray().length != 0){
				for(int x = 0;x<lis.toArray().length;x++){
					Object[] ob = lis.toArray();
					String id = ob[x].toString();
					ItemStack item = cfq.getItemStack("itemsends."+id+".item");
					int data = item.getDurability();
					cfp.set("quests."+name+".is."+id+"."+data+".amo", 0);
					
					
				}
				
				
			}
		}
		List<String> lkm = (List<String>) cfq.getList("Killmod");
		//killmob
		if(lkm != null){
			if(lkm.toArray().length != 0){
				for(int x = 0;x<lkm.toArray().length;x++){
					Object[] ob = lkm.toArray();
					String id = ob[x].toString();
					
					cfp.set("quests."+name+".km."+id+".amo", 0);
					
					
				}
				
				
			}
		}
		List<String> lcn = (List<String>) cfq.getList("CN");
		//TALKNPC
		if(lcn != null){
			if(lcn.toArray().length != 0){
				for(int x = 0;x<lcn.toArray().length;x++){
					Object[] ob = lcn.toArray();
					String id = ob[x].toString();
					
					cfp.set("quests."+name+".cn."+id, false);
					
					
				}
				
				
			}
		}
		List<String> lga = (List<String>) cfq.getList("goarena");
		//Goarea
		if(lga != null){
			if(lga.toArray().length != 0){
				for(int x = 0;x<lga.toArray().length;x++){
					Object[] ob = lga.toArray();
					String id = ob[x].toString();
					
					cfp.set("quests."+name+".ga."+id, false);
					
					
				}
				
				
			}
		}
		
		
		
		
		
		try {
			cfq.save(fq);
			cfp.save(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		spawnfirework(p);
		
		String nameq = cfq.getString("Name");
		
		p.sendMessage(ChatColor.GREEN + "[CSQ] : รับเควส "+ChatColor.RED +  nameq + ChatColor.GREEN + " เรียบร้อยแล้ว");
		
		
	}
	
	
	
	private void spawnfirework(Player p) {
		 //Spawn the Firework, get the FireworkMeta.
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        //Create our effect with this
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(Color.AQUA).withFade(Color.RED).with(Type.BALL_LARGE).trail(false).build();
       
        //Then apply the effect to the meta
        fwm.addEffect(effect);
       
        //Generate some random power and set it
        fwm.setPower(1);
       
        //Then apply this to our rocket
        fw.setFireworkMeta(fwm);     
		
	}




	public void assing(Player p,String name){
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		if(fq.exists()){
			ItemStack book = book("" + name, "CushyQuests");
			
			
			int slot = p.getInventory().getHeldItemSlot();
	        ItemStack old = p.getInventory().getItem(slot);
	        p.getInventory().setItem(slot, book);

	       ByteBuf buf = Unpooled.buffer(256);
	       buf.setByte(0, (byte)0);
	       buf.writerIndex(1);

	        PacketPlayOutCustomPayload packet = new PacketPlayOutCustomPayload("MC|BOpen", new PacketDataSerializer(buf));
	        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
	        p.getInventory().setItem(slot, old);
	        
		}
	}
	
	
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void menu(Player p,String name,String type2){
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		Inventory inv = Bukkit.createInventory(null, 54, "viewitemquests-"+name);
		int a = 1;
		if(a == 1){
			if(type2.equalsIgnoreCase("bb")){
				List<String> list = (List<String>) cfq.getList("Block");
				if(list != null){
					Object[] ob = list.toArray();
					for(int x = 0;x<ob.length;x++){
						String type = (String) ob[x];
						int data = cfq.getInt("Quests.Block." + type + ".data");
						int am = cfq.getInt("Quests.Block." + type + ".am");
						
						if(am > 64){
							ItemStack item = new ItemStack(Material.getMaterial(type),64,(short) data);
							ItemMeta meta = item.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("am : "+am);
							meta.setLore(lore);
							item.setItemMeta(meta);
							inv.setItem(x, item);
						} else {
							ItemStack item = new ItemStack(Material.getMaterial(type),am,(short) data);
							
							inv.setItem(x, item);
						}
					}
				}
				
				
			} else if(type2.equalsIgnoreCase("bp")){
				List<String> list = (List<String>) cfq.getList("BlockP");
				if(list != null){
					Object[] ob = list.toArray();
					for(int x = 0;x<ob.length;x++){
						String type = (String) ob[x];
						int data = cfq.getInt("Quests.BlockP." + type + ".data");
						int am = cfq.getInt("Quests.BlockP." + type + ".am");
						
						if(am > 64){
							ItemStack item = new ItemStack(Material.getMaterial(type),64,(short) data);
							ItemMeta meta = item.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("am : "+am);
							meta.setLore(lore);
							item.setItemMeta(meta);
							inv.setItem(x, item);
						} else {
							ItemStack item = new ItemStack(Material.getMaterial(type),am,(short) data);
							
							inv.setItem(x, item);
						}
					}
				}
			} else if(type2.equalsIgnoreCase("is")){
				List<String> list = (List<String>) cfq.getList("itemsend");
				
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
				
				
				
			} else if(type2.equalsIgnoreCase("km")){
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
								list.add(ChatColor.GREEN + "Name : "+ChatColor.RESET + Cuname.replaceAll("(&([a-z0-9]))", "\u00A7$2"));
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
				
				
			} else if(type2.equalsIgnoreCase("cn")){
				List<String> list = (List<String>) cfq.getList("CN");
				if(list != null){
					Object[] ob = list.toArray();
					for(int x = 0;x<ob.length;x++){
						String type = ((String) ob[x]).replaceAll("NPC", "");
						
						File citizen = new File("plugins//Citizens//saves.yml");
						YamlConfiguration ccc = YamlConfiguration.loadConfiguration(citizen);
						String namenpc = ccc.getString("npc." + type + ".name");
						
						ItemStack item = new ItemStack(Material.SKULL_ITEM,1,(short) 3);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GREEN + "" + namenpc);
						item.setItemMeta(meta);
						inv.setItem(x, item);
						
						
						
					}
				}
				
				
				
				
			} else if(type2.equalsIgnoreCase("ga")){
				
				List<String> list = (List<String>) cfq.getList("goarena");
				
				if(list != null){
					Object[] ob = list.toArray();
					for(int x = 0;x<list.toArray().length;x++){
						ItemStack item = new ItemStack(Material.GLASS);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GREEN + "" + ob[x]);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					}
				}
				
				
			} else if(type2.equalsIgnoreCase("rwi")){
				for(int x = 0;x<45;x++){
					ItemStack item = cfq.getItemStack("Reward.ITEM." + x);
					if(item != null){
						
						inv.setItem(x, item);
					}
					
				}
				
			} else if(type2.equalsIgnoreCase("eqi")){
				for(int x = 0;x<45;x++){
					ItemStack item = cfq.getItemStack("Eqment.ITEMSTACK." + x);
					if(item != null){
						
						inv.setItem(x, item);
					}
					
				}
				
			} else if(type2.equalsIgnoreCase("eqq")){
				List<String> list = (List<String>) cfq.getList("Eqment.QUESTS");
				
				if(list != null){
					Object[] ob = list.toArray();
					for(int x = 0;x<list.toArray().length;x++){
						ItemStack item = new ItemStack(Material.GLASS);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(ChatColor.GREEN + "" + ob[x]);
						item.setItemMeta(meta);
						inv.setItem(x, item);
					}
				}
				
			}
			
			
			
			
			
		}
		
		p.openInventory(inv);
		
		
	}
	
	
	@EventHandler
	public void Click(InventoryClickEvent e){
		if(e.getInventory().getName().contains("viewitemquests-")){
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler
	public void close(InventoryCloseEvent e){
		if(e.getInventory().getName().contains("viewitemquests-")){
			String name = e.getInventory().getName().replaceAll("viewitemquests-", "");
			Player p = (Player) e.getPlayer();
			
			this.assing(p, name);
			
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void removequests(Player p,int slot){
		int slo = slot - 1;
		
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		List<String> list = (List<String>) cfp.getList("Questslist");
		String id = list.get(slo);
		
		cfp.set("quests."+id, null);
		
		list.remove(id);
		
		cfp.set("Questslist", list);
		
		
		try {
			cfp.save(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public void givereward(Player p,int slot){
		setupEconomy();
		int slo = slot - 1;
		
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		List<String> list = (List<String>) cfp.getList("Questslist");
		String id = list.get(slo);
		
		
		File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		int price = cfq.getInt("Reward.Price");
		if(price != 0){
			economy.depositPlayer(p.getName(), price);
			p.sendMessage(ChatColor.GREEN + "[CSQ] : ได้รับเงินรางวัล "+ChatColor.RED + price);
		}
		
		int lvl = cfq.getInt("Reward.Level");
		if(lvl != 0){
			int level = p.getLevel();
			int newlevel = level + lvl;
			p.setLevel(newlevel);
			p.sendMessage(ChatColor.GREEN + "[CSQ] : ได้รับเลเวล "+ChatColor.RED + lvl);
		}
		
		int exp = cfq.getInt("Reward.EXP");
		if(exp != 0){
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "xp " + exp + " " + p.getName());
			p.sendMessage(ChatColor.GREEN + "[CSQ] : ได้รับอีเอกพี "+ChatColor.RED + exp);
		}
		
		
		List<String> lq = (List<String>) cfq.getList("Reward.QUESTS");
		if(lq != null){
			if(lq.toArray().length != 0){
				for(int x = 0;x<lq.toArray().length;x++){
					String idq = lq.get(x);
					
					this.invitquests(p, idq);
					
					
				}
			}
		}
		
		
		for(int x = 0;x<45;x++){
			ItemStack item = cfq.getItemStack("Reward.ITEM." + x);
			if(item != null){
				p.getWorld().dropItemNaturally(p.getLocation(), item);
				if(x == 0){
					p.sendMessage(ChatColor.GREEN + "[CSQ] : ได้รับ Item ");
				}
			}
			
		}
		
		List<String> lq2 = (List<String>) cfq.getList("Reward.COMMAND");
		if(lq2 != null){
			if(lq2.toArray().length != 0){
				for(int x = 0;x<lq2.toArray().length;x++){
					String idq = lq2.get(x);
					
					if(idq.contains("<c>")){
						String command = idq.replaceAll("%player%", p.getName()).replaceAll("<c>", "");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
						
					} else {
						String command = idq.replaceAll("%player%", p.getName());
						p.performCommand(command);
					}
					
					
				}
			}
		}
		
		
		int s = cfq.getInt("time.s");
		int m = cfq.getInt("time.m");
		int h = cfq.getInt("time.h");
		int d = cfq.getInt("time.d");
		int M = cfq.getInt("time.M");
		int y = cfq.getInt("time.y");
		if(s == 0 && m == 0 && h == 0 && d == 0 && M == 0 && y == 0){
			
		} else {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat ss2 = new SimpleDateFormat("ss");
			int ss = Integer.parseInt(ss2.format(cal.getTime()));
			
			SimpleDateFormat mm2 = new SimpleDateFormat("mm");
			int mm = Integer.parseInt(mm2.format(cal.getTime()));
			
			SimpleDateFormat hh2 = new SimpleDateFormat("HH");
			int hh = Integer.parseInt(hh2.format(cal.getTime()));
			
			SimpleDateFormat dd2 = new SimpleDateFormat("dd");
			int dd = Integer.parseInt(dd2.format(cal.getTime()));
			
			SimpleDateFormat MM2 = new SimpleDateFormat("MM");
			int MM = Integer.parseInt(MM2.format(cal.getTime()));
			
			SimpleDateFormat yy2 = new SimpleDateFormat("yyyy");
			int yy = Integer.parseInt(yy2.format(cal.getTime()));
			
			int ns = ss + s;
			int nm = mm + m;
			int nh = hh + h;
			int nd = dd + d;
			int nM = MM + M;
			int ny = yy + y;
			
			
			cfp.set("Quests.time."+id+".s", ns);
			cfp.set("Quests.time."+id+".m", nm);
			cfp.set("Quests.time."+id+".h", nh);
			cfp.set("Quests.time."+id+".d", nd);
			cfp.set("Quests.time."+id+".M", nM);
			cfp.set("Quests.time."+id+".y", ny);
		}
		
		List<String> li = (List<String>) cfp.getList("Quests.complete");
		if(li != null){
			if(li.contains("" + id)){
				
			} else {
				li.add("" + id);
				
				cfp.set("Quests.complete", li);
			}
			
		} else {
			ArrayList<String> lis = new ArrayList<String>();
			lis.add("" + id);
			
			cfp.set("Quests.complete", lis);
			
		}
		
		
		try {
			cfp.save(fp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String mess = cfq.getString("messfinish");
		
		p.sendMessage(mess);
		
		this.removequests(p, slot);
		
		this.spawnfirework(p);
		
	}
	
	
	
	
	private boolean setupEconomy(){
        RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return (economy != null);
    }
	
	
	
}
