package io.github.projectunified.uniitem.nova;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class NovaItemProvider implements ItemProvider {
    public static final String TYPE = "nova";

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("Nova") != null;
    }

    @Override
    public List<String> availableTypes() {
        return Collections.singletonList("nova");
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new NovaItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPE) ? new NovaItem(key.id()) : Item.INVALID;
    }
}
