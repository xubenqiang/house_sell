package com.xbq.biz.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.xbq.biz.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    List<User> getUserList(Pagination page);

    List<User> getUserListByCondition(/*Pagination pagination, */@Param("ew") Wrapper<User> wrapper);

    User selectByUserNameAndPassword(@Param("username") String username,@Param("password") String password);

}

