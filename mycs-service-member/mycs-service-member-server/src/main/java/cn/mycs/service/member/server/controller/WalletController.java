package cn.mycs.service.member.server.controller;

import cn.mycs.core.base.restful.JsonResult;

import cn.mycs.service.member.provider.bean.dto.ApplyWithDrawDto;
import cn.mycs.service.member.provider.bean.dto.BindWxDto;
import cn.mycs.service.member.provider.bean.dto.BuyBillBizDto;
import cn.mycs.service.member.provider.bean.dto.CommissionBizDto;
import cn.mycs.service.member.provider.bean.dto.WithdrawBizDto;
import cn.mycs.service.member.provider.interfaces.feign.MemberServerWalletProvider;
import cn.mycs.service.member.server.exception.BillDetailException;
import cn.mycs.service.member.server.service.IWalletService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>钱包功能controller</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/3 13:49
 * </pre>
 */
@Api(description = "钱包接口")
@RestController
@RequestMapping()
public class WalletController implements MemberServerWalletProvider {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private IWalletService walletService;


    @Override
    public JsonResult<WithdrawBizDto> withdrawDetail(@PathVariable("businessId") String businessId) {
        log.debug("提现明细：{}", businessId);
        try {
            WithdrawBizDto withdrawBizDto = walletService.withDrawBillDetail(businessId);
            return JsonResult.success(withdrawBizDto);
        } catch (BillDetailException e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @Override
    public JsonResult<BuyBillBizDto> buyDetail(@PathVariable("businessId") String businessId) {
        log.debug("购买明细：{}", businessId);
        try {
            BuyBillBizDto buyBillBizDto = walletService.buyBillDetail(businessId);
            return JsonResult.success(buyBillBizDto);
        } catch (BillDetailException e) {
            return JsonResult.fail(e.getMessage());
        }
    }

    @Override
    public JsonResult<CommissionBizDto> commissionDetail(@PathVariable("businessId") String businessId) {
        log.debug("佣金明细：{}", businessId);
        try {
            CommissionBizDto commissionBizDto = walletService.commissionBillDetail(businessId);
            return JsonResult.success(commissionBizDto);
        } catch (BillDetailException e) {
            return JsonResult.fail(e.getMessage());
        }

    }

    @Override
    public JsonResult<ApplyWithDrawDto> withdraw(@RequestParam("uid") Long uid, @RequestParam("type") Integer type, @RequestParam("money") Float money) {
        log.debug("提现，提现金额：{}", money);
        ApplyWithDrawDto withdraw = walletService.withdraw(uid, type, money);
        return JsonResult.success(withdraw);
    }


    @Override
    public JsonResult<BindWxDto> isBindWechat(@PathVariable("uid") Long uid) {
        log.debug("是否已经绑定微信,检测的用户id：{}", uid);
        BindWxDto bindWechat = walletService.isBindWechat(uid);
        return JsonResult.success(bindWechat);
    }


    @Override
    public JsonResult withDrawNotify(@PathVariable("withdrawApplyId") String withdrawApplyId, @PathVariable("status") Integer status, @RequestParam("msg") String msg) {
        return walletService.withDrawNotify(withdrawApplyId, status, msg);
    }
}
