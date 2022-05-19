package com.zty.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RespBean对象", description = "封装接口返回给前端的数据")
public class RespBean {
    /**
     * 响应给前端的状态码
     */
    @ApiModelProperty(value = "响应状态码", dataType = "login")
    private long code;
    /**
     * 响应给前端的提示信息
     */
    @ApiModelProperty("响应提示信息")
    private String message;
    /**
     * 响应给前端的数据
     */
    @ApiModelProperty("响应数据")
    private Object obj;

    /**
     * 成功返回提示
     *
     * @param message 响应提示
     * @return 返回提示
     */
    public static RespBean success(String message) {
        return new RespBean(200, message, null);
    }

    /**
     * 成功返回提示和数据
     *
     * @param message 响应提示
     * @param obj     响应数据
     * @return 返回提示和数据
     */
    public static RespBean success(String message, Object obj) {
        return new RespBean(200, message, obj);
    }

    /**
     * 成功返回提示和数据
     *
     * @param obj     响应数据
     * @return 返回提示和数据
     */
    public static RespBean success(Object obj) {
        return new RespBean(200, null, obj);
    }

    /**
     * 失败返回提示
     * @param message 响应提示
     * @return 返回提示
     */
    public static RespBean error(String message){
        return new RespBean(500,message,null);
    }

    /**
     * 失败返回提示和数据
     * @param message 响应提示
     * @param obj 响应 数据
     * @return 返回提示和数据
     */
    public static RespBean error(String message,Object obj){
        return new RespBean(500,message,obj);
    }
}
