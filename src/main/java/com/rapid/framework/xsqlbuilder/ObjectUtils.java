package com.rapid.framework.xsqlbuilder;

import java.util.Map;

import com.rapid.framework.xsqlbuilder.MapAndObject.FastPropertyUtils;

class ObjectUtils {
    public static Object getProperty(Object obj, String key) {
        if (obj == null) return null;
        if (obj instanceof Map) {
            Map map = (Map) obj;
            return map.get(key);
        } else {
            return FastPropertyUtils.getBeanPropertyValue(obj, key);
        }
    }
}

