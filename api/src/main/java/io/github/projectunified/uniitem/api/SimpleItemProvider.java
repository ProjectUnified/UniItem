package io.github.projectunified.uniitem.api;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface SimpleItemProvider extends ItemProvider {
    @Nullable String id(@NotNull ItemStack item);

    @Nullable ItemStack item(@NotNull String id);

    @Override
    default @Nullable ItemKey key(@NotNull ItemStack item) {
        String id = id(item);
        if (id == null) return null;
        String type = type()[0];
        return new ItemKey(type, id);
    }

    @Override
    default @Nullable ItemStack item(@NotNull ItemKey key) {
        if (!isValidKey(key)) return null;
        String id = key.id();
        return item(id);
    }
}
