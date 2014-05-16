package com.mraof.chatenstance.tileentity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.ServerChatEvent;

import com.mojang.authlib.GameProfile;
import com.mraof.chatenstance.Chatenstance;

public class TileEntityChatBox extends TileEntity
{
	public String message;
	public TileEntityChatBox()
	{
		message = "strike strike strike strike strike strike strike strike strike strike strike strike strike strike strike strike hate pig";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
	}

	@Override
	public void updateEntity()
	{
		EntityPlayerMP player = new EntityPlayerMP(MinecraftServer.getServer(), (WorldServer) this.worldObj, new GameProfile(null, "ChatBox"), (ItemInWorldManager) null);
		Chatenstance.chatParser.onChatMessage(new ServerChatEvent(player, message, (ChatComponentTranslation) null));
	}
}	
