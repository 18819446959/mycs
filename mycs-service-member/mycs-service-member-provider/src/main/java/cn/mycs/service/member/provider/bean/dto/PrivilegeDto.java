package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>会员特权</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/20 11:43
 * </pre>
 */
public class PrivilegeDto {
    /**
     * 会员的有效期
     */
    private String limitDate;
    /**
     * 会员类型
     */
    private String memberType;
    /**
     * 会员名称
     */
    private String name;
    /**
     * 会员权益说明
     */
    private String privilegeDesc;
    /**
     * 会员头像
     */
    private String avatar;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(String limitDate) {
        this.limitDate = limitDate;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getPrivilegeDesc() {
        return privilegeDesc;
    }

    public void setPrivilegeDesc(String privilegeDesc) {
        this.privilegeDesc = privilegeDesc;
    }
}
