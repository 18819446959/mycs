package cn.mycs.service.member.feign.interfaces.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;
import cn.mycs.service.member.feign.interfaces.WithdrawApplyClient;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>提现客户端熔断降级工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/12/2 18:15
 * </pre>
 */
public abstract class WithdrawFallbackFactory implements FallbackFactory<WithdrawApplyClient> {

}

@Configuration
@ConditionalOnMissingBean(WithdrawFallbackFactory.class)
class DefaultWithdrawApplyClient {

    @Bean
    public static WithdrawFallbackFactory defaultWithdrawApplyClient() {
        return FallbackFactoryUtil.makeFactory(WithdrawFallbackFactory.class);

    }
}
