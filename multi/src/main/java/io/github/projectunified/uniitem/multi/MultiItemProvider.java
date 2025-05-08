package io.github.projectunified.uniitem.multi;

import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MultiItemProvider implements ItemProvider {
    private final List<ItemProvider> providers = new ArrayList<>();
    private final Map<String, Optional<ItemProvider>> providerPerType = new ConcurrentHashMap<>();

    public MultiItemProvider addProvider(@NotNull ItemProvider provider) {
        providers.add(provider);
        return this;
    }

    public MultiItemProvider removeProvider(@NotNull ItemProvider provider) {
        providers.remove(provider);
        providerPerType.entrySet().removeIf(entry -> entry.getValue().isPresent() && entry.getValue().get() == provider);
        return this;
    }

    public List<ItemProvider> getProviders() {
        return Collections.unmodifiableList(providers);
    }

    private Optional<ItemProvider> getProvider(@NotNull ItemKey key) {
        return providerPerType.computeIfAbsent(key.type(), t -> {
            for (ItemProvider provider : providers) {
                if (provider.isValidKey(key)) {
                    return Optional.of(provider);
                }
            }
            return Optional.empty();
        });
    }

    @Override
    public @NotNull String[] type() {
        return providers.stream()
                .flatMap(provider -> Arrays.stream(provider.type()))
                .distinct()
                .toArray(String[]::new);
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        return getProvider(key).isPresent();
    }

    @Override
    public @Nullable ItemKey key(@NotNull ItemStack item) {
        for (ItemProvider provider : providers) {
            ItemKey key = provider.key(item);
            if (key != null) {
                providerPerType.computeIfAbsent(key.type(), t -> Optional.of(provider));
                return key;
            }
        }
        return null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull ItemKey key) {
        return getProvider(key).map(itemProvider -> itemProvider.item(key)).orElse(null);
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        return getProvider(key).map(itemProvider -> itemProvider.isSimilar(item, key)).orElse(false);
    }
}
