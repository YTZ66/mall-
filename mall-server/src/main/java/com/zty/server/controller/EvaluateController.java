package com.zty.server.controller;

import com.zty.server.service.IEvaluateService;
import com.zty.server.util.RespPageBean;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Resource
    private IEvaluateService evaluateService;

    @ApiOperation(value = "商品评价总数")
    @GetMapping("/EvaluationTotal")
    public HashMap<String, Object> getCommodityEvaluation(Integer id) {
        return evaluateService.getCommodityEvaluation(id);
    }

    @ApiOperation(value = "商品评论信息查询")
    @GetMapping("/EvaluationPage")
    public RespPageBean getCommodityEvaluationPage(Integer id,
                                                   Integer type,
                                                   @RequestParam(defaultValue = "1") Integer currenPage,
                                                   @RequestParam(defaultValue = "5") Integer size) {
        return evaluateService.getCommodityEvaluationPage(id, type, currenPage, size);
    }
}
