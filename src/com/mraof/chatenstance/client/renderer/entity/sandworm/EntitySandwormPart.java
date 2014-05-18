package com.mraof.chatenstance.client.renderer.entity.sandworm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;

public abstract class EntitySandwormPart extends EntityCreature
{
	public EntitySandwormHead head;
	int place;
	public EntitySandwormPart(World world)
	{
		super(world);
		place = 0;
	}

	@Override
	public boolean isAIEnabled()
	{
		return false;
	}
	protected void collideWithEntity(Entity par1Entity)
	{
		if(!head.parts.contains(par1Entity))
			par1Entity.applyEntityCollision(this);
	}

	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		if(head.parts.size() > place + 1)
			head.parts.get(place + 1).updatePartPosition();
	}

	public void updatePartPosition() 
	{
		if(this.place > 0)
		{
			EntitySandwormPart part = head.parts.get(place - 1);
			double diffX = part.posX - this.posX;
			double diffY = part.posY - this.posY;
			double diffZ = part.posZ - this.posZ;
			double total = Math.abs(diffX) + Math.abs(diffY) + Math.abs(diffZ);
			this.posX = this.width * (total / diffX);
			this.posY = this.width * (total / diffY);
			this.posZ = this.width * (total / diffZ);
		}

	}
}
