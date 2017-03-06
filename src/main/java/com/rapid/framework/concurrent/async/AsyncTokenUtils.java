package com.rapid.framework.concurrent.async;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

@SuppressWarnings("all")
public class AsyncTokenUtils {
    public static void execute(Executor executor, AsyncToken token, final Callable task) {
        executor.execute(new AsyncTokenRunnable(token, task));
    }

    public static void execute(Executor executor, AsyncToken token, final Runnable task) {
        executor.execute(new AsyncTokenRunnable(token, task));
    }

    public static AsyncToken execute(Executor executor, AsyncTokenFactory factory, final Callable task) {
        AsyncToken token = factory.newToken();
        executor.execute(new AsyncTokenRunnable(token, task));
        return token;
    }

    public static AsyncToken execute(Executor executor, AsyncTokenFactory factory, final Runnable task) {
        AsyncToken token = factory.newToken();
        executor.execute(new AsyncTokenRunnable(token, task));
        return token;
    }
}

