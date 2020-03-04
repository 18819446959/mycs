package cn.mycs.service.member.feign.interfaces;

import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.material.provider.interfaces.feign.MemberVideoProvider;
import cn.mycs.service.member.feign.interfaces.hystrixfactory.ShareFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * <p>分享客户端</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 15:21
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_SERVICE_VIDEO_SERVER, fallbackFactory = ShareFallbackFactory.class)
public interface ShareClient extends MemberVideoProvider {
}