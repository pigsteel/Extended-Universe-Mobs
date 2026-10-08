package com.github.pigsteel.eum.client.renderer.entity.layers;

//? >= 1.21.2 {
import com.github.pigsteel.eum.client.model.monster.witch.VilerWitchModel;
import com.github.pigsteel.eum.client.renderer.entity.state.VilerWitchRenderState;
import com.github.pigsteel.eum.util.PoseStackUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.CrossedArmsItemLayer;

public class VilerWitchItemLayer extends CrossedArmsItemLayer<VilerWitchRenderState, VilerWitchModel> {
    public VilerWitchItemLayer(RenderLayerParent<VilerWitchRenderState, VilerWitchModel> renderer) {
        super(renderer);
    }

    protected void applyTranslation(VilerWitchRenderState state, PoseStack poseStack) {
        if (state.isHoldingPotion) {
            this.getParentModel().root().translateAndRotate(poseStack);
            this.getParentModel().translateToHead(poseStack);
            this.getParentModel().getNose().translateAndRotate(poseStack);
            poseStack.translate(0.0625F, 0.25F, 0.0F);
			PoseStackUtil.rotateDegrees(poseStack, Axis.ZP, 180.0F);
			PoseStackUtil.rotateDegrees(poseStack, Axis.XP, 140.0F);
			PoseStackUtil.rotateDegrees(poseStack, Axis.ZP, 10.0F);
			PoseStackUtil.rotateDegrees(poseStack, Axis.XP, 180.0F);
        } else {
            super.applyTranslation(state, poseStack);
        }

    }
}
//?}
