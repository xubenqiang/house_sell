package com.xbq.controller;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.dao.AgentMapper;
import com.xbq.biz.dao.HouseMapper;
import com.xbq.biz.dao.HouseUserMapper;
import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.House;
import com.xbq.biz.model.User;
import com.xbq.service.UserService;
import com.xbq.vo.PageData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/agency")
@Slf4j
public class AgencyController {

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String agencyList(ModelMap modelMap,
                             @RequestParam(value = "pageSize",defaultValue = "4") Integer pageSize,
                             @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                             User query){
        Page<User> page = new Page<>(pageNum,pageSize);
        Condition condition = Condition.create();
        condition.setSqlSelect("*");
        condition.eq("type",2);
        List<User> userList = userMapper.selectPage(page,condition);
        log.info("page中的总数量为：" + page.getTotal());
        PageData<User> pageData = PageData.buildPage(pageNum,pageSize,(long)page.getTotal(),userList);

        modelMap.put("ps",pageData);
        modelMap.put("vo",query);


        return "/user/agency/agentList";
    }


    @GetMapping("/agentDetail")
    public String agencyDetail(Long id,ModelMap modelMap){

        User user = userMapper.selectById(id);
        modelMap.put("agent",user);
        //获取跟用户绑定的房产信息
        List<House> houseList = userService.getHousesByUserId(id);
        //PageData<House> bindHouse = PageData.buildPage(1,5,10L,houseList);
        modelMap.put("bindHouses",houseList);

        return "/user/agent/agentDetail";
    }


}
