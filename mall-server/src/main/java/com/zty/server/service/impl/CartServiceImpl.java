package com.zty.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zty.server.mapper.ComboMapper;
import com.zty.server.mapper.ShopMapper;
import com.zty.server.mapper.UserMapper;
import com.zty.server.pojo.*;
import com.zty.server.mapper.CartMapper;
import com.zty.server.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Resource
    private CartMapper cartMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ShopMapper shopMapper;

    @Resource
    private ComboMapper comboMapper;

    /**
     * 插入购物车
     *
     * @param cart 购物车实体类
     * @return 返回行数据
     */
    @Override
    public RespBean addCart(Cart cart) {
        if (cartMapper.insert(cart) == 1) {
            Map<String, Object> map = new HashMap<>();
            map.put("userid", cart.getUserid());
            map.put("commodityid", cart.getCommodityid());
            map.put("commodiyname", cart.getCommodiyname());
            map.put("commoditypicture", cart.getCommoditypicture());
            map.put("shopid", cart.getShopid());
            map.put("shopname", cart.getShopname());
            map.put("unitprice", cart.getUnitprice());
            map.put("amount", cart.getAmount());
            map.put("comboid", cart.getComboid());
            map.put("comboNameid", cart.getComboNameid());
            map.put("comboname", cart.getComboname());
            map.put("choose", cart.getChoose());
            map.put("quantity", cart.getQuantity());//数量
            cart.setCheckbox(false);
            return RespBean.success("添加成功！", map);
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 根据购物车id删除购物车信息
     *
     * @param id 购物车id
     * @return 返回信息
     */
    @Override
    public RespBean deleteCart(Integer id) {
        int result = cartMapper.deleteById(id);
        if (result == 1) {
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    /**
     * 根据购物车id修改购物车数量金额
     *
     * @param id 购物车id
     * @return 返回数据
     */
    @Override
    public RespBean updateCartAndAmountAndQuantity(Integer id, Integer quantity) {
        //根据id查询该商品是否存在
        Cart data = cartMapper.selectCartById(id);
        int result = cartMapper.updateCartAndAmountAndQuantity(id, quantity, quantity * data.getUnitprice());
        if (result == 1) {
            return RespBean.success("修改成功！");
        }

        return RespBean.error("修改失败！");
    }

    /**
     * 根据购物车id修改购物车套餐
     *
     * @param id 购物车id
     * @return 返回数据
     */
    @Override
    public RespBean updateCartAndCombo(Integer id, String comboName) {
        //根据id查询购物车是否存在
        Cart cart = cartMapper.selectCartById(id);
        Combo combo = comboMapper.selectOne(new QueryWrapper<Combo>().eq("name", comboName));
        int result = cartMapper.updateCartAndCombo(id, combo.getId(), combo.getComboid(), comboName, combo.getPrice(), combo.getPrice()*cart.getQuantity());
        if(result == 1){
            return RespBean.success("修改成功！");
        }
        return RespBean.error("修改失败！");
    }

    /**
     * 根据用户id查询购物车商品信息
     *
     * @param id 用户id
     * @return 返回行数据
     */
    @Override
    public Map<String, Object> selectUserOfCartInfo(Integer id) {
        //查询用户信息
        User resultUser = userMapper.selectById(id);
        if (resultUser != null) {
            List<Shop> shopOfCart = shopMapper.selectShopAndCartInfo(id);
            Map<String, Object> map = new HashMap<>();
            map.put("resultUser", resultUser);
            map.put("shopOfCart", shopOfCart);

            return map;
        }
        return null;
    }

    /**
     * 根据id查询套餐信息
     * @param id 套餐id
     * @return 返回数据
     */
    @Override
    public List<Combo> selectComboInfo(Integer id) {
        return comboMapper.selectList(new QueryWrapper<Combo>().eq("commodityid", id));
    }


}
