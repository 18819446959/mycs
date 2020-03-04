package cn.mycs.service.member.server.persistence.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员时长卡
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:20
 */
@TableName("member_identity_duration")
public class MemberIdentityDuration extends Model<MemberIdentityDuration> {

    private static final long serialVersionUID = 1L;

    /**
     * 会员身份id
     */
    @TableId(value = "member_identity_duration_id",type = IdType.UUID)
    private String memberIdentityDurationId;
    /**
     * 主键
     */
    @TableField("member_identity_id")
    private String memberIdentityId;
    /**
     * 创建人
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Integer createTime;
    /**
     * 更新人
     */
    @TableField("update_by")
    private Long updateBy;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 原价
     */
    @TableField("original_price")
    private BigDecimal originalPrice;
    /**
     * 售价
     */
    private BigDecimal price;
    /**
     * 期限
     */
    @TableField("limit_date")
    private Integer limitDate;
    /**
     * 状态，-1：删除，1：正常
     */
    private Integer status;


    public String getMemberIdentityDurationId() {
        return memberIdentityDurationId;
    }

    public void setMemberIdentityDurationId(String memberIdentityDurationId) {
        this.memberIdentityDurationId = memberIdentityDurationId;
    }

    public String getMemberIdentityId() {
        return memberIdentityId;
    }

    public void setMemberIdentityId(String memberIdentityId) {
        this.memberIdentityId = memberIdentityId;
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

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Integer limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return memberIdentityDurationId;
    }

    @Override
    public String toString() {
        return "MemberIdentityDuration{" +
                "memberIdentityDurationId=" + memberIdentityDurationId +
                ", memberIdentityId=" + memberIdentityId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", originalPrice=" + originalPrice +
                ", price=" + price +
                ", limitDate=" + limitDate +
                ", status=" + status +
                "}";
    }
}
