package cn.mycs.service.material.provider.bean.dto;

/**
 * <p>分享成功dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 17:35
 * </pre>
 */
public class ShareDto {
    /**
     * 分享id
     */
    private String shareId = "";
    /**
     * 第几位分享者
     */
    private Integer shareIndex = 1;

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public Integer getShareIndex() {
        return shareIndex;
    }

    public void setShareIndex(Integer shareIndex) {
        this.shareIndex = shareIndex;
    }
}
