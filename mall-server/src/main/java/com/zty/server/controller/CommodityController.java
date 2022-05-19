package com.zty.server.controller;


import com.zty.server.pojo.Commodity;
import com.zty.server.service.ICommodityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private ICommodityService commodityService;

    @ApiOperation(value = "获取所有商品")
    @GetMapping("/listCommodity")
    public List<Commodity> getCommodity(){
        return commodityService.list();
    }

    @ApiOperation(value = "按月销最大值查询前四条数据")
    @GetMapping("/listCommodityOfFour")
    public List<Commodity> getCommodityOfFour(){
        return commodityService.getCommodityOfFour();
    }

    @ApiOperation(value = "根据商品id查询商品详情")
    @GetMapping("/selectCombo")
    public Map<String, Object> getSelectCombo(Integer id){
        return commodityService.getProductDetails(id);
    }
}
