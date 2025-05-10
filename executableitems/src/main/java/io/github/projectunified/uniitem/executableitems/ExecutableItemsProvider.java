package io.github.projectunified.uniitem.executableitems;

import com.ssomar.score.api.executableitems.ExecutableItemsAPI;
import com.ssomar.score.sobject.SObjectInterface;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ExecutableItemsProvider implements SimpleItemProvider {
    private static final String[] TYPES = {
            "executableitems"
    };

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("ExecutableItems") != null;
    }

    @Override
    public @NotNull String[] type() {
        return TYPES;
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return ExecutableItemsAPI.getExecutableItemsManager()
                .getExecutableItem(item)
                .map(SObjectInterface::getId)
                .orElse(null);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return ExecutableItemsAPI.getExecutableItemsManager()
                .getExecutableItem(id)
                .map(i -> i.buildItem(1, Optional.empty()))
                .orElse(null);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        return ExecutableItemsAPI.getExecutableItemsManager()
                .getExecutableItem(id)
                .map(i -> i.buildItem(1, Optional.of(player)))
                .orElse(null);
    }
}
