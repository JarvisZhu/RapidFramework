package com.rapid.framework.xsqlbuilder.safesql;

import com.rapid.framework.xsqlbuilder.SafeSqlProcesser;

public class DirectReturnSafeSqlProcesser implements SafeSqlProcesser {

    public static SafeSqlProcesser INSTANCE = new DirectReturnSafeSqlProcesser();

    public String process(String value) {
        return value;
    }

}
