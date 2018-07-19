package com.xbq.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.xbq.biz.dao.CommunityMapper;
import com.xbq.biz.dao.HouseMapper;
import com.xbq.biz.dao.HouseUserMapper;
import com.xbq.biz.model.Community;
import com.xbq.biz.model.House;
import com.xbq.biz.model.HouseUser;
import com.xbq.service.HouseService;
import com.xbq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class HouseServiceImplTest {

    @Autowired
    private HouseService houseService;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void getHouseList() {

        House house = new House();
        Page<House> page = new Page<>(1,10);
        Page<House> pageList = houseService.getHouseList(page,house);
        /*houseList.forEach(v ->{
            System.o ut.println(v.getId() + "：" + v.getName());
        });*/
        pageList.getRecords().forEach( v ->{
            System.out.println(v.getId() + "：" + v.getFirstImg());
        });

        //System.out.println("firstImg为：" + page.getTotal());

    }

    @Test
    public void test1(){
       /* List<Community> communities = communityMapper.selectList(null);
        communities.forEach( c -> {
            log.info(c.getCityName());
        });*/

       redisTemplate.opsForValue().set("18672673394","xbq");
    }

    @Test
    public void test2(){
       /* HouseUser houseUser = houseUserMapper.selectByHouseId(22L);
        log.info(houseUser.toString());*/
       /*List<Long> houseIds = Lists.newArrayList(22L,23L,25L);
       List<House> houses = houseMapper.selectBatchsByHouseIds(houseIds);
       houses.forEach(h -> {
           log.info(h.getName());
       });*/
        List<House> houseList = userService.getHousesByUserId(14L);
        houseList.forEach(h -> {
            log.info(h.toString());
        });

    }


}