package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.common.ZZYResult;
import com.demo.entity.User;
import com.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author zzy
 * @desc
 */
@RestController
@RequestMapping("/admin/user")
public class UserAdminController {

    @Autowired
    private IUserService userService;
    //管理员登录
    @PostMapping("/login")
    public ZZYResult<Boolean> userLogin(HttpServletRequest request, String username, String password){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("password",password);
        wrapper.eq("user_type",2);
        User user = userService.getOne(wrapper);
        HttpSession session = request.getSession();
        session.setAttribute("adminId",user.getId());

        return user == null ? ZZYResult.getInstance(200,"无权限",false) : ZZYResult.getInstance(200,"登录成功",true);
    }

    @PostMapping("/register")
    public ZZYResult<Boolean> userRegister(User user){
        boolean isRegister = false;
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",user.getUsername());
        wrapper.eq("user_type","2");
        User user1 = userService.getOne(wrapper);
        if (user1 == null){
            user.setUserType("2");
            userService.save(user);
            isRegister = true;
        }
        return !isRegister ? ZZYResult.getInstance(200,"用户存在",false) : ZZYResult.getInstance(200,"用户注册成功",true);
    }

    //管理员退出
    @GetMapping("/logout")
    public ZZYResult<Boolean> userLogout(HttpServletRequest request){
        request.getSession(false).removeAttribute("adminId");
        return ZZYResult.getInstance(200,"管理员退出",true);
    }

    //删除用户
    @DeleteMapping("/delUserById")
    public ZZYResult<Boolean> delUserById(Integer userId){
        return userService.removeById(userId) ? ZZYResult.getInstance(200,"删除用户成功",true) : ZZYResult.getInstance(200,"删除失败",false);
    }

    //查询所有用户
    @GetMapping("/allUsers")
    public ZZYResult<List<User>> allUsers(){
        return ZZYResult.getInstance(200,"查询所有用户成功",userService.list());
    }
}
