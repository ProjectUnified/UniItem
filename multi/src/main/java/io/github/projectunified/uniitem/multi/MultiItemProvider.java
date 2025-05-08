package io.github.projectunified.uniitem.multi;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class MultiItemProvider implements ItemProvider {
    private final List<ItemProvider> providers = new ArrayList<>();
    private final LoadingCache<String, Optional<ItemProvider>> cache = CacheBuilder.newBuilder()
            .build(new CacheLoader<String, Optional<ItemProvider>>() {
                @Override
                public @NotNull Optional<ItemProvider> load(@NotNull String type) {
                    for (ItemProvider provider : providers) {
                        if (provider.isValidType(type)) {
                            return Optional.of(provider);
                        }
                    }
                    return Optional.empty();
                }
            });

    public MultiItemProvider addProvider(@NotNull ItemProvider provider) {
        providers.add(provider);
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
    public boolean isValidType(@NotNull String type) {
        try {
            return cache.get(type).isPresent();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
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
        try {
            Optional<ItemProvider> provider = cache.get(key.type());
            if (provider.isPresent()) {
                return provider.get().item(key);
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        try {
            Optional<ItemProvider> provider = cache.get(key.type());
            if (provider.isPresent()) {
                return provider.get().isSimilar(item, key);
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
