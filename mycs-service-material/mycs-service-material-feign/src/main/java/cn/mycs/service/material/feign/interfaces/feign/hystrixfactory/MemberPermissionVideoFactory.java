package cn.mycs.service.material.feign.interfaces.feign.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;

import cn.mycs.service.material.feign.interfaces.feign.MemberPermissionVideoClient;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>请求视频观看权限降级处理工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/12/2 18:15
 * </pre>
 */
public abstract class MemberPermissionVideoFactory implements FallbackFactory<MemberPermissionVideoClient> {

}

@Configuration
@ConditionalOnMissingBean(MemberPermissionVideoFactory.class)
class DefaultMemberPerVideoVideoClient {

    @Bean
    public static MemberPermissionVideoFactory memberPermaterialVideoFactory() {
        return FallbackFactoryUtil.makeFactory(MemberPermissionVideoFactory.class);
    }
}
