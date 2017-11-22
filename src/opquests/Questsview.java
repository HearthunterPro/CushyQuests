package opquests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.NBTTagCompound;
import net.minecraft.server.v1_12_R1.NBTTagList;
import net.minecraft.server.v1_12_R1.NBTTagString;
import net.minecraft.server.v1_12_R1.PacketDataSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PacketPlayOutCustomPayload;

public class Questsview implements Listener{
	
	private static int check = 0;
	
	private static int checkq = 0;
	
	private static int checkallq = 0;
	
	private static String qcom2 = "";
	
	@SuppressWarnings("unchecked")
	public static ItemStack book(Player p,String title, String author) {
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_12_R1.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        
        List<String> list = (List<String>) cfp.getList("Questslist");
        if(list != null){
            if(list.toArray().length != 0){
            	List<String> litext = checkquests(list);
                
                String q1 = litext.get(0);
                String q2 = litext.get(1);
                String q3 = litext.get(2);
                
                String h1 = litext.get(3);
                String h2 = litext.get(4);
                String h3 = litext.get(5);
                
                String r1 = litext.get(6);
                String r2 = litext.get(7);
                String r3 = litext.get(8);
                
                String color = changecolor(list);
                
                List<String> litext2 = checkquests2(list);
                
                String c1 = litext2.get(0);
                String c2 = litext2.get(1);
                String c3 = litext2.get(2);
                
                List<String> litext3 = checkquests3(list, p);
                
                String s1 = litext3.get(0);
                String s2 = litext3.get(1);
                String s3 = litext3.get(2);
                
                String s4 = litext3.get(3);
                String s5 = litext3.get(4);
                String s6 = litext3.get(5);
                
                List<String> listq = (List<String>) cfp.getList("Quests.complete");
                
                String x1 = checkquestscomplete(listq);
                
                
                
                bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
                		+ "{\"text\":\"\nYou Name : \",\"color\":\"green\"},{\"text\":\""+p.getName()+"\",\"color\":\"red\"},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+x1+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q qcom\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\"<--- เควสทีรับ --->\",\"color\":\"dark_red\"},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+q1+"\",\"color\":\""+color+"\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+h1+"\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+c1+"\",\"color\":\"dark_aqua\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q view "+h1.replaceAll("ID : ", "")+"\"}}," + "{\"text\":\""+s1+"\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q reward 1\"}}," + "{\"text\":\""+s4+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"2\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+q2+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+h2+"\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+c2+"\",\"color\":\"dark_aqua\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q view "+h2.replaceAll("ID : ", "")+"\"}}," + "{\"text\":\""+s2+"\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q reward 2\"}}," + "{\"text\":\""+s5+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"3\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+q3+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\""+h3+"\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+c3+"\",\"color\":\"dark_aqua\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q view "+h3.replaceAll("ID : ", "")+"\"}}," + "{\"text\":\""+s3+"\",\"color\":\"gold\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q reward 3\"}}," + "{\"text\":\""+s6+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือรับรางวัล\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"4\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\"<--- เควสทีรับ --->\",\"color\":\"dark_red\"},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+r1+"\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q remove 1\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+r2+"\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q remove 2\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+r3+"\",\"color\":\"dark_red\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q remove 3\"}},"
                		+ "{\"text\":\"\"}"
                		+ "]}"));
                
                if(list.toArray().length == 1){
                	qu(bp,list.get(0), p);
                }
                if(list.toArray().length == 2){
                	qu(bp,list.get(0), p);
                	qu(bp,list.get(1), p);
                }
                if(list.toArray().length == 3){
                	qu(bp,list.get(0), p);
                	qu(bp,list.get(1), p);
                	qu(bp,list.get(2), p);
                }
                
                
                bd.set("pages", bp);
                
                
                
                nmsis.setTag(bd);
                is = CraftItemStack.asBukkitCopy(nmsis);
                return is;
            } else {

            	List<String> listq = (List<String>) cfp.getList("Quests.complete");
                
                String x1 = checkquestscomplete(listq);
            	
            	bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
                		+ "{\"text\":\"\nYou Name : \",\"color\":\"green\"},{\"text\":\""+p.getName()+"\",\"color\":\"red\"},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\""+x1+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q qcom\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\"<--- เควสทีรับ --->\",\"color\":\"dark_red\"},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\"ยังไม่มีเควสใดๆเลย\",\"color\":\"red\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\n\"},"
                		+ "{\"text\":\"\"}"
                		+ "]}"));
                
                
                
                bd.set("pages", bp);
                
                
                
                nmsis.setTag(bd);
                is = CraftItemStack.asBukkitCopy(nmsis);
                return is;
            }
        } else {
        	
        	
        	
        	ArrayList<String> li = new ArrayList<String>();
        	cfp.set("Questslist", li);
        	try {
				cfp.save(fp);
			} catch (IOException e) {
				e.printStackTrace();
			}
        	
        	List<String> listq = (List<String>) cfp.getList("Quests.complete");
            
            String x1 = checkquestscomplete(listq);
        	
        	bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
            		+ "{\"text\":\"\nYou Name : \",\"color\":\"green\"},{\"text\":\""+p.getName()+"\",\"color\":\"red\"},"
            		+ "{\"text\":\"\n\"}," + "{\"text\":\""+x1+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q qcom\"}},"
            		+ "{\"text\":\"\n\"}," + "{\"text\":\"<--- เควสทีรับ --->\",\"color\":\"dark_red\"},"
            		+ "{\"text\":\"\n\"}," + "{\"text\":\"ยังไม่มีเควสใดๆเลย\",\"color\":\"red\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\n\"},"
            		+ "{\"text\":\"\"}"
            		+ "]}"));
            
            
            
            bd.set("pages", bp);
            
            
            
            nmsis.setTag(bd);
            is = CraftItemStack.asBukkitCopy(nmsis);
            return is;
        	
        	
        }
        
    }
	
	
	private static String checkquestscomplete(List<String> listq) {
		if(listq != null){
			if(listq.toArray().length != 0){
				return "[เควสทีทำสำเร็จแล้ว]";
			}
		}
		
		return "";
		
		
	}


	@SuppressWarnings("unchecked")
	private static void qu(NBTTagList bp, String name,Player p) {
		
		invitQuests iq = new invitQuests();
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
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
		
		String a1 = com(p, name, lb, "bb");
		String a2 = com(p, name, lbp, "bp");
		String a3 = com(p, name, lis, "is");
		String a4 = com(p, name, lkm, "km");
		String a5 = com(p, name, lcn, "cn");
		String a6 = com(p, name, lga, "ga");
		
		bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
        		+ "{\"text\":\"\nName : \",\"color\":\"green\"},{\"text\":\""+nameq+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\"[กลับไปหน้าแรก]\",\"color\":\"dark_red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิก\"},\"clickEvent\":{\"action\":\"change_page\",\"value\":\"1\"}},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lbc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" bb\"}}," + "{\"text\":\""+a1+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lbpc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" bp\"}}," + "{\"text\":\""+a2+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lisc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" is\"}}," + "{\"text\":\""+a3+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lkmc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" km\"}}," + "{\"text\":\""+a4+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lcnc+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" cn\"}}," + "{\"text\":\""+a5+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"}," + "{\"text\":\""+lgac+"\",\"color\":\"dark_green\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q v "+name+" ga\"}}," + "{\"text\":\""+a6+"\",\"color\":\"red\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\n\"},"
        		+ "{\"text\":\"\"}"
        		+ "]}"));
		
		
		
		
		
	}


	private static List<String> checkquests3(List<String> list,Player p) {
		ArrayList<String> li = new ArrayList<String>();
		int a = 1;
		if(list.toArray().length == 0){
			li.add("");
			li.add("");
			li.add("");
		} else if(list.toArray().length == 1){
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			
			
			li.add("");
			li.add("");
			
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
			li.add("");
			li.add("");
			
		} else if(list.toArray().length == 2){
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			if(a == 1){
				String n1 = list.get(1);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			li.add("");
			
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
			if(a == 1){
				String n1 = list.get(1);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
			li.add("");
		} else if(list.toArray().length == 3){
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			if(a == 1){
				String n1 = list.get(1);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			if(a == 1){
				String n1 = list.get(2);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("[รับรางวัล]");
				} else {
					li.add("");
				}
			}
			
			
			if(a == 1){
				String n1 = list.get(0);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
			if(a == 1){
				String n1 = list.get(1);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
			if(a == 1){
				String n1 = list.get(2);
				boolean t = checkquestall(p,n1);
				if(t){
					li.add("");
				} else {
					li.add("[ดูความคืบหน้า]");
				}
			}
			
		}
		
		return li;
	}


	private static List<String> checkquests2(List<String> list) {
		ArrayList<String> li = new ArrayList<String>();
		if(list.toArray().length == 0){
			li.add("");
			li.add("");
			li.add("");
		} else if(list.toArray().length == 1){
			li.add("[ลายละเอียด]");
			li.add("");
			li.add("");
		} else if(list.toArray().length == 2){
			li.add("[ลายละเอียด]");
			li.add("[ลายละเอียด]");
			li.add("");
		} else if(list.toArray().length == 3){
			li.add("[ลายละเอียด]");
			li.add("[ลายละเอียด]");
			li.add("[ลายละเอียด]");
		}
		
		return li;
	}


	private static String changecolor(List<String> list) {
		if(list.toArray().length == 0){
			return "red";
		} else {
			return "dark_green";
		}
	}


	private static List<String> checkquests(List<String> list) {
		ArrayList<String> li = new ArrayList<String>();
		int a = 1;
		if(list.toArray().length == 0){
			li.add("ยังไม่มีเควสใดๆเลย");
			li.add("");
			li.add("");
			
			li.add("");
			li.add("");
			li.add("");
			
			li.add("");
			li.add("");
			li.add("");
		} else if(list.toArray().length == 1){
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(0) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			
			li.add("");
			li.add("");
			
			li.add("ID : " + list.get(0));
			li.add("");
			li.add("");
			
			li.add("[ยกเลิกเควส  แรก]");
			li.add("");
			li.add("");
		} else if(list.toArray().length == 2){
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(0) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(1) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			li.add("");
			
			li.add("ID : " + list.get(0));
			li.add("ID : " + list.get(1));
			li.add("");
			
			li.add("[ยกเลิกเควส  แรก]");
			li.add("[ยกเลิกเควส  สอง]");
			li.add("");
		} else if(list.toArray().length == 3){
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(0) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(1) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			if(a == 1){
				File fq = new File("plugins//Cushyquests//quests//" + list.get(2) + ".yml");
				YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
				
				String name = cfq.getString("Name");
				
				li.add("-> " + name);
			}
			
			li.add("ID : " + list.get(0));
			li.add("ID : " + list.get(1));
			li.add("ID : " + list.get(2));
			
			li.add("[ยกเลิกเควส  แรก]");
			li.add("[ยกเลิกเควส  สอง]");
			li.add("[ยกเลิกเควส  สาม]");
			
		}
		
		return li;
	}


	public void openq(Player p){
		
		ItemStack book = book(p, "" + p.getName(), "CushyQuests");
		
		
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
	
	
	@SuppressWarnings("unchecked")
	public static boolean checkquestall(Player p,String name){
		checkq = 0;
		checkallq = 0;
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		
		List<String> lbb = (List<String>) cfq.getList("Block");
		if(lbb != null){
			if(lbb.toArray().length != 0){
				for(int x = 0;x<lbb.toArray().length;x++){
					Object[] ob = lbb.toArray();
					String id = ob[x].toString();
					int data = cfq.getInt("Quests.Block."+id+".data");
					
					boolean t = cfp.getBoolean("quests."+name+".bb."+id+"."+data);
					if(t){
						checkq = checkq + 1;
					}
					
					
				}
				
				checkallq = checkallq + lbb.toArray().length;
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
					
					boolean t = cfp.getBoolean("quests."+name+".bp."+id+"."+data);
					if(t){
						checkq = checkq + 1;
					}
					
				}
				
				checkallq = checkallq + lbp.toArray().length;
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
					
					boolean t = cfp.getBoolean("quests."+name+".is."+id+"."+data+".amo");
					if(t){
						checkq = checkq + 1;
					}
					
				}
				
				checkallq = checkallq + lis.toArray().length;
				
			}
		}
		List<String> lkm = (List<String>) cfq.getList("Killmod");
		//killmob
		if(lkm != null){
			if(lkm.toArray().length != 0){
				for(int x = 0;x<lkm.toArray().length;x++){
					Object[] ob = lkm.toArray();
					String id = ob[x].toString();
					
					boolean t = cfp.getBoolean("quests."+name+".km."+id+".amo");
					if(t){
						checkq = checkq + 1;
					}
					
				}
				
				checkallq = checkallq + lkm.toArray().length;
			}
		}
		List<String> lcn = (List<String>) cfq.getList("CN");
		//TALKNPC
		if(lcn != null){
			if(lcn.toArray().length != 0){
				for(int x = 0;x<lcn.toArray().length;x++){
					Object[] ob = lcn.toArray();
					String id = ob[x].toString();
					
					
					boolean t = cfp.getBoolean("quests."+name+".cn."+id);
					if(t){
						checkq = checkq + 1;
					}
					
				}
				
				checkallq = checkallq + lcn.toArray().length;
			}
		}
		List<String> lga = (List<String>) cfq.getList("goarena");
		//Goarea
		if(lga != null){
			if(lga.toArray().length != 0){
				for(int x = 0;x<lga.toArray().length;x++){
					Object[] ob = lga.toArray();
					String id = ob[x].toString();
					
					
					boolean t = cfp.getBoolean("quests."+name+".ga."+id);
					if(t){
						checkq = checkq + 1;
					}
				}
				
				
			}
			
			checkallq = checkallq + lga.toArray().length;
		}
		
		int all = checkallq;
		
		if(checkq == all){
			return true;
		} else {
			return false;
		}
		
		
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void v(Player p,String name,String type2){
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		int a = 1;
		if(a == 1){
			if(type2.equalsIgnoreCase("bb")){
				List<String> list = (List<String>) cfq.getList("Block");
				if(list != null){
					Object[] ob = list.toArray();
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<ob.length;x++){
						String type = (String) ob[x];
						int data = cfq.getInt("Quests.Block." + type + ".data");
						int am = cfq.getInt("Quests.Block." + type + ".am");
						
						boolean t = cfp.getBoolean("quests."+name+".bb."+type+"."+data);
						if(t == false){
							int amo = cfp.getInt("quests."+name+".bb."+type+"."+data);
							
							p.sendMessage(ChatColor.RED + "" + type + ":" + data + ChatColor.GREEN + " /> " + amo + "/" + am);
						} else {
							p.sendMessage(ChatColor.RED + "" + type + ":" + data + ChatColor.GREEN + " /> เควสสำเร็จ");
						}
						
						
					}
					Back(p);
				}
				
				
			} else if(type2.equalsIgnoreCase("bp")){
				List<String> list = (List<String>) cfq.getList("BlockP");
				if(list != null){
					Object[] ob = list.toArray();
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<ob.length;x++){
						String type = (String) ob[x];
						int data = cfq.getInt("Quests.BlockP." + type + ".data");
						int am = cfq.getInt("Quests.BlockP." + type + ".am");
						
						boolean t = cfp.getBoolean("quests."+name+".bp."+type+"."+data);
						if(t == false){
							int amo = cfp.getInt("quests."+name+".bp."+type+"."+data);
							
							p.sendMessage(ChatColor.RED + "" + type + ":" + data + ChatColor.GREEN + " /> " + amo + "/" + am);
						} else {
							p.sendMessage(ChatColor.RED + "" + type + ":" + data + ChatColor.GREEN + " /> เควสสำเร็จ");
						}
						
						
					}
					Back(p);
				}
				
				
			} else if(type2.equalsIgnoreCase("is")){
				List<String> list = (List<String>) cfq.getList("itemsend");
				
				if(list != null){
					Object[] ob = list.toArray();
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<list.toArray().length;x++){
						ItemStack item = cfq.getItemStack("itemsends." + ob[x] + ".item");
						
						
						int amo = cfq.getInt("itemsends." + ob[x] + ".amo"); 
						
						
						Boolean t = cfp.getBoolean("quests."+name+".is."+item.getType()+"."+item.getDurability()+".amo");
						if(t == false){
							int am = cfp.getInt("quests."+name+".is."+item.getType()+"."+item.getDurability()+".amo");
							
							p.sendMessage(ChatColor.RED + "" + item.getType() + ":" + item.getDurability() + ChatColor.GREEN + " /> " + am + "/" + amo);
						} else {
							p.sendMessage(ChatColor.RED + "" + item.getType() + ":" + item.getDurability() + ChatColor.GREEN + " /> เควสสำเร็จ");
						}
						
						
						
					}
					Back(p);
				}
				
				
				
			} else if(type2.equalsIgnoreCase("km")){
				List<String> lien = (List<String>) cfq.getList("Killmod");
				if(lien != null){
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<lien.toArray().length;x++){
						if(a == 1){
							Object[] ob = lien.toArray();
							String idname = ob[x].toString();
							
							String type = cfq.getString("Killmods." + idname + ".type");
							int amo = cfq.getInt("Killmods." + idname + ".AMO");
							String Cuname = cfq.getString("Killmods." + idname + ".NAME");
							if(Cuname != null){
								boolean t = cfp.getBoolean("quests."+name+".km."+Cuname+".amo");
								if(t == false){
									int am = cfp.getInt("quests."+name+".km."+Cuname+".amo");
									
									p.sendMessage(ChatColor.RED + "" + Cuname + ChatColor.GREEN + " /> " + am + "/" + amo);
								} else {
									p.sendMessage(ChatColor.RED + "" + Cuname + ChatColor.GREEN + " /> เควสสำเร็จ");
								}
								
								
							} else {
								boolean t = cfp.getBoolean("quests."+name+".km."+type+".amo");
								if(t == false){
									int am = cfp.getInt("quests."+name+".km."+type+".amo");
									
									p.sendMessage(ChatColor.RED + "" + type + ChatColor.GREEN + " /> " + am + "/" + amo);
								} else {
									p.sendMessage(ChatColor.RED + "" + type + ChatColor.GREEN + " /> เควสสำเร็จ");
								}
								
								
							}
							
							
							
						}
					}
					
					Back(p);
					
				}
				
				
			} else if(type2.equalsIgnoreCase("cn")){
				List<String> list = (List<String>) cfq.getList("CN");
				if(list != null){
					Object[] ob = list.toArray();
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<ob.length;x++){
						String type = ((String) ob[x]);
						
						boolean am = cfp.getBoolean("quests."+name+".cn."+type);
						
						if(am){
							p.sendMessage(ChatColor.RED + "" + type + ChatColor.GREEN + " /> คุยแล้วเรียบร้อย");
						} else {
							p.sendMessage(ChatColor.RED + "" + type + ChatColor.GREEN + " /> ยังไม่ได้คุยกับ NPC ตัวนี้");
						}
						
						
						
					}
					Back(p);
				}
				
				
				
				
			} else if(type2.equalsIgnoreCase("ga")){
				
				List<String> list = (List<String>) cfq.getList("goarena");
				
				if(list != null){
					Object[] ob = list.toArray();
					p.sendMessage(ChatColor.YELLOW + "=========================");
					for(int x = 0;x<list.toArray().length;x++){
						String id = ob[x].toString();
						boolean am = cfp.getBoolean("quests."+name+".ga."+id);
						if(am){
							p.sendMessage(ChatColor.RED + "" + id + ChatColor.GREEN + " /> พบเจอพื่นทีนี้แล้ว");
						} else {
							p.sendMessage(ChatColor.RED + "" + id + ChatColor.GREEN + " /> ยังไม่เจอพื่นทีนี้เลย");
						}
						
					}
					Back(p);
				}
				
				
			}
			
			
		}
		
		
		
		
		
	}


	private void Back(Player p) {
		IChatBaseComponent component = ChatSerializer.a("{\"text\":\"=======>\",\"color\":\"yellow\",\"extra\":[{\"text\":\"\"}"
				+ ",{\"text\":\"[Click Back]\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}}"
				+ ",{\"text\":\"<=======\"}"
				+ "]}");
        PacketPlayOutChat packetPlayOutChat = new PacketPlayOutChat(component);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packetPlayOutChat);
		
	}
	
	
	
	public static String com(Player p,String name,List<String> li,String type2){
		check = 0;
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
		File fq = new File("plugins//Cushyquests//quests//" + name + ".yml");
		YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
		
		if(type2.equalsIgnoreCase("bb")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						int data = cfq.getInt("Quests.Block."+id+".data");
						
						boolean t = cfp.getBoolean("quests."+name+".bb."+id+"."+data);
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		} else if(type2.equalsIgnoreCase("bp")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						int data = cfq.getInt("Quests.BlockP."+id+".data");
						
						boolean t = cfp.getBoolean("quests."+name+".bp."+id+"."+data);
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		} else if(type2.equalsIgnoreCase("is")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						ItemStack item = cfq.getItemStack("itemsends."+id+".item");
						int data = item.getDurability();
						
						boolean t = cfp.getBoolean("quests."+name+".is."+id+"."+data+".amo");
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		} else if(type2.equalsIgnoreCase("km")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						
						boolean t = cfp.getBoolean("quests."+name+".km."+id+".amo");
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		} else if(type2.equalsIgnoreCase("cn")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						
						
						boolean t = cfp.getBoolean("quests."+name+".cn."+id);
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		} else if(type2.equalsIgnoreCase("ga")){
			if(li != null){
				if(li.toArray().length != 0){
					for(int x = 0;x<li.toArray().length;x++){
						Object[] ob = li.toArray();
						String id = ob[x].toString();
						
						
						boolean t = cfp.getBoolean("quests."+name+".ga."+id);
						if(t){
							check = check + 1;
						}
						
						
					}
					
					
					if(check == li.toArray().length){
						return "[สำเร็จ]";
					}
					
					
				}
			}
		}
		
		
		return "";
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public static ItemStack book2(Player p,String title, String author) {
		//TODO book2
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
		
        ItemStack is = new ItemStack(Material.WRITTEN_BOOK, 1);
        net.minecraft.server.v1_12_R1.ItemStack nmsis = CraftItemStack.asNMSCopy(is);
        NBTTagCompound bd = new NBTTagCompound();
        bd.setString("title", title);
        bd.setString("author", author);
        NBTTagList bp = new NBTTagList();
        
        List<String> list = (List<String>) cfp.getList("Quests.complete");
        if(list != null){
            if(list.toArray().length != 0){
                
            	
            	int dd = list.toArray().length/11;
            	
            	String qcom = checkqcom(list);
            	
            	
            	
                bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
                		+ "{\"text\":\"\nYou Name : \",\"color\":\"green\"},{\"text\":\""+p.getName()+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
                		+ "{\"text\":\"\n\"}," + "{\"text\":\"vvvvvvvvvvvvvvvvvvv\"},"
                		+ qcom
                		+ "{\"text\":\"\"}"
                		+ "]}"));
                
                
                
                for(int x = 1;x<=dd;x++){
                	String de = chqcom(list,x);
                	bp.add(new NBTTagString("{\"text\":\"=====> Quests <=====\",\"color\":\"gold\",\"extra\":["
                			 + "{\"text\":\"\nYou Name : \",\"color\":\"green\"},{\"text\":\""+p.getName()+"\",\"color\":\"red\",\"hoverEvent\":{\"action\":\"show_text\",\"value\":\"คลิกเพือดู\"},\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/q\"}},"
                     		+ "{\"text\":\"\n\"}," + "{\"text\":\"vvvvvvvvvvvvvvvvvvv\"},"
                     		+ de
                     		+ "{\"text\":\"\"}"
                     		+ "]}"));
                	
                }
                
                
                
                
                bd.set("pages", bp);
                
                
                
                nmsis.setTag(bd);
                is = CraftItemStack.asBukkitCopy(nmsis);
                
            }
        }
        
        
        
		return is;
	}
	
	
	
	private static String chqcom(List<String> list, int dd) {
		qcom2 = "";
		if(list != null){
			int xd = dd * 11;
			
			int ds = list.toArray().length - xd;
			
			if(ds > 11){
				for(int x = 1;x<11;x++){
					int s = x + xd;
					
					String nm = list.get(s);
					
					String a = "{\"text\":\"\n\"}," + "{\"text\":\"Quests: "+nm+"\",\"color\":\"red\"}," + "{\"text\":\" สำเร็จแล้ว\",\"color\":\"green\"},";
					
					qcom2 = qcom2 + a;
				}
				return qcom2;
				
			} else {
				for(int x = 1;x<ds;x++){
					int s = x + xd;
					
					String nm = list.get(s);
					
					String a = "{\"text\":\"\n\"}," + "{\"text\":\"Quests: "+nm+"\",\"color\":\"red\"}," + "{\"text\":\" สำเร็จแล้ว\",\"color\":\"green\"},";
					
					qcom2 = qcom2 + a;
				}
				return qcom2;
			}
		}
		
		return "";
	}


	private static String checkqcom(List<String> list) {
		
		qcom2 = "";
		if(list.toArray().length > 11){
			for(int x = 0;x<11;x++){
				String nm = list.get(x);
				
				String a = "{\"text\":\"\n\"}," + "{\"text\":\"Quests: "+nm+"\",\"color\":\"red\"}," + "{\"text\":\" สำเร็จแล้ว\",\"color\":\"green\"},";
				
				qcom2 = qcom2 + a;
			}
			return qcom2;
		} else {
			for(int x = 0;x<list.toArray().length;x++){
				String nm = list.get(x);
				
				String a = "{\"text\":\"\n\"}," + "{\"text\":\"Quests: "+nm+"\",\"color\":\"red\"}," + "{\"text\":\" สำเร็จแล้ว\",\"color\":\"green\"},";
				
				qcom2 = qcom2 + a;
			}
			return qcom2;
		}
	}


	public void openqquestscom(Player p){
		
		ItemStack book = book2(p, "" + p.getName(), "CushyQuests");
		
		
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
