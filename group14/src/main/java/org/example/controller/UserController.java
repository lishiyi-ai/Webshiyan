package org.example.controller;

import io.jsonwebtoken.Claims;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public Result login(User user) {
        System.out.println(user);
        User u = userService.login(user);
        System.out.println(u);
        if (u != null) {
            //自定义信息
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("name", u.getName());
            //使用JWT工具类，生成身份令牌
            String token = JwtUtils.generateJwt(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }
    @RequestMapping("/getUsername")
    @ResponseBody
    public Result logout(HttpServletRequest request){
        String jwt=request.getHeader("Authorization");
        Claims claims=JwtUtils.parseJWT(jwt);
        String name=(String) claims.get("name");
        return Result.success(name);
    }
}
