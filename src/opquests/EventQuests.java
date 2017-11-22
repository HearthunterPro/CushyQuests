package opquests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import net.citizensnpcs.api.event.NPCRightClickEvent;

@SuppressWarnings("deprecation")
public class EventQuests implements Listener{
	
	private static int checkq = 0;
	
	private static int checkallq = 0;
	

	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void bb(BlockBreakEvent e){
		Player p = e.getPlayer();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					String type = e.getBlock().getType().toString();
					int data = e.getBlock().getData();
					boolean chcom = cfp.getBoolean("quests."+id+".bb."+type+"."+data);
					if(chcom == false){
						
						String tes = cfp.getString("quests."+id+".bb."+type+"."+data);
						if(tes != null){
							int amo = cfp.getInt("quests."+id+".bb."+type+"."+data);
							
							int nam = amo + 1;
							
							int amo2 = cfq.getInt("Quests.Block."+type+".am");
							if(nam == amo2){
								
								cfp.set("quests."+id+".bb."+type+"."+data, true);
								
								try {
									cfp.save(fp);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								questscom(p);
								checkquestall(p,id);
								
							} else {
								cfp.set("quests."+id+".bb."+type+"."+data, nam);
								
								
								try {
									cfp.save(fp);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							
						}
					}
					
					
				}
				
			}
			
			
			
		}
		
	}
	
	
	
	
	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void bp(BlockPlaceEvent e){
		Player p = e.getPlayer();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					String type = e.getBlock().getType().toString();
					int data = e.getBlock().getData();
					boolean chcom = cfp.getBoolean("quests."+id+".bp."+type+"."+data);
					if(chcom == false){
						
						String tes = cfp.getString("quests."+id+".bp."+type+"."+data);
						if(tes != null){
							int amo = cfp.getInt("quests."+id+".bp."+type+"."+data);
							
							int nam = amo + 1;
							
							int amo2 = cfq.getInt("Quests.BlockP."+type+".am");
							if(nam == amo2){
								
								cfp.set("quests."+id+".bp."+type+"."+data, true);
								
								try {
									cfp.save(fp);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
								questscom(p);
								checkquestall(p,id);
							} else {
								cfp.set("quests."+id+".bp."+type+"."+data, nam);
								try {
									cfp.save(fp);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
							
						}
					}
					
					
				}
				
			}

			
			
		}
	}
		
		
		
	
	
	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void is(NPCRightClickEvent e){
		Player p = e.getClicker();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					ItemStack item = p.getItemInHand();
					
					
					File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					ItemStack itemc = cfq.getItemStack("itemsends."+item.getType()+".item");
					
					boolean send = cfq.getBoolean("itemsends."+item.getType()+".send");
					boolean npc = cfq.getBoolean("itemsends."+item.getType()+".npc");
					
					boolean t = cfp.getBoolean("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
					
					if(t == false){
						String chack = cfp.getString("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
						if(chack != null){
							if(npc){
								int idnpc = Integer.parseInt(cfq.getString("itemsends."+item.getType()+".idnpc"));
								if(e.getNPC().getId() == idnpc){

									
									
									int amo = cfq.getInt("itemsends."+item.getType()+".amo");
									int amo2 = cfp.getInt("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
									int nam = amo - amo2;
									
									int am = item.getAmount();
									
									itemc.setAmount(am);
									
									if(itemc.equals(item)){
										if(am > nam){
											
											cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", true);
											
											try {
												cfp.save(fp);
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											
											questscom(p);
											checkquestall(p,id);
											
											if(send){
												item.setAmount(am - nam);
											}
										} else if(am < nam){
											int am2 = amo2 + am;
											
											cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", am2);
											
											if(send){
												item.setAmount(0);
											}
											
											try {
												cfp.save(fp);
											} catch (IOException e1) {
												e1.printStackTrace();
											}
										} else if(am == nam){
											
											cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", true);
											
											try {
												cfp.save(fp);
											} catch (IOException e1) {
												e1.printStackTrace();
											}
											
											questscom(p);
											checkquestall(p,id);
											
											if(send){
												item.setAmount(0);
											}
											
										}
									}
								}
								
								
								
								
							}
							

							
						}
						
						
					}
					
				}
				
			}
			
			
			
			
		}
	}




	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void is2(PlayerPickupItemEvent e){
		Player p = e.getPlayer();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					ItemStack item = e.getItem().getItemStack();
					
					File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					
					ItemStack itemc = cfq.getItemStack("itemsends."+item.getType()+".item");
					
					boolean send = cfq.getBoolean("itemsends."+item.getType()+".send");
					boolean npc = cfq.getBoolean("itemsends."+item.getType()+".npc");
					
					boolean t = cfp.getBoolean("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
					
					if(t == false){
						String chack = cfp.getString("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
						if(chack != null){
							if(npc == false){
								
								
								int amo = cfq.getInt("itemsends."+item.getType()+".amo");
								int amo2 = cfp.getInt("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo");
								int nam = amo - amo2;
								
								int am = item.getAmount();
								
								
								itemc.setAmount(am);
								
								if(itemc.equals(item)){
									
									if(am > nam){
										cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", true);
										
										if(send){
											e.setCancelled(true);
											int nea = am - nam;
											item.setAmount(nea);
											e.getItem().remove();
											p.getInventory().addItem(item);
											p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 1);
											
										}
										
										try {
											cfp.save(fp);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										
										questscom(p);
										checkquestall(p,id);
										
									}
									if(am < nam){
										int am2 = amo2 + am;
										
										cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", am2);
										
										if(send){
											e.setCancelled(true);
											e.getItem().remove();
											p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 1);
										}
										
										try {
											cfp.save(fp);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										
									}
									if(am == nam){
										
										cfp.set("quests."+id+".is."+item.getType()+"."+item.getDurability()+".amo", true);
										
										if(send){
											e.setCancelled(true);
											
											e.getItem().remove();
											p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 10, 1);
										}
										
										try {
											cfp.save(fp);
										} catch (IOException e1) {
											e1.printStackTrace();
										}
										
										questscom(p);
										checkquestall(p,id);
										
										
									}
								}
								
								
								
								
							}
							

							
						}
						
						
					}
					
				}
				
			}
			
			
			
			
		}
	}
	
	
	
	@SuppressWarnings({ "unchecked" })
	@EventHandler
	public void km(EntityDeathEvent e){
		if(e.getEntity().getKiller() instanceof Player){
			if(e.getEntity() instanceof LivingEntity){
				Player p = e.getEntity().getKiller();
				File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
				if(fp.exists()){
					YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
					List<String> list = (List<String>) cfp.getList("Questslist");
					if(list != null){
						for(int x = 0;x<list.toArray().length;x++){
							Object[] ob = list.toArray();
							String id = ob[x].toString();
							
							Entity en = e.getEntity();
							
							
							File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
							YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
							
							
							if(en.getCustomName() != null){
								String nameentity = en.getCustomName();
								String type = cfq.getString("Killmods."+nameentity+".type");
								if(type.equals(e.getEntityType().toString())){
									
									String name = cfq.getString("Killmods."+nameentity+".NAME");
									String loc = cfq.getString("Killmods."+nameentity+".LOC");
									boolean ts = cfp.getBoolean("quests."+id+".km."+nameentity+".amo");
									if(ts == false){
										if(nameentity.equals(name)){
											if(loc != null){
												File fl = new File("plugins//Cushyquests//location//" + loc + ".yml");
												YamlConfiguration cfl = YamlConfiguration.loadConfiguration(fl);
												if(fl.exists()){

													Location loc2 = (Location) cfl.get("Location");
													Location locen = en.getLocation();
													
													int mx = cfl.getInt("wg");
													int mz = cfl.getInt("lg");
													int my = cfl.getInt("hg");
													
													int lx = loc2.getBlockX();
													int y = loc2.getBlockY();
													int z = loc2.getBlockZ();
													
													if(locen.getWorld().getName().equals(loc2.getWorld().getName())){
														
														for(int zs = 0;zs<mz;zs++){
															int g3 = z+zs;
															for(int xs = 0;xs<mx;xs++){
																int g = lx+xs;
																for(int ys = 0;ys<my;ys++){
																	int g2 = y+ys;
																	
																	if(locen.getBlockX()==g){
																		if(locen.getBlockY()== g2){
																			if(locen.getBlockZ()== g3){
																				int amo = cfp.getInt("quests."+id+".km."+nameentity+".amo");
																				int amo2 = cfq.getInt("Killmods."+nameentity+".AMO");
																				int am = amo + 1;
																				if(amo2 == am){
																					
																					cfp.set("quests."+id+".km."+nameentity+".amo", true);
																					
																					try {
																						cfp.save(fp);
																					} catch (IOException e1) {
																						e1.printStackTrace();
																					}
																					
																					questscom(p);
																					checkquestall(p,id);
																					
																				} else {
																					
																					cfp.set("quests."+id+".km."+nameentity+".amo", am);
																					
																					try {
																						cfp.save(fp);
																					} catch (IOException e1) {
																						e1.printStackTrace();
																					}
																				}
																				
																				break;
																			}
																		}
																		
																	}
																	
																}
																
															}
															
														}
														
														
														
														
													}
												}
												
												
											} else {
												int amo = cfp.getInt("quests."+id+".km."+nameentity+".amo");
												int amo2 = cfq.getInt("Killmods."+nameentity+".AMO");
												int am = amo + 1;
												if(amo2 == am){
													
													cfp.set("quests."+id+".km."+nameentity+".amo", true);
													
													try {
														cfp.save(fp);
													} catch (IOException e1) {
														e1.printStackTrace();
													}
													
													questscom(p);
													checkquestall(p,id);
													
												} else {
													
													cfp.set("quests."+id+".km."+nameentity+".amo", am);
													
													try {
														cfp.save(fp);
													} catch (IOException e1) {
														e1.printStackTrace();
													}
												}
												
												
											}
											
											
											
										}
									}
								}
								
								
							} else {
								boolean ts = cfp.getBoolean("quests."+id+".km."+en.getType()+".amo");
								if(ts == false){
									String check = cfp.getString("quests."+id+".km."+en.getType()+".amo");
									if(check != null){
										
										String loc = cfq.getString("Killmods."+en.getType()+".LOC");
										if(loc != null){
											File fl = new File("plugins//Cushyquests//location//" + loc + ".yml");
											YamlConfiguration cfl = YamlConfiguration.loadConfiguration(fl);
											
											if(fl.exists()){
												Location loc2 = (Location) cfl.get("Location");
												
												Location locen = en.getLocation();
												
												int mx = cfl.getInt("wg");
												int mz = cfl.getInt("lg");
												int my = cfl.getInt("hg");
												
												int lx = loc2.getBlockX();
												int y = loc2.getBlockY();
												int z = loc2.getBlockZ();
												
												if(locen.getWorld().getName().equals(loc2.getWorld().getName())){
													
													for(int zs = 0;zs<mz;zs++){
														int g3 = z+zs;
														for(int xs = 0;xs<mx;xs++){
															int g = lx+xs;
															for(int ys = 0;ys<my;ys++){
																int g2 = y+ys;
																
																if(locen.getBlockX()==g){
																	if(locen.getBlockY()== g2){
																		if(locen.getBlockZ()== g3){
																			int amo = cfp.getInt("quests."+id+".km."+en.getType()+".amo");
																			int amo2 = cfq.getInt("Killmods."+en.getType()+".AMO");
																			int am = amo + 1;
																			if(amo2 == am){
																				
																				cfp.set("quests."+id+".km."+en.getType()+".amo", true);
																				
																				try {
																					cfp.save(fp);
																				} catch (IOException e1) {
																					e1.printStackTrace();
																				}
																				
																				questscom(p);
																				checkquestall(p,id);
																				
																			} else {
																				
																				cfp.set("quests."+id+".km."+en.getType()+".amo", am);
																				
																				try {
																					cfp.save(fp);
																				} catch (IOException e1) {
																					e1.printStackTrace();
																				}
																			}
																			
																			break;
																		}
																	}
																}
																
															}
															
														}
														
													}
													
													
													
													
												}
											}
											
											
										} else {
											int amo = cfp.getInt("quests."+id+".km."+en.getType()+".amo");
											int amo2 = cfq.getInt("Killmods."+en.getType()+".AMO");
											int am = amo + 1;
											if(amo2 == am){
												
												cfp.set("quests."+id+".km."+en.getType()+".amo", true);
												
												try {
													cfp.save(fp);
												} catch (IOException e1) {
													e1.printStackTrace();
												}
												
												questscom(p);
												checkquestall(p,id);
												
											} else {
												
												cfp.set("quests."+id+".km."+en.getType()+".amo", am);
												
												try {
													cfp.save(fp);
												} catch (IOException e1) {
													e1.printStackTrace();
												}
											}
											
											
										}
									}
									
								}
							}
							
							
							
							
						}
						
					}
					
					
					
					
				}
			}
		}
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void cn(NPCRightClickEvent e){
		Player p = e.getClicker();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					String idnpc = "NPC" + e.getNPC().getId();
					
					boolean t = cfp.getBoolean("quests."+id+".cn."+idnpc);
					
					if(t == false){
						String chack = cfp.getString("quests."+id+".cn."+idnpc);
						if(chack != null){
							
							cfp.set("quests."+id+".cn."+idnpc, true);
							
							try {
								cfp.save(fp);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							
							questscom(p);
							checkquestall(p,id);
							
						}
						
					}
					
					
				}
			}
			
			
		}
					
		
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	@EventHandler
	public void ga(PlayerMoveEvent e){
		Player p = e.getPlayer();
		File fp = new File("plugins//Cushyquests//Player//" + p.getName() + ".yml");
		if(fp.exists()){
			YamlConfiguration cfp = YamlConfiguration.loadConfiguration(fp);
			List<String> list = (List<String>) cfp.getList("Questslist");
			if(list != null){
				for(int x = 0;x<list.toArray().length;x++){
					Object[] ob = list.toArray();
					String id = ob[x].toString();
					
					File fq = new File("plugins//Cushyquests//quests//" + id + ".yml");
					YamlConfiguration cfq = YamlConfiguration.loadConfiguration(fq);
					List<String> li = (List<String>) cfq.getList("goarena");
					if(li != null){
						for(int x2 = 0;x2<li.toArray().length;x2++){
							Object[] ob2 = li.toArray();
							String area = ob2[x2].toString();
							boolean t = cfp.getBoolean("quests."+id+".ga."+area);
							if(t == false){
								String chack = cfp.getString("quests."+id+".ga."+area);
								if(chack != null){
									File fl = new File("plugins//Cushyquests//location//" + area + ".yml");
									YamlConfiguration cfl = YamlConfiguration.loadConfiguration(fl);
									if(fl.exists()){

										Location loc2 = (Location) cfl.get("Location");
										Location locen = p.getLocation();
										
										int mx = cfl.getInt("wg");
										int mz = cfl.getInt("lg");
										int my = cfl.getInt("hg");
										
										int lx = loc2.getBlockX();
										int y = loc2.getBlockY();
										int z = loc2.getBlockZ();
										
										if(locen.getWorld().getName().equals(loc2.getWorld().getName())){
											
											for(int zs = 0;zs<mz;zs++){
												int g3 = z+zs;
												for(int xs = 0;xs<mx;xs++){
													int g = lx+xs;
													for(int ys = 0;ys<my;ys++){
														int g2 = y+ys;
														
														if(locen.getBlockX()==g){
															if(locen.getBlockY()== g2){
																if(locen.getBlockZ()== g3){
																	
																	cfp.set("quests."+id+".ga."+area, true);
																	
																	try {
																		cfp.save(fp);
																	} catch (IOException e1) {
																		e1.printStackTrace();
																	}
																	
																	questscom(p);
																	
																	checkquestall(p,id);
																	
																	break;
																}
															}
															
														}
														
													}
													
												}
												
											}
											
											
											
											
										}
									}
									
									
								}
								
							}
							
							
							
						}
					}
					
					
					
					
					
				}
			}
			
			
		}
					
		
		
		
	}
	
	
	
	public void questscom(Player p){
        Firework fw = (Firework) p.getWorld().spawnEntity(p.getLocation(), EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
       
        FireworkEffect effect = FireworkEffect.builder().flicker(false).withColor(Color.GREEN).withFade(Color.RED).with(Type.BALL_LARGE).trail(true).build();
       
        fwm.addEffect(effect);
       
        fwm.setPower(1);
       
        fw.setFireworkMeta(fwm);     
		
		
		p.sendMessage(ChatColor.GREEN + "[CSQ] : เควสสำเร็จแล้ว");
		
	}
	
	
	@SuppressWarnings("unchecked")
	public void checkquestall(Player p,String name){
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
				
				checkallq = checkallq + lga.toArray().length;
			}
		}
		
		int all = checkallq;
		if(checkq == all){
			p.sendMessage(ChatColor.DARK_GREEN + "[CSQ] : เควสสำเร็จเสร็จหมดแล้ว");
		}
		
		
		
		
	}
	
	
	
	
	
	
}
