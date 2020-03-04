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
 * 会员身份视频关系表
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:26
 */
@TableName("member_identity_video_link")
public class MemberIdentityVideoLink extends Model<MemberIdentityVideoLink> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "member_identity_video_link_id", type = IdType.UUID)
    private String memberIdentityVideoLinkId;
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
     * 视频id
     */
    @TableField("video_id")
    private Integer videoId;
    /**
     * 会员身份id
     */
    @TableField("member_identity_id")
    private String memberIdentityId;


    public String getMemberIdentityVideoLinkId() {
        return memberIdentityVideoLinkId;
    }

    public void setMemberIdentityVideoLinkId(String memberIdentityVideoLinkId) {
        this.memberIdentityVideoLinkId = memberIdentityVideoLinkId;
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

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getMemberIdentityId() {
        return memberIdentityId;
    }

    public void setMemberIdentityId(String memberIdentityId) {
        this.memberIdentityId = memberIdentityId;
    }

    @Override
    protected Serializable pkVal() {
        return this.memberIdentityVideoLinkId;
    }

    @Override
    public String toString() {
        return "MemberIdentityVideoLink{" +
                "memberIdentityVideoLinkId=" + memberIdentityVideoLinkId +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", videoId=" + videoId +
                ", memberIdentityId=" + memberIdentityId +
                "}";
    }
}
