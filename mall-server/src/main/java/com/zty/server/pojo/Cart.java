package com.zty.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Cart对象", description="")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购物车商品id")
    @TableId(value = "cartid", type = IdType.AUTO)
    private Integer cartid;

    @ApiModelProperty(value = "用户id")
    private Integer userid;

    @ApiModelProperty(value = "商品id")
    private Integer commodityid;

    @ApiModelProperty(value = "商品名称")
    private String commodiyname;

    @ApiModelProperty(value = "商品图片")
    private String commoditypicture;

    @ApiModelProperty(value = "店铺id")
    private Integer shopid;

    @ApiModelProperty(value = "店铺名称")
    private String shopname;

    @ApiModelProperty(value = "单价")
    private Double unitprice;

    @ApiModelProperty(value = "金额")
    private Double amount;

    @ApiModelProperty(value = "套餐id")
    private Integer comboid;

    @ApiModelProperty(value = "选择套餐名称id")
    private String comboNameid;

    @ApiModelProperty(value = "套餐名称")
    private String comboname;

    @ApiModelProperty(value = "选中的套餐属性")
    private String choose;

    @ApiModelProperty(value = "数量")
    private Integer quantity;

    @ApiModelProperty(value = "checkbox框选项")
    private boolean checkbox;
}
