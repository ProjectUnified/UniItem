package io.github.projectunified.uniitem.itemedit;

import emanondev.itemedit.ItemEdit;
import emanondev.itemedit.storage.ServerStorage;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
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
        ServerStorage storage = ItemEdit.get().getServerStorage();
        for (String id : storage.getIds()) {
            ItemStack item2 = storage.getItem(id);
            if (item2 == null) {
                continue;
            }
            if (item2.isSimilar(item)) {
                return id;
            }
        }
        return null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return ItemEdit.get().getServerStorage().getItem(id);
    }
}
