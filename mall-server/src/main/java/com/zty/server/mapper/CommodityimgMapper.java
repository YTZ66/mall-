package com.zty.server.mapper;

import com.zty.server.pojo.Commodityimg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-04-23
 */
public interface CommodityimgMapper extends BaseMapper<Commodityimg> {

    /**
     * 根据id查询图片信息
     * @param id 商品id
     * @return 返回图片信息
     */
    List<Commodityimg> getCommodityimgInfo(Integer id);
}
