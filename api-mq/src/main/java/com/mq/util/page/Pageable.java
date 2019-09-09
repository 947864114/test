package com.mq.util.page;


public interface Pageable {
    int getPageNumber();

    int getPageSize();

    int getOffset();

    Sort getSort();
}
