package com.zty.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zty.server.pojo.Commoditycheck;
import com.zty.server.mapper.CommoditycheckMapper;
import com.zty.server.service.ICommoditycheckService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.server.util.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-04-09
 */
@Service
public class CommoditycheckServiceImpl extends ServiceImpl<CommoditycheckMapper, Commoditycheck> implements ICommoditycheckService {

    @Resource
    private CommoditycheckMapper commoditycheckMapper;

    /**
     * 根据分类id或商品name查询商品分页信息
     * @param currenPage 当前页
     * @param size 总条数
     * @param commoditycheck 类
     * @return 返回分页信息
     */
    @Override
    public RespPageBean getCommodityCheckPage(Integer currenPage,
                                              Integer size,
                                              Commoditycheck commoditycheck) {
        //开启分页
        Page<Commoditycheck> page = new Page<>(currenPage, size);
        IPage<Commoditycheck> commoditycheckIPage = commoditycheckMapper.getCommodityCheckPage(page, commoditycheck);
        return new RespPageBean(commoditycheckIPage.getTotal(), commoditycheckIPage.getRecords());
    }
}
