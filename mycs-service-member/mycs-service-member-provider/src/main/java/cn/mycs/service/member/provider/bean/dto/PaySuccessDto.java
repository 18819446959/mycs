package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>支付成功dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/25 15:23
 * </pre>
 */
public class PaySuccessDto {
    /**
     * 分佣的业务id
     */
    private String commissionRecordId;
    /**
     * 是否参与分佣
     */
    private int isCommission;
    /**
     * 分佣备注
     */
    private String remark;
    /**
     * 分佣金额
     */
    private long money;
    /**
     * 分佣金额
     */
    private String videoName;
    /**
     * 分佣金额
     */
    private Long uid;

    public Long getUid() {
        return uid == null ? 0L : uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getCommissionRecordId() {
        return commissionRecordId;
    }

    public void setCommissionRecordId(String commissionRecordId) {
        this.commissionRecordId = commissionRecordId;
    }

    public int getIsCommission() {
        return isCommission;
    }

    public void setIsCommission(int isCommission) {
        this.isCommission = isCommission;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }
}
