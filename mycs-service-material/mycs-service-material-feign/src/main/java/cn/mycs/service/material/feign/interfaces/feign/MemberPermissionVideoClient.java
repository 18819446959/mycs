package cn.mycs.service.material.feign.interfaces.feign;

import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.material.feign.interfaces.feign.hystrixfactory.MemberPermissionVideoFactory;
import cn.mycs.service.member.provider.interfaces.feign.MemberServerVideoProvider;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * <p>获取分类</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 11:14
 * </pre>
 */
@FeignClient(name = FeignClientConstant.Service.MYCS_SERVICE_MEMBER_SERVER, fallbackFactory = MemberPermissionVideoFactory.class)
public interface MemberPermissionVideoClient extends MemberServerVideoProvider {
}
