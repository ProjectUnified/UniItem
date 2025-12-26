package io.github.projectunified.uniitem.mythicmobs;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class MythicItem implements Item {
    private final @Nullable String id;

    MythicItem(@NotNull String id) {
        this.id = MythicBukkit.inst().getItemManager().getItem(id).isPresent() ? id : null;
    }

    MythicItem(@NotNull ItemStack itemStack) {
        this.id = MythicBukkit.inst().getItemManager().getMythicTypeFromItem(itemStack);
    }

    @Override
    public boolean isValid() {
        return id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(MythicItemProvider.TYPE, id);
    }

    @Nullable
    @Override
    public ItemStack bukkitItem() {
        if (id == null) return null;
        return MythicBukkit.inst().getItemManager().getItem(id)
                .map(item -> item.generateItemStack(1))
                .map(BukkitAdapter::adapt)
                .orElse(null);
    }

    @Nullable
    @Override
    public ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(MythicBukkit.inst().getItemManager().getMythicTypeFromItem(item), id);
    }
}
