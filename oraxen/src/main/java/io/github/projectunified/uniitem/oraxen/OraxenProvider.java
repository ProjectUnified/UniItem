package io.github.projectunified.uniitem.oraxen;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OraxenProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Oraxen") != null;
    }

    @Override
    public @NotNull String type() {
        return "oraxen";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return OraxenItems.getIdByItem(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        ItemBuilder itemBuilder = OraxenItems.getItemById(id);
        return itemBuilder != null ? itemBuilder.build() : null;
    }
}
