package cn.mycs.service.material.provider.bean.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>视频详情Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 14:09
 * </pre>
 */
public class VideoDetailDto {
    /**
     * 视频封面链接，根据RuleUtil.getPhotoUrl();运算
     */
    private String imgSrc = "";
    /**
     * 视频名称，video_user_link中title字段
     */
    private String title = "";
    /**
     * 分类名称,video_user_link中cateId 查询分类姓名
     */
    private String cateName = "";
    /**
     * 视频来源，【video】表中的upload_uid,查找上传者的名称
     */
    private String src = "";
    /**
     * 视频描述，简介，video_user_link中describe字段
     */
    private String desc = "";
    /**
     * 推荐人总数，从根据videoUserId从share表中统计推荐过该视频的人数
     */
    private Integer recommendCount = 0;

    /**
     * r用户头像列表
     */
    private List<String> recommendUser=new ArrayList<>();
    /**
     * 视频时长
     */
    private Integer duration = 0;
    /**
     * 点赞数
     */
    private Integer praiseCount = 0;
    /**
     * 播放数
     */
    private Integer playCount = 0;

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<String> getRecommendUser() {
        return recommendUser;
    }

    public void setRecommendUser(List<String> recommendUser) {
        this.recommendUser = recommendUser;
    }

    public Integer getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(Integer recommendCount) {
        this.recommendCount = recommendCount;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
