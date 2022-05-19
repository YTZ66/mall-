package com.zty.server.controller;


import com.zty.server.pojo.Classification;
import com.zty.server.service.IClassificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-28
 */
@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Resource
    private IClassificationService classificationService;

    @ApiOperation(value = "分类列表查询")
    @GetMapping("/list")
    public List<Classification> getClassification() {
        return classificationService.getClassification();
    }

    @ApiOperation(value = "根据分类列表查询商品列表")
    @GetMapping("/listByCommodity")
    public List<Classification> getClassificationAndCommodity(){
        return classificationService.getClassificationAndCommodity();
    }
}
