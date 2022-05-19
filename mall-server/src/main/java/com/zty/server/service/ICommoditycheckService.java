package com.zty.server.service;

import com.zty.server.pojo.Commoditycheck;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zty.server.util.RespPageBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-04-09
 */
public interface ICommoditycheckService extends IService<Commoditycheck> {

    RespPageBean getCommodityCheckPage(Integer currenPage, Integer size, Commoditycheck commoditycheck);
}
