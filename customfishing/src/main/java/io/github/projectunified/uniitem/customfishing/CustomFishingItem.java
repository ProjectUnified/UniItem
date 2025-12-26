package io.github.projectunified.uniitem.customfishing;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import net.momirealms.customfishing.api.BukkitCustomFishingPlugin;
import net.momirealms.customfishing.api.mechanic.context.Context;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class CustomFishingItem implements Item {
    private static final BukkitCustomFishingPlugin api = BukkitCustomFishingPlugin.getInstance();

    private final @Nullable String id;

    CustomFishingItem(@NotNull String id) {
        this.id = api.getItemManager().getItemIDs().contains(id) ? id : null;
    }

    CustomFishingItem(ItemStack item) {
        this.id = api.getItemManager().getCustomFishingItemID(item);
    }

    @Override
    public boolean isValid() {
        return id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(CustomFishingProvider.TYPE, id);
    }

    private @Nullable ItemStack bukkitItem(@NotNull Context<Player> context) {
        if (id == null) return null;
        return api.getItemManager().buildInternal(context, id);
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        return bukkitItem(Context.player(null));
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem(Context.player(player));
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(api.getItemManager().getCustomFishingItemID(item), id);
    }
}
