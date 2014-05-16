package com.mraof.chatenstance.chat;

import java.util.HashMap;
import java.util.Random;

import net.minecraft.potion.PotionEffect;

public class MagicWords extends ChatHandler
{
	public HashMap<String, Integer> effectWords = new HashMap<String, Integer>();
	public Random rand = new Random();

	@Override
	public void handleMessage(ChatMessage event) 
	{
		if(event.player != null && effectWords.containsKey(event.message.toLowerCase()))
			event.player.addPotionEffect(new PotionEffect(effectWords.get(event.message.toLowerCase()), rand.nextInt(500), 0, false));
	}
}
