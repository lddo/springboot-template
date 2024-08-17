package cn.uppp.springboot.template.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 系统配置
 *
 * @author liudongdong
 * @date 2020/11/18
 */
@Configuration
public class SystemConfig {

    /**
     * 线程池配置
     *
     * @return
     */
    @Bean
    public ExecutorService executorService() {
        return Executors.newWorkStealingPool();
    }
}
