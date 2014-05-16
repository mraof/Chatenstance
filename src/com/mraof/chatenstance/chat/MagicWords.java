package com.mraof.chatenstance.chat;

import java.util.HashMap;
import java.util.HashSet;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ServerChatEvent;

public class MagicWords extends ChatHandler
{
	public static HashMap<String, Integer> effectWords = new HashMap<String, Integer>();

	static
	{
		effectWords.put("vroom", 1);
		effectWords.put("hurry", 2);
		effectWords.put("light", 3);
		effectWords.put("heavy", 4);
		effectWords.put("mighty", 5);
		effectWords.put("boing", 8);
		effectWords.put("asdf", 9);
		effectWords.put("curo", 10);
	}

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		event.player.addPotionEffect(new PotionEffect(effectWords.get(event.message.toLowerCase()), 180, 0, false));
	}
}
