package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>提现成功Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 16:04
 * </pre>
 */
public class WithdrawBizDto {
    /**
     * 提现金额
     */
    private String money = "";
    /**
     * 提现方式，微信
     */
    private String type = "";
    /**
     * 申请时间
     */
    private String applyTime = "";
    /**
     * 到账时间
     */
    private String arrivalTime = "";
    /**
     * 失败原因，
     */
    private String failReason = "";
    /**
     * 咨询电话
     */
    private String phone = "";

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
