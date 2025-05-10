package io.github.projectunified.uniitem.headdatabase;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HeadDatabaseProvider implements SimpleItemProvider {
    private final HeadDatabaseAPI api = new HeadDatabaseAPI();

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("HeadDatabase") != null;
    }

    @Override
    public @NotNull String type() {
        return "headdatabase";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return api.getItemID(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return api.getItemHead(id);
    }
}
