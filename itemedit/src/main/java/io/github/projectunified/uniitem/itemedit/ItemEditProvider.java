package io.github.projectunified.uniitem.itemedit;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class ItemEditProvider implements ItemProvider {
    public static final String TYPE = "itemedit";

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("ItemEdit") != null;
    }

    @Override
    public List<String> availableTypes() {
        return Collections.singletonList(TYPE);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new ItemEditItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPE) ? new ItemEditItem(key.id()) : Item.INVALID;
    }
}
