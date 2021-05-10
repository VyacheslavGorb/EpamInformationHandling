package com.gorb.texthandling.parser;

import com.gorb.texthandling.entity.TextComponent;
import com.gorb.texthandling.exception.TextException;

public interface InformationParser {
    TextComponent parse(String text) throws TextException;
}
