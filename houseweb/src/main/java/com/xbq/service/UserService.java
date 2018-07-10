package com.xbq.service;

import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;

public interface UserService {

    ResultMsg validate(UserVo userVo);
}
