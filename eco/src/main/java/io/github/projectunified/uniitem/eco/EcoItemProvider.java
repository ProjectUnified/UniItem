package io.github.projectunified.uniitem.eco;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.ItemProvider;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

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

    @Override
    public List<String> availableTypes() {
        List<String> availableTypes = new ArrayList<>();
        for (String pluginName : ECO_PLUGINS.keySet()) {
            if (Bukkit.getPluginManager().getPlugin(pluginName) != null) {
                availableTypes.add(ECO_PLUGINS.get(pluginName));
            }
        }
        return availableTypes;
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemStack item) {
        return new EcoItem(item);
    }

    @Override
    public @NotNull Item wrap(@NotNull ItemKey key) {
        return new EcoItem(key);
    }
}
