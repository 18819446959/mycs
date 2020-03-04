package cn.mycs.service.member.server.persistence.model;

import cn.mycs.core.base.domain.BaseDomain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import java.math.BigDecimal;

/**
 * <p>会员开通记录表</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 11:46
 * </pre>
 */
@TableName("member_join_record")
public class MemberJoinRecord extends BaseDomain {


    private static final long serialVersionUID = 6695755672573765480L;
    @TableId(value = "member_join_record_id",type = IdType.UUID)
    private String memberJoinRecordId;
    @TableField("member_identity_id")
    private String memberIdentityId;
    @TableField("create_by")
    private Long createBy;
    @TableField("create_time")
    private Integer createTime;
    @TableField("update_by")
    private Long updateBy;
    private Long uid;
    private Integer duration;
    private BigDecimal amount;
    private Integer status;
    private Integer device;
    @TableField("share_id")
    private String shareId;
    @TableField("source_type")
    private String sourceType;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public String getMemberJoinRecordId() {
        return memberJoinRecordId;
    }

    public void setMemberJoinRecordId(String memberJoinRecordId) {
        this.memberJoinRecordId = memberJoinRecordId;
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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}