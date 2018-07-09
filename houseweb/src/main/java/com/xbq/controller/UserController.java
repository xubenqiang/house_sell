package com.xbq.controller;


import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/hello")
    public ModelAndView hello(Map map){
        User user = userMapper.selectById(7);
        log.info("数据库获取到的用户为：" + user.toString());
        ModelAndView view = new ModelAndView("hello");
        view.addObject("user",user);
        return view;
    }
}
