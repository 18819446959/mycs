package cn.mycs.service.material.provider.bean.dto;

/**
 * <p>播放Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 10:52
 * </pre>
 */
public class PlayDto {
    /**
     * 播放链接
     */
    private String playUrl;
    /**
     * 是否预览播放
     */
    private int isPreView;
    /**
     * 预览秒数
     */
    private int preViewSecond;
    /**
     * 学习记录id
     */
    private int studyLogId;

    public int getStudyLogId() {
        return studyLogId;
    }

    public void setStudyLogId(int studyLogId) {
        this.studyLogId = studyLogId;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public int getIsPreView() {
        return isPreView;
    }

    public void setIsPreView(int isPreView) {
        this.isPreView = isPreView;
    }

    public int getPreViewSecond() {
        return preViewSecond;
    }

    public void setPreViewSecond(int preViewSecond) {
        this.preViewSecond = preViewSecond;
    }
}
