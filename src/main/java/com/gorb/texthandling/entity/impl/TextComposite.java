package com.gorb.texthandling.entity.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> components = new ArrayList<>();
    private ComponentType type;

    public TextComposite(ComponentType type){
        this.type = type;
    }

    @Override
    public TextComponent getChild(int i) {
        return components.get(i);
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
    public String toString() {
        return type.name();
    }
}
