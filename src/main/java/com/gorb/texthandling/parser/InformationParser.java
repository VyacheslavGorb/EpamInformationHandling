package com.gorb.texthandling.parser;

import com.gorb.texthandling.entity.InformationComponent;
import com.gorb.texthandling.exception.TextException;

public interface InformationParser {
    InformationComponent parse(String text) throws TextException;
}
