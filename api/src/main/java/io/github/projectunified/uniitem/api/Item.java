package io.github.projectunified.uniitem.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface Item {
    Item INVALID = new Item() {
        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public @Nullable ItemKey key() {
            return null;
        }

        @Override
        public @Nullable ItemStack bukkitItem() {
            return null;
        }

        @Override
        public @Nullable ItemStack bukkitItem(@NotNull Player player) {
            return null;
        }

        @Override
        public boolean isSimilar(@NotNull ItemStack item) {
            return false;
        }
    };

    boolean isValid();

    @Nullable ItemKey key();

    @Nullable ItemStack bukkitItem();

    @Nullable ItemStack bukkitItem(@NotNull Player player);

    default @Nullable ItemStack tryBukkitItem(@Nullable Player player) {
        return player == null ? bukkitItem() : bukkitItem(player);
    }

    boolean isSimilar(@NotNull ItemStack item);
}
