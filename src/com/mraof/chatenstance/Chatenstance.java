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
		if(config.get("Parts", "LightningStrike", true).getB
	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(chatParser);
		chatParser.addHandler(new LightningStrike());
		chatParser.addHandler(new MobHate());
		chatParser.addHandler(new PlantTalk());
		chatParser.addHandler(new MagicWords());
	}
}
