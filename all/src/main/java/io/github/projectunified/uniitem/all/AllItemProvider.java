package io.github.projectunified.uniitem.all;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import io.github.projectunified.uniitem.craftengine.CraftEngineItemProvider;
import io.github.projectunified.uniitem.customfishing.CustomFishingProvider;
import io.github.projectunified.uniitem.eco.EcoItemProvider;
import io.github.projectunified.uniitem.executableitems.ExecutableItemsProvider;
import io.github.projectunified.uniitem.headdatabase.HeadDatabaseProvider;
import io.github.projectunified.uniitem.itembridge.ItemBridgeProvider;
import io.github.projectunified.uniitem.itemedit.ItemEditProvider;
import io.github.projectunified.uniitem.itemsadder.ItemsAdderProvider;
import io.github.projectunified.uniitem.mmoitems.MMOItemsProvider;
import io.github.projectunified.uniitem.mythicmobs.MythicItemProvider;
import io.github.projectunified.uniitem.nexo.NexoProvider;
import io.github.projectunified.uniitem.nova.NovaItemProvider;
import io.github.projectunified.uniitem.oraxen.OraxenProvider;
import io.github.projectunified.uniitem.slimefun.SlimefunProvider;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AllItemProvider implements ItemProvider {
    private final AtomicReference<List<ItemProvider>> providers = new AtomicReference<>();

    private List<ItemProvider> constructProviders() {
        List<ItemProvider> providers = new ArrayList<>();

        // Dependent
        if (ItemEditProvider.isAvailable()) {
            providers.add(new ItemEditProvider());
        }
        if (CraftEngineItemProvider.isAvailable()) {
            providers.add(new CraftEngineItemProvider());
        }
        if (CustomFishingProvider.isAvailable()) {
            providers.add(new CustomFishingProvider());
        }
        if (EcoItemProvider.isAvailable()) {
            providers.add(new EcoItemProvider());
        }
        if (ExecutableItemsProvider.isAvailable()) {
            providers.add(new ExecutableItemsProvider());
        }
        if (MMOItemsProvider.isAvailable()) {
            providers.add(new MMOItemsProvider());
        }
        if (MythicItemProvider.isAvailable()) {
            providers.add(new MythicItemProvider());
        }

        // Base
        if (ItemsAdderProvider.isAvailable()) {
            providers.add(new ItemsAdderProvider());
        }
        if (OraxenProvider.isAvailable()) {
            providers.add(new OraxenProvider());
        }
        if (NexoProvider.isAvailable()) {
            providers.add(new NexoProvider());
        }
        if (HeadDatabaseProvider.isAvailable()) {
            providers.add(new HeadDatabaseProvider());
        }
        if (NovaItemProvider.isAvailable()) {
            providers.add(new NovaItemProvider());
        }
        if (SlimefunProvider.isAvailable()) {
            providers.add(new SlimefunProvider());
        }

        // Bridge
        if (ItemBridgeProvider.isAvailable()) {
            providers.add(new ItemBridgeProvider());
        }

        return providers;
    }

    private List<ItemProvider> getProviders() {
        List<ItemProvider> providerList = providers.get();
        if (providerList == null) {
            providerList = constructProviders();
            providers.set(providerList);
        }
        return providerList;
    }

    @Override
    public List<String> availableTypes() {
        return getProviders().parallelStream().map(ItemProvider::availableTypes).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return getProviders().stream().map(provider -> provider.wrap(item)).filter(Item::isValid).findFirst().orElse(Item.INVALID);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return getProviders().stream().map(provider -> provider.wrap(key)).filter(Item::isValid).findFirst().orElse(Item.INVALID);
    }
}
