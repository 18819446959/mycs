package cn.mycs.service.member.server.bo.member;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;

import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.member.feign.bean.dto.BizCommissionRecordDto;
import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.server.persistence.dao.DistributionConfigMapper;
import cn.mycs.service.member.server.persistence.dao.MemberInviteRecordMapper;
import cn.mycs.service.member.server.persistence.model.DistributionConfig;
import cn.mycs.service.member.server.persistence.model.MemberInviteRecord;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import cn.mycs.service.member.server.service.IMemberService;
import cn.mycs.service.member.server.service.IShareService;


import java.math.BigDecimal;

/**
 * <p>佣金计算Bo</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 10:27
 * </pre>
 */
public class CommissionBo {
    private MemberJoinRecord memberJoinRecord;
    private String commissionId;
    private DistributionConfigMapper distributionConfigMapper;
    private MemberInviteRecordMapper memberInviteRecordMapper;
    private IMemberService memberService;
    private IShareService shareService;

    public CommissionBo(MemberJoinRecord memberJoinRecord, String commissionId) {
        this.memberJoinRecord = memberJoinRecord;
        this.commissionId = commissionId;
        this.initMapper();
    }

    private void initMapper() {
        this.distributionConfigMapper = SpringContextHolder.getBean("distributionConfigMapper");
        this.memberService = SpringContextHolder.getBean("memberServiceImpl");
        this.memberInviteRecordMapper = SpringContextHolder.getBean("memberInviteRecordMapper");
        this.shareService = SpringContextHolder.getBean("shareServiceImpl");
    }

    public PaySuccessDto cal() {
        DistributionConfig distributionConfig = distributionConfigMapper.selectById(commissionId);
        CalCommissionStrategy calStrategy = CalCommissionFactory.getCalStrategy(distributionConfig.getCommissionType());
        // 根据分佣规则计算分佣金额
        MemberInviteRecord memberInviteRecord = memberInviteRecordMapper.selectMemberJoinId(memberJoinRecord.getMemberJoinRecordId());
        BigDecimal bigDecimal = calStrategy.commissionMoney(distributionConfig, memberJoinRecord.getAmount());
        BizCommissionRecordDto commissionRecord = createCommissionRecord(memberInviteRecord, bigDecimal);
        String commissionId = memberService.addCommissionRecord(commissionRecord);
        PaySuccessDto paySuccessDto = new PaySuccessDto();
        paySuccessDto.setCommissionRecordId(commissionId);
        paySuccessDto.setIsCommission(1);
        paySuccessDto.setUid(commissionRecord.getUid());
        Float v = commissionRecord.getMoney().floatValue() * 100;
        paySuccessDto.setMoney(v.longValue());
        paySuccessDto.setRemark(commissionRecord.getRemark());
        paySuccessDto.setVideoName(getVideoName(memberInviteRecord));
        return paySuccessDto;

    }

    private String getVideoName(MemberInviteRecord memberInviteRecord) {


        if (memberInviteRecord == null) {
            return "";
        }
        ShareObjectDto share = shareService.getShareObj(memberInviteRecord.getShareId());
        if (share == null) {
            return "";
        }
        VideoInfoVo videoInfoVo = shareService.getVideoInfoVo(share.getSrcId().longValue());
        if (videoInfoVo == null) {
            return "";
        }
        return videoInfoVo.getTitle();
    }

    private BizCommissionRecordDto createCommissionRecord(MemberInviteRecord memberInviteRecord, BigDecimal bigDecimal) {
        BizCommissionRecordDto record = new BizCommissionRecordDto();
        record.setBizCommissionRecordId(CreateIdUtil.createUUID());
        record.setMemberJoinRecordId(memberJoinRecord.getMemberJoinRecordId());
        record.setCreateBy(memberJoinRecord.getUid());
        record.setCreateTime(DateTimeKit.currentTimeSecond());
        record.setMoney(bigDecimal);
        record.setSettlementStatus(0);
        record.setSettlementChanal("wx");
        record.setRemark("分享视频赚取佣金");
        record.setUid(memberInviteRecord.getInviterUid());
        record.setBusinessId(memberJoinRecord.getMemberJoinRecordId());
        record.setBusinessType(1);
        return record;
    }
}
