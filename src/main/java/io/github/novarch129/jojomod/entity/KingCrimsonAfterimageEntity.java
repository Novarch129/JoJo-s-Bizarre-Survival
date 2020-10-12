package io.github.novarch129.jojomod.entity;

import io.github.novarch129.jojomod.capability.Stand;
import io.github.novarch129.jojomod.init.EntityInit;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.NetworkHooks;

@MethodsReturnNonnullByDefault
public class KingCrimsonAfterimageEntity extends CreatureEntity implements IEntityAdditionalSpawnData {
    private PlayerEntity master;

    public KingCrimsonAfterimageEntity(EntityType<? extends CreatureEntity> type, World world) {
        super(type, world);
    }

    public KingCrimsonAfterimageEntity(World world, PlayerEntity master) {
        super(EntityInit.KING_CRIMSON_AFTERIMAGE.get(), world);
        this.master = master;
        setPosition(master.getPosX(), master.getPosY(), master.getPosZ());
        rotationYaw = master.rotationYaw;
        rotationPitch = master.rotationPitch;
        rotationYawHead = master.rotationYawHead;
        setCustomName(master.getDisplayName());
        setItemStackToSlot(EquipmentSlotType.HEAD, master.getItemStackFromSlot(EquipmentSlotType.HEAD));
        setItemStackToSlot(EquipmentSlotType.CHEST, master.getItemStackFromSlot(EquipmentSlotType.CHEST));
        setItemStackToSlot(EquipmentSlotType.LEGS, master.getItemStackFromSlot(EquipmentSlotType.LEGS));
        setItemStackToSlot(EquipmentSlotType.FEET, master.getItemStackFromSlot(EquipmentSlotType.FEET));
        setItemStackToSlot(EquipmentSlotType.MAINHAND, master.getItemStackFromSlot(EquipmentSlotType.MAINHAND));
        setItemStackToSlot(EquipmentSlotType.OFFHAND, master.getItemStackFromSlot(EquipmentSlotType.OFFHAND));
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(3, new AvoidEntityGoal<>(this, PlayerEntity.class, 10, 0.6, 0.62));
        goalSelector.addGoal(3, new AvoidEntityGoal<>(this, MonsterEntity.class, 10, 0.6, 0.62));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
    }

    public PlayerEntity getMaster() {
        return master;
    }

    public void setMaster(PlayerEntity master) {
        this.master = master;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        buffer.writeInt(master == null ? -1 : master.getEntityId());
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
        Entity entity = world.getEntityByID(additionalData.readInt());
        if (entity instanceof PlayerEntity)
            master = (PlayerEntity) entity;
    }

    @Override
    public void tick() {
        super.tick();
        if (master == null) {
            remove();
            return;
        }
        Stand.getLazyOptional(master).ifPresent(stand -> {
            if (!stand.getStandOn() || !stand.getAbility() || !(stand.getTimeLeft() > 800) || stand.getCooldown() != 0 || stand.getInvulnerableTicks() > 0)
                remove();
        });
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
