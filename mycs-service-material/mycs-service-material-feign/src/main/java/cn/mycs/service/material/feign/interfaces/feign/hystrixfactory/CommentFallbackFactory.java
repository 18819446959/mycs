package cn.mycs.service.material.feign.interfaces.feign.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;
import cn.mycs.service.material.feign.interfaces.feign.CommentClient;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>获取评论列表熔断降级工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/12/2 18:15
 * </pre>
 */
public abstract class CommentFallbackFactory implements FallbackFactory<CommentClient> {

}

@Configuration
@ConditionalOnMissingBean(CommentFallbackFactory.class)
class DefaultCommentClient {

    @Bean
    public static CommentFallbackFactory commentFallbackFactory() {
        return FallbackFactoryUtil.makeFactory(CommentFallbackFactory.class);

    }
}
