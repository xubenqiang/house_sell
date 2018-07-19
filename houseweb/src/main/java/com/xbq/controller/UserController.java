package com.xbq.controller;


import com.mysql.jdbc.StringUtils;
import com.xbq.biz.dao.UserMapper;
import com.xbq.biz.model.User;
import com.xbq.constant.CommonConstants;
import com.xbq.service.UserService;
import com.xbq.vo.ResultMsg;
import com.xbq.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/accounts")
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
    @PostMapping("/register")
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

    /**
     * 用户登录
     * @param request
     * @param username：用户名
     * @param password：密码
     * @param target：目标地址
     * @return
     */
    @RequestMapping(value = "/signin",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView signin(HttpServletRequest request,String username,String password,String target){
        ModelAndView view = new ModelAndView();
        if(username == null || password == null){
            request.setAttribute("target",target);
            view.setViewName("/user/accounts/signin");
            return view;
        }

        //验证用户登录信息
        User user = userService.auth(username,password);
        if(user == null){
            view.setViewName("redirect:/accounts/signin?"+"target="+target+"&username="+username
            +"&" + ResultMsg.errorMsg("用户名密码错误").asUrlParams());
        }else {
            //将用户信息存放在session中
            HttpSession session = request.getSession(true);
            session.setAttribute(CommonConstants.USER_ATTRIBUTE,user);
            session.setAttribute(CommonConstants.PLAIN_USER_ATTRIBUTE,user);

            if(!StringUtils.isNullOrEmpty(target)){
                view.setViewName("redirect:"+target);
            }else {
                view.setViewName("redirect:/index");
            }
        }
        return view;
    }


    /**
     * 登出
     * @param request
     * @return
     */
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        ModelAndView view = new ModelAndView("redirect:/index");
        return view;
    }


    /**
     * 个人页面
     * 1、提供页面信息
     * 2、更新用户信息
     * @param updateUser
     * @return
     */
    @GetMapping("/profile")
    public String profile(User updateUser,HttpServletRequest request){
        if(updateUser.getEmail() == null){
            return "/accounts/profile";
        }

        //更新用户
        userService.updateUser(updateUser);

        User user = userMapper.selectById(updateUser.getId());
        //更新session中的user
        request.getSession().setAttribute(CommonConstants.USER_ATTRIBUTE,user);
        return "redirect:/accounts/profile?" + ResultMsg.successMsg("更新成功").asUrlParams();

    }

}
