/**
 * @author zhaoyy
 */
package com.example.mybatisplus.util;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.List;

public class PageEntity<E> {
    private int page;
    private int limit;
    private long total;
    private List<E> data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }

    public static PageEntity getInstance(IPage page){
        PageEntity pageEntity= new PageEntity();
        pageEntity.setData(page.getRecords());
        pageEntity.setLimit((int)page.getSize());
        pageEntity.setPage((int)page.getPages());
        pageEntity.setTotal(page.getTotal());
        return pageEntity;
    }
}
