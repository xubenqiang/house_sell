package com.xbq.vo;

import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@ToString
public class UserVo {

    private String name;
    private String phone;
    private String email;
    private String passwd;
    private String confirmPasswd;
    private String newPasswd;
    private String avatar;  //头像图片
    private MultipartFile avator;
    private Integer type;   //用户类型，1-普通用户，2-经纪人
    private Integer enable; //是否激活
    private Long agencyId;
    private String key;




}
