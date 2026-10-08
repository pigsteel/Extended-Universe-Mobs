package com.github.pigsteel.eum.platform;

import com.github.pigsteel.eum.core.EUMDataAttachments;
import com.mojang.serialization.Codec;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jspecify.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

//? fabric {
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
//?} neoforge {
/*import net.neoforged.neoforge.attachment.AttachmentType;
*///?}

public interface Platform {
	boolean isModLoaded(String modId);

	ModLoader loader();

	String mcVersion();

	boolean isDevelopmentEnvironment();

	default boolean isDebug() {
		return isDevelopmentEnvironment();
	}

	<T> Supplier<DataComponentType<T>> register(
			final String id,
			final UnaryOperator<DataComponentType.Builder<T>> builder
	);

	<T extends LivingEntity> void register(Supplier<EntityType<T>> entityType, Supplier<AttributeSupplier.Builder> supplier);

	void register(EntityDataSerializer<?> serializer, String name);

	<T extends Entity> Supplier<EntityType<T>> register(String id, EntityType.Builder<T> builder);

	<T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory);

	Holder<MobEffect> register(final String name, final MobEffect mobEffect);

	Supplier<SimpleParticleType> register(final String name, final boolean overrideLimiter);

	Supplier<SoundEvent> registerSoundEvent(String name);

	void register(ModelLayerLocation modelLayerLocation, Supplier<LayerDefinition> consumer);

	<U extends Sensor<?>> Supplier<SensorType<U>> register(String name, Supplier<U> factory);

	<U> Supplier<MemoryModuleType<U>> registerMemoryModuleType(String name, Optional<Codec<U>> maybeCodec);

	<T extends Entity> void register(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider);

	<A> DataAttachmentHandle<A> register(String name, Consumer<AgnosticBuilder<A>> consumer);

	Supplier<Block> register(String name, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties);

	Supplier<Block> register(BlockItemId id, Function<BlockBehaviour.Properties, Block> blockFactory, BlockBehaviour.Properties properties);

	interface DataAttachmentHandle<T> {
		boolean hasAttached(Entity entity);

		T getAttached(Entity entity);

		T getAttachedOrElse(Entity entity, T defaultValue);

		void setAttached(Entity entity, T value);

		T getAttachedOrSet(Entity entity, T defaultValue);
	}

	static <A> AgnosticBuilder<A> builder() {
		return new AgnosticBuilder<>();
	}

	class AgnosticBuilder<A> {
		@Nullable
		private Supplier<A> defaultInitializer = null;
		@Nullable
		private Codec<A> persistenceCodec = null;
		@Nullable
		private StreamCodec<? super RegistryFriendlyByteBuf, A> streamCodec = null;
		private boolean copyOnDeath = false;
		private int maxSyncSize = -1;

		public AgnosticBuilder<A> persistent(Codec<A> codec) {
			Objects.requireNonNull(codec, "codec cannot be null");

			this.persistenceCodec = codec;
			return this;
		}

		public AgnosticBuilder<A> copyOnDeath() {
			this.copyOnDeath = true;
			return this;
		}

		public AgnosticBuilder<A> initializer(Supplier<A> initializer) {
			Objects.requireNonNull(initializer, "initializer cannot be null");

			this.defaultInitializer = initializer;
			return this;
		}

		public AgnosticBuilder<A> syncWith(StreamCodec<? super RegistryFriendlyByteBuf, A> streamCodec) {
			Objects.requireNonNull(streamCodec, "stream codec cannot be null");

			this.streamCodec = streamCodec;
			return this;
		}

		//? fabric {
		public void fabricImpl(AttachmentRegistry.Builder<A> builder) {
			if(this.defaultInitializer != null) {
				builder.initializer(this.defaultInitializer);
			}

			if(this.streamCodec != null) {
				builder.syncWith(streamCodec, AttachmentSyncPredicate.all());
			}

			if(this.persistenceCodec != null) {
				builder.persistent(this.persistenceCodec);
			}

			if(this.copyOnDeath) {
				builder.copyOnDeath();
			}
		}
		//?}

		//? neoforge {
		/*public AttachmentType.Builder<A> neoforgeImpl() {
			Objects.requireNonNull(defaultInitializer, "defaultInitializer cannot be null");

			AttachmentType.Builder<A> builder = AttachmentType.builder(defaultInitializer);

			if(this.streamCodec != null) {
				builder.sync(this.streamCodec);
			}

			if(this.persistenceCodec != null) {
				builder.serialize(persistenceCodec.fieldOf("persistent"));
			}

			if(this.copyOnDeath) {
				builder.copyOnDeath();
			}

			return builder;
		}
		*///?}
	}

	enum ModLoader {
		FABRIC, NEOFORGE, FORGE, QUILT
	}
}
