package com.rapid.framework.datamodifier.modifier;

import com.rapid.framework.datamodifier.DataModifier;
import com.rapid.framework.datamodifier.DefaultUtils;

public class DefaultDataModifier implements DataModifier {
    private static final String DEFAULT_STRING = "";

    public Object modify(Object value, String modifierArgument) {
        if (value == null)
            return DefaultUtils.defaultString(modifierArgument, DEFAULT_STRING);
        return value;
    }
}

