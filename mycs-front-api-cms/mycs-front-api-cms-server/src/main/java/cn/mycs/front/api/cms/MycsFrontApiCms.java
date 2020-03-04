package cn.mycs.front.api.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  *<p>cms启动类</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/3 14:56
  *</pre>
  */
@SpringBootApplication
@EnableDiscoveryClient
public class MycsFrontApiCms {
    public static void main(String[] args) {
        SpringApplication.run(MycsFrontApiCms.class, args);
    }
}
