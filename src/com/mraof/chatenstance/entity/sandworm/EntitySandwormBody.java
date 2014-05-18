package com.mraof.chatenstance.entity.sandworm;

import net.minecraft.world.World;

public class EntitySandwormBody extends EntitySandwormPart 
{
	boolean justSpawned = true;
	public EntitySandwormBody(World world)
	{
		super(world);
		//if(!worldObj.isRemote && head == null)
			//this.setDead();
	}

	public EntitySandwormBody(World world, EntitySandwormHead entitySandwormHead, int place)
	{
		this(world);
		this.head = entitySandwormHead;
		this.place = place;
	}
	public void updatePartPosition() 
	{
		if(this.place > 0)
		{
			if(justSpawned)
			{
				this.justSpawned = false;
				this.posX = head.posX + place;
				this.posY = head.posY;
				this.posZ = head.posZ;
				System.out.printf("%d: %f %f %f\n", this.place, this.posX, this.posY, this.posZ);
			}
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
