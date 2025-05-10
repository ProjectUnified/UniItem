package io.github.projectunified.uniitem.itemedit;

import emanondev.itemedit.ItemEdit;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemEditProvider implements SimpleItemProvider {
    private static final String[] TYPES = {
            "itemedit"
    };

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("ItemEdit") != null;
    }

    @Override
    public @NotNull String[] type() {
        return TYPES;
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return ItemEdit.get().getServerStorage().getId(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return ItemEdit.get().getServerStorage().getItem(id);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        return  ItemEdit.get().getServerStorage().getItem(id, player);
    }
}
