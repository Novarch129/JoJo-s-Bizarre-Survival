package io.github.novarch129.jojomod.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static io.github.novarch129.jojomod.util.Util.Null;

public class StandChunkEffects implements ICapabilitySerializable<INBT> {
    @CapabilityInject(StandChunkEffects.class)
    public static final Capability<StandChunkEffects> STAND_CHUNK_EFFECTS = Null();
    private final ChunkPos chunk;
    private final World world;
    private LazyOptional<StandChunkEffects> holder = LazyOptional.of(() -> new StandChunkEffects(getWorld(), getChunk()));
    private Map<UUID, BlockPos> bombs = new ConcurrentHashMap<>();

    public StandChunkEffects(World world, ChunkPos chunk) {
        this.world = world;
        this.chunk = chunk;
    }

    public static LazyOptional<StandChunkEffects> getLazyOptional(Chunk chunk) {
        return chunk.getCapability(STAND_CHUNK_EFFECTS);
    }

    public static StandChunkEffects getCapabilityFromChunk(Chunk chunk) {
        return getLazyOptional(chunk).orElse(new StandChunkEffects(chunk.getWorld(), chunk.getPos()));
    }

    public static void register() {
        CapabilityManager.INSTANCE.register(StandChunkEffects.class, new Capability.IStorage<StandChunkEffects>() {
            @Override
            public INBT writeNBT(Capability<StandChunkEffects> capability, StandChunkEffects instance, Direction side) {
                CompoundNBT nbt = new CompoundNBT();
                ListNBT listNBT = new ListNBT();
                instance.bombs.forEach(((uuid, pos) -> {
                    CompoundNBT compoundNBT = new CompoundNBT();
                    compoundNBT.putUniqueId("standUser", uuid);
                    compoundNBT.putDouble("blockPosX", pos.getX());
                    compoundNBT.putDouble("blockPosY", pos.getY());
                    compoundNBT.putDouble("blockPosZ", pos.getZ());
                    listNBT.add(compoundNBT);
                }));
                nbt.put("bombs", listNBT);
                return nbt;
            }

            @Override
            public void readNBT(Capability<StandChunkEffects> capability, StandChunkEffects instance, Direction side, INBT nbt) {
                CompoundNBT compoundNBT = (CompoundNBT) nbt;
                compoundNBT.getList("bombs", Constants.NBT.TAG_COMPOUND).forEach(compound -> {
                    if (compound instanceof CompoundNBT && ((CompoundNBT) compound).contains("blockPosX"))
                        instance.bombs.put(compoundNBT.getUniqueId("standUser"), new BlockPos(((CompoundNBT) compound).getDouble("blockPosX"), ((CompoundNBT) compound).getDouble("blockPosY"), ((CompoundNBT) compound).getDouble("blockPosZ")));
                });
            }
        }, () -> new StandChunkEffects(Null(), Null()));
    }

    public ChunkPos getChunk() {
        return chunk;
    }

    public World getWorld() {
        return world;
    }

    public void addBombPos(PlayerEntity player, BlockPos blockPos) {
        bombs.put(player.getUniqueID(), blockPos);
        onDataUpdated();
    }

    public void removeBombPos(PlayerEntity player) {
        bombs.remove(player.getUniqueID());
        onDataUpdated();
    }

    public Map<UUID, BlockPos> getBombs() {
        return bombs;
    }

    private void onDataUpdated() {
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == STAND_CHUNK_EFFECTS ? holder.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT() {
        return STAND_CHUNK_EFFECTS.getStorage().writeNBT(STAND_CHUNK_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null);
    }

    @Override
    public void deserializeNBT(INBT nbt) {
        STAND_CHUNK_EFFECTS.getStorage().readNBT(STAND_CHUNK_EFFECTS, holder.orElseThrow(() -> new IllegalArgumentException("LazyOptional is empty!")), null, nbt);
    }
}
