package com.mq.util.page;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<E> implements Serializable {
    public int pageShow =10;
    public int totalPage;
    public int totalCount;
    public int start;
    public int nowPage;
    public List<E> result = Collections.emptyList();

    public Page() {
    }

    public Page(PageInfo pageInfo) {
        this.result = pageInfo.getList();
        this.setNowPage(pageInfo.getPageNum());
        this.setPageShow(pageInfo.getPageSize());
        this.setTotalPage(pageInfo.getPages());
        this.setTotalCount((int) pageInfo.getTotal());
    }

    public Page(List<E> result, PageVO pageVO) {
        this.result = result;
        this.setNowPage(pageVO.getPage());
        this.setPageShow(pageVO.getPageSize());
    }

    public int getStart() {
        this.start = (this.getNowPage() - 1) * this.getPageShow();
        if (this.start < 0) {
            this.start = 0;
        }

        return this.start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPageShow() {
        return this.pageShow;
    }

    public void setPageShow(int pageShow) {
        this.pageShow = pageShow;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<E> getResult() {
        return this.result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

    /**
     * 设置数据结果集和分页参数
     * @param result
     * @param pageInfo
     */
    public void setResultAndPageInfo(List<E> result, PageInfo<E> pageInfo){
        this.result = result;
        this.totalCount = (int)pageInfo.getTotal();
        this.totalPage = pageInfo.getPages();
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return (int)Math.ceil((double)this.totalCount * 1.0D / (double)this.pageShow);
    }

    public int getNowPage() {
//        if (this.nowPage <= 0) {
//            this.nowPage = 1;
//        }
//
//        if (this.nowPage > this.getTotalPage()) {
//            this.nowPage = this.getTotalPage();
//        }

        return this.nowPage;
    }

}
