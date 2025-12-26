package io.github.projectunified.uniitem.eco;

import com.willfp.eco.core.items.CustomItem;
import com.willfp.eco.core.items.Items;
import com.willfp.eco.core.items.TestableItem;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class EcoItem implements Item {
    private final @Nullable CustomItem customItem;

    EcoItem(ItemKey key) {
        TestableItem testableItem = Items.lookup(key.type() + ":" + key.id());
        this.customItem = testableItem instanceof CustomItem ? (CustomItem) testableItem : null;
    }

    EcoItem(ItemStack itemStack) {
        this.customItem = Items.getCustomItem(itemStack);
    }

    @Override
    public boolean isValid() {
        return customItem != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (customItem == null) return null;
        NamespacedKey key = customItem.getKey();
        return new ItemKey(key.getNamespace(), key.getKey());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (customItem == null) return null;
        return customItem.getItem();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (customItem == null) return false;
        return customItem.matches(item);
    }
}
