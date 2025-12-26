package io.github.projectunified.uniitem.oraxen;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.th0rgal.oraxen.api.OraxenItems;
import io.th0rgal.oraxen.items.ItemBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class OraxenItem implements Item {
    private final @Nullable String id;

    OraxenItem(@NotNull String id) {
        this.id = OraxenItems.exists(id) ? id : null;
    }

    OraxenItem(@NotNull ItemStack itemStack) {
        this.id = OraxenItems.getIdByItem(itemStack);
    }

    @Override
    public boolean isValid() {
        return id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(OraxenProvider.TYPE, id);
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (id == null) return null;
        ItemBuilder builder = OraxenItems.getItemById(id);
        return builder != null ? builder.build() : null;
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(OraxenItems.getIdByItem(item), id);
    }
}
