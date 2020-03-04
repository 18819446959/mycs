package cn.mycs.service.member.server.bo.member;

import cn.mycs.core.constant.DatasourceEnum;
import cn.mycs.core.multidatasource.DataSourceContextHolder;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.support.StrKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;

import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.member.provider.bean.dto.MemberIdentityDto;
import cn.mycs.service.member.provider.bean.dto.PurchaseMemberDto;
import cn.mycs.service.member.server.exception.MemberIdentityNotExitException;
import cn.mycs.service.member.server.persistence.dao.MemberInviteRecordMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberInviteRecord;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import cn.mycs.service.member.server.service.IMemberService;
import cn.mycs.service.member.server.service.IShareService;

import com.alipay.api.domain.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>会员</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/5 20:37
 * </pre>
 */
public class MemberBo {
    private Logger log = LoggerFactory.getLogger(getClass());
    /**
     * 会员身份
     */
    private MemberIdentityBo memberIdentity;
    /**
     * 用户信息
     */
    private Long uid;
    /**
     * 用户信息
     */
    private Member member;

    private MemberInviteRecordMapper memberInviteRecordMapper;


    private IMemberService memberService;
    private IShareService shareService;

    public MemberBo(Long uid) {
        this.uid = uid;
        initMapper();
    }

    private void initMapper() {
        this.shareService = SpringContextHolder.getBean("shareServiceImpl");


        this.memberService = SpringContextHolder.getBean("memberServiceImpl");
        this.memberInviteRecordMapper = SpringContextHolder.getBean("memberInviteRecordMapper");


    }

    /**
     * 获取视频列表
     *
     * @return 会员拥有视频列表
     */
    public List<Video> getVideoList() {
        return memberIdentity.getVideoList();
    }

    /**
     * 获取开通历史
     */
    public void getJoinHistory() {

    }

    /**
     * 获取邀请记录
     */
    public void getInviteRecord() {

    }

    /**
     * 获取资源
     */
    public void getSrc() {

    }

    public String getLimitDate() {
        if (member != null) {
            Integer endTime = member.getEndTime();
            return DateTimeKit.parseScondTime(endTime, "yyyy-MM-dd");

        }
        return "";
    }

    public MemberIdentityBo getMemberIdentity() {
        /* 返回会员身份 */

        if (memberIdentity == null) {
            member = memberService.getMemberByUid(uid);
            if (member != null) {
                memberIdentity = new MemberIdentityBo(member);
            }
        }
        return memberIdentity;
    }

    public PurchaseMemberDto purchasing(String shareId, String memberIdentityId, Integer days, Float money, Integer device) throws MemberIdentityNotExitException {
        /* 购买会员
         */
        // 检测会员身份是否存在
        MemberIdentity memberIdentity = memberService.getMemberIdentity(memberIdentityId);
        if (memberIdentity == null) {
            log.error("购买会员，会员身份不存在，会员身份id：{}", memberIdentityId);
            throw new MemberIdentityNotExitException("不存在该会员身份");
        }

        MemberJoinRecord memberJoinRecord = createMemberJoinRecord(memberIdentityId, days, money, device, shareId);
        memberService.addMemberJoinRecord(memberJoinRecord);
        PurchaseMemberDto purchaseMemberDto = new PurchaseMemberDto();
        purchaseMemberDto.setMemberJoinId(memberJoinRecord.getMemberJoinRecordId());

        MemberIdentityDto memberIdentityDto = new MemberIdentityDto();
        memberIdentityDto.setMemberIdentityId(memberIdentity.getMemberIdentityId());
        memberIdentityDto.setName(memberIdentity.getTitle());
        purchaseMemberDto.setMemberIdentity(memberIdentityDto);
        // 如果推荐者的id不为空,要写入推荐记录表中
        insertInviteRecord(shareId, purchaseMemberDto.getMemberJoinId());
        return purchaseMemberDto;
    }

    public void insertInviteRecord(String shareId, String joinId) {
        if (StrKit.isNotEmpty(shareId)) {
            ShareObjectDto share = shareService.getShareObj(shareId);
            if (share != null) {
                // 相同的用户只能被同一个人邀请一次
                DataSourceContextHolder.setDataSourceType(DatasourceEnum.DATA_SOURCE_USER);
                MemberInviteRecord memberInviteRecord = memberInviteRecordMapper.selectInveterUidAnduid(share.getShareUid(), uid);
                if (memberInviteRecord == null) {
                    memberInviteRecord = new MemberInviteRecord();
                    memberInviteRecord.setCreateBy(uid);
                    memberInviteRecord.setCreateTime(DateTimeKit.currentTimeSecond());
                    memberInviteRecord.setUpdateBy(uid);
                    memberInviteRecord.setMemberJoinRecordId(joinId);
                    memberInviteRecord.setShareId(shareId);
                    memberInviteRecord.setInviterUid(share.getShareUid());
                    memberInviteRecord.setMemberInviteRecordId(CreateIdUtil.createUUID());
                    memberInviteRecord.setUid(uid);
                    memberInviteRecordMapper.insert(memberInviteRecord);
                }
            }
        }
    }

    private MemberJoinRecord createMemberJoinRecord(String memberIdentityId, Integer days, Float money, Integer device, String shareId) {
        MemberJoinRecord memberJoinRecord = new MemberJoinRecord();
        memberJoinRecord.setMemberJoinRecordId(CreateIdUtil.createUUID());
        memberJoinRecord.setAmount(new BigDecimal(money));
        memberJoinRecord.setCreateBy(uid);
        memberJoinRecord.setCreateTime(DateTimeKit.currentTimeSecond());
        memberJoinRecord.setUpdateBy(uid);
        memberJoinRecord.setMemberIdentityId(memberIdentityId);
        memberJoinRecord.setStatus(0);
        memberJoinRecord.setDuration(days);
        memberJoinRecord.setUid(uid);
        memberJoinRecord.setDevice(device);
        memberJoinRecord.setShareId(shareId);
        return memberJoinRecord;

    }
}
