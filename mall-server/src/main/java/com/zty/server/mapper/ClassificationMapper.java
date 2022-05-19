package com.zty.server.mapper;

import com.zty.server.pojo.Classification;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-28
 */
public interface ClassificationMapper extends BaseMapper<Classification> {

    /**
     * 分类列表查询
     * @return 返回查询
     */
    List<Classification> getClassification();

    /**
     * 根据分类表字段查询商品列表
     * @return 返回查询
     */
    List<Classification> getClassificationAndCommodity();
}
