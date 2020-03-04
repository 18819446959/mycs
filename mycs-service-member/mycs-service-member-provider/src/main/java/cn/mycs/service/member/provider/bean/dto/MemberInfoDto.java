package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>会员信息Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 11:47
 * </pre>
 */
public class MemberInfoDto {
    /**
     * 是否是会员，0：不是会员，1：是会员
     */
    private Integer isMember;
    /**
     * 未读订单数
     */
    private Integer orderNum;
    /**
     * 会员的开始时间
     */
    private String startTime;
    /**
     * 会员的结束时间
     */
    private String endTime;
    /**
     * 会员名称
     */
    private String memberName = "非会员";

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
