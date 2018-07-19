package com.xbq.web;

import com.google.common.collect.Lists;
import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.User;
import com.xbq.constant.CommonConstants;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HouseApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void test(){
        User user = userMapper.selectById(7);
        System.out.println(user);
    }

    @Test
    public void testUserMapper() throws InvocationTargetException, IllegalAccessException {
       /* Condition condition = Condition.create();
        condition.setSqlSelect("*")
                .like("name","hello");*/
       /*List<User> users = userMapper.selectList(null); *//*userMapper.selectPage(new Page<>(1,10),condition);*//*
       for(User user : users){
           log.info(user.toString());
       }*/
        /*String username = "xbq@qq.com";
        String password = "123456";
        User user = userMapper.selectByUserNameAndPassword(username,password);
        //log.info(user.toString());
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user,vo);
        log.info(vo.toString());*/
        //User user = userMapper.selectById(30);
        ListOperations<String,String> listOperations = redisTemplate.opsForList();
       /* List<Long> houseIds = Lists.newArrayList(22L,23L,24L,25L,26L);
        System.out.println(houseIds.toString());*/
        listOperations.leftPushAll(CommonConstants.HOT_HOUSE_KEY,"22","23","24","25","26");

    }

    @Test
    public void testAsUrl(){
        //ResultMsg msg = new ResultMsg();
        String url = "http://localhost:8080/index?" + ResultMsg.successMsg("成功").asUrlParams();
        System.out.println(url);
    }
}