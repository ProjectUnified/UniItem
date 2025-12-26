package io.github.projectunified.uniitem.executableitems;

import com.ssomar.score.api.executableitems.ExecutableItemsAPI;
import com.ssomar.score.api.executableitems.config.ExecutableItemInterface;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.Optional;

class ExecutableItem implements Item {
    private final @Nullable ExecutableItemInterface customItem;

    ExecutableItem(String id) {
        this.customItem = ExecutableItemsAPI.getExecutableItemsManager().getExecutableItem(id).orElse(null);
    }

    ExecutableItem(ItemStack itemStack) {
        this.customItem = ExecutableItemsAPI.getExecutableItemsManager().getExecutableItem(itemStack).orElse(null);
    }

    @Override
    public boolean isValid() {
        return customItem != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (customItem == null) return null;
        return new ItemKey(ExecutableItemsProvider.TYPE, customItem.getId());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (customItem == null) return null;
        return customItem.buildItem(1, Optional.empty());
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        if (customItem == null) return null;
        return customItem.buildItem(1, Optional.of(player));
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (this.customItem == null) return false;
        ExecutableItemInterface customItem = ExecutableItemsAPI.getExecutableItemsManager().getExecutableItem(item).orElse(null);
        if (customItem == null) return false;
        return Objects.equals(customItem.getId(), this.customItem.getId());
    }
}
