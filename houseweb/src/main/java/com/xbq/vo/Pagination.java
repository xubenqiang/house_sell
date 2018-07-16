package com.xbq.vo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

@Data
public class Pagination {

    private int pageNum;
    private int pageSize;
    private long totalCount;
    private List<Integer> pages = Lists.newArrayList();

    public Pagination(Integer pageNum,Integer pageSize,Long totalCount){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
    }

}
