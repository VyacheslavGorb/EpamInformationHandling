package com.gorb.texthandling.entity.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.List;

public class CompositeLeaf implements TextComponent {
    private static final Logger logger = LogManager.getLogger();
    private ComponentType type;
    private char symbol;

    public CompositeLeaf(ComponentType symbolType, char symbol) throws TextException {
        EnumSet<ComponentType> symbolTypes = EnumSet.range(ComponentType.SYMBOL_LEAF, ComponentType.EXPRESSION_LEAF);
        if (!symbolTypes.contains(symbolType)) {
            throw new TextException("Illegal leaf type");
        }
        this.symbol = symbol;
        this.type = symbolType;
    }

    @Override
    public TextComponent getChild(int i) {
        logger.log(Level.ERROR, "Unsupported operation get child on leaf");
        throw new UnsupportedOperationException("Unsupported operation get child on leaf");
    }

    @Override
    public List<TextComponent> getChildren() {
        logger.log(Level.ERROR, "Unsupported operation get children on leaf");
        throw new UnsupportedOperationException("Unsupported operation get children on leaf");
    }

    @Override
    public void add(TextComponent component) {
        logger.log(Level.ERROR, "Unsupported operation add on leaf");
        throw new UnsupportedOperationException("Unsupported operation add on leaf");
    }

    @Override
    public void remove(TextComponent component) {
        logger.log(Level.ERROR, "Unsupported operation remove on leaf");
        throw new UnsupportedOperationException("Unsupported operation remove on leaf");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
