package com.xbq.biz.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@TableName("house_user")
@Data
@ToString
public class HouseUser {

    private Long id;
    private Long houseId;
    private Long userId;
    private Date createTime;
    private Integer type;

}
