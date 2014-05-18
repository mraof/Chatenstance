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
		if(!worldObj.isRemote)
			for(int i = 0; i < 8; i++)
			{
				System.out.println(i + 1);
				EntitySandwormBody body = new EntitySandwormBody(this.worldObj, this, i + 1);
				body.setPosition(this.posX + i + 1, this.posY, this.posZ);
				this.worldObj.spawnEntityInWorld(body);
				parts.add(body);
			}
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData entityLivingData)
	{
		entityLivingData = super.onSpawnWithEgg(entityLivingData);
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
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if(!source.equals(DamageSource.inWall))
			return super.attackEntityFrom(source, damage);
		return true;
	}
}	
