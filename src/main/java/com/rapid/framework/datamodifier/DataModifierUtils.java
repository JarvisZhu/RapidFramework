package com.rapid.framework.datamodifier;

import java.util.Map;

import com.rapid.framework.datamodifier.modifier.BigDecimalDataModifier;
import com.rapid.framework.datamodifier.modifier.BigIntegerDataModifier;
import com.rapid.framework.datamodifier.modifier.BooleanDataModifier;
import com.rapid.framework.datamodifier.modifier.ByteDataModifier;
import com.rapid.framework.datamodifier.modifier.DateDataModifier;
import com.rapid.framework.datamodifier.modifier.DefaultDataModifier;
import com.rapid.framework.datamodifier.modifier.DoubleDataModifier;
import com.rapid.framework.datamodifier.modifier.FloatDataModifier;
import com.rapid.framework.datamodifier.modifier.IntegerDataModifier;
import com.rapid.framework.datamodifier.modifier.LongDataModifier;
import com.rapid.framework.datamodifier.modifier.ShortDataModifier;
import com.rapid.framework.datamodifier.modifier.SqlDateDataModifier;
import com.rapid.framework.datamodifier.modifier.SqlTimeDataModifier;
import com.rapid.framework.datamodifier.modifier.StringDataModifier;
import com.rapid.framework.datamodifier.modifier.TimestampDataModifier;

public class DataModifierUtils {
    private static DataModifierBean delegate = new DataModifierBean();

    static {
        registerDefaultDataModifiers(delegate);
    }

    private DataModifierUtils() {
    }

    public static void registerDefaultDataModifiers(DataModifierBean bean) {
        bean.registerDataModifier("default", new DefaultDataModifier());
        bean.registerDataModifier("boolean", new BooleanDataModifier());
        bean.registerDataModifier("string", new StringDataModifier());
        bean.registerDataModifier("byte", new ByteDataModifier());
        bean.registerDataModifier("short", new ShortDataModifier());
        bean.registerDataModifier("int", new IntegerDataModifier());
        bean.registerDataModifier("long", new LongDataModifier());
        bean.registerDataModifier("float", new FloatDataModifier());
        bean.registerDataModifier("double", new DoubleDataModifier());
        bean.registerDataModifier("BigInteger", new BigIntegerDataModifier());
        bean.registerDataModifier("BigDecimal", new BigDecimalDataModifier());
        bean.registerDataModifier("date", new DateDataModifier());
        bean.registerDataModifier("sqldate", new SqlDateDataModifier());
        bean.registerDataModifier("sqltime", new SqlTimeDataModifier());
        bean.registerDataModifier("timestamp", new TimestampDataModifier());
    }

    public static void deregisterDataModifier(String modifierName) {
        delegate.deregisterDataModifier(modifierName);
    }

    public static void registerDataModifier(String modifierName, DataModifier modifier) {
        delegate.registerDataModifier(modifierName, modifier);
    }

    public Map getAvailableDataModifiers() {
        return delegate.getAvailableDataModifiers();
    }


    public static Object directModify(String dataModifierExpression, Object dataModifyValue) {
        return delegate.directModify(dataModifierExpression, dataModifyValue);
    }

    public static Object modify(String completeExpression, Object dataModifyValue) {
        return delegate.modify(completeExpression, dataModifyValue);
    }

    public static String getModifyVariable(String completeExpression) {
        return DataModifierBean.getModifyVariable(completeExpression);
    }
}

