package io.github.projectunified.uniitem.itemsadder;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class ItemsAdderProvider implements ItemProvider {
    public static final String TYPE = "itemsadder";
    public static final List<String> TYPES = Arrays.asList(
            TYPE,
            "ia"
    );

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("ItemsAdder") != null;
    }

    @Override
    public List<String> availableTypes() {
        return TYPES;
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new IAItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPES) ? new IAItem(key.id()) : Item.INVALID;
    }
}
