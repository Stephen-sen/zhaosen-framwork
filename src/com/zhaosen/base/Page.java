package com.zhaosen.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"serial", "rawtypes"})
public class Page implements Serializable {
    private static int DEFAULT_PAGE_SIZE = 20;
    private int pageSize;
    private long start;
	private List data;
    private long totalCount;

    public Page() {
        this(0L, 0L, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    public Page(long start, long totalSize, int pageSize, List data) {
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.pageSize = pageSize;
        this.start = start;
        this.totalCount = totalSize;
        this.data = data;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public long getTotalPageCount() {
        return this.totalCount % (long)this.pageSize == 0L?this.totalCount / (long)this.pageSize:this.totalCount / (long)this.pageSize + 1L;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public long getStart() {
        return this.start;
    }

    public List getResult() {
        return this.data;
    }

    public long getCurrentPageNo() {
        return this.start / (long)this.pageSize + 1L;
    }

    public boolean isHasNextPage() {
        return this.getCurrentPageNo() < this.getTotalPageCount();
    }

    public boolean isHasPreviousPage() {
        return this.getCurrentPageNo() > 1L;
    }

    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}
