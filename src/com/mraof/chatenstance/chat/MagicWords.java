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
		effectWords.put("my flesh is like steel", 11);
		effectWords.put("i cannot burn", 12);
		effectWords.put("i'm a fish", 13);
		effectWords.put("I am invisible", 14);
		effectWords.put("ow, my eyes", 15);
		effectWords.put("i see all", 16);
		effectWords.put("i'm full", 17);
		effectWords.put("i am too weak", 18);
		effectWords.put("ow, a spider bite", 19);
		effectWords.put("i have a plague", 20);
		effectWords.put("i am too healthy", 21);
		effectWords.put("yellow hearts", 22);
	}

	@Override
	public void handleMessage(ServerChatEvent event) 
	{
		event.player.addPotionEffect(new PotionEffect(effectWords.get(event.message.toLowerCase()), 180, 0, false));
	}
}
