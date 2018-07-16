package com.xbq.service.impl;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.dao.HouseMapper;
import com.xbq.biz.model.House;
import com.xbq.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;

    //分页获取房屋列表，带查询条件
    public Page<House> getHouseList(Page<House> page,House house){

        //Page<House> page = new Page<>(pageNumber,pageSize);
        Condition condition = Condition.create();
        condition.setSqlSelect("*");
        if(!StringUtils.isEmpty(house.getType())){
            condition.eq("type",house.getType());
        }
        if(!StringUtils.isEmpty(house.getName())){
            //根据小区模糊查询
            condition.like("name",house.getName());
        }


        condition.orderBy("price",true);

        List<House> houseList = houseMapper.selectPage(page,condition);
        page.setRecords(houseList);

        return page;
    }

}
