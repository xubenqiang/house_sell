package com.xbq.biz.model;


import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.ToString;

@TableName("agency")
@Data
@ToString
public class Agent {

    private Integer id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private String aboutUs;
    private String mobile;
    private String webSite;
}
