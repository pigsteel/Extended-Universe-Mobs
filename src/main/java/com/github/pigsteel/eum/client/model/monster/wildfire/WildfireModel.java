package com.github.pigsteel.eum.client.model.monster.wildfire;

//? >= 1.21.2 {
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;import net.minecraft.client.model.geom.builders.CubeListBuilder;import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.monster.blaze.BlazeModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

public class WildfireModel extends EntityModel<LivingEntityRenderState> {
	public WildfireModel(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().addBox(-4.0F, 0.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().addBox(-2.0F, 24.0F, -2.0F, 4.0F, 24.0F, 4.0F), PartPose.ZERO);


		return LayerDefinition.create(mesh, 64, 64);
	}
}
//?}
