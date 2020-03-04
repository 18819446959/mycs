package cn.mycs.service.material.server.persistence.model;



import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 分享推广日志表
 * </p>
 *
 * @author User
 * @date 2019-09-05 18:02:45
 */
@TableName("share_click_log")
public class ShareClickLog extends Model<ShareClickLog> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId("click_log_id")
    private String clickLogId;
    /**
     * 分享id
     */
    @TableField("share_id")
    private String shareId;
    /**
     * 资源id
     */
    @TableField("src_id")
    private Long srcId;
    /**
     * 分享者id
     */
    @TableField("share_uid")
    private Long shareUid;
    /**
     * 点击者
     */
    @TableField("click_uid")
    private Long clickUid;
    /**
     * 添加时间，（秒）
     */
    @TableField("add_time")
    private Integer addTime;
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


    public String getClickLogId() {
        return clickLogId;
    }

    public void setClickLogId(String clickLogId) {
        this.clickLogId = clickLogId;
    }

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

    public Long getShareUid() {
        return shareUid;
    }

    public void setShareUid(Long shareUid) {
        this.shareUid = shareUid;
    }

    public Long getClickUid() {
        return clickUid;
    }

    public void setClickUid(Long clickUid) {
        this.clickUid = clickUid;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
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
        return this.clickLogId;
    }

    @Override
    public String toString() {
        return "ClickLog{" +
        "clickLogId=" + clickLogId +
        ", shareId=" + shareId +
        ", srcId=" + srcId +
        ", shareUid=" + shareUid +
        ", clickUid=" + clickUid +
        ", addTime=" + addTime +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        "}";
    }
}
