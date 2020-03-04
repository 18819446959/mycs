package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>购买会员成功之后，返回数据Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 14:10
 * </pre>
 */
public class PurchaseMemberDto {
    /**
     * 成功开通会员id
     */
    private String memberJoinId = "";
    /**
     * 会员身份信息
     */
    private MemberIdentityDto memberIdentity;

    public String getMemberJoinId() {
        return memberJoinId;
    }

    public void setMemberJoinId(String memberJoinId) {
        this.memberJoinId = memberJoinId;
    }

    public MemberIdentityDto getMemberIdentity() {
        return memberIdentity;
    }

    public void setMemberIdentity(MemberIdentityDto memberIdentity) {
        this.memberIdentity = memberIdentity;
    }

}
