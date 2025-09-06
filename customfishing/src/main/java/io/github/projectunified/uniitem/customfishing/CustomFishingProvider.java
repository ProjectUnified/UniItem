package io.github.projectunified.uniitem.customfishing;

import io.github.projectunified.uniitem.api.SimpleItemProvider;
import net.momirealms.customfishing.api.BukkitCustomFishingPlugin;
import net.momirealms.customfishing.api.mechanic.context.Context;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomFishingProvider implements SimpleItemProvider {
    private final BukkitCustomFishingPlugin api = BukkitCustomFishingPlugin.getInstance();

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("CustomFishing") != null;
    }

    @Override
    public @NotNull String type() {
        return "customfishing";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        return api.getItemManager().getCustomFishingItemID(item);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return api.getItemManager().buildInternal(Context.player(null), id);
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        return api.getItemManager().buildInternal(Context.player(player), id);
    }
}
