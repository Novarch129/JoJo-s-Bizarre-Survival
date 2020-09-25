package io.github.novarch129.jojomod.world.gen.feature.structure;

import com.google.common.collect.ImmutableMap;
import io.github.novarch129.jojomod.JojoBizarreSurvival;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DesertStructurePieces {
    public static final ResourceLocation DESERT_STRUCTURE_PIECE_1_LOCATION = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "desertstructure1");
    public static final ResourceLocation DESERT_STRUCTURE_PIECE_2_LOCATION = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "desertstructure2");
    public static final ResourceLocation DESERT_STRUCTURE_PIECE_3_LOCATION = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "desertstructure3");
    //    private static final Map<ResourceLocation, BlockPos> map = ImmutableMap.of(DESERT_STRUCTURE_PIECE_1_LOCATION, new BlockPos(-40, -4, 0), DESERT_STRUCTURE_PIECE_2_LOCATION, new BlockPos(-5, 0, 20), DESERT_STRUCTURE_PIECE_3_LOCATION, BlockPos.ZERO);
    private static final Map<ResourceLocation, BlockPos> map = ImmutableMap.of(DESERT_STRUCTURE_PIECE_1_LOCATION, BlockPos.ZERO, DESERT_STRUCTURE_PIECE_2_LOCATION, BlockPos.ZERO, DESERT_STRUCTURE_PIECE_3_LOCATION, BlockPos.ZERO);
    public static IStructurePieceType DESERT_STRUCTURE_PIECE = null;

    public static void setup(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random random, NoFeatureConfig noFeatureConfig) {
        BlockPos structurePiece2Pos = pos.add(-17, 0, 0);
        BlockPos structurePiece3Pos = pos.add(-45, 0, 0);
        switch (rotation) {
            case CLOCKWISE_180: {
                structurePiece2Pos = pos.add(17, 0, 0);
                structurePiece3Pos = pos.add(45, 0, 0);
                break;
            }
            case COUNTERCLOCKWISE_90: {
                structurePiece2Pos = pos.add(0, 0, 17);
                structurePiece3Pos = pos.add(0, 0, 45);
                break;
            }
            case CLOCKWISE_90: {
                structurePiece2Pos = pos.add(0, 0, -17);
                structurePiece3Pos = pos.add(0, 0, -45);
                break;
            }
            default:
                break;
        }
        structurePieces.add(new Piece(manager, DESERT_STRUCTURE_PIECE_3_LOCATION, pos, rotation, pos.getY()));
        structurePieces.add(new Piece(manager, DESERT_STRUCTURE_PIECE_2_LOCATION, structurePiece2Pos, rotation, pos.getY()));
        structurePieces.add(new Piece(manager, DESERT_STRUCTURE_PIECE_1_LOCATION, structurePiece3Pos, rotation, pos.getY()));
    }

    @ParametersAreNonnullByDefault
    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation structure;
        private final Rotation rotation;
        private int y;

        public Piece(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation) {
            super(DESERT_STRUCTURE_PIECE, 0);
            this.structure = structure;
            this.rotation = rotation;
            this.y = 0;
            BlockPos blockpos = map.get(structure);
            templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            setupStructure(manager);
        }

        public Piece(TemplateManager manager, ResourceLocation structure, BlockPos pos, Rotation rotation, int y) {
            super(DESERT_STRUCTURE_PIECE, 0);
            this.structure = structure;
            this.rotation = rotation;
            this.y = y;
            BlockPos blockpos = map.get(structure);
            templatePosition = pos.add(blockpos.getX(), blockpos.getY(), blockpos.getZ());
            setupStructure(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(DESERT_STRUCTURE_PIECE, nbt);
            structure = new ResourceLocation(nbt.getString("Template"));
            rotation = Rotation.valueOf(nbt.getString("Rotation"));
            y = nbt.getInt("Y");
            setupStructure(manager);
        }

        /**
         * (abstract) Helper method to read subclass data from NBT
         */
        protected void readAdditional(CompoundNBT tagCompound) {
            super.readAdditional(tagCompound);
            tagCompound.putString("Template", structure.toString());
            tagCompound.putString("Rotation", rotation.name());
            tagCompound.putInt("Y", y);
        }

        private void setupStructure(TemplateManager manager) {
            Template template = manager.getTemplateDefaulted(structure);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setMirror(Mirror.NONE).setCenterOffset(map.get(structure)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            setup(template, templatePosition, placementsettings);
        }

        @Override
        public boolean create(IWorld worldIn, ChunkGenerator<?> chunkGeneratorIn, Random randomIn, MutableBoundingBox mutableBoundingBoxIn, ChunkPos chunkPosIn) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(rotation).setMirror(Mirror.NONE).setCenterOffset(map.get(structure)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockPos = DesertStructurePieces.map.get(structure);
            BlockPos blockPos1 = templatePosition.add(Template.transformedBlockPos(placementsettings, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
            int posY = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockPos1.getX(), blockPos1.getZ()) - 1;
            templatePosition = new BlockPos(templatePosition.getX(), y - 13, templatePosition.getZ());
            return super.create(worldIn, chunkGeneratorIn, randomIn, mutableBoundingBoxIn, chunkPosIn);
        }


        @Override
        protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
        }
    }
}
