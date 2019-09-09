package com.mq.util.page;

public class PageVO {
    private int pageSize = 10;
    private int page = 1;

    public PageVO() {
    }


    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize < 1) {
            this.pageSize = 1;
        } else {
            this.pageSize = pageSize;
        }

    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        if (page < 1) {
            this.page = 1;
        } else {
            this.page = page;
        }

    }


}
