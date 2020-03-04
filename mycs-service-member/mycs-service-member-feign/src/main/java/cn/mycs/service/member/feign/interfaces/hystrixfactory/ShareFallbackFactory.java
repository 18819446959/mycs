package cn.mycs.service.member.feign.interfaces.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;
import cn.mycs.service.member.feign.interfaces.ShareClient;
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
public abstract class ShareFallbackFactory implements FallbackFactory<ShareClient> {

}

@Configuration
@ConditionalOnMissingBean(ShareFallbackFactory.class)
class DefaultShareClient {

    @Bean
    public static ShareFallbackFactory defaultShareClient() {
        return FallbackFactoryUtil.makeFactory(ShareFallbackFactory.class);

    }
}
