package io.github.projectunified.uniitem.slimefun;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SlimefunProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Slimefun") != null;
    }

    @Override
    public @NotNull String type() {
        return "slimefun";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return SlimefunItem.getOptionalByItem(item).map(SlimefunItem::getId).orElse(null);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return SlimefunItem.getOptionalById(id).map(SlimefunItem::getItem).map(ItemStack::clone).orElse(null);
    }
}
