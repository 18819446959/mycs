package cn.mycs.service.material.provider.bean.dto;

/**
 * <p>进入分享封面Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 17:42
 * </pre>
 */
public class VideoInfoDto {
    /**
     * 分享视频缩略图
     */
    private String   videoThumb;
    /**
     * 分享视频标题
     */
    private String  videoTitle;
    /**
     * 分享海报
     */
    private String   videoPoster;

    public String getVideoThumb() {
        return videoThumb;
    }

    public void setVideoThumb(String videoThumb) {
        this.videoThumb = videoThumb;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoPoster() {
        return videoPoster;
    }

    public void setVideoPoster(String videoPoster) {
        this.videoPoster = videoPoster;
    }
}
