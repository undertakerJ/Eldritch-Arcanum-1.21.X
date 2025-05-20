package net.undertaker.eldritch_arcanum.items;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EldritchArcanum.MOD_ID);

    public static final DeferredItem<Item> RAW_CEBBITE_ORE =
            ITEMS.register("raw_cebbite_ore", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> CEBBITE_INGOT =
            ITEMS.register("cebbite_ingot", () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
