package com.gorb.texthandling.entity.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class TextComposite implements TextComponent {
    private static final String TABULATION_REGEX = "\t";
    private List<TextComponent> components = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type) throws TextException {
        EnumSet<ComponentType> symbolTypes = EnumSet.range(ComponentType.TEXT, ComponentType.EXPRESSION);
        if (!symbolTypes.contains(type)) {
            throw new TextException("Illegal text composite type");
        }
        this.type = type;
    }

    @Override
    public List<TextComponent> getChildren() {
        return components;
    }

    @Override
    public void add(TextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(TextComponent component) {
        components.remove(component);
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return components.equals(that.components) && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + components.hashCode();
        result += result * 31 + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (type == ComponentType.TEXT) {
            builder.append(TABULATION_REGEX);
        }
        String delimiter = type.getDelimiter();
        for (TextComponent component : components) {
            builder.append(component.toString()).append(delimiter);
        }
        return builder.toString();
    }
}
