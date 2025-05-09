package io.github.projectunified.uniitem.all;

import io.github.projectunified.uniitem.api.ItemProvider;
import io.github.projectunified.uniitem.eco.EcoItemProvider;
import io.github.projectunified.uniitem.executableitems.ExecutableItemsProvider;
import io.github.projectunified.uniitem.headdatabase.HeadDatabaseProvider;
import io.github.projectunified.uniitem.itembridge.ItemBridgeProvider;
import io.github.projectunified.uniitem.itemedit.ItemEditProvider;
import io.github.projectunified.uniitem.itemsadder.ItemsAdderProvider;
import io.github.projectunified.uniitem.multi.MultiItemProvider;
import io.github.projectunified.uniitem.nexo.NexoProvider;
import io.github.projectunified.uniitem.nova.NovaItemProvider;
import io.github.projectunified.uniitem.oraxen.OraxenProvider;
import io.github.projectunified.uniitem.slimefun.SlimefunProvider;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

public class AllItemProvider extends MultiItemProvider {
    public AllItemProvider() {
        register(ItemBridgeProvider::isAvailable, ItemBridgeProvider::new);
        register(ItemsAdderProvider::isAvailable, ItemsAdderProvider::new);
        register(OraxenProvider::isAvailable, OraxenProvider::new);
        register(NexoProvider::isAvailable, NexoProvider::new);
        register(EcoItemProvider::isAvailable, EcoItemProvider::new);
        register(ItemEditProvider::isAvailable, ItemEditProvider::new);
        register(HeadDatabaseProvider::isAvailable, HeadDatabaseProvider::new);
        register(ExecutableItemsProvider::isAvailable, ExecutableItemsProvider::new);
        register(NovaItemProvider::isAvailable, NovaItemProvider::new);
        register(SlimefunProvider::isAvailable, SlimefunProvider::new);
    }

    public void register(BooleanSupplier condition, Supplier<ItemProvider> provider) {
        if (condition.getAsBoolean()) {
            addProvider(provider.get());
        }
    }
}
