package com.easy.arch.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptors extends HandlerInterceptorAdapter {

    private final String[] NO_FILTER_PAGES = {"/a1","/log","/reg","/c1"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();

        for (String s: NO_FILTER_PAGES){
            if (s.equals(uri)){
                return true;
            }
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("name")==null){
            response.sendRedirect("/a1");
        }
        return true;
    }
}
