package com.github.pigsteel.eum.mixin;

import com.github.pigsteel.eum.EUM;
import net.minecraft.world.level.pathfinder.PathType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinIntrinsics;
import org.spongepowered.asm.mixin.Shadow;

//? fabric {
@Mixin(PathType.class)
public enum PathTypeMixin {
	//? < 26.3 {
	/*EUM_CLIMBABLE(0.0F);
	@Shadow
	PathTypeMixin(float defaultCost) {}
	*///?} >= 26.3 {
	EUM_CLIMBABLE(MixinIntrinsics.currentEnumOrdinal(), 0.0F);
	@Shadow
	PathTypeMixin(int id, float defaultCost) {}
	//?}
}
//?} neoforge {
/*@Mixin(EUM.class)
public class PathTypeMixin { // dud

}
*///?}
