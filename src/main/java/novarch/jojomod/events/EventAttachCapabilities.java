package novarch.jojomod.events;

import novarch.jojomod.JojoBizarreSurvival;
import novarch.jojomod.capabilities.stand.Stand;
import novarch.jojomod.capabilities.timestop.Timestop;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JojoBizarreSurvival.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventAttachCapabilities {
    public static final ResourceLocation STAND_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "stand_capability");
    public static final ResourceLocation TIMESTOP_CAPABILITY = new ResourceLocation(JojoBizarreSurvival.MOD_ID, "timestop_capability");

    @SubscribeEvent
    public static void attachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof PlayerEntity)
            event.addCapability(STAND_CAPABILITY, new Stand((PlayerEntity) event.getObject()));
        event.addCapability(TIMESTOP_CAPABILITY, new Timestop(event.getObject()));
    }
}
