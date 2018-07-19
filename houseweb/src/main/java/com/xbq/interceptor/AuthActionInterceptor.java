package com.xbq.interceptor;

import com.xbq.biz.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
public class AuthActionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        //用户没有登陆，直接从定向到登陆页
        User user = UserContext.getUser();
        if(user == null){
            String msg = URLEncoder.encode("请登录","UTF-8");
            String target = URLEncoder.encode(request.getRequestURL().toString(),"UTF-8");
            /*if("GET".equalsIgnoreCase(request.getMethod())){
                response.sendRedirect("/accounts/signin?errorMsg=" + msg + "&target=" + target);
            }else {
                response.sendRedirect("/accounts/signin?errorMsg=" + msg);
            }*/
            response.sendRedirect("/accounts/signin");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
