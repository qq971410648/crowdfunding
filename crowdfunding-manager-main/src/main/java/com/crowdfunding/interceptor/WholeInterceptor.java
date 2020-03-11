package com.crowdfunding.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
public class WholeInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        List<String> list = new ArrayList<>();
        list.add("/crowdfunding_manager_main_war_exploded/crowdfunding/to_Login.htm");
        list.add("/crowdfunding_manager_main_war_exploded/crowdfunding/to_Login.do");
        list.add("/crowdfunding_manager_main_war_exploded/user/doLogin.do");
        list.add("/crowdfunding_manager_main_war_exploded/user/doLogin.htm");
        list.add("/crowdfunding_manager_main_war_exploded/user/doLogout.do");
        list.add("/crowdfunding_manager_main_war_exploded/user/doLogout.htm");

        String requestURI = httpServletRequest.getRequestURI();
        System.out.println(requestURI);///crowdfunding_manager_main_war_exploded/crowdfunding/to_Login.htm

        if (list.contains(requestURI)) {
            //直接放行
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        Object username = session.getAttribute("username");
        if (username!=null) {
            //表示已经登录了
            return true;
        }

        session.setAttribute("login_error","请先登录");
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/crowdfunding/to_Login.htm");
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
