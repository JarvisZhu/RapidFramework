package com.rapid.framework.concurrent.async;

public interface IResponder<T> {

    public void onResult(T result);

    public void onFault(Exception fault);

}
