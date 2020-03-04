package cn.mycs.service.member.server.service;

import cn.mycs.core.base.restful.JsonResult;

import cn.mycs.service.member.feign.bean.dto.BizCommissionRecordDto;
import cn.mycs.service.member.provider.bean.dto.IdentityDurationDto;
import cn.mycs.service.member.provider.bean.dto.MemberIdentityDto;
import cn.mycs.service.member.provider.bean.dto.MemberInfoDto;
import cn.mycs.service.member.provider.bean.dto.MemberTypeDto;
import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.provider.bean.dto.ThirdPartyUserDto;
import cn.mycs.service.member.server.persistence.model.Member;
import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;

import java.util.List;

/**
 * <p>会员服务接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 11:57
 * </pre>
 */
public interface IMemberService {
    /**
     * 会员开通/续费
     *
     * @return 开通结果
     */
    JsonResult memberJoin();

    /**
     * 进入会员购买页面，获取会员时长列表
     *
     * @param identityId 会员身份id
     * @return 会员购买价格列表
     */
    List<IdentityDurationDto> purchasingView(String identityId);

    /**
     * 检测会员身份
     *
     * @param uid 用户id
     * @return 会员身份信息
     */
    MemberInfoDto checked(Long uid);

    /**
     * 开通会员付款成功
     *
     * @param memberJoinRecordId 开通记录id
     * @return 操作结果
     */
    JsonResult<PaySuccessDto> paySuccess(String memberJoinRecordId);

    /**
     * 获取会员类型
     *
     * @param uid 用户di
     * @return 会员类型列表
     */
    List<MemberTypeDto> memberType(Long uid);

    /**
     * 获取会员身份
     *
     * @param memberTypeId 会员类型id
     * @return 会员身份身份
     */
    List<MemberIdentityDto> memberIdentityByType(String memberTypeId);

    /**
     * 获取会员通过uid
     *
     * @param uid 用户id
     * @return 会员
     */
    Member getMemberByUid(Long uid);

    /**
     * 获取会员身份
     *
     * @param memberIdentityId 身份id
     * @return 会员身份
     */
    MemberIdentity getMemberIdentity(String memberIdentityId);

    /**
     * 添加会员开通记录
     *
     * @param memberJoinRecord
     * @return
     */
    Integer addMemberJoinRecord(MemberJoinRecord memberJoinRecord);

    /**
     * 佣金分佣成功
     *
     * @param commissionRecordId 分佣记录id
     * @return 分佣成功
     */
    int commissionSettlementSuccess(String commissionRecordId);

    /**
     * 获取第三方用户基本信息
     *
     * @param userId 用户 id
     * @param appId  appid
     * @return 第三方用户基本信息
     */
    ThirdPartyUserDto findThirdPartyUser(Long userId, String appId);

    /**
     * 添加分佣记录
     *
     * @param commissionRecord 分佣记录对象
     * @return 分佣记录id
     */
    String addCommissionRecord(BizCommissionRecordDto commissionRecord);
}
