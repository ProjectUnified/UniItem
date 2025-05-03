package io.github.projectunified.itembridge.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public interface ItemProvider {
    @NotNull String[] type();

    @Nullable String id(@NotNull ItemStack item);

    @Nullable ItemStack item(@NotNull String id);

    default boolean isSimilar(@NotNull ItemStack item, @NotNull String id) {
        return Objects.equals(id, id(item));
    }
}
