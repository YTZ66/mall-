package com.zty.server.controller;


import com.zty.server.pojo.Shop;
import com.zty.server.service.IShopService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Resource
    private IShopService shopService;

    @ApiOperation(value = "根据id查询店铺信息")
    @GetMapping("/shopInfo")
    public Shop getShopInfo(Integer id){
        return shopService.getShopInfo(id);
    }
}
