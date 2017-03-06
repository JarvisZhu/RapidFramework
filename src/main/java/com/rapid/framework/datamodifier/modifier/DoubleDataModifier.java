package com.rapid.framework.datamodifier.modifier;

import com.rapid.framework.datamodifier.DataModifier;

public class DoubleDataModifier implements DataModifier {
    public Object modify(Object value, String modifierArgument) {
        if (value == null) return null;
        if (value instanceof Double) return value;
        return new Double(value.toString());
    }
}

