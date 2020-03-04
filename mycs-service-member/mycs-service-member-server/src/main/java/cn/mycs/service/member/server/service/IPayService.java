package cn.mycs.service.member.server.service;

import cn.mycs.core.base.restful.JsonResult;

/**
 * <p>支付服务接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:52
 * </pre>
 */
public interface IPayService {
    /**
     * 检测用户订单
     *
     * @param uid     用户
     * @param orderId 订单id
     * @return 订单是否成功
     */
    JsonResult checkedOrder(Long uid, String orderId);
}
