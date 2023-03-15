package com.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zzy
 * @since 2023-02-23
 */
@RestController
@RequestMapping("/front/user")
public class UserController {

    @Autowired
    private IUserService userService;

    //用户登录
    @PostMapping("/login")
    public ZZYResult<Boolean> userLogin(HttpServletRequest request, String username,String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        wrapper.eq("user_type",0);
        User user = userService.getOne(wrapper);
        HttpSession session = request.getSession();
        session.setAttribute("userId",user.getId());
        return user == null ? ZZYResult.getInstance(200,"用户不存在",false) : ZZYResult.getInstance(200,"登录成功",true);
    }


    //用户退出
    @GetMapping("/logout")
    public ZZYResult<Boolean> userLogout(HttpServletRequest request){
        request.getSession(false).removeAttribute("userId");
        return ZZYResult.getInstance(200,"用户退出",true);
    }

    //用户注册
    @PostMapping("/register")
    public ZZYResult<Boolean> userRegister(User user){
        boolean isRegister = false;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        wrapper.eq("user_type","0");
        User user1 = userService.getOne(wrapper);
        if (user1 == null){
            user.setUserType("0");
            userService.save(user);
            isRegister = true;
        }
        return !isRegister ? ZZYResult.getInstance(200,"用户存在",false) : ZZYResult.getInstance(200,"用户注册成功",true);
    }

    //用户修改信息
    @PostMapping("/upload")
    public ZZYResult<Boolean> userUpload(HttpServletRequest request , User user){
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        user.setId((Integer) userId);
        return userService.updateById(user) ? ZZYResult.getInstance(200,"用户修改成功",true) : ZZYResult.getInstance(200,"用户修改失败",false);
    }

    //用户信息
    @GetMapping("/Info")
    public ZZYResult<User> Info(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        Object userId = session.getAttribute("userId");
        return ZZYResult.getInstance(200,"用户信息",userService.getById((Integer) userId));
    }




}
