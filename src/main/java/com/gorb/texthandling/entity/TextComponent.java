package com.gorb.texthandling.entity;

import java.util.List;

public interface TextComponent {
    List<TextComponent> getChildren();

    void add(TextComponent component);

    void remove(TextComponent component);

    ComponentType getType();
}
