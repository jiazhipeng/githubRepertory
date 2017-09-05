package cn.cuco.page;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by WangShuai on 2016/7/21.
 */
public class PageResult<T> {

    /**
     * 第几页
     */
    private int page;

    /**
     * 每页多少条
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总条数
     */
    private int totalSize;

    /**
     * 结果集
     */
    private List<T> items;

    public PageResult() {
    }

    public PageResult(int page, int pageSize, int totalSize, List<T> items) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.items = items;
    }

    /*  getter/setter */
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        if(totalPage==0 && this.getPageSize()>0){
            totalPage = this.getTotalSize()/this.getPageSize()+(this.getTotalSize()%this.getPageSize()==0?0:1);
        }
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
