package com.zty.server.service;

import com.zty.server.pojo.Evaluate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.server.util.RespPageBean;

import java.util.HashMap;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface IEvaluateService extends IService<Evaluate> {

    /**
     * 根据商品id查询商品评价总数
     * @param id 商品id
     * @return 返回行数据
     */
    HashMap<String, Object> getCommodityEvaluation(Integer id);

    /**
     * 根据商品id或评价类型查询商品评价信息
     * @param id 商品id
     * @param type 评价类型
     * @param currenPage 当前页
     * @param size 总条数
     * @return 返回分页数据u
     */
    RespPageBean getCommodityEvaluationPage(Integer id, Integer type, Integer currenPage, Integer size);
}
