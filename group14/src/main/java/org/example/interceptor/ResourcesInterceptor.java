package org.example.interceptor;

import io.jsonwebtoken.Claims;
import org.example.utils.JwtUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ResourcesInterceptor extends HandlerInterceptorAdapter{
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)throws Exception{
        if (Objects.equals(request.getMethod(), "OPTIONS")){
            System.out.println("OPTIONS");
            return true;
        }
        //获取请求路径
        String url=request.getRequestURI();
        if(url.indexOf("login")>=0){
            return true;
        }
        String token = request.getHeader("Authorization");
        System.out.println(token);
        Claims claims= JwtUtils.parseJWT(token);
        return true;
    }
}
