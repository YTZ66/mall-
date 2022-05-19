package com.zty.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zty.server.config.security.component.JwtTokenUtil;
import com.zty.server.pojo.RespBean;
import com.zty.server.pojo.User;
import com.zty.server.mapper.UserMapper;
import com.zty.server.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private UserMapper userMapper;

    /**
     * 登录后返回token
     *
     * @param username 用户账号
     * @param password 密码
     * @param request  请求
     * @return 返回token
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        //查询username是否存在，将根据查询到的username的信息保存
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
        //通过session获取验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        //验证码是否正确
        if (ObjectUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)) {
            return RespBean.error("验证码错误，请重新输入！");
        }
        //根据用户将用户信息存入到userDetails中来判断用户名是否正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        //判断userDetails是否为空，密码是否相同
        if (userDetails == null) {
            return RespBean.error("用户名不正确");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("密码不正确");
        }
        //更新security登录用户对象 对用户信息进行验证
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //将用户对象存放到security全局上下文中
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        map.put("user", user);

        return RespBean.success("登录成功", map);
    }

    /**
     * 获取用户信息
     *
     * @param username 用户账号
     * @return 返回用户信息
     */
    @Override
    public User getUserName(String username) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
    }

    /**
     * 修改密码
     *
     * @param newPassword 新密码
     * @param oldPassword 旧密码
     * @param userId      用户id
     * @return 返回修改数据
     */
    @Override
    public RespBean updatePassword(String newPassword, String oldPassword, Integer userId) {
        User user = userMapper.selectById(userId);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //判断旧密码是否与用户现在的密码是否相同
        if (bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            //设置新密码
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));//加密
            int result = userMapper.updateById(user);
            if (result == 1) {
                return RespBean.success("修改成功！");
            }
        }
        return RespBean.error("修改失败！");
    }

    /**
     * 注册
     *
     * @return 返回行数据
     */
    @Override
    public RespBean insertUser(String avatar,
                               String username,
                               String password,
                               String name,
                               Integer gender,
                               String phone) {
            User user = new User();
            user.setAvatar(avatar);
            user.setUsername(username);
            user.setPassword(password);
            user.setName(name);
            user.setGender(gender);
            user.setPhone(phone);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            //加密的密码重新放到用户中
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            if (userMapper.insert(user) == 1) {
                return RespBean.success("注册成功！");
            }

        return RespBean.error("注册失败！");
    }

    /**
     * 编辑个人资料
     *
     * @param id     用户id
     * @param name   昵称
     * @param phone  手机号
     * @param gender 性别
     * @return 返回user数据
     */
    @Override
    public User updateUser(Integer id, String name, String phone, Integer gender) {
        User user = userMapper.selectById(id);
        user.setName(name);
        user.setPhone(phone);
        user.setGender(gender);
        userMapper.updateById(user);
        return user;
    }

    /**
     * 更新头像
     *
     * @param userId 用户id
     * @param avatar 头像
     * @return 返回头像数据
     */
    @Override
    public RespBean updateAvatar(Integer userId, String avatar) {
        User user = userMapper.selectById(userId);
        user.setAvatar(avatar);
        if (userMapper.updateById(user) == 1) {
            return RespBean.success("更新成功！", avatar);
        }
        return RespBean.error("更新失败！");
    }

    /**
     * 根据用户id查询用户信息
     * @return 返回行数据
     */
    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据username查询是否存在该username
     * @param username 用户账号
     * @return 返回行数据
     */
    @Override
    public List<User> selectByUsername(String username) {
        return userMapper.selectUserName(username);
    }
}
