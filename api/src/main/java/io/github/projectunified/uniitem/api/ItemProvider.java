package io.github.projectunified.uniitem.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface ItemProvider {
    @NotNull String[] type();

    @Nullable ItemKey key(@NotNull ItemStack item);

    @Nullable ItemStack item(@NotNull ItemKey key);

    default boolean isValidType(@NotNull String type) {
        for (String t : type()) {
            if (t.equalsIgnoreCase(type)) return true;
        }
        return false;
    }

    default boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        if (!isValidType(key.type())) return false;

        ItemKey itemKey = key(item);
        if (itemKey == null) return false;

        return Objects.equals(itemKey.id(), key.id());
    }
}
