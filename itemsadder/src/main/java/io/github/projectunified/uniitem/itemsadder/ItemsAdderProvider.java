package io.github.projectunified.uniitem.itemsadder;

import dev.lone.itemsadder.api.CustomStack;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemsAdderProvider implements ItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("ItemsAdder") != null;
    }

    @Override
    public @NotNull String[] type() {
        return new String[]{
                "itemsadder",
                "ia"
        };
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        CustomStack customStack = CustomStack.byItemStack(item);
        return customStack != null ? customStack.getNamespacedID() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        CustomStack customStack = CustomStack.getInstance(id);
        return customStack != null ? customStack.getItemStack() : null;
    }
}
