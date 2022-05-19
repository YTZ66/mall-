package com.zty.server.mapper;

import com.zty.server.pojo.RespBean;
import com.zty.server.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名称查询用户是否存在
     * @param username 用户名称
     * @return 返回行数据
     */
    List<User> selectUserName(String username);
}
