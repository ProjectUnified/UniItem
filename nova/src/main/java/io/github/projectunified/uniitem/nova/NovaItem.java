package io.github.projectunified.uniitem.nova;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import xyz.xenondevs.nova.api.Nova;

import java.util.Objects;

class NovaItem implements Item {
    private final @Nullable xyz.xenondevs.nova.api.item.NovaItem novaItem;

    NovaItem(String id) {
        this.novaItem = Nova.getNova().getItemRegistry().getOrNull(id);
    }

    NovaItem(ItemStack itemStack) {
        this.novaItem = Nova.getNova().getItemRegistry().getOrNull(itemStack);
    }

    @Override
    public boolean isValid() {
        return novaItem != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (novaItem == null) return null;
        return new ItemKey(NovaItemProvider.TYPE, novaItem.getId().toString());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (novaItem == null) return null;
        return novaItem.createItemStack();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (this.novaItem == null) return false;
        xyz.xenondevs.nova.api.item.NovaItem novaItem = Nova.getNova().getItemRegistry().getOrNull(item);
        if (novaItem == null) return false;
        return Objects.equals(novaItem.getId(), this.novaItem.getId());
    }
}
