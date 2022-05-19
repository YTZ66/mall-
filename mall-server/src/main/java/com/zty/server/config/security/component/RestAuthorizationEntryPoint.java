package com.zty.server.config.security.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zty.server.pojo.RespBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/***
 * 当未登录或者token失效时访问接口时，自定义返回结果
 */
@Component
public class RestAuthorizationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        //文本输出流打印对象的格式化表示形式
        PrintWriter out = response.getWriter();
        RespBean bean = RespBean.error("尚未登录，请登录!");
        bean.setCode(401);
        //通过writeValueAsString方法 可以把 对象转json串、List转Json串、Map转json串
        out.write(new ObjectMapper().writeValueAsString(bean));
        out.flush();//清空缓冲区
        out.close();//关闭
    }
}
