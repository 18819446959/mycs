package cn.mycs.service.member.server;


import cn.mycs.service.member.provider.bean.dto.IdentityDurationDto;
import cn.mycs.service.member.provider.bean.dto.MemberInfoDto;
import cn.mycs.service.member.provider.bean.dto.PurchaseMemberDto;
import cn.mycs.service.member.provider.bean.dto.ThirdPartyUserDto;
import cn.mycs.service.member.server.base.BaseTest;
import cn.mycs.service.member.server.bo.member.MemberBo;
import cn.mycs.service.member.server.service.IMemberService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>MemberServiceTest</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 15:50
 * </pre>
 */
public class MemberServiceTest extends BaseTest {
    @Autowired
    private IMemberService memberService;

    @Test
    public void testCheckedNotExitsUid() {
        MemberInfoDto checked = memberService.checked(-1L);
        System.out.println(checked);
    }

    @Test
    public void testCheckedExitsUid() {
        MemberInfoDto checked = memberService.checked(555L);
        System.out.println(checked);
    }

    @Test
    public void testPurchasingView() {
        //==== 身份id为空
        List<IdentityDurationDto> result = memberService.purchasingView(null);
        System.out.println(result);
        // === 身份id不为空，数据库中没有为该身份配上价格套餐
        result = memberService.purchasingView("-1");
        System.out.println(result);
        // == 身份id不为空，且数据库中是否价格套餐
        result = memberService.purchasingView("qwe");
        System.out.println(result);

    }

    @Test
    public void testPurchasing() throws Exception {
        //==== 身份id为空
        MemberBo memberBo = new MemberBo(545L);
        PurchaseMemberDto purchasing = memberBo.purchasing("", "", 30, 28f, 3);
        System.out.println(purchasing);
        purchasing = memberBo.purchasing("d94f9136c5104a9d89ba131279a6fe81", "qwe", 30, 28f, 3);
        System.out.println(purchasing);

    }

    @Test
    public void testFindOpenid() {
        ThirdPartyUserDto userOpenids = memberService.findThirdPartyUser(578739L, "wx337527ff444e9bd1");
        System.out.println(JSON.toJSONString(userOpenids));
    }

}
