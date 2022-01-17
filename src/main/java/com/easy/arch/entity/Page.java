package com.easy.arch.entity;


import java.util.List;

public class Page {

    //当前页
    private int currentPage;
    //页面大小
    private int pageSize;
    //数据总数
    private int totalCount;
    //总页数
    private int totalPage;

    private List<Chairs_signal> chairs_signals;



    public Page(int currentPage, int pageSize, int totalCount, int totalPage, List<Chairs_signal>chairs_signals) {
        super();
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.chairs_signals=chairs_signals;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getpageSize() {
        return pageSize;
    }

    public void setpageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Chairs_signal> getChairs_signals() {
        return chairs_signals;
    }

    public void setChairs_signals(List<Chairs_signal> chairs_signals) {
        this.chairs_signals = chairs_signals;
    }
}
