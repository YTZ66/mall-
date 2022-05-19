package com.zty.server.controller;

import com.zty.server.pojo.RespBean;
import com.zty.server.pojo.User;
import com.zty.server.service.IUserService;
import com.zty.server.util.SystemConstant;
import com.zty.server.util.UUIDUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;


    @ApiOperation(value = "更换头像")
    @PostMapping("/avatar")
    public RespBean renewAvatar(@RequestParam("id") Integer userId,
                                @RequestParam("file") MultipartFile file) {
        //判断文件是否为null
        if (file.isEmpty()) return RespBean.error("文件为空！");
        //判断文件大小
        if (file.getSize() > SystemConstant.AVATAR_MAX_SIZE) {
            return RespBean.error("文件大小超出限制！");
        }
        //判断文件类型是否是我们规定的后缀类型
        String contentType = file.getContentType();//获取文件类型
        //如果集合包含某个元素则返回值为true
        if (!SystemConstant.AVATAR_TYPE.contains(contentType)) {
            return RespBean.error("文件类型不支持！");
        }
        //上传文件.../avatar/文件.png
        File dir = new File("C:/project/hotel/avatar/");
        //检测目录是否存在
        if (!dir.exists()) {
            dir.mkdirs();//创建当前目录
        }
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀名
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        //截取后缀名
        String suffix = originalFilename.substring(index);
        //文件名+后缀名
        String filename = UUIDUtils.randomUUID() + suffix;
        //将文件夹和文件放入file中
        File dest = new File(dir, filename);
        //参数file中的数据写入到这个空文件夹中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userService.updateAvatar(userId, filename);
    }

    @ApiOperation(value = "注册账号")
    @PostMapping("/register")
    public RespBean register(String username,
                             String password,
                             String name,
                             Integer gender,
                             String phone,
                             @RequestParam("file") MultipartFile file) {
        //判断文件是否为null
        if (file.isEmpty()) {
            RespBean.error("文件为空！");
        }
        //判断文件的大小是否超出限制
        if (file.getSize() > SystemConstant.AVATAR_MAX_SIZE) {
            RespBean.error("文件大小超出限制！");
        }
        //判断文件类型是否是规定的后缀类型
        String contentType = file.getContentType();//获取文件类型
        //如果集合包含某个元素则返回值为true
        if (!SystemConstant.AVATAR_TYPE.contains(contentType)) {
            RespBean.error("文件类型不支持！");
        }
        //上传文件.../avatar/文件.png
        File dir = new File("C:/project/hotel/avatar/");
        //检测目录是否存在
        if (!dir.exists()) {
            dir.mkdirs();//创建文件目录
        }
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件的后缀名
        assert originalFilename != null;
        int index = originalFilename.lastIndexOf(".");
        //截取后缀名
        String suffix = originalFilename.substring(index);
        //文件名+后缀名
        String filename = UUIDUtils.randomUUID() + suffix;
        //将文件夹和文件放到file中
        File dest = new File(dir, filename);
        //参数file中的数据写入到这个空文件夹中
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userService.insertUser(filename, username, password, name, gender, phone);
    }

    @ApiOperation(value = "根据用户id查询用户信息")
    @GetMapping("/selectById")
    public User selectById(Integer id) {
        return userService.selectById(id);
    }


    @ApiOperation(value = "根据username查询是否存在该username")
    @GetMapping("/selectByUsername")
    public List<User> selectByUsername(String username){
        return userService.selectByUsername(username);
    }
}
