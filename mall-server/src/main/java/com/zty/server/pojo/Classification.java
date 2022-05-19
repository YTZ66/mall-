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

/**
 * <p>
 * 
 * </p>
 *
 * @author zty
 * @since 2022-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Classification对象", description="")
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "分类表id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "分类层级")
    private Integer hierarchy;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Classification> subclass;

    @ApiModelProperty(value = "商品表字段")
    @TableField(exist = false)
    private List<Commodity> commodity;

    @ApiModelProperty(value = "商品查看表字段")
    @TableField(exist = false)
    private List<Commoditycheck> commoditychecks;
}
