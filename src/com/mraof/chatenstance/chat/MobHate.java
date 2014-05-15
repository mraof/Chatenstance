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
		entities.put("spider", net.minecraft.entity.monster.EntitySpider.class);
		entities.put("spiders", net.minecraft.entity.monster.EntitySpider.class);
	};
	@Override
	public void handleMessage(ServerChatEvent event)
	{
		boolean hate = false;
		String message = event.message;
		while(!message.isEmpty()) 
		{
			int wordEnd = message.indexOf(' ') != -1 ? message.indexOf(' ') : message.length() - 1;
			String word = message.substring(0, wordEnd);
			if(word.equalsIgnoreCase("HATE"))
				hate = true;
			if (entities.containsKey(word.toLowerCase()) && hate)
				try {
					event.player.worldObj.spawnEntityInWorld(entities.get(
							word.toLowerCase()).getConstructor(net.minecraft.world.World.class).newInstance(event.player.worldObj));
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
	}
}
