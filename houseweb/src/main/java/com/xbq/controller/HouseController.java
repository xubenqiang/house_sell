package com.xbq.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.xbq.biz.model.House;
import com.xbq.service.HouseService;
import com.xbq.vo.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

    /**
     * 分页获取房屋列表
     * 1：支持小区搜索，类型搜索
     * 2：支持排序
     * 3：分页
     * 4：支持展示图片，价格，标题，地址等信息
     * @return
     */
    @GetMapping("/list")
    public String houseList(Integer pageSize,
                            Integer pageNum,
                            House query,
                            ModelMap modelMap){
        Page<House> page = new Page<>(pageNum,pageSize);
        Page<House> housePage = houseService.getHouseList(page,query);
        PageData<House> pageData = PageData.buildPage(pageNum,pageSize,(long)page.getTotal(),page.getRecords());
        modelMap.put("ps",pageData);
        modelMap.put("vo",query);
        return "house/listing";
    }


}
