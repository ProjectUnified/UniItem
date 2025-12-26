package io.github.projectunified.uniitem.itembridge;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ItemBridgeProvider implements ItemProvider {
    public static final String TYPE = "itembridge";
    public static final List<String> TYPES = Arrays.asList(
            TYPE,
            "item-bridge"
    );

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
    public List<String> availableTypes() {
        return TYPES;
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new ItemBridgeItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPES) ? new ItemBridgeItem(key.id()) : Item.INVALID;
    }
}
