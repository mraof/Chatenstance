package com.mraof.chatenstance;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.mraof.chatenstance.chat.ChatParser;
import com.mraof.chatenstance.chat.LightningStrike;
import com.mraof.chatenstance.chat.MagicWords;
import com.mraof.chatenstance.chat.MobHate;
import com.mraof.chatenstance.chat.PlantTalk;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "Chatenstance", name = "Chatenstance", version = "@VERSION@")
public class Chatenstance
{
	ChatParser chatParser;
	@EventHandler 
	public void preInit(FMLPreInitializationEvent event)
	{
		chatParser = new ChatParser();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		if(config.get("Parts", "LightningStrike", true).getBoolean(true))
			chatParser.addHandler(new LightningStrike());
		if(config.get("Parts", "MobHate", true).getBoolean(true))
			chatParser.addHandler(new MobHate());
		if(config.get("Parts", "PlantTalk", true).getBoolean(true))
			chatParser.addHandler(new PlantTalk());
		if(config.get("Parts", "MagicWords", true).getBoolean(true))
		{
			MagicWords magicWords = new MagicWords();
			magicWords.effectWords.put(config.get("MagicWords", "Speed", "vroom").getString(), 1);
			magicWords.effectWords.put(config.get("MagicWords", "Slow", "hurry").getString(), 2);
			magicWords.effectWords.put(config.get("MagicWords", "Digfast", "light").getString(), 3);
			magicWords.effectWords.put(config.get("MagicWords", "Digslow", "heavy").getString(), 4);
			magicWords.effectWords.put(config.get("MagicWords", "Strength", "mighty").getString(), 5);
			magicWords.effectWords.put(config.get("MagicWords", "Jump", "boing").getString(), 8);
			magicWords.effectWords.put(config.get("MagicWords", "Confusion", "asdf").getString(), 9);
			magicWords.effectWords.put(config.get("MagicWords", "Regen", "curo").getString(), 10);
			magicWords.effectWords.put(config.get("MagicWords", "Protection", "my flesh is like steel").getString(), 11);
			magicWords.effectWords.put(config.get("MagicWords", "FireProtection", "i cannot burn").getString(), 12);
			magicWords.effectWords.put(config.get("MagicWords", "Breath", "i'm a fish").getString(), 13);
			magicWords.effectWords.put(config.get("MagicWords", "Invisible", "I am invisible").getString(), 14);
			magicWords.effectWords.put(config.get("MagicWords", "Blindness", "ow, my eyes").getString(), 15);
			magicWords.effectWords.put(config.get("MagicWords", "NightVision", "i see all").getString(), 16);
			magicWords.effectWords.put(config.get("MagicWords", "Hunger", "i'm full").getString(), 17);
			magicWords.effectWords.put(config.get("MagicWords", "Weakness", "i am too weak").getString(), 18);
			magicWords.effectWords.put(config.get("MagicWords", "Poison", "ow, a spider bite").getString(), 19);
			magicWords.effectWords.put(config.get("MagicWords", "Wither", "i have a plague").getString(), 20);
			magicWords.effectWords.put(config.get("MagicWords", "HealthBoost", "i am too healthy").getString(), 21);
			magicWords.effectWords.put(config.get("MagicWords", "Absorption", "yellow hearts").getString(), 22);
			chatParser.addHandler(magicWords);
		}
		config.save();
			
	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(chatParser);
	}
}
