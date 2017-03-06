package com.rapid.framework.concurrent.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.SwingUtilities;

public class AsyncToken<T> {
    public static final String DEFAULT_TOKEN_GROUP = "default";
    private static AtomicLong tokenIdSequence = new AtomicLong(1);

    //tokenGroup tokenName tokenId
    private String tokenGroup = DEFAULT_TOKEN_GROUP;
    private String tokenName;
    private long tokenId;

    private List<IResponder> _responders = new ArrayList(2);

    private UncaughtExceptionHandler uncaughtExceptionHandler;
    private T _result;
    private Exception _fault;
    private boolean _isFiredResult;

    private CountDownLatch awaitResultSignal = null;

    public AsyncToken() {
        this(null);
    }

    public AsyncToken(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this(DEFAULT_TOKEN_GROUP, null);
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    public AsyncToken(String tokenGroup, String tokenName) {
        setTokenGroup(tokenGroup);
        setTokenName(tokenName);
        this.tokenId = tokenIdSequence.getAndIncrement();
    }

    public String getTokenGroup() {
        return tokenGroup;
    }

    public void setTokenGroup(String tokenGroup) {
        if (tokenGroup == null) throw new IllegalArgumentException("'tokenGroup' must be not null");
        this.tokenGroup = tokenGroup;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public long getTokenId() {
        return tokenId;
    }

    public void addResponder(final IResponder<T> responder) {
        addResponder(responder, false);
    }

    public void addResponder(final IResponder<T> responder, boolean invokeResponderInOtherThread) {
        _responders.add(responder);

        if (_isFiredResult) {
            if (invokeResponderInOtherThread) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        fireResult2Responder(responder);
                    }
                });
            } else {
                fireResult2Responder(responder);
            }
        }
    }

    public List<IResponder> getResponders() {
        return _responders;
    }

    public boolean hasResponder() {
        return _responders != null && _responders.size() > 0;
    }

    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler;
    }

    public void setUncaughtExceptionHandler(UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.uncaughtExceptionHandler = uncaughtExceptionHandler;
    }

    private void fireResult2Responder(IResponder responder) {
        try {
            if (_fault != null) {
                responder.onFault(_fault);
            } else {
                responder.onResult(_result);
            }
        } catch (RuntimeException e) {
            if (getUncaughtExceptionHandler() != null) {
                getUncaughtExceptionHandler().uncaughtException(responder, e);
            } else {
                throw e;
            }
        } catch (Error e) {
            if (getUncaughtExceptionHandler() != null) {
                getUncaughtExceptionHandler().uncaughtException(responder, e);
            } else {
                throw e;
            }
        }
    }

    private void fireResult2Responders() {
        synchronized (this) {
            _isFiredResult = true;
            if (awaitResultSignal != null) {
                awaitResultSignal.countDown();
            }
        }

        for (IResponder r : _responders) {
            fireResult2Responder(r);
        }
    }

    public void setComplete() {
        setComplete(null);
    }

    public void setComplete(T result) {
        if (_isFiredResult) throw new IllegalStateException("token already fired");
        this._result = result;
        fireResult2Responders();
    }

    public void setFault(Exception fault) {
        if (fault == null) throw new NullPointerException();
        if (_isFiredResult) throw new IllegalStateException("token already fired");
        this._fault = fault;
        fireResult2Responders();
    }

    public boolean isDone() {
        synchronized (this) {
            return _isFiredResult;
        }
    }

    @Deprecated
    public Object waitForResult() throws InterruptedException, Exception {
        return waitForResult(-1, null);
    }

    @Deprecated
    public Object waitForResult(long timeout, TimeUnit timeUnit) throws InterruptedException, Exception {
        synchronized (this) {
            if (_isFiredResult) {
                if (_fault != null) {
                    throw _fault;
                } else {
                    return _result;
                }
            }

            awaitResultSignal = new CountDownLatch(1);
        }

        if (timeout > 0) {
            awaitResultSignal.await(timeout, timeUnit);
        } else {
            awaitResultSignal.await();
        }

        if (_fault != null) {
            throw _fault;
        } else {
            return _result;
        }
    }
}

