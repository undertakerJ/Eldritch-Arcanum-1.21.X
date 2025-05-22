package net.undertaker.eldritch_arcanum.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.undertaker.eldritch_arcanum.EldritchArcanum;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CEBBITE_TOOL = createTag("needs_cebbite_tool");
        public static final TagKey<Block> INCORRECT_FOR_CEBBITE_TOOL = createTag("incorrect_for_cebbite_tool");

        public static final TagKey<Block> NEEDS_ADAMANTITE_TOOL = createTag("needs_adamantite_tool");
        public static final TagKey<Block> INCORRECT_FOR_ADAMANTITE_TOOL = createTag("incorrect_for_adamantite_tool");

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, name));
        }
    }

    public static class Items {


        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(EldritchArcanum.MOD_ID, name));
        }
    }
}