package cn.mycs.front.api.web.feign.interfaces.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;
import cn.mycs.front.api.web.feign.interfaces.DemoClient;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>分享客户端熔断降级工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/12/2 18:15
 * </pre>
 */
public abstract class BaseDemoFallbackFactory implements FallbackFactory<DemoClient> {

}

@Configuration
@ConditionalOnMissingBean(BaseDemoFallbackFactory.class)
class DefaultShareClient {

    @Bean
    public static BaseDemoFallbackFactory defaultDemoClient() {
        return FallbackFactoryUtil.makeFactory(BaseDemoFallbackFactory.class);

    }
}
