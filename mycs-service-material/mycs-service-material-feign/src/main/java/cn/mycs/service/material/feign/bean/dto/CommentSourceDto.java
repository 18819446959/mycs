package cn.mycs.service.material.feign.bean.dto;

import java.io.Serializable;

/**
 * <p>学术交流评论</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 14:25
 * </pre>
 */
public class CommentSourceDto implements Serializable {

    private Integer id;
    /**
     * 发表者id
     */
    private Long fromUid;
    /**
     * 被回复者uid，默认值0则为回复话题,非0则为话题内回复某个用户
     */
    private Long replyUid;
    /**
     * 所属的交流话题id，默认值0则为发起交流话题，非0为话题讨论
     */
    private Integer parentId;
    /**
     * 发布时间，时间戳
     */
    private Integer addTime;
    private String title;
    /**
     * 话题评论内容
     */
    private String content;
    /**
     * 评论对象id，具体类型参照target_type
     */
    private Long targetId;
    /**
     * 评论对象类型，默认值0为名医学术交流，1--视频评论，2--教程评论，3--sop评论,,4--案例资源回复 ,5--活动评论 6-- 课程评论
     */
    private Integer targetType;
    /**
     * 点赞数
     */
    private Integer praiseNum;
    /**
     * 是否已读，0--未读，1--已读
     */
    private Integer isRead;
    /**
     * 回复的评论id，没有针对某个评论进行回复默认为0
     */
    private Integer replyId;
    /**
     * 接收信息的用户id
     */
    private Long toUid;
    /**
     * 回复的内容标题
     */
    private String replyTitle;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    public Long getReplyUid() {
        return replyUid;
    }

    public void setReplyUid(Long replyUid) {
        this.replyUid = replyUid;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public String getReplyTitle() {
        return replyTitle;
    }

    public void setReplyTitle(String replyTitle) {
        this.replyTitle = replyTitle;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", fromUid=" + fromUid +
                ", replyUid=" + replyUid +
                ", parentId=" + parentId +
                ", addTime=" + addTime +
                ", title=" + title +
                ", content=" + content +
                ", targetId=" + targetId +
                ", targetType=" + targetType +
                ", praiseNum=" + praiseNum +
                ", isRead=" + isRead +
                ", replyId=" + replyId +
                ", toUid=" + toUid +
                ", replyTitle=" + replyTitle +
                "}";
    }

}
