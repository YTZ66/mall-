package com.zty.server.service;

import com.zty.server.pojo.RespBean;
import com.zty.server.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface IUserService extends IService<User> {

    RespBean login(String username, String password, String code, HttpServletRequest request);

    User getUserName(String username);

    RespBean updatePassword(String newPassword, String oldPassword, Integer userId);

    RespBean insertUser(String avatar, String username, String password, String name, Integer gender, String phone);

    User updateUser(Integer id, String name, String phone, Integer gender);

    RespBean updateAvatar(Integer userId, String avatar);

    User selectById(Integer id);

    List<User> selectByUsername(String username);
}
