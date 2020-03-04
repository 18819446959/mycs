package cn.mycs.service.member.feign.bean.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 佣金记录表Dto
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 18:00:07
 */
public class BizCommissionRecordDto {


    /**
     * 主键id
     */
    private String bizCommissionRecordId;
    /**
     * 会员开通记录id
     */
    private String memberJoinRecordId;
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
     * 佣金金额
     */
    private BigDecimal money;
    /**
     * 结算状态,0:未结算，1：已结算
     */
    private Integer settlementStatus;
    /**
     * 结算渠道
     */
    private String settlementChanal;
    /**
     * 审核者uid
     */
    private Long auditorUid;
    /**
     * 审核状态
     */
    private Integer auditStatus;
    /**
     * 审核时间
     */
    private Integer auditTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 分佣者id
     */
    private Long uid;
    /**
     * 业务id
     */
    private String businessId;
    /**
     * 业务类型，1：会员开通
     */
    private Integer businessType;


    public String getBizCommissionRecordId() {
        return bizCommissionRecordId;
    }

    public void setBizCommissionRecordId(String bizCommissionRecordId) {
        this.bizCommissionRecordId = bizCommissionRecordId;
    }

    public String getMemberJoinRecordId() {
        return memberJoinRecordId;
    }

    public void setMemberJoinRecordId(String memberJoinRecordId) {
        this.memberJoinRecordId = memberJoinRecordId;
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

    public Integer getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(Integer settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getSettlementChanal() {
        return settlementChanal;
    }

    public void setSettlementChanal(String settlementChanal) {
        this.settlementChanal = settlementChanal;
    }

    public Long getAuditorUid() {
        return auditorUid;
    }

    public void setAuditorUid(Long auditorUid) {
        this.auditorUid = auditorUid;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Integer auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }


    @Override
    public String toString() {
        return "BizCommissionRecord{" +
                "bizCommissionRecordId=" + bizCommissionRecordId +
                ", memberJoinRecordId=" + memberJoinRecordId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", money=" + money +
                ", settlementStatus=" + settlementStatus +
                ", settlementChanal=" + settlementChanal +
                ", auditorUid=" + auditorUid +
                ", auditStatus=" + auditStatus +
                ", auditTime=" + auditTime +
                ", remark=" + remark +
                ", uid=" + uid +
                ", businessId=" + businessId +
                ", businessType=" + businessType +
                "}";
    }
}
