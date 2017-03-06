package com.rapid.framework.datamodifier.modifier;

import com.rapid.framework.datamodifier.DataModifier;

public class ByteDataModifier implements DataModifier {
    public Object modify(Object value, String modifierArgument) {
        if (value == null) return null;
        if (value instanceof Byte) return value;
        return new Byte(value.toString());
    }
}

