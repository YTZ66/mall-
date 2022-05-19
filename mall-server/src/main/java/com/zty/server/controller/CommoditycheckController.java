package com.zty.server.controller;


import com.zty.server.pojo.Commoditycheck;
import com.zty.server.service.ICommoditycheckService;
import com.zty.server.util.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 *
 * @since 2022-04-09
 */
@RestController
@RequestMapping("/commoditycheck")
public class CommoditycheckController {

    @Resource
    private ICommoditycheckService commoditycheckService;

    @ApiOperation(value = "根据分类id或商品name查询商品分页信息")
    @GetMapping("/listCommodityPage")
    public RespPageBean getCommodityCheckPage(@RequestParam(defaultValue = "1") Integer currenPage,
                                              @RequestParam(defaultValue = "10") Integer size,
                                              Commoditycheck commoditycheck){
        return commoditycheckService.getCommodityCheckPage(currenPage, size, commoditycheck);
    }
}
