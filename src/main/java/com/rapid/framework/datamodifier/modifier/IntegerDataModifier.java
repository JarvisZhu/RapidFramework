package com.rapid.framework.datamodifier.modifier;

import com.rapid.framework.datamodifier.DataModifier;

public class IntegerDataModifier implements DataModifier {
    public Object modify(Object value, String modifierArgument) {
        if (value == null) return null;
        if (value instanceof Integer) return value;
        return new Integer(value.toString());
    }
}

