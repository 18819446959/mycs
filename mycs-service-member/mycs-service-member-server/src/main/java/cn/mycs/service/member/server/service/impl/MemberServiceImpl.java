package cn.mycs.service.member.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.support.StrKit;
import cn.mycs.front.service.user.provider.UserProvider;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.msc.support.exception.ServiceException;

import cn.mycs.service.member.feign.bean.dto.BizCommissionRecordDto;
import cn.mycs.service.member.feign.interfaces.BizCommissionRecordClient;
import cn.mycs.service.member.provider.bean.dto.IdentityDurationDto;
import cn.mycs.service.member.provider.bean.dto.MemberIdentityDto;
import cn.mycs.service.member.provider.bean.dto.MemberInfoDto;
import cn.mycs.service.member.provider.bean.dto.MemberTypeDto;
import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.provider.bean.dto.ThirdPartyUserDto;
import cn.mycs.service.member.server.bo.member.PaySuccessBo;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityDurationMapper;
import cn.mycs.service.member.server.persistence.dao.MemberIdentityMapper;
import cn.mycs.service.member.server.persistence.dao.MemberJoinRecordMapper;
import cn.mycs.service.member.server.persistence.dao.MemberMapper;
import cn.mycs.service.member.server.persistence.dao.MemberTypeMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberIdentityDuration;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import cn.mycs.service.member.server.persistence.model.MemberType;
import cn.mycs.service.member.server.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>会员服务接口实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 11:58
 * </pre>
 */
@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberTypeMapper memberTypeMapper;
    @Autowired
    private MemberIdentityMapper memberIdentityMapper;
    @Autowired
    private MemberIdentityDurationMapper memberIdentityDurationMapper;
    @Autowired
    private MemberJoinRecordMapper memberJoinRecordMapper;
    @Autowired
    private BizCommissionRecordClient bizCommissionRecordClient;
    @Autowired
    private UserProvider userProvider;

    @Override
    public JsonResult memberJoin() {
        /* 根据用户的id，在会员开通记录表member_join_record中分页查询该用户开通会员的记录         */
        return null;
    }


    @Override
    public List<IdentityDurationDto> purchasingView(String identityId) {
        /* 根据会员身份id，查询出该会员身份下所有的时长及对应的价格         */

        List<IdentityDurationDto> identityDurationDtos = new ArrayList<>();
        if (StrKit.isNotEmpty(identityId)) {
            List<MemberIdentityDuration> memberIdentityDurations = memberIdentityDurationMapper.selectByIdentityId(identityId);
            IdentityDurationDto identityDurationDto;
            for (MemberIdentityDuration memberIdentityDuration : memberIdentityDurations) {
                identityDurationDto = new IdentityDurationDto();
                identityDurationDto.setIdentityDurationId(memberIdentityDuration.getMemberIdentityDurationId());
                identityDurationDto.setIdentityId(memberIdentityDuration.getMemberIdentityId());
                identityDurationDto.setOrginalPrice(memberIdentityDuration.getOriginalPrice().floatValue());
                identityDurationDto.setPrice(memberIdentityDuration.getPrice().floatValue());
                identityDurationDto.setLimitDate(memberIdentityDuration.getLimitDate());
                identityDurationDtos.add(identityDurationDto);
            }
        }
        return identityDurationDtos;
    }

    @Override
    public MemberInfoDto checked(Long uid) {
          /*
            检测会员身份，根据会员的用户id，从member表中查询会员信息
            1、查询记录
            2、如果存在
            3、对比会员的结束时间跟当前时间，查看该会员是否已过期
            4、返回会员信息（包括，会员状态（是否在有效期内），开始时间、结束时间）
        */
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        int isMember = 0;
        if (uid != null) {
            Member member = memberMapper.selectByUid(uid);
            if (member != null) {
                if (member.getEndTime() > DateTimeKit.currentTimeSecond()) {
                    // 该会员还在有效期，这是续期，
                    isMember = 1;
                    // 会员开始时间
                    memberInfoDto.setStartTime(DateTimeKit.parseScondTime(member.getStartTime(),DateTimeKit.NORM_DATE_PATTERN));
                    // 会员结束时间
                    memberInfoDto.setEndTime(DateTimeKit.parseScondTime(member.getEndTime(),DateTimeKit.NORM_DATE_PATTERN));
                    MemberIdentity memberIdentity = memberIdentityMapper.selectById(member.getMemberIdentityId());
                    memberInfoDto.setMemberName(memberIdentity.getTitle());
                }

            }

        }
        memberInfoDto.setIsMember(isMember);
        return memberInfoDto;
    }

    @Override
    public JsonResult<PaySuccessDto> paySuccess(String memberJoinRecordId) {
        /* 委托bo进行操作         */
        return new PaySuccessBo(memberJoinRecordId).success();
    }

    @Override
    public List<MemberTypeDto> memberType(Long uid) {
        /* 查询会员身份         */
        List<MemberType> memberTypes = memberTypeMapper.selectAll();
        List<MemberTypeDto> memberTypeDtos = new ArrayList<>();
        MemberTypeDto memberTypeDto;
        for (MemberType memberType : memberTypes) {
            memberTypeDto = new MemberTypeDto();
            memberTypeDto.setMemberTypeId(memberType.getMemberTypeId());
            memberTypeDto.setName(memberType.getTitle());
            memberTypeDtos.add(memberTypeDto);
        }
        return memberTypeDtos;
    }

    @Override
    public List<MemberIdentityDto> memberIdentityByType(String memberTypeId) {
        /* 查询会员身份         */
        List<MemberIdentity> identities = memberIdentityMapper.selectByMemberType(memberTypeId);
        List<MemberIdentityDto> memberIdentityDtos = new ArrayList<>();
        MemberIdentityDto memberTypeDto;
        for (MemberIdentity identitie : identities) {
            memberTypeDto = new MemberIdentityDto();
            memberTypeDto.setMemberIdentityId(identitie.getMemberIdentityId());
            memberTypeDto.setName(identitie.getTitle());
            memberIdentityDtos.add(memberTypeDto);
        }
        return memberIdentityDtos;
    }

    @Override
    public Member getMemberByUid(Long uid) {
        return memberMapper.selectByUid(uid);
    }

    @Override
    public MemberIdentity getMemberIdentity(String memberIdentityId) {
        return memberIdentityMapper.selectById(memberIdentityId);
    }

    @Override
    public Integer addMemberJoinRecord(MemberJoinRecord memberJoinRecord) {
        return memberJoinRecordMapper.insert(memberJoinRecord);
    }

    @Override
    public int commissionSettlementSuccess(String commissionRecordId) {
        // 分佣结算成功，更新佣金记录状态
        JsonResult result = bizCommissionRecordClient.updateSettlement(commissionRecordId);
        return result.getCode();
    }


    @Override
    public ThirdPartyUserDto findThirdPartyUser(Long userId, String appId) {
        ThirdPartyUserDto thirdPartyUserDto = new ThirdPartyUserDto();
        try {
            List<UserProtocol.ThirdLoginMycsInfo> thirdLoginMycses = userProvider.getUserThird(userId);
            boolean haveAppid = StrKit.isNotEmpty(appId);
            boolean find = false;

            List<ThirdPartyUserDto.ThirdPartyUser> users = new ArrayList<>();
            for (UserProtocol.ThirdLoginMycsInfo thirdLoginMycs : thirdLoginMycses) {
                if (haveAppid) {
                    if (appId.equals(thirdLoginMycs.getAppid())) {
                        find = true;
                        users.add(fillThirdPartyUser(thirdLoginMycs));
                        break;
                    }
                }
            }
            if (!find && !haveAppid) {
                for (UserProtocol.ThirdLoginMycsInfo thirdLoginMycs : thirdLoginMycses) {
                    users.add(fillThirdPartyUser(thirdLoginMycs));
                }
            }
            thirdPartyUserDto.setUsers(users);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return thirdPartyUserDto;

    }

    private ThirdPartyUserDto.ThirdPartyUser fillThirdPartyUser(UserProtocol.ThirdLoginMycsInfo thirdLoginMycs) {
        ThirdPartyUserDto.ThirdPartyUser thirdPartyUser = new ThirdPartyUserDto.ThirdPartyUser();
        thirdPartyUser.setUserId(thirdLoginMycs.getUid());
        thirdPartyUser.setOpenid(thirdLoginMycs.getOpenid());
        thirdPartyUser.setType(thirdLoginMycs.getType());
        thirdPartyUser.setUnionid(thirdLoginMycs.getUnionid());
        thirdPartyUser.setAppid(thirdLoginMycs.getAppid());
        return thirdPartyUser;
    }

    @Override
    public String addCommissionRecord(BizCommissionRecordDto commissionRecord) {
        JsonResult<String> result = bizCommissionRecordClient.addCommissionRecord(commissionRecord);
        if (result.isSuccess()) {
            return result.getData();
        }
        return null;
    }
}

