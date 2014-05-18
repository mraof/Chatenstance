package com.mraof.chatenstance.entity.sandworm;

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
		if(head == null || head.isDead)
			this.setDead();
	}

	public void updatePartPosition() 
	{
		if(this.place > 0)
		{
			EntitySandwormPart part = head.parts.get(place - 1);
			double diffX = this.posX - part.posX;
			double diffY = this.posY - part.posY;
			double diffZ = this.posZ - part.posZ;
			double ratio = this.width / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
			this.posX = part.posX + diffX * ratio;
			this.posY = part.posY + diffY * ratio;
			this.posZ = part.posZ + diffZ * ratio;
		}

	}
}
