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
 * 会员邀请记录表
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:31
 */
@TableName("member_invite_record")
public class MemberInviteRecord extends Model<MemberInviteRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "member_invite_record_id",type = IdType.UUID)
    private String memberInviteRecordId;
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
     * 邀请者uid
     */
    @TableField("inviter_uid")
    private Long inviterUid;
    /**
     * ShareId
     */
    @TableField("share_id")
    private String shareId;
    /**
     * 用户id
     */
    private Long uid;
    /**
     * 会员开通id
     */
    @TableField("member_join_record_id")
    private String memberJoinRecordId;


    public String getMemberInviteRecordId() {
        return memberInviteRecordId;
    }

    public void setMemberInviteRecordId(String memberInviteRecordId) {
        this.memberInviteRecordId = memberInviteRecordId;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
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

    public Long getInviterUid() {
        return inviterUid;
    }

    public void setInviterUid(Long inviterUid) {
        this.inviterUid = inviterUid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getMemberJoinRecordId() {
        return memberJoinRecordId;
    }

    public void setMemberJoinRecordId(String memberJoinRecordId) {
        this.memberJoinRecordId = memberJoinRecordId;
    }

    @Override
    protected Serializable pkVal() {
        return this.memberInviteRecordId;
    }

    @Override
    public String toString() {
        return "MemberInviteRecord{" +
        "memberInviteRecordId=" + memberInviteRecordId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", inviterUid=" + inviterUid +
        ", uid=" + uid +
        ", memberJoinRecordId=" + memberJoinRecordId +
        "}";
    }
}
