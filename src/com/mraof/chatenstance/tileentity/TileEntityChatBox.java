package com.mraof.chatenstance.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import com.mraof.chatenstance.Chatenstance;

public class TileEntityChatBox extends TileEntity
{
	public String message;
	public int ticker = 0;
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
		ticker++;
		if(ticker > 60)
		{
			Chatenstance.chatParser.onChatMessage(message, this.xCoord, this.yCoord, this.zCoord, this.worldObj);
			ticker = 0;
		}
	}
}	
