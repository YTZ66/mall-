package com.zty.server.service;

import com.zty.server.pojo.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.server.pojo.Combo;
import com.zty.server.pojo.RespBean;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface ICartService extends IService<Cart> {

    RespBean addCart(Cart cart);

    RespBean deleteCart(Integer id);

    RespBean updateCartAndAmountAndQuantity(Integer id, Integer quantity);

    RespBean updateCartAndCombo(Integer id, String comboName);

    Map<String, Object> selectUserOfCartInfo(Integer id);

    List<Combo> selectComboInfo(Integer id);
}
