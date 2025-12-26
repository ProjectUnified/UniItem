package io.github.projectunified.uniitem.nexo;

import com.nexomc.nexo.api.NexoItems;
import com.nexomc.nexo.items.ItemBuilder;
import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class NexoItem implements Item {
    private final @Nullable String id;

    NexoItem(@NotNull String id) {
        this.id = NexoItems.exists(id) ? id : null;
    }

    NexoItem(@NotNull ItemStack itemStack) {
        this.id = NexoItems.idFromItem(itemStack);
    }

    @Override
    public boolean isValid() {
        return id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(NexoProvider.TYPE, id);
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (id == null) return null;
        ItemBuilder builder = NexoItems.itemFromId(id);
        return builder != null ? builder.build() : null;
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(NexoItems.idFromItem(item), id);
    }
}
