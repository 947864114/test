package com.mq.util.page;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;


public class PageRequest extends RowBounds implements Pageable, Serializable {
    private int page;
    private int size = 10;
    private Sort sort;
    private static int defaultPageSize = 10;
    protected boolean containsTotalCount;
    protected Boolean asyncTotalCount;

    public PageRequest() {
        this.containsTotalCount = false;
    }

    public PageRequest(int page) {
        this(page, defaultPageSize);
    }

    public PageRequest(int page, int size) {
        this(page, size, (Sort)null, true);
    }

    public PageRequest(int page, int size, Sort.Direction direction, String... properties) {
        this(page, size, new Sort(direction, properties), true);
    }

    public PageRequest(int page, int size, Sort sort) {
        this(page, size, sort, true);
    }

    public PageRequest(int page, int size, Sort sort, boolean containsTotalCount) {
        if(0 > page) {
            throw new IllegalArgumentException("Page index must not be less than or equal to zero!");
        } else if(0 >= size) {
            throw new IllegalArgumentException("Page size must not be less than or equal to zero!");
        } else {
            this.page = page > 0?page - 1:0;
            this.size = size;
            this.sort = sort;
            this.containsTotalCount = containsTotalCount;
        }
    }

    public int getPageNumber() {
        return this.page;
    }

    public int getPageSize() {
        return this.size;
    }

    public int getOffset() {
        return this.page * this.size;
    }

    public Sort getSort() {
        return this.sort;
    }

    public boolean isContainsTotalCount() {
        return this.containsTotalCount;
    }

    public void setContainsTotalCount(boolean containsTotalCount) {
        this.containsTotalCount = containsTotalCount;
    }

    public Boolean getAsyncTotalCount() {
        return this.asyncTotalCount;
    }

    public void setAsyncTotalCount(Boolean asyncTotalCount) {
        this.asyncTotalCount = asyncTotalCount;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(!(obj instanceof PageRequest)) {
            return false;
        } else {
            PageRequest that = (PageRequest)obj;
            boolean pageEqual = this.page == that.page;
            boolean sizeEqual = this.size == that.size;
            boolean sortEqual = this.sort == null?that.sort == null:this.sort.equals(that.sort);
            return pageEqual && sizeEqual && sortEqual;
        }
    }

    public int hashCode() {
        byte result = 17;
        int result1 = 31 * result + this.page;
        result1 = 31 * result1 + this.size;
        result1 = 31 * result1 + (null == this.sort?0:this.sort.hashCode());
        return result1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PageRequest{");
        sb.append("page=").append(this.page);
        sb.append(", size=").append(this.size);
        sb.append(", sort=").append(this.sort);
        sb.append(", containsTotalCount=").append(this.containsTotalCount);
        sb.append(", asyncTotalCount=").append(this.asyncTotalCount);
        sb.append('}');
        return sb.toString();
    }

    static {
        defaultPageSize = 10;
    }
}
