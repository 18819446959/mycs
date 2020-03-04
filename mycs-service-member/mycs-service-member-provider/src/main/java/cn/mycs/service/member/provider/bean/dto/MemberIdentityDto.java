package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>会员身份</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/18 11:56
 * </pre>
 */
public class MemberIdentityDto {
    private String memberIdentityId;
    private String name;

    public String getMemberIdentityId() {
        return memberIdentityId;
    }

    public void setMemberIdentityId(String memberIdentityId) {
        this.memberIdentityId = memberIdentityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
