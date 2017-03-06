package com.rapid.framework.datamodifier.modifier;

import com.rapid.framework.datamodifier.DataModifier;

public class StringDataModifier implements DataModifier {
    public Object modify(Object value, String modifierArgument) {
        if (value == null) return null;
        if (value instanceof String) return value;
        return value.toString();
    }
}

