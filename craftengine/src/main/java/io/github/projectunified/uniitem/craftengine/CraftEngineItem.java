package io.github.projectunified.uniitem.craftengine;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.bukkit.item.BukkitItemDefinition;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class CraftEngineItem implements Item {
    private final @Nullable BukkitItemDefinition customItem;

    CraftEngineItem(String id) {
        Key key = Key.of(id);
        this.customItem = CraftEngineItems.byId(key);
    }

    CraftEngineItem(ItemStack itemStack) {
        Key key = CraftEngineItems.getCustomItemId(itemStack);
        this.customItem = key == null ? null : CraftEngineItems.byId(key);
    }

    @Override
    public boolean isValid() {
        return customItem != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (customItem == null) return null;
        return new ItemKey(CraftEngineItemProvider.TYPE, customItem.id().asString());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (customItem == null) return null;
        return customItem.buildBukkitItem();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        if (customItem == null) return null;
        return customItem.buildBukkitItem(player);
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (customItem == null) return false;
        Key key = CraftEngineItems.getCustomItemId(item);
        return Objects.equals(key, customItem.id());
    }
}
