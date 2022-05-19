package com.zty.server.controller;


import com.zty.server.pojo.Cart;
import com.zty.server.pojo.Combo;
import com.zty.server.pojo.RespBean;
import com.zty.server.service.ICartService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Resource
    private ICartService cartService;


    @ApiOperation(value = "添加购物车")
    @PostMapping("/addCart")
    public RespBean addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    @ApiOperation(value = "删除购物车")
    @DeleteMapping("/deleteCart")
    public RespBean deleteCart(Integer id) {
        return cartService.deleteCart(id);
    }

    @ApiOperation(value = "修改购物车数量金额")
    @PutMapping("/updateCartAndAmountAndQuantity")
    public RespBean updateCartAndAmountAndQuantity(Integer id, Integer unitprice) {
        return cartService.updateCartAndAmountAndQuantity(id, unitprice);
    }

    @ApiOperation(value = "根据购物车id修改购物车套餐")
    @PutMapping("/updateCartAndCombo")
    public RespBean updateCartAndCombo(Integer id, String comboName) {
        return cartService.updateCartAndCombo(id, comboName);
    }

    @ApiOperation(value = "根据用户id查询购物车商品信息")
    @GetMapping("/selectUserOfCartInfo")
    public Map<String, Object> selectUserOfCartInfo(Integer id){
        return cartService.selectUserOfCartInfo(id);
    }

    @ApiOperation(value = "根据套餐id查询套餐信息")
    @GetMapping("/selectComboInfo")
    public List<Combo> selectComboInfo(Integer id){
        return cartService.selectComboInfo(id);
    }
}
