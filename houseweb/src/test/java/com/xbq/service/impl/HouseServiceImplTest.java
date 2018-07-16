package com.xbq.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.model.House;
import com.xbq.service.HouseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseServiceImplTest {

    @Autowired
    private HouseService houseService;

    @Test
    public void getHouseList() {

        House house = new House();
        Page<House> page = new Page<>(1,10);
        page = houseService.getHouseList(page,house);
        /*houseList.forEach(v ->{
            System.o ut.println(v.getId() + "：" + v.getName());
        });*/
        page.getRecords().forEach( v ->{
            System.out.println(v.getId() + "：" + v.getName());
        });

        System.out.println("总共有：" + page.getTotal());

    }
}