package com.mraof.chatenstance.tileentity;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;

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
		EntityPlayerMP player = new EntityPlayerMP(MinecraftServer.getS);
	}
}	
