package com.mraof.chatenstance.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public abstract class EntitySandwormPart extends EntityLiving
{
	public EntitySandwormHead head;
	int place;
	public EntitySandwormPart(World world)
	{
		super(world);
		this.setSize(2.0F, 2.0F);
		place = 0;
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
	protected void collideWithEntity(Entity par1Entity)
	{
		if(head == null || !head.parts.contains(par1Entity))
			par1Entity.applyEntityCollision(this);
	}

	public void onEntityUpdate()
	{
		super.onEntityUpdate();
	}

	public void updatePartPosition() 
	{
	}
	@Override
	public void setDead()
	{
		super.setDead();
		System.out.println(place + " died (" + worldObj.isRemote + ")");
		if(worldObj.isRemote)
		{
			try
			{
				Integer crash = null;
				crash.byteValue();
			} catch(Exception e)
			{ e.printStackTrace(); }
		}
	}
}
