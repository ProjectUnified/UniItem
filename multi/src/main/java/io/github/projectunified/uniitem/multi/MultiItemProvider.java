package io.github.projectunified.uniitem.multi;

import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiItemProvider implements ItemProvider {
    private final List<ItemProvider> providers = new ArrayList<>();

    public MultiItemProvider addProvider(@NotNull ItemProvider provider) {
        providers.add(provider);
        return this;
    }

    public MultiItemProvider removeProvider(@NotNull ItemProvider provider) {
        providers.remove(provider);
        return this;
    }

    public List<ItemProvider> getProviders() {
        return Collections.unmodifiableList(providers);
    }

    @Override
    public @NotNull String[] type() {
        return new String[]{
                "multi"
        };
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        for (ItemProvider provider : providers) {
            if (provider.isValidKey(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable ItemKey key(@NotNull ItemStack item) {
        for (ItemProvider provider : providers) {
            ItemKey key = provider.key(item);
            if (key != null) {
                return key;
            }
        }
        return null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull ItemKey key) {
        for (ItemProvider provider : providers) {
            ItemStack item = provider.item(key);
            if (item != null) {
                return item;
            }
        }
        return null;
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        for (ItemProvider provider : providers) {
            if (provider.isSimilar(item, key)) {
                return true;
            }
        }
        return false;
    }
}
