package com.mraof.chatenstance.chat;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;

public class LightningStrike extends ChatHandler
{
	Random rand = new Random();

	@Override
	public void handleMessage(ChatMessage event) 
	{
		String[] words = event.message.split(" ");
		int matches = 0;
		for(String word : words)
		{
			if(word.equalsIgnoreCase("lightning") || word.equalsIgnoreCase("lightening") || word.equalsIgnoreCase("strike"))
				matches++;
		}
		for(int i = 0; i < words.length; i++)
		if(.05 * matches / words.length > rand.nextDouble())
		{
			double x = event.x + rand.nextInt(17) - 8;
			double z = event.z + rand.nextInt(17) - 8;
			EntityLightningBolt bolt = new EntityLightningBolt(event.world, x, event.world.getPrecipitationHeight((int) x, (int) z), z);
			event.world.addWeatherEffect(bolt);

		}

	}

}
