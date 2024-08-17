//package cn.uppp.springboot.template.spring.config;
//
//import cn.hutool.core.util.StrUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.interceptor.CacheResolver;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.cache.interceptor.NamedCacheResolver;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
///**
// * 缓存配置器
// *
// * @author liudongdong
// * @date 2024/8/15
// */
//@Configuration
//public class CacheConfig extends CachingConfigurerSupport {
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Override
//    public CacheResolver cacheResolver() {
//        return new NamedCacheResolver(cacheManager, "");
//    }
//
//    @Override
//    public KeyGenerator keyGenerator() {
//        return (target, method, params) -> {
//            StringBuilder sb = new StringBuilder();
//            // 统一前缀
//            sb.append("cache");
//            sb.append(StrUtil.COLON);
//            // 类名
//            sb.append(target.getClass().getSimpleName());
//            sb.append(StrUtil.COLON);
//            // 方法名
//            sb.append(method.getName());
//            if (params.length > 0) {
//                sb.append(StrUtil.COLON);
//                sb.append(StringUtils.arrayToDelimitedString(params, "_"));
//            }
//            return sb.toString();
//        };
//    }
//}
