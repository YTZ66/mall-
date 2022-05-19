package com.zty.server.service;

import com.zty.server.pojo.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
public interface ICommodityService extends IService<Commodity> {

    List<Commodity> getCommodityOfFour();

    Map<String, Object> getProductDetails(Integer id);
}
