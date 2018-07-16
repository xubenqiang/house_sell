package com.xbq.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {

    private List<T> list;
    private Pagination pagination;

    public PageData(Pagination pagination,List<T> list){
        this.pagination = pagination;
        this.list = list;
    }


    public static <T> PageData<T> buildPage(Integer pageNum,Integer pageSize,Long totalCount,List<T> list){
       Pagination pagination = new Pagination(pageNum,pageSize,totalCount);
       return new PageData<>(pagination,list);
    }


}
