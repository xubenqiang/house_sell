package com.xbq.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.dao.*;
import com.xbq.biz.model.House;
import com.xbq.biz.model.HouseUser;
import com.xbq.biz.model.User;
import com.xbq.constant.CommonConstants;
import com.xbq.service.HouseService;
import com.xbq.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @Autowired
    private CommunityMapper communityMapper;

    @Autowired
    private HouseMapper houseMapper;

    @Autowired
    private HouseUserMapper houseUserMapper;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 分页获取房屋列表
     * 1：支持小区搜索，类型搜索
     * 2：支持排序
     * 3：分页
     * 4：支持展示图片，价格，标题，地址等信息
     * @return
     */
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    public String houseList(@RequestParam(value = "pageSize",defaultValue = "2") Integer pageSize,
                            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                            House query,
                            ModelMap modelMap){
        Page<House> page = new Page<>(pageNum,pageSize);

        Page<House> housePage = houseService.getHouseList(page,query);
        PageData<House> pageData = PageData.buildPage(pageNum,pageSize,(long)page.getTotal(),housePage.getRecords());
        modelMap.put("ps",pageData);
        modelMap.put("vo",query);

        return "house/listing";
    }


    @GetMapping("/toAdd")
    public String toAdd(ModelMap modelMap){
        modelMap.addAttribute("communitys",communityMapper.selectList(null));
        modelMap.addAttribute("citys",communityMapper.selectList(null));
        return "/house/add";
    }

    /*@GetMapping("/add")
    public String add(House house, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(CommonConstants.USER_ATTRIBUTE);
        house
    }*/

    /**
     * 查询房屋详情
     * 查询关联经纪人
     * @param id
     * @param modelMap
     * @return
     */
    @GetMapping("/detail")
    public String houseDetail(Long id,ModelMap modelMap){
        House house = houseMapper.selectById(id);
        HouseUser houseUser = houseUserMapper.selectByHouseId(house.getId());
        Long userId = houseUser.getUserId();
        if(userId != null && userId != 0){
            User user = userMapper.selectById(userId);
            modelMap.put("agent",user);
        }
        modelMap.put("house",house);
        return "/house/detail";
    }





}
