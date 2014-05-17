package com.mraof.chatenstance.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityMummy extends EntityZombie
{

	public EntityMummy(World par1World) 
	{
		super(par1World);
	}
	protected void getEntityAttribute()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}
}
