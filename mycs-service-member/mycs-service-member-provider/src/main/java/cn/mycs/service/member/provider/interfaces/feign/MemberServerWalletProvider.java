package cn.mycs.service.member.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.member.provider.bean.dto.ApplyWithDrawDto;
import cn.mycs.service.member.provider.bean.dto.BuyBillBizDto;
import cn.mycs.service.member.provider.bean.dto.CommissionBizDto;
import cn.mycs.service.member.provider.bean.dto.WithdrawBizDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>钱包功能controller</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/3 13:49
 * </pre>
 */
public interface MemberServerWalletProvider {


    /**
     * 提现明细
     *
     * @param businessId 业务id
     * @return 提现明细
     */
    @RequestMapping(value = "/wallet/bill/detail/{businessId}/withdraw/", method = RequestMethod.GET)
    JsonResult<WithdrawBizDto> withdrawDetail(@PathVariable("businessId") String businessId);

    /**
     * 购买明细
     *
     * @param businessId 业务id
     * @return 购买明细
     */
    @RequestMapping(value = "/wallet/bill/detail/{businessId}/buy", method = RequestMethod.GET)
    JsonResult<BuyBillBizDto> buyDetail(@PathVariable("businessId") String businessId);

    /**
     * 分佣明细
     *
     * @param businessId 业务id
     * @return 分佣明细
     */
    @RequestMapping(value = "/wallet/bill/detail/{businessId}/commission", method = RequestMethod.GET)
    JsonResult<CommissionBizDto> commissionDetail(@PathVariable("businessId") String businessId);

    /**
     * 提现明细
     *
     * @param uid   用户id
     * @param type  提现方式
     * @param money 提现金额
     * @return 分佣明细
     */
    @RequestMapping(value = "/wallet/withdraw", method = RequestMethod.POST, consumes = "application/json")
    JsonResult<ApplyWithDrawDto> withdraw(@RequestParam("uid") Long uid, @RequestParam("type") Integer type, @RequestParam("money") Float money);


    /**
     * 是否已绑定微信
     *
     * @param uid 用户id
     * @return 是否已绑定微信
     */
    @RequestMapping(value = "/wallet/bind/wechat/{uid}", method = RequestMethod.GET)
    JsonResult isBindWechat(@PathVariable("uid") Long uid);

    /**
     * 提现成功/失败通知
     *
     * @param withdrawApplyId id
     * @param status          提现成功/失败，
     * @param msg             如果是提现失败，则会附带失败信息
     * @return 提现成功/失败
     */
    @RequestMapping(value = "/wallet/withdraw/notify/{withdrawApplyId}/{status}", method = RequestMethod.POST)
    JsonResult withDrawNotify(@PathVariable("withdrawApplyId") String withdrawApplyId, @PathVariable("status") Integer status, @RequestParam("msg") String msg);

}
