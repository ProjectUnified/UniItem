package io.github.projectunified.uniitem.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SimpleItemProvider extends ItemProvider {
    @NotNull String type();

    @Nullable String id(@NotNull ItemStack item);

    @Nullable ItemStack item(@NotNull String id);

    default @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        return item(id);
    }

    default boolean isValidKey(@NotNull ItemKey key) {
        return key.type().equalsIgnoreCase(type());
    }

    @Override
    default @Nullable ItemKey key(@NotNull ItemStack item) {
        String id = id(item);
        if (id == null) return null;
        String type = type();
        return new ItemKey(type, id);
    }

    @Override
    default @Nullable ItemStack item(@NotNull ItemKey key) {
        if (!isValidKey(key)) return null;
        String id = key.id();
        return item(id);
    }

    @Override
    default @Nullable ItemStack item(@NotNull ItemKey key, @NotNull Player player) {
        if (!isValidKey(key)) return null;
        String id = key.id();
        return item(id, player);
    }
}
