package io.github.projectunified.uniitem.multi;

import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class MultiItemProvider implements ItemProvider {
    private final List<ItemProvider> providers = new ArrayList<>();
    private final Map<String, ItemProvider> providerMap = new HashMap<>();
    private final Map<String, String> aliases = new HashMap<>();

    private static String normalize(String type) {
        return type.toLowerCase(Locale.ROOT);
    }

    public void addProvider(SimpleItemProvider provider, String... alias) {
        providers.add(provider);
        providerMap.put(normalize(provider.type()), provider);
        for (String a : alias) {
            aliases.put(normalize(a), normalize(provider.type()));
        }
    }

    public void addProvider(ItemProvider provider, String... type) {
        providers.add(provider);
        for (String t : type) {
            providerMap.put(normalize(t), provider);
        }
    }

    private String getType(@NotNull ItemKey key) {
        String type = key.type();
        type = aliases.getOrDefault(normalize(type), type);
        return normalize(type);
    }

    public Collection<String> getPossibleTypes() {
        Set<String> types = new HashSet<>();
        types.addAll(providerMap.keySet());
        types.addAll(aliases.keySet());
        return types;
    }

    public Collection<ItemProvider> getProviders() {
        return Collections.unmodifiableCollection(providers);
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        String type = getType(key);
        ItemProvider provider = providerMap.get(type);
        return provider != null && provider.isValidKey(new ItemKey(type, key.id()));
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
        String type = getType(key);
        ItemProvider provider = providerMap.get(type);
        return provider != null ? provider.item(new ItemKey(type, key.id())) : null;
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        String type = getType(key);
        ItemProvider provider = providerMap.get(type);
        return provider != null && provider.isSimilar(item, new ItemKey(type, key.id()));
    }
}
