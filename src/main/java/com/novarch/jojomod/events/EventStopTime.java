package com.novarch.jojomod.events;

import com.novarch.jojomod.JojoBizarreSurvival;
import com.novarch.jojomod.entities.stands.theWorld.EntityTheWorld;
import com.novarch.jojomod.events.custom.StandEvent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
import java.util.HashMap;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventStopTime {
    public static HashMap<String, Double> entityPositions = new HashMap<>();
    public static HashMap<String, Float> entityLook = new HashMap<>();

    @SubscribeEvent
    public static void stopTime(StandEvent.StandTickEvent event) {
        PlayerEntity player = event.getPlayer();
        if(event.getStand() instanceof EntityTheWorld) {
            EntityTheWorld theWorld = (EntityTheWorld) event.getStand();
            if(theWorld.ability) {
                if (!theWorld.world.isRemote)
                    theWorld.world.getServer().getWorld(theWorld.dimension).getEntities()
                            .filter(entity -> entity != theWorld)
                            .filter(entity -> entity != player)
                            .filter(entity -> entity instanceof LivingEntity)
                            .forEach(entity -> {
                                UUID uuid = entity.getUniqueID();
                                if (theWorld.timestopTick == 2) {
                                    entityPositions.put(uuid + "posX", entity.getPosX());
                                    entityPositions.put(uuid + "posY", entity.getPosY());
                                    entityPositions.put(uuid + "posZ", entity.getPosZ());
                                    entityPositions.put(uuid + "motionX", entity.getMotion().getX());
                                    entityPositions.put(uuid + "motionY", entity.getMotion().getY());
                                    entityPositions.put(uuid + "motionZ", entity.getMotion().getZ());
                                    entityLook.put(uuid + "rotationYaw", entity.rotationYaw);
                                    entityLook.put(uuid + "rotationPitch", entity.rotationPitch);
                                } else {
                                    try {
                                        if(entityPositions.containsKey(uuid + "posX") &&
                                                entityPositions.containsKey(uuid + "posY") &&
                                                entityPositions.containsKey(uuid + "posZ") &&
                                                entityPositions.containsKey(uuid + "motionX") &&
                                                entityPositions.containsKey(uuid + "motionY") &&
                                                entityPositions.containsKey(uuid + "motionZ") &&
                                                entityPositions.containsKey(uuid + "rotationYaw") &&
                                                entityPositions.containsKey(uuid + "rotationPitch"))
                                        entity.setPosition(entityPositions.get(uuid + "posX"), entityPositions.get(uuid + "posY"), entityPositions.get(uuid + "posZ"));
                                        entity.setMotion(entityPositions.get(uuid + "motionX"), entityPositions.get(uuid + "motionY"), entityPositions.get(uuid + "motionZ"));
                                        entity.rotationYaw = entityLook.get(uuid + "rotationYaw");
                                        entity.rotationPitch = entityLook.get(uuid + "rotationPitch");
                                    } catch(NullPointerException exception) {
                                        player.sendMessage(new StringTextComponent("a"));
                                        exception.printStackTrace();
                                    }
                                }
                            });
            } else
                entityPositions.clear();
        }
    }
}
