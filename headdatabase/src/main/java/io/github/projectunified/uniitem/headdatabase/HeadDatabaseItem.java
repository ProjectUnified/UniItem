package io.github.projectunified.uniitem.headdatabase;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import me.arcaniax.hdb.api.HeadDatabaseAPI;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class HeadDatabaseItem implements Item {
    private static final HeadDatabaseAPI api = new HeadDatabaseAPI();
    private final @Nullable String id;

    public HeadDatabaseItem(@NotNull String id) {
        this.id = api.isHead(id) ? id : null;
    }

    public HeadDatabaseItem(ItemStack itemStack) {
        this.id = api.getItemID(itemStack);
    }

    public String getBase64() {
        if (id == null) return null;
        return api.getBase64(id);
    }

    @Override
    public boolean isValid() {
        return id != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (id == null) return null;
        return new ItemKey(HeadDatabaseProvider.TYPE, id);
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (id == null) return null;
        return api.getItemHead(id);
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        return bukkitItem();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (id == null) return false;
        return Objects.equals(api.getItemID(item), id);
    }
}
