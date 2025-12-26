package io.github.projectunified.uniitem.mythicmobs;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class MythicItemProvider implements ItemProvider {
    public static final String TYPE = "mythicmobs";
    public static final List<String> TYPES = Arrays.asList(
            TYPE,
            "mm"
    );

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("MythicMobs") != null;
    }

    @Override
    public List<String> availableTypes() {
        return TYPES;
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new MythicItem(item);
    }

    @NotNull
    @Override
    public Item wrap(@NotNull ItemKey key) {
        return key.isType(TYPES) ? new MythicItem(key.id()) : Item.INVALID;
    }
}
