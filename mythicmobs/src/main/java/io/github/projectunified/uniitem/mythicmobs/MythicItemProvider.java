package io.github.projectunified.uniitem.mythicmobs;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MythicItemProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("MythicMobs") != null;
    }

    @NotNull
    @Override
    public String type() {
        return "mythicmobs";
    }

    @Nullable
    @Override
    public String id(@NotNull ItemStack item) {
        return MythicBukkit.inst().getItemManager().getMythicTypeFromItem(item);
    }

    @Nullable
    @Override
    public ItemStack item(@NotNull String id) {
        return MythicBukkit.inst().getItemManager()
                .getItem(id)
                .map(item -> item.generateItemStack(1))
                .map(BukkitAdapter::adapt)
                .orElse(null);
    }
}
