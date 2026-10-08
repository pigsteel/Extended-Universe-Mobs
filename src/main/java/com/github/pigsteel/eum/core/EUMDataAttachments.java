package com.github.pigsteel.eum.core;

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.platform.Platform;
import com.mojang.serialization.Codec;
import net.minecraft.network.codec.ByteBufCodecs;

import java.util.function.Consumer;

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
