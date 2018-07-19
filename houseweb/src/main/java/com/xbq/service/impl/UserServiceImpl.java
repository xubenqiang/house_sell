package com.xbq.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.xbq.biz.dao.HouseMapper;
import com.xbq.biz.dao.HouseUserMapper;
import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.House;
import com.xbq.biz.model.HouseUser;
import com.xbq.biz.model.User;
import com.xbq.service.UserService;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.pattern.PathPattern;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private HouseMapper houseMapper;

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

    /**
     * 验证用户信息
     * @param username
     * @param password
     * @return
     */
    @Override
    public User auth(String username, String password) {
        User user = userMapper.selectByUserNameAndPassword(username,password);
        return user;
    }


    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        userMapper.updateById(user);
    }


    public List<User> getUsers(){
        return userMapper.selectList(null);
    }



    @Override
    public List<House> getHousesByUserId(Long userId){
        Condition houseUserCondition = Condition.create();
        houseUserCondition.setSqlSelect("*");
        houseUserCondition.eq("user_id",userId);

        List<HouseUser> houseUserList = houseUserMapper.selectList(houseUserCondition);
        List<Long> houseIds = Lists.newArrayList();
        for (HouseUser hu : houseUserList) {
            houseIds.add(hu.getHouseId());
        }
        List<House> houseList = houseMapper.selectBatchsByHouseIds(houseIds);

        return houseList;
    }

}
