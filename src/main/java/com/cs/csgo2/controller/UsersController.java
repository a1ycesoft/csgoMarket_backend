package com.cs.csgo2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.cs.csgo2.common.JwtUtils;
import com.cs.csgo2.common.Res;
import com.cs.csgo2.entity.Users;
import com.cs.csgo2.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
@Slf4j
public class UsersController {
    @Autowired
    private UsersService usersService;
    // 注册
    @PostMapping("/register")
    public Res<Users> register(Users user){
        log.info(user.toString());
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",user.getUserName());
        Users u = usersService.getOne(queryWrapper);
        if(u!=null)
            return Res.error("用户名重复！");
        usersService.save(user);
        return Res.success(user);
    }
    // 登录
    @PostMapping ("/login")
    public Res<String> login(HttpServletRequest request, String username, String password) {
        QueryWrapper<Users> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_name",username).eq("password",password);
        Users user=usersService.getOne(queryWrapper);
        if (user == null) {
            return Res.error("用户名或者密码错误");
        } else {
            String token = JwtUtils.createToken(user.getUserId().toString(), user.getPassword());
            request.getSession().setAttribute("users", user.getUserId());
            return Res.success(token);
        }
    }
    // 获取所有用户信息
    @GetMapping("/getAll")
    public Res<List<Users>> getAll(){
        return Res.success(usersService.list());
    }

    // 获取单个用户信息
    @GetMapping
    public Res<Users> getUserInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        return Res.success(usersService.getById(userId));
    }
    // 修改用户密码
    @PostMapping("/changePassword")
    public Res<String> changePassword(HttpServletRequest request,String beforePassword,String afterPassword){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        Users user=usersService.getById(userId);
        if(user.getPassword().equals(beforePassword))
        {
            user.setPassword(afterPassword);
            usersService.updateById(user);
            return Res.success("修改成功");
        }
        else
        {
            return Res.error("修改失败");
        }

    }
    // 修改头像
    @PostMapping("/changeAvatar")
    public Res<String> changeAvatar(HttpServletRequest request,String filename){
        String token = request.getHeader("Authorization");
        String userId = JwtUtils.getAudience(token);
        Users user=usersService.getById(userId);
        user.setAvatar(filename);
        usersService.updateById(user);
        return Res.success("修改成功");
    }
}
