package cn.mycs.service.member.server.bo.member;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;

import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityMapper;
import cn.mycs.service.member.server.persistence.dao.MemberInviteRecordMapper;
import cn.mycs.service.member.server.persistence.dao.MemberJoinRecordMapper;
import cn.mycs.service.member.server.persistence.dao.MemberMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberInviteRecord;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>开通会员付款成功</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 9:54
 * </pre>
 */
public class PaySuccessBo {
    private Logger log = LoggerFactory.getLogger(getClass());
    private String memberJoinRecordId;
    private MemberMapper memberMapper;
    private MemberIdentityMapper memberIdentityMapper;
    private MemberJoinRecordMapper memberJoinRecordMapper;
    private MemberInviteRecordMapper memberInviteRecordMapper;

    public PaySuccessBo(String memberJoinRecordId) {
        this.memberJoinRecordId = memberJoinRecordId;
        initMapper();
    }

    private void initMapper() {
        this.memberMapper = SpringContextHolder.getBean("memberMapper");
        this.memberIdentityMapper = SpringContextHolder.getBean("memberIdentityMapper");
        this.memberJoinRecordMapper = SpringContextHolder.getBean("memberJoinRecordMapper");
        this.memberInviteRecordMapper = SpringContextHolder.getBean("memberInviteRecordMapper");
    }

    private boolean updateJoinRecordStatus() {
        int row = memberJoinRecordMapper.updateJoinSuccess(memberJoinRecordId);
        return row > 0;
    }

    /**
     * 分开付款成功
     */
    public JsonResult<PaySuccessDto> success() {
        // 更新付款状态为已开通
        /* 开通会员付款成功
            1、根据开通记录id，查询开通记录
            2、根据开通者uid检测会员【member】表中是否有开通过
            3、如果member中有在期的会员，则叠加，
            4、否则以member_join_record表的创建时间为开始时间，以开始时间+上duration为结束时间--新建/更新member--
            5、memberJoinRecordId在member_invite_record表中查找是否有邀请记录
                如果有，则按邀请者inviter_uid，查询该会员的身份，计算佣金，并插入佣金表biz_commission_record
         */
        boolean b = updateJoinRecordStatus();
        if (b) {
            //如果更新成功才进行开通
            MemberJoinRecord memberJoinRecord = memberJoinRecordMapper.selectById(memberJoinRecordId);
            if (memberJoinRecord != null) {
                // 会员开通的月份数，要转换成秒数
                Integer duration = memberJoinRecord.getDuration();
                Integer second = duration * 30 * 24 * 3600;
                Member member = memberMapper.selectByUid(memberJoinRecord.getUid());
                if (member != null) {
                    member.setMemberIdentityId(memberJoinRecord.getMemberIdentityId());
                    if (member.getEndTime() > DateTimeKit.currentTimeSecond()) {
                        // 该会员还在有效期，这是续期，
                        int endTime = member.getEndTime() + second;
                        member.setEndTime(endTime);
                    } else {
                        // 不在有效期  开始时间按天开始
                        String s = DateTimeKit.parseScondTime(memberJoinRecord.getCreateTime(), DateTimeKit.NORM_DATE_PATTERN);
                        int startTime = DateTimeKit.parseStr2Scond(s, DateTimeKit.NORM_DATE_PATTERN);
                        int entTime = startTime + second;
                        member.setStartTime(startTime);
                        member.setEndTime(entTime);
                    }
                    try {
                        int row = memberMapper.updateById(member);
                        if (row == 0) {
                            log.error("更新会员记录失败，失败数据：{}", JSON.toJSONString(member));
                        }
                    } catch (Exception e) {
                        log.error("会员update开通失败，开通记录id：{},失败数据：{},失败原因：{}", memberJoinRecordId, JSON.toJSONString(member), e);
                        return JsonResult.fail("开通会员失败");
                    }

                } else {
                    // 不存在会员，新建
                    member = createMember(memberJoinRecord, second);
                    try {
                        Integer row = memberMapper.insert(member);
                        if (row == 0) {
                            log.error("插入会员记录失败，失败数据：{}", JSON.toJSONString(member));
                        }
                    } catch (Exception e) {
                        log.error("会员insert开通失败，开通记录id：{},失败数据：{},失败原因：{}", memberJoinRecordId, JSON.toJSONString(member), e);
                        return JsonResult.fail("开通会员失败");
                    }
                }

                if (StringUtils.isNotBlank(memberJoinRecord.getShareId())) {
                    // 如果推荐者的id不为空, 写入邀请记录表中
                    new MemberBo(memberJoinRecord.getUid())
                            .insertInviteRecord(memberJoinRecord.getShareId(), memberJoinRecord.getMemberJoinRecordId());
                }
                // 计算佣金
                PaySuccessDto paySuccessDto = calCommission(memberJoinRecord, member);
                return JsonResult.success(paySuccessDto);
            }
        } else {
            return JsonResult.fail("开通会员失败");
        }
        return JsonResult.fail("会员开通失败");
    }

    private PaySuccessDto calCommission(MemberJoinRecord memberJoinRecord, Member joinMember) {
        MemberInviteRecord mir = memberInviteRecordMapper.selectMemberJoinId(memberJoinRecord.getMemberJoinRecordId());
        if (mir != null) {
            // 如果有邀请记录才分析是否能分佣,根据邀请者取出邀请者的会员身份
            Member member = memberMapper.selectByUid(mir.getInviterUid());
            if (member != null && member.getEndTime() > DateTimeKit.currentTimeSecond()) {
                // 取出该会员身份是否参与分佣
                MemberIdentity memberIdentity = memberIdentityMapper.selectById(joinMember.getMemberIdentityId());
                if (memberIdentity != null) {
                    if (memberIdentity.getIsCommission() == 1) {
                        // 参与分佣，从分佣配置表中查询分佣配置
                        log.debug("会员身份进行分佣，身份id：{}", joinMember.getMemberIdentityId());
                        return new CommissionBo(memberJoinRecord, memberIdentity.getCommissionId()).cal();
                    } else {
                        log.info("改会员身份不行分佣：{}", joinMember.getMemberIdentityId());
                    }
                } else {
                    log.info("分佣会员身份不存在，会员身份id为：{}", joinMember.getMemberIdentityId());
                }
            } else {
                log.info("用户对应的会员记录不存，不能进行分佣，会员uid为：{}", mir.getInviterUid());
            }
        } else {
            log.info("不存在分享记录不能分佣，会员开通记录id为:{}", memberJoinRecord);
        }
        return new PaySuccessDto();
    }

    private Member createMember(MemberJoinRecord memberJoinRecord, Integer second) {
        Member member = new Member();
        member.setMemberId(CreateIdUtil.createUUID());
        member.setMemberIdentityId(memberJoinRecord.getMemberIdentityId());
        member.setMemberId(CreateIdUtil.createUUID());
        member.setCreateBy(memberJoinRecord.getUid());
        member.setCreateTime(DateTimeKit.currentTimeSecond());
        member.setUid(memberJoinRecord.getUid());
        // 开始时间按天开始
        String s = DateTimeKit.parseScondTime(memberJoinRecord.getCreateTime(), DateTimeKit.NORM_DATE_PATTERN);
        int startTime = DateTimeKit.parseStr2Scond(s, DateTimeKit.NORM_DATE_PATTERN);
        int entTime = startTime + second;
        member.setStartTime(startTime);
        member.setEndTime(entTime);
        return member;
    }
}
