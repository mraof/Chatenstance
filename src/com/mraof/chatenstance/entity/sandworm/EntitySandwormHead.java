package com.mraof.chatenstance.entity.sandworm;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySandwormHead extends EntityCreature implements IMob
{
	public ArrayList<EntityLiving> parts = new ArrayList<EntityLiving>();
	public EntityAIAttackOnCollide entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, 0.8F, false);
	int size;
	public float sizeSingle = 2F;
	Random rand = new Random();

	public EntitySandwormHead(World world)
	{
		super(world);
		//sizeSingle = rand.nextInt(4) + 1;
		this.setSize(sizeSingle, sizeSingle);
		parts.add(this);
		size = rand.nextInt(8) + 7;
		this.stepHeight = 1.0F;
		for(int i = 1; i < size; i++)
		{
			EntitySandwormBody body = new EntitySandwormBody(this, i);
			body.setWidth(sizeSingle);
			world.spawnEntityInWorld(body);
			parts.add(body);
		}
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6F));
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(46);
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
			entityAIAttackOnCollide = new EntityAIAttackOnCollide(this, 0.8F, false);
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
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		for(int i = 1; i < parts.size(); i++)
			parts.get(i).attackEntityFrom(source, damage);
		return super.attackEntityFrom(source, damage);
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
			double ratio = (part.width - 0.5) / Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
			
			double destX = previousPart.posX + diffX * ratio;
			double destY = previousPart.posY + diffY * ratio; 
			double destZ = previousPart.posZ + diffZ * ratio;
			
			if(this.worldObj.getBlock((int) destX, (int) destY, (int) destZ).isOpaqueCube())
				destY = Math.floor(destY + 1);

			part.setPositionAndRotation(destX, destY, destZ, (float) Math.atan2(diffX, diffZ), (float) Math.asin(diffY / Math.sqrt(diffX * diffX + diffZ * diffZ)));
			previousPart = part;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.sizeSingle = tagCompound.getFloat("Width");
		if(this.sizeSingle == 0)
			//sizeSingle = rand.nextInt(4) + 1;
		this.setSize(sizeSingle, sizeSingle);
		for(int i = 1; i < parts.size(); i++)
			((EntitySandwormBody)parts.get(i)).setWidth(sizeSingle);
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setFloat("Width", sizeSingle);
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL && super.getCanSpawnHere() && rand.nextInt(8) == 0;
	}
}
