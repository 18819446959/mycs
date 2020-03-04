package cn.mycs.service.material.feign.interfaces.feign.hystrixfactory;

import cn.mycs.core.util.FallbackFactoryUtil;
import cn.mycs.service.material.feign.interfaces.feign.StudyLogClient;
import feign.hystrix.FallbackFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>添加学习日志降级工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/12/2 18:15
 * </pre>
 */
public abstract class StudyLogFallbackFactory implements FallbackFactory<StudyLogClient> {

}

@Configuration
@ConditionalOnMissingBean(StudyLogFallbackFactory.class)
class DefaultStudyLogClient {

    @Bean
    public static StudyLogFallbackFactory studyLogFallbackFactory() {
        return FallbackFactoryUtil.makeFactory(StudyLogFallbackFactory.class);

    }
}
