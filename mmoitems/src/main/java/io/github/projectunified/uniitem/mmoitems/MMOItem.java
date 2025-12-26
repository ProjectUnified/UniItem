package io.github.projectunified.uniitem.mmoitems;

import io.github.projectunified.uniitem.api.Item;
import io.github.projectunified.uniitem.api.ItemKey;
import net.Indyuce.mmoitems.MMOItems;
import net.Indyuce.mmoitems.api.Type;
import net.Indyuce.mmoitems.api.item.template.MMOItemTemplate;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

class MMOItem implements Item {
    private final @Nullable MMOItemTemplate template;

    MMOItem(ItemStack itemStack) {
        Type type = MMOItems.getType(itemStack);
        String id = MMOItems.getID(itemStack);
        this.template = type != null && id != null
                ? MMOItems.plugin.getTemplates().getTemplate(type, id)
                : null;
    }

    MMOItem(String id) {
        ItemKey key;
        try {
            key = ItemKey.fromString(id);
        } catch (Exception e) {
            key = null;
        }
        Type type = key != null ? Type.get(key.type()) : null;
        this.template = type != null ? MMOItems.plugin.getTemplates().getTemplate(type, key.id()) : null;
    }

    @Override
    public boolean isValid() {
        return template != null;
    }

    @Override
    public @Nullable ItemKey key() {
        if (template == null) return null;
        return new ItemKey(MMOItemsProvider.TYPE, new ItemKey(template.getType().getName(), template.getId()).toString());
    }

    @Override
    public @Nullable ItemStack bukkitItem() {
        if (template == null) return null;
        return template.newBuilder().build().newBuilder().build();
    }

    @Override
    public @Nullable ItemStack bukkitItem(@NotNull Player player) {
        if (template == null) return null;
        return template.newBuilder(player).build().newBuilder().build();
    }

    @Override
    public boolean isSimilar(@NotNull ItemStack item) {
        if (template == null) return false;
        Type type = MMOItems.getType(item);
        String id = MMOItems.getID(item);
        return Objects.equals(type, template.getType()) && Objects.equals(id, template.getId());
    }
}
