package com.zty.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zty.server.pojo.Commoditycheck;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-04-09
 */
public interface CommoditycheckMapper extends BaseMapper<Commoditycheck> {

    /**
     * 根据分类id或商品name查询商品分页信息
     * @param page 分页
     * @param commoditycheck 类
     * @return 返回分页数据
     */
    IPage<Commoditycheck> getCommodityCheckPage(Page<Commoditycheck> page,
                                                @Param("commoditycheck") Commoditycheck commoditycheck);


}
