package com.rapid.framework.xsqlbuilder;

public class IbatisStyleXsqlBuilder extends XsqlBuilder {
    public IbatisStyleXsqlBuilder() {
        setAsIbatisStyle();
    }

    public IbatisStyleXsqlBuilder(boolean isRemoveEmptyStrings,
                                  SafeSqlProcesser safeSqlProcesser) {
        super(isRemoveEmptyStrings, safeSqlProcesser);
        setAsIbatisStyle();
    }

    public IbatisStyleXsqlBuilder(boolean isRemoveEmptyStrings) {
        super(isRemoveEmptyStrings);
        setAsIbatisStyle();
    }

    public IbatisStyleXsqlBuilder(SafeSqlProcesser safeSqlProcesser) {
        super(safeSqlProcesser);
        setAsIbatisStyle();
    }

    private void setAsIbatisStyle() {
        markKeyEndChar = "#";
        markKeyStartChar = "#";

        replaceKeyEndChar = "$";
        replaceKeyStartChar = "$";
    }
}

