package com.xbq.biz.model;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@TableName("community")
@ToString
public class Community {

    private Integer id;
    private String cityCode;
    private String cityName;
    private String name;


}
