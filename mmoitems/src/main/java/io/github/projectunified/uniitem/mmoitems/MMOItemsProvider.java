package io.github.projectunified.uniitem.mmoitems;

import io.github.projectunified.uniitem.api.ItemKey;
import io.github.projectunified.uniitem.api.SimpleItemProvider;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MMOItemsProvider implements SimpleItemProvider {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().getPlugin("MMOItems") != null;
    }

    @Override
    public @NotNull String type() {
        return "mmoitems";
    }

    @Override
    public @Nullable String id(@NotNull ItemStack item) {
        String type = MMOItems.getTypeName(item);
        if (type == null) return null;

        String id = MMOItems.getID(item);
        if (id == null) return null;

        ItemKey key = new ItemKey(type, id);
        return key.toString();
    }

    private @Nullable MMOItemTemplate template(String id) {
        ItemKey key;
        try {
            key = ItemKey.fromString(id);
        } catch (IllegalArgumentException e) {
            return null;
        }

        Type type = Type.get(key.type());
        if (type == null) return null;

        return MMOItems.plugin.getTemplates().getTemplate(type, key.id());
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id) {
        MMOItemTemplate template = template(id);
        if (template == null) return null;

        return template.newBuilder().build().newBuilder().build();
    }

    @Override
    public @Nullable ItemStack item(@NotNull String id, @NotNull Player player) {
        MMOItemTemplate template = template(id);
        if (template == null) return null;

        return template.newBuilder(player).build().newBuilder().build();
    }
}
