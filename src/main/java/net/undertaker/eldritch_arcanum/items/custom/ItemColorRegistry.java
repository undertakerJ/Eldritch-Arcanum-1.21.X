package net.undertaker.eldritch_arcanum.items.custom;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.undertaker.eldritch_arcanum.EldritchArcanum;
import net.undertaker.eldritch_arcanum.essence.Essence;
import net.undertaker.eldritch_arcanum.essence.ModEssences;
import net.undertaker.eldritch_arcanum.items.ModItems;
@EventBusSubscriber(modid = EldritchArcanum.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ItemColorRegistry {
    @SubscribeEvent
    public static void onItemColorInit(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex != 0) return 0xFFFFFFFF;

            Essence ess = ((EssenceCrystalItem) stack.getItem())
                    .getBoundEssence(stack)
                    .orElse(null);
            if (ess == null) {
                return 0xFFFFFFFF;
            } else {
                return 0xFF000000 | ess.getColor();
            }
        }, ModItems.ESSENCE_CRYSTAL.get());
    }

}
