package cn.mycs.service.bi.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>数据报表服务</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/8/28 11:54
 * </pre>
 */
@SpringBootApplication(exclude = {JmsAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.mycs.service.bi.feign.interfaces")
@ComponentScan(value = {"cn.mycs.core", "cn.mycs.core.util", "cn.mycs.service.bi", "cn.mycs.server"})
public class BiServerApp {
    public static void main(String[] args) {
        SpringApplication.run(BiServerApp.class, args);
    }
}
