package cn.mycs.service.member.server.bo.member;

import cn.mycs.common.util.RuleUtil;
import cn.mycs.core.constant.StorageDirEnum;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.SpringContextHolder;

import cn.mycs.service.member.provider.bean.dto.PrivilegeDto;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityMapper;
import cn.mycs.service.member.server.persistence.dao.MemberTypeMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberType;
import com.alipay.api.domain.Video;

import java.util.List;

/**
 * <p>会员身份抽象</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/5 20:31
 * </pre>
 */
public class MemberIdentityBo {
    private String identityId;
    private Member member;
    private MemberIdentity memberIdentity;
    private MemberIdentityMapper memberIdentityMapper;
    private MemberTypeMapper memberTypeMapper;

    public MemberIdentityBo(Member member) {
        this.identityId = member.getMemberIdentityId();
        this.member = member;
        initMapper();
    }

    private void initMapper() {
        this.memberIdentityMapper = SpringContextHolder.getBean("memberIdentityMapper");
        this.memberTypeMapper = SpringContextHolder.getBean("memberTypeMapper");
    }

    private MemberIdentity getMemberIdentity() {
        if (memberIdentity == null) {
            memberIdentity = memberIdentityMapper.selectById(identityId);
            if (memberIdentity == null) {
                memberIdentity = new MemberIdentity();
            }
        }
        return memberIdentity;
    }

    /**
     * 获取视频列表
     *
     * @return 会员身份拥有的视频列表
     */
    public List<Video> getVideoList() {
        return null;
    }

    /**
     * 获取佣金规则
     *
     * @return 视频身份下的佣金规则
     */
    public String getCommissionRule() {
        return getMemberIdentity().getCommissionExplanation();
    }

    /**
     * 获取价格
     *
     * @return 会员身份价格
     */
    public Float getPrice() {
        return 0f;
    }

    /**
     * 获取身份特权
     *
     * @return 身份特权
     */
    public PrivilegeDto privilege() {
        PrivilegeDto privilegeDto = new PrivilegeDto();
        privilegeDto.setPrivilegeDesc(getMemberIdentity().getRightsDesc());
        Integer endTime = member.getEndTime();
        String ld = DateTimeKit.parseScondTime(endTime, "yyyy-MM-dd");
        privilegeDto.setLimitDate(ld);
        MemberType memberType = memberTypeMapper.selectById(getMemberIdentity().getMemberTypeId());
        if (memberType != null) {
            privilegeDto.setMemberType(memberType.getTitle());
        }
        Long uid = member.getUid();
        privilegeDto.setAvatar(RuleUtil.getPhotoUrl(uid, StorageDirEnum.AVATAR));
        privilegeDto.setName(getMemberIdentity().getTitle());

        return privilegeDto;
    }

}
