package com.gorb.texthandling.entity.impl;

import com.gorb.texthandling.entity.ComponentType;
import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.List;

public class SymbolLeaf implements InformationComponent {
    private static final Logger logger = LogManager.getLogger();
    private ComponentType type;
    private char symbol;

    public SymbolLeaf(ComponentType symbolType, char symbol) throws TextException {
        EnumSet<ComponentType> symbolTypes = EnumSet.range(ComponentType.SYMBOL_LEAF, ComponentType.EXPRESSION_LEAF);
        if (!symbolTypes.contains(symbolType)) {
            throw new TextException("Illegal leaf type");
        }
        this.symbol = symbol;
        this.type = symbolType;
    }

    @Override
    public List<InformationComponent> getChildren() {
        logger.log(Level.ERROR, "Unsupported operation get children on leaf");
        throw new UnsupportedOperationException("Unsupported operation get children on leaf");
    }

    @Override
    public void add(InformationComponent component) {
        logger.log(Level.ERROR, "Unsupported operation add on leaf");
        throw new UnsupportedOperationException("Unsupported operation add on leaf");
    }

    @Override
    public void remove(InformationComponent component) {
        logger.log(Level.ERROR, "Unsupported operation remove on leaf");
        throw new UnsupportedOperationException("Unsupported operation remove on leaf");
    }

    @Override
    public ComponentType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolLeaf that = (SymbolLeaf) o;
        return symbol == that.symbol && type == that.type;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += result * 31 + type.hashCode();
        result += result * 31 + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
