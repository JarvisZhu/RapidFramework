package com.rapid.framework.datamodifier.modifier;

import java.sql.Timestamp;
import java.text.DateFormat;

import com.rapid.framework.datamodifier.DataModifier;
import com.rapid.framework.datamodifier.DefaultUtils;

public class TimestampDataModifier implements DataModifier {
    private static final String DEFAULT_TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

    private String dateFormat = DEFAULT_TIMESTAMP_FORMAT;

    public TimestampDataModifier() {
    }

    public TimestampDataModifier(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Object modify(Object value, String modifierArgument) throws Exception {
        if (value == null) return null;
        if (value instanceof Timestamp) return value;
        DateFormat df = DefaultUtils.defaultDateFormat(modifierArgument, dateFormat);
        return new Timestamp(df.parse(value.toString()).getTime());
    }

}

