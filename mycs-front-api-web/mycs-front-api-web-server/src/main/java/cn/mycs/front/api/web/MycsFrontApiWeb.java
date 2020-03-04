package cn.mycs.front.api.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
/**
  *<p>web--api</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/3 14:56
  *</pre>
  */
@EnableFeignClients("cn.mycs.front.api.web.feign.interfaces")
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableCaching
public class MycsFrontApiWeb {

    public static void main(String[] args) {
        SpringApplication.run(MycsFrontApiWeb.class, args);
    }
}
