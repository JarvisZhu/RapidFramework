package com.rapid.framework.datamodifier;

public class DataModifierException extends RuntimeException {
    public DataModifierException() {
        super();
    }

    public DataModifierException(String msg, Throwable e) {
        super(msg, e);
    }

    public DataModifierException(String msg) {
        super(msg);
    }

    public DataModifierException(Throwable e) {
        super(e);
    }
}

