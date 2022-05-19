package com.zty.server.mapper;

import com.zty.server.pojo.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zty.server.pojo.RespBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface CommodityMapper extends BaseMapper<Commodity> {

    /**
     * 按月销最大值查询前四条数据
     * @return 返回行数据
     */
    List<Commodity> getCommodityOfFour();

    /**
     * 根据id查询商品信息
     * @param id 商品id
     * @return 返回商品信息
     */
    Commodity getCommodityInfo(Integer id);
}
