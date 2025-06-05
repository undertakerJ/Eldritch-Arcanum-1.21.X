package net.undertaker.eldritch_arcanum.essence;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.undertaker.eldritch_arcanum.util.ModRegistries;

import java.util.List;

public class Essence {
    private final ResourceLocation id; // Уникальный идентификатор
    private final int color;          // Цвет в RGB (0xRRGGBB)

    public Essence(ResourceLocation id, int color) {
        this.id = id;
        this.color = color;
    }

    // Геттеры
    public ResourceLocation getId() { return id; }
    public int getColor() { return color; }

    // Локализованное имя
    public Component getDisplayName() {
        return Component.translatable("essence." + id.getNamespace() + "." + id.getPath());
    }

    // Сериализация в NBT
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putString("Id", id.toString());
        return tag;
    }

    public static Essence deserializeNBT(CompoundTag tag) {
        ResourceLocation id = ResourceLocation.parse(tag.getString("Id"));
        return ModRegistries.ESSENCE_REGISTRY.get(id);
    }
}
