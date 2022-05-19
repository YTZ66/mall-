package com.zty.server.service;

import com.zty.server.pojo.Classification;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zty
 * @since 2022-03-28
 */
public interface IClassificationService extends IService<Classification> {

    List<Classification> getClassification();

    List<Classification> getClassificationAndCommodity();
}
