package com.gorb.texthandling.entity;

import java.util.List;

public interface InformationComponent {
    List<InformationComponent> getChildren();

    void add(InformationComponent component);

    void remove(InformationComponent component);

    ComponentType getType();
}
