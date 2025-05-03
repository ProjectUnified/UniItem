package io.github.projectunified.uniitem.ecoitems;

import com.willfp.ecoitems.items.EcoItem;
import com.willfp.ecoitems.items.EcoItems;
import com.willfp.ecoitems.items.ItemUtilsKt;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EcoItemsProvider implements ItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("EcoItems") != null;
    }

    @Override
    public @NotNull String[] type() {
        return new String[]{
                "ecoitems"
        };
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        EcoItem ecoItem = ItemUtilsKt.getEcoItem(item);
        return ecoItem != null ? ecoItem.getID() : null;
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        EcoItem ecoItem = EcoItems.INSTANCE.getByID(id);
        return ecoItem != null ? ecoItem.getItemStack() : null;
    }
}
