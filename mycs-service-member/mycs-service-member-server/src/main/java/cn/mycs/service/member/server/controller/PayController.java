package cn.mycs.service.member.server.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.member.provider.interfaces.feign.MemberServerPayProvider;
import cn.mycs.service.member.server.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>支付</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:52
 * </pre>
 */
@RestController
@RequestMapping
public class PayController implements MemberServerPayProvider {
    @Autowired
    private IPayService payService;

    @Override
    public JsonResult checkedOrder(@PathVariable("uid") Long uid, @PathVariable("orderId") String orderId) {
        return payService.checkedOrder(uid, orderId);
    }
}
