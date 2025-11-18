package io.github.projectunified.uniitem.craftengine;

import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.item.CustomItem;
import net.momirealms.craftengine.core.item.ItemBuildContext;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This is here because I keep getting NoClassDefFoundError on the Player class.
 * So it's here to rearrange the order of class loading.
 */
class CraftEngineUtil {
    @Nullable String id(@NotNull ItemStack item) {
        Key key = CraftEngineItems.getCustomItemId(item);
        return key != null ? key.asString() : null;
    }

    @Nullable ItemStack item(@NotNull String id) {
        Key key = Key.of(id);
        CustomItem<ItemStack> customItem = CraftEngineItems.byId(key);
        return customItem != null ? customItem.buildItemStack() : null;
    }

    @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        Key key = Key.of(id);
        CustomItem<ItemStack> customItem = CraftEngineItems.byId(key);
        if (customItem == null) return null;
        net.momirealms.craftengine.core.entity.player.Player customPlayer = net.momirealms.craftengine.bukkit.api.BukkitAdaptors.adapt(player);
        ItemBuildContext context = ItemBuildContext.of(customPlayer);
        return customItem.buildItemStack(context);
    }
}
