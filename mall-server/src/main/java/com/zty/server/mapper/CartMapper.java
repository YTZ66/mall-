package com.zty.server.mapper;

import com.zty.server.pojo.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 根据购物车id修改购物数量金额
     * @param id 购物车id
     * @return 返回数据
     */
    int updateCartAndAmountAndQuantity(Integer id, Integer quantity, Double amount);

    /**
     * 根据购物车id修改购物车套餐
     * @param id 购物车id
     * @return 返回数据
     */
    int updateCartAndCombo(Integer id, Integer comboId, String comboNameId, String comboName, Double unitprice, Double amount);

    /**
     * 根据id查询购物车信息
     * @param cartId 购物车id
     * @return 返回数据
     */
    Cart selectCartById(Integer cartId);
}
