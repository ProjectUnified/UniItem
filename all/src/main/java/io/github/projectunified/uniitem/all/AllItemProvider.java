package io.github.projectunified.uniitem.all;

import io.github.projectunified.uniitem.customfishing.CustomFishingProvider;
import io.github.projectunified.uniitem.eco.EcoItemProvider;
import io.github.projectunified.uniitem.executableitems.ExecutableItemsProvider;
import io.github.projectunified.uniitem.headdatabase.HeadDatabaseProvider;
import io.github.projectunified.uniitem.itembridge.ItemBridgeProvider;
import io.github.projectunified.uniitem.itemedit.ItemEditProvider;
import io.github.projectunified.uniitem.itemsadder.ItemsAdderProvider;
import io.github.projectunified.uniitem.mmoitems.MMOItemsProvider;
import io.github.projectunified.uniitem.multi.MultiItemProvider;
import io.github.projectunified.uniitem.mythicmobs.MythicItemProvider;
import io.github.projectunified.uniitem.nexo.NexoProvider;
import io.github.projectunified.uniitem.nova.NovaItemProvider;
import io.github.projectunified.uniitem.oraxen.OraxenProvider;
import io.github.projectunified.uniitem.slimefun.SlimefunProvider;

public class AllItemProvider extends MultiItemProvider {
    public AllItemProvider() {
        // Dependent
        if (ItemEditProvider.isAvailable()) {
            addProvider(new ItemEditProvider());
        }
        if (CustomFishingProvider.isAvailable()) {
            addProvider(new CustomFishingProvider());
        }
        if (EcoItemProvider.isAvailable()) {
            EcoItemProvider provider = new EcoItemProvider();
            addProvider(provider, provider.type());
        }
        if (ExecutableItemsProvider.isAvailable()) {
            addProvider(new ExecutableItemsProvider());
        }
        if (MMOItemsProvider.isAvailable()) {
            addProvider(new MMOItemsProvider());
        }
        if (MythicItemProvider.isAvailable()) {
            addProvider(new MythicItemProvider(), "mm");
        }

        // Base
        if (ItemsAdderProvider.isAvailable()) {
            addProvider(new ItemsAdderProvider(), "ia");
        }
        if (OraxenProvider.isAvailable()) {
            addProvider(new OraxenProvider(), "orx");
        }
        if (NexoProvider.isAvailable()) {
            addProvider(new NexoProvider());
        }
        if (HeadDatabaseProvider.isAvailable()) {
            addProvider(new HeadDatabaseProvider(), "hdb", "headdb");
        }
        if (NovaItemProvider.isAvailable()) {
            addProvider(new NovaItemProvider());
        }
        if (SlimefunProvider.isAvailable()) {
            addProvider(new SlimefunProvider(), "sf");
        }

        // Bridge
        if (ItemBridgeProvider.isAvailable()) {
            addProvider(new ItemBridgeProvider(), "item-bridge");
        }
    }
}
