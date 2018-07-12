package com.xbq.service;

import com.xbq.biz.model.User;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;

public interface UserService {

    ResultMsg validate(UserVo userVo);

    User auth(String username, String passwords);

    void updateUser(User user);
}
