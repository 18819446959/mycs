package cn.mycs.service.material.provider.bean.dto;

/**
 * <p>视频推荐dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 14:52
 * </pre>
 */
public class RecommendDto {
    /**
     * 推荐时间，share
     */
    private String time = "";
    /**
     * 推荐理由
     */
    private String recommendReason = "";
    /**
     * 推荐者名称
     */
    private String name;
    /**
     * 推荐者头像
     */
    private String recommendUserAvatar;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRecommendReason() {
        return recommendReason;
    }

    public void setRecommendReason(String recommendReason) {
        this.recommendReason = recommendReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendUserAvatar() {
        return recommendUserAvatar;
    }

    public void setRecommendUserAvatar(String recommendUserAvatar) {
        this.recommendUserAvatar = recommendUserAvatar;
    }
}
