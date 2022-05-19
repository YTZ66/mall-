package com.zty.server.service.impl;

import com.zty.server.mapper.ComboMapper;
import com.zty.server.mapper.CommodityimgMapper;
import com.zty.server.mapper.ShopMapper;
import com.zty.server.pojo.Combo;
import com.zty.server.pojo.Commodity;
import com.zty.server.mapper.CommodityMapper;
import com.zty.server.pojo.Commodityimg;
import com.zty.server.pojo.Shop;
import com.zty.server.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {

    @Resource
    private CommodityMapper commodityMapper;

    @Resource
    private CommodityimgMapper commodityimgMapper;

    @Resource
    private ComboMapper comboMapper;

    @Resource
    private ShopMapper shopMapper;

    /**
     * 按月销最大值查询前四条数据
     *
     * @return 返回行数据
     */
    @Override
    public List<Commodity> getCommodityOfFour() {
        return commodityMapper.getCommodityOfFour();
    }

    /**
     * 根据商品id查询商品详情
     *
     * @param id 商品id
     * @return 返回行数据
     */
    @Override
    public Map<String, Object> getProductDetails(Integer id) {
        //查询商品基本信息
        Commodity commodity = commodityMapper.getCommodityInfo(id);
        if(commodity != null){
            //查询店铺信息
            Shop shop = shopMapper.getShopInfo(id);
            //查询套餐信息
            List<Combo> combos = comboMapper.getComboInfo(id);
            //查询图片
            List<Commodityimg> commodityimgs = commodityimgMapper.getCommodityimgInfo(id);
            //存在map中
            HashMap<String, Object> map = new HashMap<>();
            map.put("shop", shop);
            map.put("commodity", commodity);
            map.put("combos", combos);
            map.put("commodityimgs", commodityimgs);

            return map;
        }
        return null;
    }


}
