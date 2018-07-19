package com.xbq.biz.model;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@TableName("user")
@Data
@ToString
public class User {
    private Integer id;
    private String name;
    private String phone;
    private String email;
    private String passwd;
    private String avatar;
    private Integer type;   //用户类型，1——普通用户，2——经纪人
    private Date createTime;
    private Integer enable;
    private Integer agencyId;
}
