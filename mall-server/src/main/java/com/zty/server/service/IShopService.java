package com.zty.server.service;

import com.zty.server.pojo.Shop;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface IShopService extends IService<Shop> {

    Shop getShopInfo(Integer id);
}
