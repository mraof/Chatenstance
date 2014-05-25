package com.mraof.chatenstance;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
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
import com.mraof.chatenstance.client.ClientProxy;
import com.mraof.chatenstance.entity.EntityMummy;
import com.mraof.chatenstance.entity.sandworm.EntitySandwormBody;
import com.mraof.chatenstance.entity.sandworm.EntitySandwormHead;
import com.mraof.chatenstance.world.WorldProviderChatland;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "Chatenstance", name = "Chatenstance", version = "@VERSION@", acceptableRemoteVersions="*")
public class Chatenstance
{
	public static ChatParser chatParser;
	public int chatDimensionId = 0;
	private int chatProviderId;
	private boolean spawnInOverworld = false;
	public static int maxSandwormLength;
	public static boolean hasMobs;
	@SidedProxy(clientSide="com.mraof.chatenstance.client.ClientProxy", serverSide="com.mraof.chatenstance.CommonProxy")
	public static CommonProxy proxy;
	public static String defaultChatBoxPhrase = "i hate slimes";

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
			magicWords.effectWords.put(config.get("MagicWords", "Digfast", "giga drill breaker").getString().toLowerCase(), 3);
			magicWords.effectWords.put(config.get("MagicWords", "Digslow", "a lifetime in prison").getString().toLowerCase(), 4);
			magicWords.effectWords.put(config.get("MagicWords", "Strength", "I am mighty").getString().toLowerCase(), 5);
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
		if(config.get("Parts", "Mobs", true).getBoolean(true))
		{
			hasMobs = true;
			maxSandwormLength = config.get("Mobs", "MaxSandwormLength", 25).getInt() - 7;
			spawnInOverworld = config.get("Mobs", "SpawnInOverworld", true).getBoolean(true);
		}
		if(config.get("Parts", "ChatBox", true).getBoolean(true))
		{
			defaultChatBoxPhrase = config.get("ChatBox", "Message", "i hate slimes").getString();
			GameRegistry.registerBlock(new ChatBox(), "chatBox").setCreativeTab(CreativeTabs.tabDecorations);
		}

		config.save();

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
		if(event.getSide().isClient())
		{
			ClientProxy.registerRenderers();
		}
		if(hasMobs)
		{
			EntityList.addMapping(EntityMummy.class, "Mummy", 1800, 0xE1DD9A, 0x536b51);
			EntityRegistry.registerModEntity(EntityMummy.class, "Mummy", 0, this, 80, 3, true);
			EntityList.addMapping(EntitySandwormHead.class, "Sandworm", 1801, 0xC5C690, 0xDEEDA0);
			EntityRegistry.registerModEntity(EntitySandwormHead.class, "Sandworm", 1, this, 80, 3, true);
			EntityRegistry.registerModEntity(EntitySandwormBody.class, "SandwormBody", 2, this, 80, 3, true);
			if(spawnInOverworld)
				EntityRegistry.addSpawn(EntitySandwormHead.class, 1, 1, 2, EnumCreatureType.creature,  BiomeGenBase.desert, BiomeGenBase.plains, BiomeGenBase.mesa, BiomeGenBase.mesaPlateau, BiomeGenBase.mesaPlateau_F, BiomeGenBase.desertHills);
		}
	}
}
