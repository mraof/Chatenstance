package com.mraof.chatenstance;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import com.mraof.chatenstance.block.ChatBox;
import com.mraof.chatenstance.chat.ChatParser;
import com.mraof.chatenstance.chat.DimensionWords;
import com.mraof.chatenstance.chat.LightningStrike;
import com.mraof.chatenstance.chat.MagicWords;
import com.mraof.chatenstance.chat.MobHate;
import com.mraof.chatenstance.chat.PlantTalk;
import com.mraof.chatenstance.world.WorldProviderChatland;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Chatenstance", name = "Chatenstance", version = "@VERSION@")
public class Chatenstance
{
	public static ChatParser chatParser;
	public int chatDimensionId = 0;
	private int chatProviderId;
	@EventHandler 
	public void preInit(FMLPreInitializationEvent event)
	{
		chatParser = new ChatParser();
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		if(config.get("Parts", "LightningStrike", true).getBoolean(true))
			chatParser.addHandler(new LightningStrike());
		if(config.get("Parts", "MobHate", true).getBoolean(true))
		{
			MobHate mobHate = new MobHate();
			for(String key : config.get("MobHate", "Creeper", new String[] {"creeper", "creepers"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntityCreeper.class);
			for(String key : config.get("MobHate", "Skeleton", new String[] {"skeleton", "skeletons"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntitySkeleton.class);
			for(String key : config.get("MobHate", "Spider", new String[] {"spider", "spiders"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntitySpider.class);
			for(String key : config.get("MobHate", "Zombie", new String[] {"zombie", "zombies"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntityZombie.class);
			for(String key : config.get("MobHate", "Enderman", new String[] {"enderman", "endermen"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntityEnderman.class);
			for(String key : config.get("MobHate", "Silverfish", new String[] {"silverfish"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntitySilverfish.class);
			for(String key : config.get("MobHate", "Slime", new String[] {"slime", "slimes"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntitySlime.class);
			for(String key : config.get("MobHate", "Blaze", new String[] {"blaze", "blazes"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntityBlaze.class);
			for(String key : config.get("MobHate", "Ghast", new String[] {"ghast", "ghasts"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.monster.EntityGhast.class);
			for(String key : config.get("MobHate", "Pig", new String[] {"pig", "pigs"}).getStringList())
				mobHate.entities.put(key, net.minecraft.entity.passive.EntityPig.class);

			chatParser.addHandler(mobHate);
		}
		if(config.get("Parts", "PlantTalk", true).getBoolean(true))
			chatParser.addHandler(new PlantTalk());
		if(config.get("Parts", "MagicWords", true).getBoolean(true))
		{
			MagicWords magicWords = new MagicWords();
			magicWords.effectWords.put(config.get("MagicWords", "Speed", "vroom").getString().toLowerCase(), 1);
			magicWords.effectWords.put(config.get("MagicWords", "Slow", "hurry").getString().toLowerCase(), 2);
			magicWords.effectWords.put(config.get("MagicWords", "Digfast", "light").getString().toLowerCase(), 3);
			magicWords.effectWords.put(config.get("MagicWords", "Digslow", "heavy").getString().toLowerCase(), 4);
			magicWords.effectWords.put(config.get("MagicWords", "Strength", "mighty").getString().toLowerCase(), 5);
			magicWords.effectWords.put(config.get("MagicWords", "Jump", "boing").getString().toLowerCase(), 8);
			magicWords.effectWords.put(config.get("MagicWords", "Confusion", "asdf").getString().toLowerCase(), 9);
			magicWords.effectWords.put(config.get("MagicWords", "Regen", "curo").getString().toLowerCase(), 10);
			magicWords.effectWords.put(config.get("MagicWords", "Protection", "my flesh is like steel").getString().toLowerCase(), 11);
			magicWords.effectWords.put(config.get("MagicWords", "FireProtection", "i cannot burn").getString().toLowerCase(), 12);
			magicWords.effectWords.put(config.get("MagicWords", "Breath", "i'm a fish").getString().toLowerCase(), 13);
			magicWords.effectWords.put(config.get("MagicWords", "Invisible", "I am invisible").getString().toLowerCase(), 14);
			magicWords.effectWords.put(config.get("MagicWords", "Blindness", "ow, my eyes").getString().toLowerCase(), 15);
			magicWords.effectWords.put(config.get("MagicWords", "NightVision", "i see all").getString().toLowerCase(), 16);
			magicWords.effectWords.put(config.get("MagicWords", "Hunger", "i'm full").getString().toLowerCase(), 17);
			magicWords.effectWords.put(config.get("MagicWords", "Weakness", "i am too weak").getString().toLowerCase(), 18);
			magicWords.effectWords.put(config.get("MagicWords", "Poison", "ow, a spider bite").getString().toLowerCase(), 19);
			magicWords.effectWords.put(config.get("MagicWords", "Wither", "i have a plague").getString().toLowerCase(), 20);
			magicWords.effectWords.put(config.get("MagicWords", "HealthBoost", "i am too healthy").getString().toLowerCase(), 21);
			magicWords.effectWords.put(config.get("MagicWords", "Absorption", "yellow hearts").getString().toLowerCase(), 22);
			chatParser.addHandler(magicWords);
		}
		if(config.get("Parts", "DimensionWords", true).getBoolean(true))
		{
			DimensionWords dimensionWords = new DimensionWords();
			chatDimensionId = config.get("DimensionWords", "ChatlandId", 23).getInt();
			dimensionWords.dimension = chatDimensionId;
			chatProviderId = config.get("DimensionWords", "ChatlandProviderId", 23).getInt();
			dimensionWords.teleportPhrase = config.get("DimensionWords", "teleportPhrase", "I want to go to that place").getString();
			dimensionWords.returnPhrase = config.get("DimensionWords", "returnPhrase", "I want to go home").getString();
			chatParser.addHandler(dimensionWords);
		}

		config.save();
		GameRegistry.registerBlock(new ChatBox(), "chatBox").setCreativeTab(CreativeTabs.tabDecorations);

	}
	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		MinecraftForge.EVENT_BUS.register(chatParser);
		if(chatDimensionId != 0)
		{
			DimensionManager.registerProviderType(chatProviderId, WorldProviderChatland.class, true);
			DimensionManager.registerDimension(chatDimensionId, chatProviderId);
		}
		if(event.getSide.isClient())
		{
			ClientProxy.registerRenderers();
		}
	}
}
