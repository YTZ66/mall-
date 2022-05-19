package com.zty.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="Evaluate对象", description="")
public class Evaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评价id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "商品id")
    private Integer productid;

    @ApiModelProperty(value = "商品名称")
    private String productname;

    @ApiModelProperty(value = "订单项")
    private String order;

    @ApiModelProperty(value = "评论用户id")
    private Integer userid;

    @ApiModelProperty(value = "是否匿名")
    private Integer anonymous;

    @ApiModelProperty(value = "评价类型（1好评，0中评，-1差评）")
    private Integer type;

    @ApiModelProperty(value = "评价等级;1：好评 2：中评 3：差评")
    private Integer grade;

    @ApiModelProperty(value = "评价内容")
    private String information;

    @ApiModelProperty(value = "评价晒图(JSON {img1:url1,img2:url2}  )")
    private String blueprint;

    @ApiModelProperty(value = "评价时间;可为空")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private String time;

    @ApiModelProperty(value = "是否回复（0:未回复，1:已回复）")
    private Integer reply;

    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDateTime replytime;

    @ApiModelProperty(value = "是否显示（1:是，0:否）")
    private Integer show;

    @ApiModelProperty(value = "用户实体类")
    @TableField(exist = false)
    private List<User> userList;

    @ApiModelProperty(value = "商品实体类")
    @TableField(exist = false)
    private Commodity commodity;
}
