package io.github.projectunified.uniitem.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Locale;

public interface ItemProvider {
    List<String> availableTypes();

    @NotNull Item wrap(@NotNull ItemStack item);

    @NotNull Item wrap(@NotNull ItemKey key);

    default boolean isValidKey(@NotNull ItemKey key) {
        return wrap(key).isValid();
    }

    default @Nullable ItemKey key(@NotNull ItemStack item) {
        return wrap(item).key();
    }

    default @Nullable ItemStack item(@NotNull ItemKey key) {
        return wrap(key).bukkitItem();
    }

    default @Nullable ItemStack item(@NotNull ItemKey key, @NotNull Player player) {
        return wrap(key).bukkitItem(player);
    }

    default @Nullable ItemStack tryItem(@NotNull ItemKey key, @Nullable Player player) {
        return wrap(key).tryBukkitItem(player);
    }

    default boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        return wrap(key).isSimilar(item);
    }

    default @NotNull String normalize(@NotNull String type) {
        List<String> types = availableTypes();
        return types.contains(type.toLowerCase(Locale.ROOT)) ? types.getFirst() : type;
    }

    default @NotNull ItemKey normalize(@NotNull ItemKey key) {
        return new ItemKey(normalize(key.type()), key.id());
    }
}
