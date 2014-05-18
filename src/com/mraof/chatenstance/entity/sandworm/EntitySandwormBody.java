package com.mraof.chatenstance.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySandwormBody extends EntityLiving
{
	public EntitySandwormHead head;
	
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
	}
	
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
