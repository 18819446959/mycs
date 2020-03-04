package cn.mycs.service.member.feign.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 提现申请表Dto
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 18:00:19
 */
public class WithdrawApplyDto {


    /**
     * 主键id
     */
    private String withdrawApplyId;
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Integer createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 提现金额
     */
    private BigDecimal money;
    /**
     * 提现者id
     */
    private Long uid;
    /**
     * 提现者姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 申请状态
     */
    private Integer status;
    /**
     * 申请时间
     */
    private Integer applyTime;
    /**
     * 到账时间
     */
    private Integer gainedTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 审核人id
     */
    private Long auditorUid;
    /**
     * 审核时间
     */
    private Integer auditTime;


    public String getWithdrawApplyId() {
        return withdrawApplyId;
    }

    public void setWithdrawApplyId(String withdrawApplyId) {
        this.withdrawApplyId = withdrawApplyId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Integer applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getGainedTime() {
        return gainedTime;
    }

    public void setGainedTime(Integer gainedTime) {
        this.gainedTime = gainedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAuditorUid() {
        return auditorUid;
    }

    public void setAuditorUid(Long auditorUid) {
        this.auditorUid = auditorUid;
    }

    public Integer getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Integer auditTime) {
        this.auditTime = auditTime;
    }


    @Override
    public String toString() {
        return "WithdrawApply{" +
                "withdrawApplyId=" + withdrawApplyId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", money=" + money +
                ", uid=" + uid +
                ", name=" + name +
                ", mobile=" + mobile +
                ", status=" + status +
                ", applyTime=" + applyTime +
                ", gainedTime=" + gainedTime +
                ", remark=" + remark +
                ", auditorUid=" + auditorUid +
                ", auditTime=" + auditTime +
                "}";
    }
}
