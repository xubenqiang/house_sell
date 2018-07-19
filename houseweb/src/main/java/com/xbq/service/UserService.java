package com.xbq.service;

import com.xbq.biz.model.House;
import com.xbq.biz.model.User;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;

import java.util.List;

public interface UserService {

    ResultMsg validate(UserVo userVo);

    User auth(String username, String passwords);

    void updateUser(User user);

    /**
     * 获取跟userId绑定的 House信息
     * @param userId
     * @return
     */
    List<House> getHousesByUserId(Long userId);
}
