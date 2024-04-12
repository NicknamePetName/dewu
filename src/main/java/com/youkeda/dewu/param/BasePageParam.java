package com.youkeda.dewu.param;

public class BasePageParam {
    private int pagination = 0;
    private int pageSize = 10;

    public int getPagination() {
        return pagination;
    }

    public void setPagination(int pagination) {
        this.pagination = pagination;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
