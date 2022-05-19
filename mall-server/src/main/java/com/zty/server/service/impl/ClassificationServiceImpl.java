package com.zty.server.service.impl;

import com.zty.server.pojo.Classification;
import com.zty.server.mapper.ClassificationMapper;
import com.zty.server.service.IClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-28
 */
@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification> implements IClassificationService {

    @Resource
    private ClassificationMapper classificationMapper;

    /**
     * 分类列表查询
     * @return 返回行数据
     */
    @Override
    public List<Classification> getClassification() {
        return classificationMapper.getClassification();
    }

    /**
     * 根据分类表字段查询商品列表
     * @return 返回行数据
     */
    @Override
    public List<Classification> getClassificationAndCommodity() {
        return classificationMapper.getClassificationAndCommodity();
    }
}
