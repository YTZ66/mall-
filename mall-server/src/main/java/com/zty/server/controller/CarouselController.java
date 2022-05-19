package com.zty.server.controller;


import com.zty.server.pojo.Carousel;
import com.zty.server.service.ICarouselService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-04-07
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Resource
    private ICarouselService carouselService;

    @ApiOperation(value = "查询所有轮播器表")
    @GetMapping("/list")
    public List<Carousel> listCarousel(){
        return carouselService.list();
    }
}
