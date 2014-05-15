package com.mraof.chatenstance.chat;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.ServerChatEvent;

public class MobHate extends ChatHandler
{
	Random rand = new Random();
	static HashMap<String, Class<? extends Entity>> entities = new HashMap<String, Class<? extends Entity>>();

	static
	{
		entities.put("creeper", net.minecraft.entity.monster.EntityCreeper.class);
		entities.put("creepers", net.minecraft.entity.monster.EntityCreeper.class);
		entities.put("skeleton", net.minecraft.entity.monster.EntitySkeleton.class);
		entities.put("skeletons", net.minecraft.entity.monster.EntitySkeleton.class);
		entities.put("spider", net.minecraft.entity.monster.EntitySpider.class);
		entities.put("spiders", net.minecraft.entity.monster.EntitySpider.class);
		entities.put("zombie", net.minecraft.entity.monster.EntityZombie.class);
		entities.put("zombies", net.minecraft.entity.monster.EntityZombie.class);
		entities.put("enderman", net.minecraft.entity.monster.EntityEnderman.class);
		entities.put("endermen", net.minecraft.entity.monster.EntityEnderman.class);
		entities.put("silverfish", net.minecraft.entity.monster.EntitySilverfish.class);
		entities.put("slime", net.minecraft.entity.monster.EntitySlime.class);
		entities.put("slimes", net.minecraft.entity.monster.EntitySlime.class);
		entities.put("blaze", net.minecraft.entity.monster.EntityBlaze.class);
		entities.put("blazes", net.minecraft.entity.monster.EntityBlaze.class);
		entities.put("ghast", net.minecraft.entity.monster.EntityGhast.class);
		entities.put("ghasts", net.minecraft.entity.monster.EntityGhast.class);
		entities.put("pig", net.minecraft.entity.passive.EntityPig.class);
		entities.put("pigs", net.minecraft.entity.passive.EntityPig.class);
	};
	@Override
	public void handleMessage(ServerChatEvent event)
	{
		boolean hate = false;
		String message = event.message + " ";
		Entity entity = null;
		while(!message.isEmpty()) 
		{
			int wordEnd = message.indexOf(' ') != -1 ? message.indexOf(' ') : message.length() - 1;
			String word = message.substring(0, wordEnd);
			System.out.println("\"" + word + "\"");
			if(word.equalsIgnoreCase("HATE"))
				hate = true;
			if (entities.containsKey(word.toLowerCase()))
			{
				try {
					entity = entities.get(word.toLowerCase()).getConstructor(net.minecraft.world.World.class).newInstance(event.player.worldObj);
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}	
			message = message.substring(wordEnd + 1);	
		}
		if(entity != null && hate)
		{
			entity.setPosition(event.player.posX, event.player.posY, event.player.posZ);
			event.player.worldObj.spawnEntityInWorld(entity);
		}
	}
}
