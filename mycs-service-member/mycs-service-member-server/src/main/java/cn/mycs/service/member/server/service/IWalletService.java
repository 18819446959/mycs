package cn.mycs.service.member.server.service;

import cn.mycs.core.base.restful.JsonResult;

import cn.mycs.service.member.provider.bean.dto.ApplyWithDrawDto;
import cn.mycs.service.member.provider.bean.dto.BindWxDto;
import cn.mycs.service.member.provider.bean.dto.BuyBillBizDto;
import cn.mycs.service.member.provider.bean.dto.CommissionBizDto;
import cn.mycs.service.member.provider.bean.dto.WithdrawBizDto;
import cn.mycs.service.member.server.exception.BillDetailException;

/**
 * <p>钱包服务接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 14:49
 * </pre>
 */
public interface IWalletService {


    /**
     * 提现
     *
     * @param uid   用户uid
     * @param type  提现方式
     * @param money 提现金额
     * @return 提现结果
     */
    ApplyWithDrawDto withdraw(Long uid, Integer type, Float money);


    /**
     * 购买账单明细
     *
     * @param businessId 账单id
     * @return 购买账单明细
     */
    BuyBillBizDto buyBillDetail(String businessId) throws BillDetailException;

    /**
     * 佣金收益账单明细
     *
     * @param businessId 账单id
     * @return 佣金收益账单明细
     */
    CommissionBizDto commissionBillDetail(String businessId) throws BillDetailException;

    /**
     * 提现账单明细
     *
     * @param businessId 账单id
     * @return 提现账单明细
     */
    WithdrawBizDto withDrawBillDetail(String businessId) throws BillDetailException;

    /**
     * 是否绑定微信
     *
     * @param uid 用户id
     * @return 是否绑定微信
     */
    BindWxDto isBindWechat(Long uid);

    /**
     * 更新提现状态
     *
     * @param withdrawApplyId 提现申请业务id
     * @param status          提现状态 3 提现成功，4，提现失败
     * @param msg             提现失败时会有信息
     * @return 更新
     */
    JsonResult withDrawNotify(String withdrawApplyId, Integer status, String msg);
}
