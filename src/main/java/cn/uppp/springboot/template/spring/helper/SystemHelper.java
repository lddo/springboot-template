package cn.uppp.springboot.template.spring.helper;

import cn.uppp.springboot.template.pojo.consts.SystemConst;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 系统工具类
 *
 * @author liudongdong
 * @date 2024/8/15
 */
@Component
public class SystemHelper {
    private static ApplicationContext applicationContext;

    public SystemHelper(ApplicationContext applicationContext) {
        if (SystemHelper.applicationContext == null) {
            SystemHelper.applicationContext = applicationContext;
        }
    }

    public static boolean isLocal() {
        return isProfile("local");
    }

    public static boolean isDev() {
        return isProfile("dev");
    }

    public static boolean isProd() {
        return isProfile("prod");
    }

    public static String getDomain() {
        return isProfile("prod") ? SystemConst.DOMAIN_PROD : SystemConst.DOMAIN_DEV;
    }

    /**
     * 判断当前环境
     *
     * @return
     */
    private static boolean isProfile(String profile) {
        String[] profiles = applicationContext.getEnvironment().getActiveProfiles();
        Map<String, String> profileMap = Stream.of(profiles).collect(Collectors.toMap(Function.identity(), Function.identity()));
        return profileMap.get(profile) != null;
    }
}
