package io.github.projectunified.uniitem.all;

import io.github.projectunified.uniitem.api.ItemProvider;
import io.github.projectunified.uniitem.itemsadder.ItemsAdderProvider;
import io.github.projectunified.uniitem.jojodmo.JojodmoItemBridgeProvider;
import io.github.projectunified.uniitem.multi.MultiItemProvider;
import io.github.projectunified.uniitem.nexo.NexoProvider;
import io.github.projectunified.uniitem.oraxen.OraxenProvider;

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
