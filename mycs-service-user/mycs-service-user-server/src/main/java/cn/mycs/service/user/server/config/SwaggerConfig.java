package cn.mycs.service.user.server.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
  *<p>swagger配置</p>
  *
  *<pre>
  * @author gitamacai
  * @date 2020/3/1 22:05
  *</pre>
  */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(prefix = "swagger",value = "enable",havingValue = "true")
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        /*
         * 这里采用包含注解的方式来确定要显示的接口
         * apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
         *
         * 还可以采用包扫描的方式来确定要显示的接口
         * apis(RequestHandlerSelectors.basePackage("cn.mycs.cms.modular.system.controller"))
         */
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mycs-service-member DOC")
                .description("mycs-service-member wap文档")
                .contact("mycs")
                .version("1.0")
                .build();
    }

}
