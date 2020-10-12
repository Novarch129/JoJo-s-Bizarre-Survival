package io.github.novarch129.jojomod.world.gen.feature.structure;

import com.google.common.collect.Lists;
import com.mojang.datafixers.Dynamic;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class DesertStructure extends Structure<NoFeatureConfig> {
    private static final List<Biome.SpawnListEntry> PILLAGE_OUTPOST_ENEMIES = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.PILLAGER, 1, 1, 1));

    public DesertStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public IStartFactory getStartFactory() {
        return DesertStructure.Start::new;
    }

    @Override
    public String getStructureName() {
        return JojoBizarreSurvival.STRUCTURE.toString();
    }

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public List<Biome.SpawnListEntry> getSpawnList() {
        return PILLAGE_OUTPOST_ENEMIES;
    }

    @Override
    protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> chunkGenerator, Random random, int x, int z, int spacingOffsetsX, int spacingOffsetsZ) {
        int i = 70;
        int j = 10;
        int k = x + i * spacingOffsetsX;
        int l = z + i * spacingOffsetsZ;
        int i1 = k < 0 ? k - i + 1 : k;
        int j1 = l < 0 ? l - i + 1 : l;
        int k1 = i1 / i;
        int l1 = j1 / i;
        ((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(chunkGenerator.getSeed(), k1, l1, 10387319);
        k1 = k1 * i;
        l1 = l1 * i;
        k1 = k1 + (random.nextInt(i - j) + random.nextInt(i - j)) / 2;
        l1 = l1 + (random.nextInt(i - j) + random.nextInt(i - j)) / 2;
        return new ChunkPos(k1, l1);
    }

    @Override
    public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn) {
        ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
        if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
            for (Biome biome : generatorIn.getBiomeProvider().getBiomes(chunkX * 16 + 9, generatorIn.getSeaLevel(), chunkZ * 16 + 9, 32))
                if (!generatorIn.hasStructure(biome, this))
                    return false;
            return true;
        } else
            return false;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> structure, int i, int j, MutableBoundingBox boundingBox, int k, long l) {
            super(structure, i, j, boundingBox, k, l);
        }

        @Override
        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            NoFeatureConfig nofeatureconfig = generator.getStructureConfig(biomeIn, JojoBizarreSurvival.DESERT_STRUCTURE);
            BlockPos blockpos = new BlockPos(chunkX * 16, generator.getGroundHeight(), chunkZ * 16);
            Rotation rotation = Rotation.values()[rand.nextInt(Rotation.values().length)];
            DesertStructurePieces.setup(templateManagerIn, blockpos, rotation, components);
            recalculateStructureSize();
        }
    }
}
