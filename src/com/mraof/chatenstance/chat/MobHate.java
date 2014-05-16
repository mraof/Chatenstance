package com.mraof.chatenstance.chat;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.entity.Entity;

public class MobHate extends ChatHandler
{
	Random rand = new Random();
	public HashMap<String, Class<? extends Entity>> entities = new HashMap<String, Class<? extends Entity>>();

	@Override
	public void handleMessage(ChatMessage event)
	{
		boolean hate = false;
		String message = event.message + " ";
		Entity entity = null;
		while(!message.isEmpty()) 
		{
			int wordEnd = message.indexOf(' ') != -1 ? message.indexOf(' ') : message.length() - 1;
			String word = message.substring(0, wordEnd);
			if(word.equalsIgnoreCase("HATE"))
				hate = true;
			if (entities.containsKey(word.toLowerCase()))
			{
				try {
					entity = entities.get(word.toLowerCase()).getConstructor(net.minecraft.world.World.class).newInstance(event.world);
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
		if(entity != null && hate && rand.nextDouble() < .1)
		{
			entity.setPosition(event.x + rand.nextInt(33) - 16, event.y, event.z + rand.nextInt(33) - 16);
			event.world.spawnEntityInWorld(entity);
		}
	}
}
