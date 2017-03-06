package com.rapid.framework.freemarker;

public class FreemarkerTemplateException extends RuntimeException {
    private static final long serialVersionUID = -3001339513837419069L;

    public FreemarkerTemplateException() {
    }

    public FreemarkerTemplateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreemarkerTemplateException(String message) {
        super(message);
    }

    public FreemarkerTemplateException(Throwable cause) {
        super(cause);
    }
}

