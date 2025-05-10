package io.github.projectunified.uniitem.nova;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.xenondevs.nova.api.Nova;
import xyz.xenondevs.nova.api.item.NovaItem;

public class NovaItemProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Nova") != null;
    }

    @Override
    public @NotNull String type() {
        return "nova";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        NovaItem novaItem = Nova.getNova().getItemRegistry().getOrNull(item);
        return novaItem != null ? novaItem.getId().toString() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        NovaItem novaItem = Nova.getNova().getItemRegistry().getOrNull(id);
        return novaItem != null ? novaItem.createItemStack() : null;
    }
}
