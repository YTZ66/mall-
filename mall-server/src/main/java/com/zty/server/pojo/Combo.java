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
@ApiModel(value="Combo对象", description="")
public class Combo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "关联表id")
    private Integer commodityid;

    @ApiModelProperty(value = "选择套餐名称id")
    private String comboid;

    @ApiModelProperty(value = "套餐名称")
    private String name;

    @ApiModelProperty(value = "选中套餐")
    private String choose;

    @ApiModelProperty(value = "不同套餐的价格")
    private Double price;
}
