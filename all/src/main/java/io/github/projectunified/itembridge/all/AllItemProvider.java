package io.github.projectunified.itembridge.all;

import io.github.projectunified.itembridge.api.ItemProvider;
import io.github.projectunified.itembridge.itemsadder.ItemsAdderProvider;
import io.github.projectunified.itembridge.jojodmo.JojodmoItemBridgeProvider;
import io.github.projectunified.itembridge.multi.MultiItemProvider;
import io.github.projectunified.itembridge.nexo.NexoProvider;
import io.github.projectunified.itembridge.oraxen.OraxenProvider;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class AllItemProvider extends MultiItemProvider {
    public AllItemProvider() {
        register(JojodmoItemBridgeProvider::isAvailable, JojodmoItemBridgeProvider::new);
        register(ItemsAdderProvider::isAvailable, ItemsAdderProvider::new);
        register(OraxenProvider::isAvailable, OraxenProvider::new);
        register(NexoProvider::isAvailable, NexoProvider::new);
    }

    public void register(BooleanSupplier condition, Supplier<ItemProvider> provider) {
        if (condition.getAsBoolean()) {
            addProvider(provider.get());
        }
    }
}
