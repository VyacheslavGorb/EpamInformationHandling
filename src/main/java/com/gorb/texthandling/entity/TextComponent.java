package com.gorb.texthandling.entity;

import java.util.List;

public interface TextComponent {
    TextComponent getChild(int i);

    List<TextComponent> getChildren();

    void add(TextComponent component);

    void remove(TextComponent component);

    ComponentType getType();
}
