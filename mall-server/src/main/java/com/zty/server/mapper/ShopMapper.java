package com.zty.server.mapper;

import com.zty.server.pojo.Shop;
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
public interface ShopMapper extends BaseMapper<Shop> {

    /**
     * 根据商品id查询店铺信息
     * @param id 商品id
     * @return 返回行数据
     */
    Shop getShopInfo(Integer id);

    /**
     * 根据店铺id查询购物车商品信息
     * @param userId 用户id
     * @return 返回行数据
     */
    List<Shop> selectShopAndCartInfo(Integer userId);
}
