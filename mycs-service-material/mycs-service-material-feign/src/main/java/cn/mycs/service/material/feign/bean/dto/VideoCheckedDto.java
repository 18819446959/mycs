package cn.mycs.service.material.feign.bean.dto;

import java.io.Serializable;

/**
 * <p>视频检测dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 14:52
 * </pre>
 */

public class VideoCheckedDto implements Serializable {
    private String id;
    private Long uid;
    private Integer userType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}