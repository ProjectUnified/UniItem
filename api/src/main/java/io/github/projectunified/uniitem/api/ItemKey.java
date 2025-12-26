package io.github.projectunified.uniitem.api;

import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

public final class ItemKey {
    private static final String SEPARATOR = ":";

    private final String type;
    private final String id;

    private final String lowerType;
    private final String lowerId;

    public ItemKey(String type, String id) {
        this.type = type;
        this.id = id;
        this.lowerType = type.toLowerCase(Locale.ROOT);
        this.lowerId = id.toLowerCase(Locale.ROOT);
    }

    public static ItemKey fromString(String input) {
        int separatorIndex = input.indexOf(SEPARATOR);
        if (separatorIndex == -1) {
            throw new IllegalArgumentException("Invalid ItemKey format: " + input);
        }
        String type = input.substring(0, separatorIndex);
        String id = input.substring(separatorIndex + 1);
        return new ItemKey(type, id);
    }

    @Override
    public String toString() {
        return type + SEPARATOR + id;
    }

    public String type() {
        return type;
    }

    public boolean isType(String type) {
        return this.type.equalsIgnoreCase(type);
    }

    public boolean isType(Collection<String> types) {
        for (String type : types) {
            if (isType(type)) return true;
        }
        return false;
    }

    public String id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemKey itemKey = (ItemKey) o;
        return Objects.equals(lowerType, itemKey.lowerType) && Objects.equals(lowerId, itemKey.lowerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lowerType, lowerId);
    }
}
