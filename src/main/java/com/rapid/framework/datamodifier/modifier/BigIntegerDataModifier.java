package com.rapid.framework.datamodifier.modifier;

import java.math.BigInteger;

import com.rapid.framework.datamodifier.DataModifier;

public class BigIntegerDataModifier implements DataModifier {
    public Object modify(Object value, String modifierArgument) {
        if (value == null) return null;
        if (value instanceof BigInteger) return value;
        return new BigInteger(value.toString());
    }
}

