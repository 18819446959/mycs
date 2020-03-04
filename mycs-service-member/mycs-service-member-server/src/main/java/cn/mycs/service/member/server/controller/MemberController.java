package cn.mycs.service.member.server.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.util.ReqHeaderUtil;

import cn.mycs.service.member.provider.bean.dto.IdentityDurationDto;
import cn.mycs.service.member.provider.bean.dto.MemberIdentityDto;
import cn.mycs.service.member.provider.bean.dto.MemberInfoDto;
import cn.mycs.service.member.provider.bean.dto.MemberTypeDto;
import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.provider.bean.dto.PrivilegeDto;
import cn.mycs.service.member.provider.bean.dto.PurchaseMemberDto;
import cn.mycs.service.member.provider.bean.dto.ThirdPartyUserDto;
import cn.mycs.service.member.provider.interfaces.feign.MemberServerProvider;
import cn.mycs.service.member.server.bo.member.MemberBo;
import cn.mycs.service.member.server.exception.MemberIdentityNotExitException;
import cn.mycs.service.member.server.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>会员相关接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/3 13:52
 * </pre>
 */
@RestController
@RequestMapping
@Api(description = "会员接口")
public class MemberController implements MemberServerProvider {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private IMemberService memberService;

    @Override
    @ApiOperation(value = "会员特权")
    public JsonResult<PrivilegeDto> privilege(@PathVariable("uid") Long uid) {
        log.debug("会员特权,当前登录用户为，{}，请求终端为：{}", ReqHeaderUtil.getCurrentDevice(), ReqHeaderUtil.getCurrentDevice());
        MemberBo memberBo = new MemberBo(uid);
        PrivilegeDto privilege;
        if (memberBo.getMemberIdentity() != null) {
            privilege = memberBo.getMemberIdentity().privilege();
        } else {
            privilege = new PrivilegeDto();
        }

        return JsonResult.success(privilege);
    }

    @Override
    public JsonResult<String> commissionRule(@PathVariable("uid") Long uid) {
        log.debug("会员特权,当前登录用户为，{}，请求终端为：{}", ReqHeaderUtil.getCurrentDevice(), ReqHeaderUtil.getCurrentDevice());
        MemberBo memberBo = new MemberBo(uid);
        String commissionRule = "";
        if (memberBo.getMemberIdentity() != null) {
            commissionRule = memberBo.getMemberIdentity().getCommissionRule();
        }
        return JsonResult.success(commissionRule);
    }

    @Override
    public JsonResult<List<IdentityDurationDto>> purchasingView(@PathVariable("identityId") String identityId) {
        log.debug("进入会员购买页面Service");
        List<IdentityDurationDto> identityDurationDtos = memberService.purchasingView(identityId);
        return JsonResult.success(identityDurationDtos);
    }

    @Override
    public JsonResult<PurchaseMemberDto> purchasing(@RequestParam("uid") Long uid, @RequestParam("shareId") String shareId, @RequestParam("memberIdentityId") String memberIdentityId, @RequestParam("days") Integer days, @RequestParam("money") Float money, @RequestParam("device") Integer device) {
        log.debug("购买会员，会员身份id：{}，购买天数：{}，金额：{},购买终端：{}", memberIdentityId, days, money, device);
        try {
            PurchaseMemberDto purchasing = new MemberBo(uid).purchasing(shareId, memberIdentityId, days, money, device);
            return JsonResult.success(purchasing);
        } catch (MemberIdentityNotExitException e) {
            return JsonResult.fail(e.getMessage());
        }
    }


    @Override
    public JsonResult<MemberInfoDto> checked(@PathVariable("uid") Long uid) {
        log.debug("检测会员身份");
        MemberInfoDto checked = memberService.checked(uid);
        return JsonResult.success(checked);
    }

    @Override
    public JsonResult<List<MemberTypeDto>> memberType(@PathVariable("uid") Long uid) {
        log.debug("获取会员类型");
        List<MemberTypeDto> memberTypeDtos = memberService.memberType(uid);
        return JsonResult.success(memberTypeDtos);
    }

    @Override
    public JsonResult<List<MemberIdentityDto>> memberIdentityByType(@PathVariable("typeId") String typeId) {
        log.debug("获取会员身份");
        List<MemberIdentityDto> memberIdentityDtos = memberService.memberIdentityByType(typeId);
        return JsonResult.success(memberIdentityDtos);
    }

    @Override
    public JsonResult<PaySuccessDto> paySuccess(@PathVariable("memberJoinRecordId") String memberJoinRecordId) {
        log.debug("获取支付成功");
        return memberService.paySuccess(memberJoinRecordId);
    }

    @Override
    public JsonResult commissionSettlementSuccess(@PathVariable("commissionRecordId") String commissionRecordId) {
        log.debug("获取支付成功");
        int row = memberService.commissionSettlementSuccess(commissionRecordId);
        if (row == 1) {
            return JsonResult.success();
        } else {
            return JsonResult.fail("更新失败");
        }
    }


    @Override
    public JsonResult<ThirdPartyUserDto> findThirdPartyUser(@RequestParam("userId") Long userId, @RequestParam("appId") String appId) {
        ThirdPartyUserDto users = memberService.findThirdPartyUser(userId, appId);
        return JsonResult.success(users);
    }
}
