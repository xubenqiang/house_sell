package com.xbq.biz.model;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("community")
public class Community {

    private Integer id;
    private String cityCode;
    private String cityName;
    private String name;


}
