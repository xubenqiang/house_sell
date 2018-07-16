package com.xbq.biz.model;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@TableName("house")
@Data
@ToString
public class House {

    private Long id;        //房屋标识
    private Integer type;   //房屋类型
    private Integer price;  //价格
    private String name;    //房屋名
    private String images;  //房屋图片
    private Integer area;   //房屋面积
    private Integer beds;   //卧室
    private Integer baths;  //卫生间
    private Double rating;  //评分

    private String remarks;
    private String properties;
    private String floorPlan;       //户型图
    private String tags;            //标签
    private Date createTime;        //创建时间
    private Integer cityId;         //城市ID
    private Integer communityId;    //小区ID

    private String address;         //地址
    private String firstImg;        //主图
}
