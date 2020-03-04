package cn.mycs.service.member.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.member.provider.bean.dto.IdentityDurationDto;
import cn.mycs.service.member.provider.bean.dto.MemberIdentityDto;
import cn.mycs.service.member.provider.bean.dto.MemberInfoDto;
import cn.mycs.service.member.provider.bean.dto.MemberTypeDto;
import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.provider.bean.dto.PrivilegeDto;
import cn.mycs.service.member.provider.bean.dto.PurchaseMemberDto;
import cn.mycs.service.member.provider.bean.dto.ThirdPartyUserDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>会员相关接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/3 13:52
 * </pre>
 */
public interface MemberServerProvider {

    /**
     * 会员特权
     *
     * @param uid 用户id
     * @return 会员特权说明
     */
    @RequestMapping(value = "/memberServer/member/privilege/{uid}", method = RequestMethod.GET)
    JsonResult<PrivilegeDto> privilege(@PathVariable("uid") Long uid);

    /**
     * 分佣规则特权
     *
     * @param uid 用户id
     * @return 会员特权说明
     */
    @RequestMapping(value = "/memberServer/member/commissionRule/{uid}", method = RequestMethod.GET)
    JsonResult<String> commissionRule(@PathVariable("uid") Long uid);

    /**
     * 会员购买页
     *
     * @param identityId 会员身份id
     * @return 列表
     */
    @RequestMapping(value = "/memberServer/member/purchasing/view/{identityId}", method = RequestMethod.GET)
    JsonResult<List<IdentityDurationDto>> purchasingView(@PathVariable("identityId") String identityId);

    /**
     * 购买会员
     *
     * @param uid              购买者id
     * @param shareId          推荐id
     * @param memberIdentityId 会员身份id
     * @param days             购买月份
     * @param money            金额
     * @param device           购买的终端
     * @return 购买结果
     */
    @RequestMapping(value = "/memberServer/member/purchasing", method = RequestMethod.POST, consumes = "application/json")
    JsonResult<PurchaseMemberDto> purchasing(@RequestParam("uid") Long uid, @RequestParam("shareId") String shareId, @RequestParam("memberIdentityId") String memberIdentityId, @RequestParam("days") Integer days, @RequestParam("money") Float money, @RequestParam("device") Integer device);

    /**
     * 检测会员身份
     *
     * @param uid 用户id
     * @return
     */
    @RequestMapping(value = "/memberServer/member/checked/{uid}", method = RequestMethod.GET)
    JsonResult<MemberInfoDto> checked(@PathVariable("uid") Long uid);

    /**
     * 获取会员类型
     *
     * @param uid 用户id
     * @return
     */
    @RequestMapping(value = "/memberServer/member/type/{uid}", method = RequestMethod.GET)
    JsonResult<List<MemberTypeDto>> memberType(@PathVariable("uid") Long uid);

    /**
     * 获取会员身份
     *
     * @param typeId 身份id
     * @return
     */
    @RequestMapping(value = "/memberServer/member/identity/{typeId}", method = RequestMethod.GET)
    JsonResult<List<MemberIdentityDto>> memberIdentityByType(@PathVariable("typeId") String typeId);

    /**
     * 付款成功
     *
     * @param memberJoinRecordId 会员开通业务id
     * @return 购买VIP成功
     */
    @RequestMapping(value = "/memberServer/member/pay/success/{memberJoinRecordId}", method = RequestMethod.GET)
    JsonResult<PaySuccessDto> paySuccess(@PathVariable("memberJoinRecordId") String memberJoinRecordId);

    /**
     * 佣金分佣成功
     *
     * @param commissionRecordId 佣金记录id
     * @return 分佣成功
     */
    @RequestMapping(value = "/memberServer/member/commission/success/{commissionRecordId}", method = RequestMethod.POST)
    JsonResult commissionSettlementSuccess(@PathVariable("commissionRecordId") String commissionRecordId);


    /**
     * 查询第三方用户基本信息
     *
     * @param userId 用户id
     * @param appId  APPID，可以为空，为空则查询该用户所有的第三方用户基本信息
     * @return 第三方用户基本信息列表
     */
    @RequestMapping(value = "/memberServer/member/find/ThirdPartyUser", method = RequestMethod.POST)
    JsonResult<ThirdPartyUserDto> findThirdPartyUser(@RequestParam("userId") Long userId, @RequestParam("appId") String appId);

}
