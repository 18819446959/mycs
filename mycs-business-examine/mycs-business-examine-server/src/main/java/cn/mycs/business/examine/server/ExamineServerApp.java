package cn.mycs.business.examine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  *<p>考核服务启动类</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/3 12:32
  *</pre>
  */
@SpringBootApplication(exclude = {JmsAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.mycs.business.examine.feign.interfaces")
@ComponentScan(value = {"cn.mycs.core", "cn.mycs.core.util", "cn.mycs.business.examine.server", "cn.mycs.server"})
public class ExamineServerApp {
    public static void main(String[] args) {
        SpringApplication.run(ExamineServerApp.class, args);
    }
}
