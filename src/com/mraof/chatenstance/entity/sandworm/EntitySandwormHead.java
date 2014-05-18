package com.mraof.chatenstance.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;

public class EntitySandwormHead extends EntityCreature
{
	public ArrayList<EntityLiving> parts = new ArrayList<EntityLiving>();

	public EntitySandwormHead(World world)
	{
		super(world);
		this.setSize(2F, 2F);
		parts.add(this);
		for(int i = 1; i < 9; i++)
		{
			EntitySandwormBody body = new EntitySandwormBody(this, i);
			world.spawnEntityInWorld(body);
			parts.add(body);
		}
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		this.updatePartPositions();
	}

	@Override 
	public Entity[] getParts()
	{
		return parts.asArray(new Entity[parts.size()]);
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if(!parts.contains(entity))
			super.collideWithEntity(entity);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, double yaw, double pitch)
	{
		super.setPositionAndRotation(x, y, z, yaw, pitch);
		this.updatePartPositions();
	}

	public void updatePartPositions()
	{
		EntityLiving part = this;
		for(int i = 0; i < 
				double diffX = this.posX - part.posX;
				double diffY = this.posY - part.posY;
				double diffZ = this.posZ - part.posZ;
				double ratio = this.width / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
				this.setPosition(part.posX + diffX * ratio, part.posY + diffY * ratio, part.posZ + diffZ * ratio);
	}

}
