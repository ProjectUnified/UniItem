package io.github.projectunified.uniitem.itembridge;

import com.jojodmo.itembridge.ItemBridge;
import com.jojodmo.itembridge.ItemBridgeKey;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class ItemBridgeItem implements Item {
    private final @Nullable ItemBridgeKey key;

    ItemBridgeItem(String id) {
        String[] split = id.split(":", 2);
        ItemBridgeKey key = split.length == 2 ? new ItemBridgeKey(split[0], split[1]) : null;
        this.key = key != null && ItemBridge.getItemStack(key) != null ? key : null;
    }

    ItemBridgeItem(ItemStack itemStack) {
        this.key = ItemBridge.getItemKey(itemStack);
    }

    @Override
    public boolean isValid() {
        return key != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (key == null) return null;
        return new ItemKey(ItemBridgeProvider.TYPE, key.toString());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (key == null) return null;
        return ItemBridge.getItemStack(key);
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        if (key == null) return null;
        return ItemBridge.getItemStack(key);
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (this.key == null) return false;
        ItemBridgeKey key = ItemBridge.getItemKey(item);
        if (key == null) return false;
        return Objects.equals(key.getNamespace(), this.key.getNamespace()) && Objects.equals(key.getItem(), this.key.getItem());
    }
}
