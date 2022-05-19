package com.zty.server.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zty.server.pojo.Evaluate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface EvaluateMapper extends BaseMapper<Evaluate> {

    /**
     * 根据商品id查询商品中评评价数
     *
     * @param id 商品id
     * @return 返回行数据
     */
    int selectMidTypeById(Integer id);

    /**
     * 根据商品id查询商品好评评价数
     *
     * @param id 商品id
     * @return 返回行数据
     */
    int selectGoodTypeById(Integer id);

    /**
     * 根据商品id查询商品差评评价数
     *
     * @param id 商品id
     * @return 返回行数据
     */
    int selectBadTypeById(Integer id);

    /**
     * 根据商品id查询商品评价信息
     * @param id 商品id
     * @return 返回分页数据
     */
    IPage<Evaluate> selectEvaluateByProductId(@Param("id") Integer id, @Param("type") Integer type, Page<Evaluate> page);
}
