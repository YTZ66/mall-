package com.zty.server.config.security.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * JwtToken工具类
 */
@Component
public class JwtTokenUtil {

    /**
     * 用户名的key
     */
    private static final String CLAIM_KEY_USERNAME = "sub";
    /**
     * jwt创建时间的key
     */
    private static final String CLAIM_KEY_CREATED = "created";
    /**
     * jwt秘钥
     */
    @Value("${jwt.secret}")
    private String secret;
    /**
     * jwt失效时间
     */
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 根据用户信息生成token
     *
     * @param userDetails 用户信息
     * @return 返回生成的token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        //生成token key
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());//获取用户名称
        //生成token时间
        claims.put(CLAIM_KEY_CREATED, new Date());//设置时间
        return generateToken(claims);//返回给generateToken方法
    }

    /**
     * 根据荷载生成jwt token
     * @param claims 存放用户权限
     * @return 返回生成的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)//map中可存放用户权限信息
                .setExpiration(generateExpirationDate())//设置token过期时间
                .signWith(SignatureAlgorithm.HS512, secret)//设置加密方式和加密密码
                .compact();//返回一个字符串
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断token是否有效
     */
    public boolean validateToken(String token,UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否失效
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     * */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * 从token中获取荷载
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;

        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 生成token失效时间
     *
     * @return 返回失效时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
