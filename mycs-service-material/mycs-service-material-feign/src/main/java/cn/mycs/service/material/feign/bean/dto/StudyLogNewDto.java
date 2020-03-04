package cn.mycs.service.material.feign.bean.dto;

import java.io.Serializable;

/**
 * <p>学习日志传输类</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 11:43
 * </pre>
 */
public class StudyLogNewDto implements Serializable {
    private Integer id;

    private Integer goodsId;
    private Integer goodsType;
    private Integer courseId;
    private Integer chapterId;
    private Integer videoId;
    /**
     * 商品拥有者id
     */
    private Long ownerUid;
    /**
     * 学习者uid
     */
    private Long studyUid;
    /**
     * 学习任务id，为0则为自主学习
     */
    private Integer taskId;
    private Integer addTime;
    /**
     * 学习用时
     */
    private Integer studyTime;
    /**
     * 付费类型，0--内部公开，1--外部付费，2--外部验证
     */
    private Integer payType;
    private Integer device;
    private Integer recid;
    /**
     * 正确率
     */
    private Float accuracy;
    /**
     * 是否通过，0-未通过 1-已通过
     */
    private Integer passed;
    /**
     * 结束时间
     */
    private Integer endTime;
    /**
     * 视频播放进度时间点
     */
    private Integer videoTimeSpot;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Long getOwnerUid() {
        return ownerUid;
    }

    public void setOwnerUid(Long ownerUid) {
        this.ownerUid = ownerUid;
    }


    public Long getStudyUid() {
        return studyUid;
    }

    public void setStudyUid(Long studyUid) {
        this.studyUid = studyUid;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getAddTime() {
        return addTime;
    }

    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    public Integer getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Integer studyTime) {
        this.studyTime = studyTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }


    public Integer getDevice() {
        return device;
    }

    public void setDevice(Integer device) {
        this.device = device;
    }

    public Integer getRecid() {
        return recid;
    }

    public void setRecid(Integer recid) {
        this.recid = recid;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getPassed() {
        return passed;
    }

    public void setPassed(Integer passed) {
        this.passed = passed;
    }

    public Integer getEndTime() {
        return endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getVideoTimeSpot() {
        return videoTimeSpot;
    }

    public void setVideoTimeSpot(Integer videoTimeSpot) {
        this.videoTimeSpot = videoTimeSpot;
    }
}
