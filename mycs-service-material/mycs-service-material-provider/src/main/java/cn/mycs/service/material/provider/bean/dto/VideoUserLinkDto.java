package cn.mycs.service.material.provider.bean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>VideoUserLink</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 10:18
 * </pre>
 */
public class VideoUserLinkDto {

    private Integer id;
    /**
     * 视频拥有者id
     */
    private Long uid = 0L;
    /**
     * 视频id
     */
    private Integer videoId = 0;
    /**
     * 视频获取类型，0--自制，1--购买
     */
    private Integer type = 0;
    /**
     * 视频标题
     */
    private String title = "";
    /**
     * 视频描述
     */
    private String describe = "";
    /**
     * 个人付费价格
     */
    private Integer personPrice = 0;
    /**
     * 团体付费价格
     */
    private Integer groupPrice = 0;
    /**
     * 外部验证文字
     */
    private String checkWord = "";
    /**
     * 分类id
     */
    private Integer cateId = 0;
    /**
     * 难度级别
     */
    private Integer classId = 0;
    /**
     * 分享图片
     */
    private Integer shareCover = 0;
    /**
     * 中西医
     */
    private Integer cateType = 0;
    /**
     * 外部权限,0--外部不公开,1--外部公开,2--外部验证可看,3--外部付费可看
     */
    private Integer extPermission = 0;
    /**
     * 内部权限,0--内部不公开,1--内部公开,2:科室公开
     */
    private Integer intPermission = 0;
    /**
     * 添加时间
     */
    private Integer addtime = 0;
    /**
     * 状态，0--未删除，1--已删除，2--未审核
     */
    private Integer status = 2;
    private String tag = "";
    /**
     * '是否上传视频封面 ，--0，未上传，--1，已上传'
     */
    private Integer videocover = 0;
    /**
     * 资源作者用户id，0--默认等同上传者
     */
    private Integer authorUid = 0;
    /**
     * 来源部门
     */
    private Integer deptId = 0;
    /**
     * 封面轮播图数组，json格式
     */
    private String picList = "";
    private Date lastUpdate = new Date();

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getPersonPrice() {
        return personPrice;
    }

    public void setPersonPrice(Integer personPrice) {
        this.personPrice = personPrice;
    }

    public Integer getGroupPrice() {
        return groupPrice;
    }

    public void setGroupPrice(Integer groupPrice) {
        this.groupPrice = groupPrice;
    }

    public String getCheckWord() {
        return checkWord;
    }

    public void setCheckWord(String checkWord) {
        this.checkWord = checkWord;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCateType() {
        return cateType;
    }

    public void setCateType(Integer cateType) {
        this.cateType = cateType;
    }

    public Integer getExtPermission() {
        return extPermission;
    }

    public void setExtPermission(Integer extPermission) {
        this.extPermission = extPermission;
    }

    public Integer getIntPermission() {
        return intPermission;
    }

    public void setIntPermission(Integer intPermission) {
        this.intPermission = intPermission;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getShareCover() {
        return shareCover;
    }

    public void setShareCover(Integer shareCover) {
        this.shareCover = shareCover;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getVideocover() {
        return videocover;
    }

    public void setVideocover(Integer videocover) {
        this.videocover = videocover;
    }


    public Integer getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(Integer authorUid) {
        this.authorUid = authorUid;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getPicList() {
        return picList;
    }

    public void setPicList(String picList) {
        this.picList = picList;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


    @Override
    public String toString() {
        return "VideoUserLink{" +
                "id=" + id +
                ", uid=" + uid +
                ", type=" + type +
                ", title=" + title +
                ", describe=" + describe +
                ", personPrice=" + personPrice +
                ", groupPrice=" + groupPrice +
                ", checkWord=" + checkWord +
                ", cateId=" + cateId +
                ", classId=" + classId +
                ", cateType=" + cateType +
                ", extPermission=" + extPermission +
                ", intPermission=" + intPermission +
                ", addtime=" + addtime +
                ", status=" + status +
                ", tag=" + tag +
                ", videocover=" + videocover +
                ", authorUid=" + authorUid +
                ", deptId=" + deptId +
                ", picList=" + picList +
                ", shareCover=" + shareCover +
                ", lastUpdate=" + lastUpdate +
                "}";
    }
}
