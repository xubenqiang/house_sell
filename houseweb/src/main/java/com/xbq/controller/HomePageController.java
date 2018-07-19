package com.xbq.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.dao.HouseMapper;
import com.xbq.biz.model.House;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
public class HomePageController {

    @Autowired
    private HouseMapper houseMapper;

    @GetMapping("")
    public String startPage(){
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(ModelMap modelMap){

        Condition condition = Condition.create();
        condition.setSqlSelect("*");
        condition.orderBy("create_time");
        Page<House> page = new Page<>(1,8);
        List<House> houses = houseMapper.selectPage(page,condition);
        log.info("总共的房子有：" + houses.size());
        modelMap.put("recomHouses",houses);
        return "homepage/index";
    }


}
