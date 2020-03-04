package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>会员类型</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/18 11:56
 * </pre>
 */
public class MemberTypeDto {
    private String memberTypeId;
    private String name;

    public String getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(String memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
