package com.xbq.service.impl;

import com.xbq.service.UserService;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {


    /**
     * 验证用户注册信息
     * @return
     */
    @Override
    public ResultMsg validate(UserVo account) {
        if(StringUtils.isEmpty(account.getEmail())){
            return ResultMsg.errorMsg("Email 有误");
        }

        if(StringUtils.isEmpty(account.getName())){
            return ResultMsg.errorMsg("Name 有误");
        }

        if(StringUtils.isEmpty(account.getConfirmPasswd()) || StringUtils.isEmpty(account.getPasswd()) || !account.getPasswd().equals(account.getConfirmPasswd())){
            return ResultMsg.errorMsg("密码输入有误");
        }

        return ResultMsg.successMsg("");
    }
}
