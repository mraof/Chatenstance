package com.mraof.chatenstance.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityMummy extends EntityZombie
{

	public EntityMummy(World par1World) 
	{
		super(par1World);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(field_110186_bp).setBaseValue(0.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
	}

	@Override
	protected boolean isValidLightLevel()
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.boundingBox.minY);
		int k = MathHelper.floor_double(this.posZ);

		if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j, k) > this.rand.nextInt(32))
		{
			return false;
		}
		return true;
	}
}
