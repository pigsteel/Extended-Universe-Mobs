package com.github.pigsteel.eum.util;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

public class PoseStackUtil {
	public static void rotateDegrees(PoseStack poseStack, Axis axis, float degrees) {
		//? < 26.3 {
		poseStack.mulPose(axis.rotationDegrees(degrees));
		//?} >= 26.3 {
		/*poseStack.rotateDegrees(axis, degrees);
		*///?}
	}
}
