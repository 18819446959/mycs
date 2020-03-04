package cn.mycs.service.member.server.persistence.model;



import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分销配置表
 * </p>
 *
 * @author User
 * @date 2019-09-12 10:42:56
 */
@TableName("distribution_config")
public class DistributionConfig extends Model<DistributionConfig> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "distribution_config_id",type = IdType.UUID)
    private String distributionConfigId;
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
     * 分销类型,1，按金额，2：按比例
     */
    @TableField("commission_type")
    private Integer commissionType;
    /**
     * 金额
     */
    private Integer amount;
    /**
     * 比例
     */
    private Integer scale;


    public String getDistributionConfigId() {
        return distributionConfigId;
    }

    public void setDistributionConfigId(String distributionConfigId) {
        this.distributionConfigId = distributionConfigId;
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

    public Integer getCommissionType() {
        return commissionType;
    }

    public void setCommissionType(Integer commissionType) {
        this.commissionType = commissionType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    @Override
    protected Serializable pkVal() {
        return this.distributionConfigId;
    }

    @Override
    public String toString() {
        return "DistributionConfig{" +
        "distributionConfigId=" + distributionConfigId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", commissionType=" + commissionType +
        ", amount=" + amount +
        ", scale=" + scale +
        "}";
    }
}
