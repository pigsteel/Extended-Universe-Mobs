package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.world.level.block.IceBouquetBlock;
//? >= 26.2 {
/*import net.minecraft.references.BlockItemId;
*///?} < 26.2 {
import com.github.pigsteel.eum.util.BlockItemId;
//?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;
import java.util.function.Supplier;

public class EUMBlocks {
	public static final PushReaction UHH_WTF_MOJANG = //? < 26.3 || fabric {
			PushReaction.DESTROY;
	//?} >= 26.3 && neoforge {
			/*PushReaction.POPPED;
	*///?}

	public static final Supplier<Block> ICE_BOUQUET;

	static {
		ICE_BOUQUET = register(
				"ice_bouquet",
				IceBouquetBlock::new,
				BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).replaceable().noCollision().instabreak().lightLevel((statex) -> 10).sound(SoundType.WOOL).pushReaction(UHH_WTF_MOJANG)
		);
	}

	private static Supplier<Block> register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		return EUM.xplat().register(name, blockFactory, properties);
	}

	private static Supplier<Block> register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties) {
		return EUM.xplat().register(id, blockFactory, properties);
	}

	public static void load() {}
}
