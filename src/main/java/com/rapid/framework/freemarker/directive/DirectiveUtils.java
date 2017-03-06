package com.rapid.framework.freemarker.directive;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class DirectiveUtils {
    public static String BLOCK = "__ftl_override__";

    public static String getOverrideVariableName(String name) {
        return BLOCK + name;
    }

    public static void exposeRapidMacros(Configuration conf) {
        conf.setSharedVariable("block", new BlockDirective());
        conf.setSharedVariable("extends", new ExtendsDirective());
        conf.setSharedVariable("override", new OverrideDirective());
    }

    static String getRequiredParam(Map params, String key) throws TemplateException {
        Object value = params.get(key);
        if ((value == null) || (StringUtils.isEmpty(value.toString()))) {
            throw new TemplateModelException("not found required parameter:" + key + " for directive");
        }
        return value.toString();
    }

    static String getParam(Map params, String key, String defaultValue) throws TemplateException {
        Object value = params.get(key);
        return ((value == null) ? defaultValue : value.toString());
    }
}

