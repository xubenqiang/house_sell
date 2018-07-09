package com.xbq.web;

import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = userMapper.selectById(7);
        System.out.println(user);
    }
}