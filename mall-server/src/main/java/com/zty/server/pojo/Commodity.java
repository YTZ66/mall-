package com.zty.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zty
 * @since 2022-03-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Commodity对象", description="")
public class Commodity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "价格")
    private Double price;

    @ApiModelProperty(value = "商品图片")
    private String picture;

    @ApiModelProperty(value = "月销")
    private Integer monthlysales;

    @ApiModelProperty(value = "库存")
    private Integer quantity;

    @ApiModelProperty(value = "关联分类表id")
    private Integer classificationid;

    @ApiModelProperty(value = "关联分类表子分类id")
    private Integer subclassid;

    @ApiModelProperty(value = "关联店铺表id")
    private Integer shopid;

    @ApiModelProperty(value = "关联表id")
    private Integer associationid;

    @ApiModelProperty(value = "店铺实体类")
    @TableField(exist = false)
    private List<Shop> shops;

    @ApiModelProperty(value = "商品图片实体类")
    @TableField(exist = false)
    private List<Commodityimg> commodityimgs;

    @ApiModelProperty(value = "套餐实体类")
    @TableField(exist = false)
    private List<Combo> combos;
}
