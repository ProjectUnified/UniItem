package io.github.projectunified.uniitem.itemedit;

import emanondev.itemedit.ItemEdit;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class ItemEditItem implements Item {
    private final @Nullable String id;

    ItemEditItem(@NotNull String id) {
        this.id = ItemEdit.get().getServerStorage().getItem(id) != null ? id : null;
    }

    ItemEditItem(@NotNull ItemStack itemStack) {
        this.id = ItemEdit.get().getServerStorage().getId(itemStack);
    }

    @Override
    public boolean isValid() {
        return this.id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(ItemEditProvider.TYPE, id);
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (id == null) return null;
        return ItemEdit.get().getServerStorage().getItem(id);
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        if (id == null) return null;
        return ItemEdit.get().getServerStorage().getItem(id, player);
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(ItemEdit.get().getServerStorage().getId(item), id);
    }
}
