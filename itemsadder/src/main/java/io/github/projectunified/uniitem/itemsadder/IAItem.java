package io.github.projectunified.uniitem.itemsadder;

import dev.lone.itemsadder.api.CustomStack;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class IAItem implements Item {
    private final @Nullable CustomStack customStack;

    IAItem(String id) {
        this.customStack = CustomStack.getInstance(id);
    }

    IAItem(ItemStack itemStack) {
        this.customStack = CustomStack.byItemStack(itemStack);
    }

    @Override
    public boolean isValid() {
        return customStack != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (customStack == null) return null;
        return new ItemKey(ItemsAdderProvider.TYPE, customStack.getNamespacedID());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (customStack == null) return null;
        return customStack.getItemStack();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (customStack == null) return false;
        CustomStack customStack = CustomStack.byItemStack(item);
        if (customStack == null) return false;
        return this.customStack.matchNamespacedID(customStack);
    }
}
