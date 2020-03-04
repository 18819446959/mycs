package cn.mycs.service.member.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>会员购买检测</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:49
 * </pre>
 */
public interface MemberServerPayProvider {

    /**
     * 检测是否购买成功
     *
     * @param uid     用户id
     * @param orderId 要检测的订单id
     * @return 会员特权说明
     */
    @RequestMapping(value = "/memberServer/pay/checked/{uid}/{orderId}", method = RequestMethod.GET)
    JsonResult checkedOrder(@PathVariable("uid") Long uid, @PathVariable("orderId") String orderId);

}
