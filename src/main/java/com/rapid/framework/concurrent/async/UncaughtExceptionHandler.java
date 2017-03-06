package com.rapid.framework.concurrent.async;

@SuppressWarnings("all")
public interface UncaughtExceptionHandler {

    void uncaughtException(IResponder responder,Throwable e);

}
