package com.mraof.chatenstance.entity.sandworm;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntitySandwormHead extends EntityCreature
{
	public ArrayList<EntityLiving> parts = new ArrayList<EntityLiving>();
	public EntityAIAttackOnCollide entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, .4F, false);

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
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.3F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
				
		if (world != null && !world.isRemote)
		{
			this.setCombatTask();
		};
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		this.updatePartPositions();
	}
	
	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		return entity.attackEntityFrom(DamageSource.causeMobDamage(this), 6);
	}

	protected void setCombatTask()
	{
		if(entityAIAttackOnCollide == null)
			entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, .4F, false);
		this.tasks.removeTask(this.entityAIAttackOnCollide);
		this.tasks.addTask(4, entityAIAttackOnCollide);
	}

	@Override 
	public Entity[] getParts()
	{
		return parts.toArray(new Entity[parts.size()]);
	}

	@Override
	public void collideWithEntity(Entity entity)
	{
		if(!parts.contains(entity))
			super.collideWithEntity(entity);
	}

	@Override
	public void setPositionAndRotation(double x, double y, double z, float yaw, float pitch)
	{
		super.setPositionAndRotation(x, y, z, yaw, pitch);
		this.updatePartPositions();
	}

	public void updatePartPositions()
	{
		EntityLiving previousPart = this;
		for(int i = 1; i < parts.size(); i++)
		{
			EntityLiving part = parts.get(i);
			double diffX = part.posX - previousPart.posX;
			double diffY = part.posY - previousPart.posY;
			double diffZ = part.posZ - previousPart.posZ;
			double ratio = part.width / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
			

			part.setPositionAndRotation(previousPart.posX + diffX * ratio, previousPart.posY + diffY * ratio, previousPart.posZ + diffZ * ratio, (float) Math.atan2(diffX, diffZ), (float) Math.asin(diffY / Math.sqrt(diffX * diffX + diffZ * diffZ)));
			previousPart = part;
		}
	}

}
