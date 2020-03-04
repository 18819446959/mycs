package cn.mycs.front.api.web.feign.interfaces;

import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.front.api.web.feign.interfaces.hystrixfactory.BaseDemoFallbackFactory;
import cn.mycs.service.user.provider.interfaces.feign.DemoProvider;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>测试例子</p>
 * <pre>
 * @author gitamacai
 * @date 2020/3/3 16:18
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_SERVICE_USER_SERVER,path = "/demo",fallbackFactory = BaseDemoFallbackFactory.class)
public interface DemoClient extends DemoProvider {
}
