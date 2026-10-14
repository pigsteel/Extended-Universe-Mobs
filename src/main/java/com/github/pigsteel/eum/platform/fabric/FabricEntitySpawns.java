package com.github.pigsteel.eum.platform.fabric;

//? fabric  {

import com.github.pigsteel.eum.EUM;
import com.github.pigsteel.eum.util.EntityTypesUtil;
import com.github.pigsteel.eum.world.entity.monster.VilerWitch;
import com.github.pigsteel.eum.world.entity.monster.zombie.Frostbitten;
import com.github.pigsteel.eum.world.entity.monster.zombie.Reclaimed;
import com.mojang.serialization.MapCodec;
import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ClampedInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;

import static com.github.pigsteel.eum.core.EUMEntityTypes.FROSTBITTEN;
import static com.github.pigsteel.eum.core.EUMEntityTypes.RECLAIMED;
import static com.github.pigsteel.eum.core.EUMEntityTypes.VILER_WITCH;

public class FabricEntitySpawns {
    public static void AddSpawns() {
        // Reweighting zombies and frostbitten to function like Strays
        BiomeModifications.create(Identifier.fromNamespaceAndPath(EUM.MOD_ID,"adjust_winter_zombie_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.ICE_SPIKES, Biomes.SNOWY_PLAINS, Biomes.JAGGED_PEAKS, Biomes.FROZEN_PEAKS, Biomes.SNOWY_TAIGA),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityTypesUtil.ZOMBIE);

							addSpawn(spawnSettings, MobCategory.MONSTER, EntityTypesUtil.ZOMBIE, 4, 4, 15);

                            addSpawn(spawnSettings, MobCategory.MONSTER, FROSTBITTEN.get(), 4, 4, 80);
                        }
                );

        // Ditto
        BiomeModifications.create(Identifier.fromNamespaceAndPath(EUM.MOD_ID,"adjust_jungle_zombie_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityTypesUtil.ZOMBIE);

                            addSpawn(spawnSettings, MobCategory.MONSTER, EntityTypesUtil.ZOMBIE, 4, 4, 10);

							addSpawn(spawnSettings, MobCategory.MONSTER, RECLAIMED.get(), 4, 4, 85);
                        }
                );

        BiomeModifications.create(EUM.id("adjust_dark_forest_witch_spawns"))
                .add(ModificationPhase.REPLACEMENTS,
                        BiomeSelectors.includeByKey(Biomes.DARK_FOREST),
                        context -> {
                            var spawnSettings = context.getMobSpawnSettings();

                            spawnSettings.removeSpawnsOfEntityType(EntityTypesUtil.WITCH);

                            addSpawn(spawnSettings, MobCategory.MONSTER, VILER_WITCH.get(), 1, 1, 5);
                        }
                );
    }

    public static void registerSpawnRules() {
        SpawnPlacements.register(
                FROSTBITTEN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Frostbitten::checkFrostbittenSpawnRules
        );
        SpawnPlacements.register(
                RECLAIMED.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Reclaimed::checkSurfaceMonstersSpawnRules
        );
        SpawnPlacements.register(
                VILER_WITCH.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                VilerWitch::checkMonsterSpawnRules
        );
    }

	public static void addSpawn(BiomeModificationContext.MobSpawnSettingsContext spawnSettings, MobCategory mobCategory, EntityType<?> entityType, int minCount, int maxCount, int weight) {
		//? < 26.3 {
		/*spawnSettings.addSpawn(
				mobCategory,
				new MobSpawnSettings.SpawnerData(entityType, minCount, maxCount),
				weight
		);
		*///?} >= 26.3 {
		spawnSettings.addSpawn(entityType, weight, minCount, maxCount);
		//?}
	}
}
//?}
