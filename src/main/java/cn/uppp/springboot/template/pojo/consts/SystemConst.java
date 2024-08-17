package cn.uppp.springboot.template.pojo.consts;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 系统常量
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SystemConst {
    /**
     * 域名（开发环境）
     */
    public static final String DOMAIN_DEV = "https://dev.com";
    /**
     * 域名（生产环境）
     */
    public static final String DOMAIN_PROD = "https://prod.com";
    /**
     * 排除不需要认证的路径
     */
    public static final String[] EXCLUDE_URLS = new String[]{
            "/login", "/logout",// 基础接口
            "/doc.html", "/webjars/**", "/swagger-resources/**", "/v3/api-docs", // Swagger接口
    };
}
