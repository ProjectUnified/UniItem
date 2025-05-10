package io.github.projectunified.uniitem.eco;

import com.willfp.eco.core.items.CustomItem;
import com.willfp.eco.core.items.Items;
import com.willfp.eco.core.items.TestableItem;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EcoItemProvider implements ItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("eco") != null;
    }

    public @NotNull String[] type() {
        return Items.getCustomItems().stream().map(CustomItem::getKey).map(NamespacedKey::getNamespace).toArray(String[]::new);
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        return Items.lookup(key.type() + ":" + key.id()) instanceof CustomItem;
    }

    @Override
    public @Nullable ItemKey key(@NotNull ItemStack item) {
        CustomItem customItem = Items.getCustomItem(item);
        if (customItem == null) {
            return null;
        }
        NamespacedKey key = customItem.getKey();
        return new ItemKey(key.getNamespace(), key.getKey());
    }

    @Override
    public @Nullable ItemStack item(@NotNull ItemKey key) {
        TestableItem customItem = Items.lookup(key.type() + ":" + key.id());
        if (!(customItem instanceof CustomItem)) {
            return null;
        }
        return customItem.getItem();
    }
}
