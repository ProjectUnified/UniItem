package io.github.projectunified.uniitem.eco;

import com.willfp.eco.core.items.CustomItem;
import com.willfp.eco.core.items.Items;
import com.willfp.eco.core.items.TestableItem;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EcoItemProvider implements ItemProvider {
    private static final Map<String, String> ECO_PLUGINS = new HashMap<String, String>() {{
        put("EcoArmor", "ecoarmor");
        put("EcoCrates", "ecocrates");
        put("EcoItems", "ecoitems");
        put("EcoMobs", "ecomobs");
        put("EcoPets", "ecopets");
        put("EcoScrolls", "ecoscrolls");
        put("Reforges", "reforges");
        put("StatTrackers", "stattrackers");
        put("Talismans", "talismans");
    }};

    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("eco") != null;
    }

    public @NotNull String[] type() {
        List<String> types = new ArrayList<>();
        for (String pluginName : ECO_PLUGINS.keySet()) {
            if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                types.add(ECO_PLUGINS.get(pluginName));
            }
        }
        return types.toArray(new String[0]);
    }

    @Override
    public boolean isValidKey(@NotNull ItemKey key) {
        return Items.lookup(key.type() + ":" + key.id()) instanceof CustomItem;
    }

    @Override
    public @Nullable ItemKey key(@NotNull ItemStack item) {
        CustomItem customItem = Items.getCustomItem(item);
        if (customItem == null) {
            return null;
        }
        NamespacedKey key = customItem.getKey();
        return new ItemKey(key.getNamespace(), key.getKey());
    }

    @Override
    public @Nullable ItemStack item(@NotNull ItemKey key) {
        TestableItem customItem = Items.lookup(key.type() + ":" + key.id());
        if (!(customItem instanceof CustomItem)) {
            return null;
        }
        return customItem.getItem();
    }
}
