package com.mraof.chatenstance.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySandwormBody extends EntityLiving
{
	public EntitySandwormHead head;
	int place;
	
	public EntitySandwormBody(World world)
	{
		super(world);
		this.setSize(2F, 2F);
	}

	public EntitySandwormBody(EntitySandwormHead entitySandwormHead, int place)
	{
		super(entitySandwormHead.worldObj);
		this.setSize(2F, 2F);
		this.head = entitySandwormHead;
		this.place = place;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		if(this.head != null)
			super.writeEntityToNBT(tagCompound);
		else
			this.setDead();
	}

	@Override
	public void onUpdate() 
	{
		super.onUpdate();
		if(this.head == null || (this.head).isDead)
		{
			this.setDead();
		}
		else
		{
			EntityLiving part = head.parts.get(place - 1);
			double diffX = this.posX - part.posX;
			double diffY = this.posY - part.posY;
			double diffZ = this.posZ - part.posZ;
			double ratio = this.width / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
			this.setPosition(part.posX + diffX * ratio, part.posY + diffY * ratio, part.posZ + diffZ * ratio);
		}
	}
	//@Override
	//public void setDead()
	//{
	//	super.setDead();
	//	if(this.head != null && !this.head.isDead)
	//		head.setDead();
	//}
	
	@Override
	public boolean isEntityEqual(Entity entity)
	{
		return this == entity || this.head == entity;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}
}
