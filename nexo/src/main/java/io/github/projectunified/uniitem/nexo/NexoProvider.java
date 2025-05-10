package io.github.projectunified.uniitem.nexo;

import com.nexomc.nexo.api.NexoItems;
import com.nexomc.nexo.items.ItemBuilder;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class NexoProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Nexo") != null;
    }

    @Override
    public @NotNull String type() {
        return "nexo";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return NexoItems.idFromItem(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        ItemBuilder builder = NexoItems.itemFromId(id);
        return builder != null ? builder.build() : null;
    }
}
