package com.zty.server.mapper;

import com.zty.server.pojo.Combo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface ComboMapper extends BaseMapper<Combo> {

    /**
     * 根据id查询套餐信息
     * @param id 商品id
     * @return 返回套餐信息
     */
    List<Combo> getComboInfo(Integer id);
}
