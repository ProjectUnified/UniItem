package io.github.projectunified.uniitem.itembridge;

import com.jojodmo.itembridge.ItemBridge;
import com.jojodmo.itembridge.ItemBridgeKey;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemBridgeProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        if (Bukkit.getPluginManager().getPlugin("ItemBridge") == null) {
            return false;
        }

        try {
            Class.forName("com.jojodmo.itembridge.ItemBridge");
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    @Override
    public @NotNull String type() {
        return "itembridge";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        ItemBridgeKey key = ItemBridge.getItemKey(item);
        return key != null ? key.toString() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return ItemBridge.getItemStack(id);
    }
}
