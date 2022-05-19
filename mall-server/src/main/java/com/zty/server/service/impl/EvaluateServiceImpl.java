package com.zty.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zty.server.pojo.Evaluate;
import com.zty.server.mapper.EvaluateMapper;
import com.zty.server.service.IEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zty.server.util.RespPageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@Service
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements IEvaluateService {

    @Resource
    private EvaluateMapper evaluateMapper;

    /**
     * 根据商品id查询评价等级
     *
     * @param id 商品id
     * @return 返回行数据
     */
    @Override
    public HashMap<String, Object> getCommodityEvaluation(Integer id) {
        //查询当前商品的评价总数
        Integer total = evaluateMapper.selectCount(new QueryWrapper<Evaluate>().eq("commodityid", id));
        //查询商品好评评价数
        Integer goodTotal = evaluateMapper.selectGoodTypeById(id);
        //根据商品id查询商品中评评价数
        Integer midTotal = evaluateMapper.selectMidTypeById(id);
        //根据商品id查询商品差评评价数
        Integer badTotal = evaluateMapper.selectBadTypeById(id);
        //计算好评率
        double percent = (Double.parseDouble(goodTotal + "") / Double.parseDouble(total + "")) * 100;
        String percentValue = (percent + "").substring(0, (percent + "").lastIndexOf(".") + 2);

        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("goodTotal", goodTotal);
        map.put("midTotal", midTotal);
        map.put("badTotal", badTotal);
        map.put("percent", percentValue);

        if (map.size() == 0) {
            return null;
        }

        return map;
    }

    @Override
    public RespPageBean getCommodityEvaluationPage(Integer id,
                                                   Integer type,
                                                   Integer currenPage,
                                                   Integer size) {
        //开启分页
        Page<Evaluate> page = new Page<>(currenPage, size);
        IPage<Evaluate> evaluateIPage = evaluateMapper.selectEvaluateByProductId(id, type, page);
        return new RespPageBean(evaluateIPage.getTotal(), evaluateIPage.getRecords());
    }
}
