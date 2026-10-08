package com.github.pigsteel.eum.platform.neoforge.subscriber;

//? neoforge {
import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.core.EUMCustomRegistries;
import com.github.pigsteel.eum.world.entity.monster.skeleton.SunkenVariant;
import net.minecraft.core.Registry;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.NewDatapackRegistryEvent;
//? >= 26.3 {

//?} < 26.3 {
/*import net.neoforged.neoforge.registries.DataPackRegistryEvent;
*///?}

@EventBusSubscriber(modid = EUM.MOD_ID)
public class NeoforgeDatapackRegistries {
	//? >= 26.3 {
	@SubscribeEvent
	public static void registerDatapackRegistries(NewDatapackRegistryEvent event) {
		event.<SunkenVariant>reloadableRegistry(builder -> builder.key(EUMCustomRegistries.SUNKEN_VARIANT).codec(SunkenVariant.DIRECT_CODEC).networkCodec(SunkenVariant.NETWORK_CODEC));
	}
	//?} < 26.3 {
	/*@SubscribeEvent
	public static void registerDatapackRegistries(DataPackRegistryEvent.NewRegistry event) {
		event.dataPackRegistry(
				EUMCustomRegistries.SUNKEN_VARIANT,
				SunkenVariant.DIRECT_CODEC,
				SunkenVariant.NETWORK_CODEC,
				builder -> builder.maxId(256)
		);
	}
	*///?}
}
//?}
