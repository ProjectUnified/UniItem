package io.github.projectunified.uniitem.oraxen;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class OraxenProvider implements ItemProvider {
    public static final String TYPE = "oraxen";
    public static final List<String> TYPES = Arrays.asList(
            TYPE,
            "orx"
    );

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Oraxen") != null;
    }

    @Override
    public List<String> availableTypes() {
        return TYPES;
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new OraxenItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPES) ? new OraxenItem(key.id()) : Item.INVALID;
    }
}
