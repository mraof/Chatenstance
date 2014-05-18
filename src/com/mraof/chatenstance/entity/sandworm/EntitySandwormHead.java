package com.mraof.chatenstance.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySandwormHead extends EntitySandwormPart
{
	public ArrayList<EntitySandwormPart> parts = new ArrayList<EntitySandwormPart>();
	public EntitySandwormHead(World world)
	{
		super(world);
		this.head = this;
		parts.add(this);
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
	{
		entityLivingData = super.onSpawnWithEgg(entityLivingData);
		if(!worldObj.isRemote)
			for(int i = 0; i < 8; i++)
			{
				EntitySandwormBody body = new EntitySandwormBody(this.worldObj, this, i + 2);
				body.setPosition(this.posX + i + 1, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(body);
				parts.add(body);
			}
		return entityLivingData;

	}
	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		for(int i = 1; i < parts.size(); i++)
			head.parts.get(i).updatePartPosition();
	}
	@Override
	public boolean isAIEnabled()
	{
		return true;
	}
	@Override
	public void damageEntity(DamageSource source, float damage)
	{
		
	}
}	
