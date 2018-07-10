package com.xbq.controller;


import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.User;
import com.xbq.service.UserService;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public ModelAndView hello(Map map){
        User user = userMapper.selectById(7);
        log.info("数据库获取到的用户为：" + user.toString());
        ModelAndView view = new ModelAndView("hello");
        view.addObject("user",user);
        return view;
    }

    /**
     * 注册提交
     * step1: 注册验证
     * step2：发送邮件
     * step3：验证失败重定向到注册页面
     * 注册页面获取：根据Account对象判断
     * @return
     */
    @PostMapping("account/register")
    public ModelAndView register(@RequestBody UserVo account){
        if(account == null || account.getName() == null){
            return new ModelAndView("/user/accounts/register");
        }

        //用户注册信息验证
        ResultMsg resultMsg = userService.validate(account);
        if(resultMsg.isSuccess()){
            //用户验证通过
            return new ModelAndView("/user/accounts/registerSubmit");
        }else {
            return new ModelAndView("redirect:/accounts/register?" + resultMsg.asUrlParams());
        }


    }
}
