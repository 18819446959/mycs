package cn.mycs.service.member.server;


import cn.mycs.service.member.provider.bean.dto.PrivilegeDto;
import cn.mycs.service.member.server.base.BaseTest;
import cn.mycs.service.member.server.bo.member.MemberBo;
import cn.mycs.service.member.server.persistence.dao.MemberMapper;
import cn.mycs.service.member.server.persistence.model.Member;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>会员bo测试</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 16:56
 * </pre>
 */
public class MemberBoTest extends BaseTest {
    @Autowired
    private MemberMapper memberMapper;
    @Test
    public void testPrivilegeNotMember() {
        // 测试获取会员特权，会员不存在情况
        MemberBo memberBo = new MemberBo(-1L);
        PrivilegeDto privilege = memberBo.getMemberIdentity().privilege();
        System.out.println("测试获取会员特权，会员不存在情况===" + privilege);
    }

    @Test
    public void testPrivilegeNotIdentityMember() {
        // 测试获取会员特权，会员存在情况,但是身份不存情况
        MemberBo memberBo = new MemberBo(123L);
        PrivilegeDto privilege = memberBo.getMemberIdentity().privilege();
        System.out.println("测试获取会员特权，会员存在情况,但是身份不存情况==" + privilege);
    }

    @Test
    public void testPrivilegeHaveIdentityMember() {
        // 测试获取会员特权，会员存在情况,身份存在情况
        MemberBo memberBo = new MemberBo(0L);
        PrivilegeDto privilege = memberBo.getMemberIdentity().privilege();
        System.out.println("测试获取会员特权，会员存在情况,身份存在情况==" + privilege);
    }
    @Test
    public void testAddMember() {
        // 测试获取会员特权，会员存在情况,身份存在情况
        Member memberBo = new Member();
        memberBo.setUid(1L);
        memberBo.setMemberId("1");
        memberBo.setEndTime(11);
        memberBo.setStartTime(113);
        memberBo.setCreateBy(0L);
        memberBo.setMemberId("1");
        memberMapper.insert(memberBo);
    }
}
