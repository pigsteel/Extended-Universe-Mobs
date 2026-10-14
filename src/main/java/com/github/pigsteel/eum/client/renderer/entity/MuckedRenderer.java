package com.github.pigsteel.eum.client.renderer.entity;

import com.github.pigsteel.eum.client.model.monster.zombie.MuckedModel;
import com.github.pigsteel.eum.client.renderer.entity.state.MuckedRenderState;
import com.github.pigsteel.eum.world.entity.monster.zombie.Mucked;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class MuckedRenderer extends AbstractZombieRenderer<Mucked, MuckedRenderState, MuckedModel> {
	protected MuckedRenderer(EntityRendererProvider.Context context, MuckedModel model, MuckedModel babyModel, ArmorModelSet<MuckedModel> armorSet, ArmorModelSet<MuckedModel> babyArmorSet) {
		super(context, model, babyModel, armorSet, babyArmorSet);
	}

	@Override
	public MuckedRenderState createRenderState() {
		return new MuckedRenderState();
	}
}
