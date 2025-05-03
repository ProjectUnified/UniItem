package io.github.projectunified.uniitem.multi;

import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MultiItemProvider implements ItemProvider {
    private final List<ItemProvider> providers = new ArrayList<>();
    private final Map<String, ItemProvider> providerPerType = new HashMap<>();

    public MultiItemProvider addProvider(@NotNull ItemProvider provider) {
        String[] types = provider.type();
        for (String type : types) {
            if (providerPerType.containsKey(type.toLowerCase(Locale.ROOT))) {
                throw new IllegalArgumentException("Provider already registered for type: " + type);
            }
        }
        for (String type : types) {
            providerPerType.put(type.toLowerCase(Locale.ROOT), provider);
        }
        providers.add(provider);
        return this;
    }

    public MultiItemProvider removeProvider(@NotNull ItemProvider provider) {
        providers.remove(provider);
        String[] types = provider.type();
        for (String type : types) {
            providerPerType.remove(type.toLowerCase(Locale.ROOT));
        }
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
    public @Nullable String id(@NotNull ItemStack item) {
        for (ItemProvider provider : providers) {
            String type = provider.type()[0];
            String id = provider.id(item);
            if (id != null) {
                return type + ":" + id;
            }
        }
        return null;
    }

    private Optional<String[]> getTypeAndId(String id) {
        String[] parts = id.split(":", 2);
        if (parts.length == 2) {
            return Optional.of(parts);
        }
        return Optional.empty();
    }

    public @Nullable ItemStack item(@NotNull String type, @NotNull String id) {
        ItemProvider provider = providerPerType.get(type.toLowerCase(Locale.ROOT));
        return provider != null ? provider.item(id) : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        return getTypeAndId(id)
                .map(parts -> {
                    String type = parts[0];
                    String itemId = parts[1];
                    return item(type, itemId);
                })
                .orElse(null);
    }

    public boolean isSimilar(@NotNull ItemStack item, @NotNull String type, @NotNull String id) {
        ItemProvider provider = providerPerType.get(type.toLowerCase(Locale.ROOT));
        return provider != null && provider.isSimilar(item, id);
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull String id) {
        return getTypeAndId(id)
                .map(parts -> {
                    String type = parts[0];
                    String itemId = parts[1];
                    return isSimilar(item, type, itemId);
                })
                .orElse(false);
    }
}
