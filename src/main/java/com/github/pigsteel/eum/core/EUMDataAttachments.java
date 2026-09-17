package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
//? fabric {
/*import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
*///?} neoforge {
import com.github.pigsteel.eum.platform.Platform;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
//?}
import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EUMDataAttachments {
	// for reference: use these when we want to attach arbitrary data (the same way we would with an EntityDataAccessor) for an entity we do not own; like the zombie

    public static final Platform.DataAttachmentHandle<Boolean> DATA_FROSTBITTEN_CONVERSION_ID = register(
            "data_frostbitten_conversion_id",
            builder -> builder
                    .initializer(() -> false)
                    .syncWith(
                            ByteBufCodecs.BOOL
                    ).persistent(Codec.BOOL)
    );

	public static final Platform.DataAttachmentHandle<Boolean> DATA_SUNKEN_CONVERSION_ID = register(
			"data_sunken_conversion_id",
			builder -> builder
					.initializer(() -> false)
					.syncWith(
							ByteBufCodecs.BOOL
					).persistent(Codec.BOOL)
	);

    private EUMDataAttachments() {
    }

	private static <A> Platform.DataAttachmentHandle<A> register(String id, Consumer<Platform.AgnosticBuilder<A>> consumer) {
		return EUM.xplat().register(id, consumer);
	}

    public static void load() {}
}
