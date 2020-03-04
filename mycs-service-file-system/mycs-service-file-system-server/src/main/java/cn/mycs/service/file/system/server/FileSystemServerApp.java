package cn.mycs.service.file.system.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  *<p>文件服务启动</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/3 11:34
  *</pre>
  */
@SpringBootApplication(exclude = {JmsAutoConfiguration.class})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "cn.mycs.service.file.system.feign.interfaces")
@ComponentScan(value = {"cn.mycs.core", "cn.mycs.core.util", "cn.mycs.service.file.system", "cn.mycs.server"})
public class FileSystemServerApp {
    public static void main(String[] args) {
        SpringApplication.run(FileSystemServerApp.class, args);
    }
}
