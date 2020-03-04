package cn.mycs.service.member.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.front.service.course.material.provider.VideoInfoProvider;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.user.provider.UserProvider;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.msc.support.exception.ServiceException;

import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.member.feign.bean.dto.BizCommissionRecordDto;
import cn.mycs.service.member.feign.bean.dto.WithdrawApplyDto;
import cn.mycs.service.member.feign.interfaces.BizCommissionRecordClient;
import cn.mycs.service.member.feign.interfaces.ShareClient;
import cn.mycs.service.member.feign.interfaces.WithdrawApplyClient;
import cn.mycs.service.member.provider.bean.dto.ApplyWithDrawDto;
import cn.mycs.service.member.provider.bean.dto.BindWxDto;
import cn.mycs.service.member.provider.bean.dto.BuyBillBizDto;
import cn.mycs.service.member.provider.bean.dto.CommissionBizDto;
import cn.mycs.service.member.provider.bean.dto.WithdrawBizDto;
import cn.mycs.service.member.server.exception.BillDetailException;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityMapper;
import cn.mycs.service.member.server.persistence.dao.MemberInviteRecordMapper;
import cn.mycs.service.member.server.persistence.dao.MemberJoinRecordMapper;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberInviteRecord;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import cn.mycs.service.member.server.service.IWalletService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>钱包服务接口实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 18:17
 * </pre>
 */
@Service
public class WalletServiceImpl implements IWalletService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserProvider userProvider;
    @Autowired
    private WithdrawApplyClient withdrawApplyClient;
    @Autowired
    private BizCommissionRecordClient bizCommissionRecordClient;
    @Autowired
    private MemberJoinRecordMapper memberJoinRecordMapper;
    @Autowired
    private MemberIdentityMapper memberIdentityMapper;
    @Autowired
    private ShareClient shareClient;
    @Autowired
    private MemberInviteRecordMapper inviteRecordMapper;
    @Autowired
    private VideoInfoProvider videoInfoProvider;

    @Override
    public ApplyWithDrawDto withdraw(Long uid, Integer type, Float money) {
        log.info("提现操作，提现用户id：{}，提现方式：{}，提现金额：{}", uid, type, money);
        /* 检测可提现账户中是否有足够的金额
        1、如果没有足够的金额则提示不能提现
        2、有，则在现金钱包中减去提现的金额
        3、将提现的信息写入提现申请表中
        * */
        WithdrawApplyDto withdrawApply = new WithdrawApplyDto();
        withdrawApply.setWithdrawApplyId(CreateIdUtil.createUUID());
        withdrawApply.setCreateBy(uid);
        withdrawApply.setCreateTime(DateTimeKit.currentTimeSecond());
        withdrawApply.setMoney(new BigDecimal(money));
        withdrawApply.setUid(uid);
        UserProtocol.UserBaseVo baseUser = userProvider.getBaseUser(uid);
        if (baseUser != null) {
            withdrawApply.setName(baseUser.getRealName());
            withdrawApply.setMobile(baseUser.getPhone());
        }
        // 初始状态是个不可用状态
        withdrawApply.setStatus(-1);
        withdrawApply.setApplyTime(DateTimeKit.currentTimeSecond());
        JsonResult<String> stringJsonResult = withdrawApplyClient.addWithdrawApply(withdrawApply);
        ApplyWithDrawDto applyWithDrawDto = new ApplyWithDrawDto();
        if (stringJsonResult.isSuccess()) {
            String withdrawApplyId = stringJsonResult.getData();
            applyWithDrawDto.setApplyId(withdrawApplyId);
            applyWithDrawDto.setMobile(withdrawApply.getMobile());
            applyWithDrawDto.setRealname(withdrawApply.getName());
            try {
                List<UserProtocol.ThirdLoginMycsInfo> mycses = userProvider.getUserThird(uid);
                if (mycses.size() > 0) {
                    String type1 = "wx";
                    String type2 = "wechat";
                    for (UserProtocol.ThirdLoginMycsInfo mycs : mycses) {
                        if (type1.equals(mycs.getType()) || type2.equals(mycs.getType())) {
                            applyWithDrawDto.setOpenid(mycs.getOpenid());
                            applyWithDrawDto.setWxType(mycs.getType());
                            break;
                        }
                    }
                }

            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }

        return applyWithDrawDto;
    }


    @Override
    public BuyBillBizDto buyBillDetail(String businessId) throws BillDetailException {
        // type 1：佣金收益，2：提现成功，3：购买成功
        log.debug("钱包：购买：service");
        MemberJoinRecord memberJoinRecord = memberJoinRecordMapper.selectById(businessId);
        BuyBillBizDto buyBillBizDto = new BuyBillBizDto();
        if (memberJoinRecord != null) {
            MemberIdentity memberIdentity = memberIdentityMapper.selectById(memberJoinRecord.getMemberIdentityId());
            if (memberIdentity != null) {
                buyBillBizDto.setCommodity("购买" + memberIdentity.getTitle());
            }
            buyBillBizDto.setValidityPeriod(memberJoinRecord.getDuration() + "个月");
            return buyBillBizDto;
        } else {
            log.error("不存在该业务交易单，业务单号：{}", businessId);
            throw new BillDetailException("不存在该业务交易单");
        }
    }

    @Override
    public CommissionBizDto commissionBillDetail(String businessId) throws BillDetailException {
        // type 1：佣金收益，2：提现成功，3：购买成功
        log.debug("分佣：明细：service");
        JsonResult<BizCommissionRecordDto> commissionResult = bizCommissionRecordClient.selectById(businessId);
        if (commissionResult.isSuccess() && commissionResult.getData() != null) {
            CommissionBizDto commissionBizDto = new CommissionBizDto();
            MemberJoinRecord memberJoinRecord = memberJoinRecordMapper.selectById(commissionResult.getData().getMemberJoinRecordId());
            if (memberJoinRecord != null) {
                UserProtocol.UserBaseVo user = userProvider.getBaseUser(memberJoinRecord.getUid());
                if (user != null) {
                    commissionBizDto.setBuyer(user.getRealName());
                }
                commissionBizDto.setOrderMoney(memberJoinRecord.getAmount().toString());
                MemberInviteRecord memberInviteRecord = inviteRecordMapper.selectMemberJoinId(memberJoinRecord.getMemberJoinRecordId());
                if (memberInviteRecord == null) {
                    log.error("邀请记录为空，会员开通记录id====》{}", memberJoinRecord.getMemberJoinRecordId());
                    throw new BillDetailException("邀请记录为空");
                }
                JsonResult<ShareObjectDto> shareObject = shareClient.getShareObject(memberInviteRecord.getShareId());
                ShareObjectDto share = shareObject.getData();
                if (share == null) {
                    log.error("分享记录为空，分享id====》{}", memberInviteRecord.getShareId());
                    throw new BillDetailException("分享记录为空");
                }
                VideoInfoVo videoInfoVo = videoInfoProvider.detail(share.getSrcId().longValue());
                if (videoInfoVo != null) {
                    commissionBizDto.setVideoName(videoInfoVo.getTitle());
                    commissionBizDto.setVideoUserId(share.getSrcId());
                }

            }
            commissionBizDto.setMoney(commissionResult.getData().getMoney().toString());

            return commissionBizDto;
        } else {
            throw new BillDetailException("不存在该类型");
        }
    }

    @Override
    public WithdrawBizDto withDrawBillDetail(String businessId) throws BillDetailException {
        // type 1：佣金收益，2：提现成功，3：购买成功
        log.debug("钱包：账单：明细：service");
        JsonResult<WithdrawApplyDto> result = withdrawApplyClient.selectById(businessId);
        if (result.isSuccess()) {
            WithdrawApplyDto withdrawApply = result.getData();
            WithdrawBizDto withdrawDetailDto = new WithdrawBizDto();
            withdrawDetailDto.setApplyTime(DateTimeKit.parseScondTime(withdrawApply.getApplyTime(), "yyyy-MM-dd"));
            withdrawDetailDto.setArrivalTime("--");
            if (withdrawApply.getGainedTime() > 0) {
                withdrawDetailDto.setArrivalTime(DateTimeKit.parseScondTime(withdrawApply.getGainedTime(), "yyyy-MM-dd"));
            }
            withdrawDetailDto.setFailReason(withdrawApply.getRemark());
            return withdrawDetailDto;
        } else {
            throw new BillDetailException("不存在该类型");
        }
    }

    @Override
    public BindWxDto isBindWechat(Long uid) {
        BindWxDto bindWxDto = new BindWxDto();
        try {
            List<UserProtocol.ThirdLoginMycsInfo> mycses = userProvider.getUserThird(uid);
            // 没有绑定微信
            bindWxDto.setIsBind(0);
            if (mycses.size() > 0) {
                String type1 = "wx";
                String type2 = "wechat";
                for (UserProtocol.ThirdLoginMycsInfo mycs : mycses) {
                    if (type1.equals(mycs.getType()) || type2.equals(mycs.getType())) {
                        bindWxDto.setIsBind(1);
                        // 微信头像
                        bindWxDto.setWxAvatar(mycs.getFigureurl());
                        // 微信名称
                        bindWxDto.setWxName(mycs.getThusername());
                        break;
                    }
                }
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return bindWxDto;
    }

    @Override
    public JsonResult withDrawNotify(String withdrawApplyId, Integer status, String msg) {
        int arrivalMoney = 3;
        int arrivalTime = 0;
        if (status != null && status == arrivalMoney) {
            arrivalTime = DateTimeKit.currentTimeSecond();
        }
        JsonResult result = withdrawApplyClient.arrivalMoney(withdrawApplyId, status, msg, arrivalTime);
        if (result.isSuccess()) {
            log.error("更新提现状态失败");
            return JsonResult.fail("更新提现状态失败");
        }
        return JsonResult.success();
    }
}
