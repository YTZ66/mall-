package com.zty.server.controller;

import com.zty.server.pojo.RespBean;
import com.zty.server.pojo.User;
import com.zty.server.pojo.UserLoginParam;
import com.zty.server.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "登录后返回token和用户信息")
    @PostMapping("/login")
    public RespBean login(@RequestBody UserLoginParam loginParam, HttpServletRequest request) {
        return userService.login(loginParam.getUsername(), loginParam.getPassword(), loginParam.getCode(), request);
    }

    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("/user/info")
    public User getUserInfo(Principal principal) {
        if (principal == null) {
            return null;
        }
        String username = principal.getName();
        User user = userService.getUserName(username);
        user.setPassword(null);
        return user;
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout() {
        return RespBean.success("退出登录成功！");
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("/revise")
    public RespBean revise(@RequestBody Map<String, Object> info) {
        //新密码
        String newPassword = (String) info.get("newPassword");
        //旧密码
        String oldPassword = (String) info.get("oldPassword");
        //获取id
        Integer userId = (Integer) info.get("userId");
        return userService.updatePassword(newPassword, oldPassword, userId);
    }

    @ApiOperation(value = "编辑个人资料")
    @PutMapping("/changeUser")
    public User changeUser(@RequestBody Map<String, Object> info) {
        Integer id = (Integer) info.get("id");
        String name = (String) info.get("name");
        String phone = (String) info.get("phone");
        Integer gender = (Integer) info.get("gender");
        return userService.updateUser(id, name, phone, gender);
    }
}
