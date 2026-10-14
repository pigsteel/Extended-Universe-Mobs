package com.github.pigsteel.eum.world.entity.monster.zombie;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Mucked extends Zombie implements Shearable {
	public Mucked(EntityType<? extends Mucked> type, Level level) {
		super(type, level);
	}

	@Override
	public void shear(ServerLevel level, SoundSource soundSource, ItemStack tool) {

	}

	@Override
	public boolean readyForShearing() {
		return false;
	}
}
