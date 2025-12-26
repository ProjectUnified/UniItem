package io.github.projectunified.uniitem.slimefun;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class SFItem implements Item {
    private final @Nullable SlimefunItem slimefunItem;

    SFItem(ItemStack itemStack) {
        this.slimefunItem = SlimefunItem.getByItem(itemStack);
    }

    SFItem(String id) {
        this.slimefunItem = SlimefunItem.getById(id);
    }

    @Override
    public boolean isValid() {
        return slimefunItem != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (slimefunItem == null) return null;
        return new ItemKey(SlimefunProvider.TYPE, slimefunItem.getId());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (slimefunItem == null) return null;
        return slimefunItem.getItem().clone();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (slimefunItem == null) return false;
        return slimefunItem.isItem(item);
    }
}
