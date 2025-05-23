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

    public Collection<String> getTypes() {
        return Collections.unmodifiableCollection(providerMap.keySet());
    }

    public Collection<String> getAliases() {
        return Collections.unmodifiableCollection(aliases.keySet());
    }

    public Collection<ItemProvider> getProviders() {
        return Collections.unmodifiableCollection(providers);
    }

    public @Nullable String getType(String alias) {
        return aliases.get(normalize(alias));
    }

    public @Nullable ItemProvider getProvider(String type) {
        return providerMap.get(normalize(type));
    }

    private ItemKey normalizeKey(@NotNull ItemKey key) {
        String type = key.type();
        type = aliases.getOrDefault(normalize(type), type);
        return new ItemKey(normalize(type), key.id());
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        key = normalizeKey(key);
        ItemProvider provider = providerMap.get(key.type());
        return provider != null && provider.isValidKey(key);
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
        key = normalizeKey(key);
        ItemProvider provider = providerMap.get(key.type());
        return provider != null ? provider.item(key) : null;
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item, @NotNull ItemKey key) {
        key = normalizeKey(key);
        ItemProvider provider = providerMap.get(key.type());
        return provider != null && provider.isSimilar(item, key);
    }
}
