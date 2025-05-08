package io.github.projectunified.uniitem.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface ItemProvider {
    @NotNull String[] type();

    @Nullable ItemKey key(@NotNull ItemStack item);

    @Nullable ItemStack item(@NotNull ItemKey key);

    default boolean isValidKey(@NotNull ItemKey key) {
        for (String type : type()) {
            if (key.type().equalsIgnoreCase(type)) {
                return true;
            }
        }
        return false;
    }

    default boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        if (!isValidKey(key)) return false;

        ItemKey itemKey = key(item);
        if (itemKey == null) return false;

        return Objects.equals(itemKey.id(), key.id());
    }
}
