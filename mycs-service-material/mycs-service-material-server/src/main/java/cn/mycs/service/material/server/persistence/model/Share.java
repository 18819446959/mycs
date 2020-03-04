package cn.mycs.service.material.server.persistence.model;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 推广分享表
 * </p>
 *
 * @author User
 * @date 2019-09-05 18:02:27
 */
@TableName("share")
public class Share extends Model<Share> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("share_id")
    private String shareId;
    /**
     * 资源id,视频、教程等
     */
    @TableField("src_id")
    private Long srcId;
    /**
     * 资源名称
     */
    @TableField("src_name")
    private String srcName;
    /**
     * 资源类型,1：视频，2：教程，3：sop等
     */
    @TableField("src_type")
    private Integer srcType;
    /**
     * 分享者uid
     */
    @TableField("share_uid")
    private Long shareUid;
    /**
     * 推荐理由
     */
    @TableField("share_reason")
    private String shareReason;
    /**
     * 分享码，邀请码
     */
    @TableField("share_code")
    private String shareCode;
    /**
     * 分享时间，单位秒
     */
    @TableField("share_time")
    private Integer shareTime;
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


    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public Long getSrcId() {
        return srcId;
    }

    public void setSrcId(Long srcId) {
        this.srcId = srcId;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Integer getSrcType() {
        return srcType;
    }

    public void setSrcType(Integer srcType) {
        this.srcType = srcType;
    }

    public Long getShareUid() {
        return shareUid;
    }

    public void setShareUid(Long shareUid) {
        this.shareUid = shareUid;
    }

    public String getShareReason() {
        return shareReason;
    }

    public void setShareReason(String shareReason) {
        this.shareReason = shareReason;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public Integer getShareTime() {
        return shareTime;
    }

    public void setShareTime(Integer shareTime) {
        this.shareTime = shareTime;
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

    @Override
    protected Serializable pkVal() {
        return this.shareId;
    }

    @Override
    public String toString() {
        return "Share{" +
                "shareId=" + shareId +
                ", srcId=" + srcId +
                ", srcName=" + srcName +
                ", srcType=" + srcType +
                ", shareUid=" + shareUid +
                ", shareReason=" + shareReason +
                ", shareCode=" + shareCode +
                ", shareTime=" + shareTime +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                "}";
    }
}
