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
 * 会员身份表
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:13
 */
@TableName("member_identity")
public class MemberIdentity extends Model<MemberIdentity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "member_identity_id",type = IdType.UUID)
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
     * 会员类型id
     */
    @TableField("member_type_id")
    private String memberTypeId;
    /**
     * 会员身份名称
     */
    private String title;
    /**
     * 权益说明
     */
    @TableField("rights_desc")
    private String rightsDesc;
    /**
     * 是否参与分销0不参与，1参与
     */
    @TableField("is_commission")
    private Integer isCommission;
    /**
     * 分销配置id
     */
    @TableField("commission_id")
    private String commissionId;
    /**
     * 分销说明
     */
    @TableField("commission_explanation")
    private String commissionExplanation;
    /**
     * 状态，-1：删除，1：正常
     */
    private Integer status;


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

    public String getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(String memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRightsDesc() {
        return rightsDesc;
    }

    public void setRightsDesc(String rightsDesc) {
        this.rightsDesc = rightsDesc;
    }

    public Integer getIsCommission() {
        return isCommission;
    }

    public void setIsCommission(Integer isCommission) {
        this.isCommission = isCommission;
    }

    public String getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(String commissionId) {
        this.commissionId = commissionId;
    }

    public String getCommissionExplanation() {
        return commissionExplanation;
    }

    public void setCommissionExplanation(String commissionExplanation) {
        this.commissionExplanation = commissionExplanation;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.memberIdentityId;
    }

    @Override
    public String toString() {
        return "MemberIdentity{" +
        "memberIdentityId=" + memberIdentityId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", memberTypeId=" + memberTypeId +
        ", title=" + title +
        ", rightsDesc=" + rightsDesc +
        ", isCommission=" + isCommission +
        ", commissionId=" + commissionId +
        ", commissionExplanation=" + commissionExplanation +
        ", status=" + status +
        "}";
    }



}
