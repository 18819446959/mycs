package cn.mycs.service.material.provider.bean.dto;

/**
 * <p>视频评论dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 14:52
 * </pre>
 */
public class CommentDto {
    /**
     * 评论时间，comment表中的addTime字段
     */
    private String time = "";
    /**
     * 评论id
     */
    private Integer id;
    /**
     * 评论用户id
     */
    private Long uid;
    private String name;
    private String pic;
    private Long replyUid;
    private String replyName;
    /**
     * 评论内容
     */
    private String content = "";

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Long getReplyUid() {
        return replyUid;
    }

    public void setReplyUid(Long replyUid) {
        this.replyUid = replyUid;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }
}
