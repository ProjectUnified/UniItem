package io.github.projectunified.uniitem.craftengine;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CraftEngineItemProvider implements SimpleItemProvider {
    private final CraftEngineUtil craftEngineUtil = new CraftEngineUtil();

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("CraftEngine") != null;
    }

    @Override
    public @NotNull String type() {
        return "craftengine";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return craftEngineUtil.id(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return craftEngineUtil.item(id);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        return craftEngineUtil.item(id, player);
    }
}
