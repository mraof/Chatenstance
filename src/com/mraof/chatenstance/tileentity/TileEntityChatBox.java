package com.mraof.chatenstance.tileentity;

import java.util.Random;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.mraof.chatenstance.Chatenstance;

public class TileEntityChatBox extends TileEntity
{
	public String message;
	public int ticker = 0;
	public Random rand = new Random();
	public TileEntityChatBox()
	{
		message = Chatenstance.defaultChatBoxPhrase;
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
		ticker--;
		if(ticker < 0)
		{
			Chatenstance.chatParser.onChatMessage(message, this.xCoord, this.yCoord, this.zCoord, this.worldObj);
			ticker = rand.nextInt(120);
		}
	}
}	
