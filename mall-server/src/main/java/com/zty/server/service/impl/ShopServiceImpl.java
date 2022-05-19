package com.zty.server.service.impl;

import com.zty.server.pojo.Shop;
import com.zty.server.mapper.ShopMapper;
import com.zty.server.service.IShopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Resource
    private ShopMapper shopMapper;

    @Override
    public Shop getShopInfo(Integer id) {
        return shopMapper.selectById(id);
    }
}
