package io.github.projectunified.uniitem.craftengine;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import net.momirealms.craftengine.bukkit.api.BukkitAdaptors;
import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.item.CustomItem;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftEngineItemProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("CraftEngine") != null;
    }

    @Override
    public @NotNull String type() {
        return "craftengine";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        Key key = CraftEngineItems.getCustomItemId(item);
        return key != null ? key.asString() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        Key key = Key.of(id);
        CustomItem<ItemStack> customItem = CraftEngineItems.byId(key);
        return customItem != null ? customItem.buildItemStack() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        Key key = Key.of(id);
        CustomItem<ItemStack> customItem = CraftEngineItems.byId(key);
        return customItem != null ? customItem.buildItemStack(BukkitAdaptors.adapt(player)) : null;
    }
}
