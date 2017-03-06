package com.rapid.framework.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Page<T> implements Serializable, Iterable<T> {
    protected List<T> result;
    protected int pageSize;
    protected int pageNumber;
    protected int totalCount;

    public Page(PageRequest p, int totalCount) {
        this(p.getPageNumber(), p.getPageSize(), totalCount);
    }

    public Page(int pageNumber, int pageSize, int totalCount) {
        this(pageNumber, pageSize, totalCount, new ArrayList(0));
    }

    public Page(int pageNumber, int pageSize, int totalCount, List<T> result) {
        this.totalCount = 0;

        if (pageSize <= 0) {
            throw new IllegalArgumentException("[pageSize] must great than zero");
        }
        this.pageSize = pageSize;
        this.pageNumber = PageUtils.computePageNumber(pageNumber, pageSize, totalCount);
        this.totalCount = totalCount;
        setResult(result);
    }

    public void setResult(List<T> elements) {
        if (elements == null) {
            throw new IllegalArgumentException("'result' must be not null");
        }
        result = elements;
    }

    public List<T> getResult() {
        return result;
    }

    public boolean isFirstPage() {
        return (getThisPageNumber() == 1);
    }

    public boolean isLastPage() {
        return (getThisPageNumber() >= getLastPageNumber());
    }

    public boolean isHasNextPage() {
        return (getLastPageNumber() > getThisPageNumber());
    }

    public boolean isHasPreviousPage() {
        return (getThisPageNumber() > 1);
    }

    public int getLastPageNumber() {
        return PageUtils.computeLastPageNumber(totalCount, pageSize);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getThisPageFirstElementNumber() {
        return ((getThisPageNumber() - 1) * getPageSize() + 1);
    }

    public int getThisPageLastElementNumber() {
        int fullPage = getThisPageFirstElementNumber() + getPageSize() - 1;
        return ((getTotalCount() < fullPage) ? getTotalCount() : fullPage);
    }

    public int getNextPageNumber() {
        return (getThisPageNumber() + 1);
    }

    public int getPreviousPageNumber() {
        return (getThisPageNumber() - 1);
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getThisPageNumber() {
        return pageNumber;
    }

    public Integer[] getLinkPageNumbers() {
        return PageUtils.generateLinkPageNumbers(getThisPageNumber(), getLastPageNumber(), 10);
    }

    public int getFirstResult() {
        return PageUtils.getFirstResult(pageNumber, pageSize);
    }

    public Iterator<T> iterator() {
        if (result == null) {
            return new ArrayList<T>().iterator();
        }
        return result.iterator();
    }
}

