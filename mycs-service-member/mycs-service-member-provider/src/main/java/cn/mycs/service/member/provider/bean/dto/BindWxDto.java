package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>是否有绑定微信Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 19:29
 * </pre>
 */
public class BindWxDto {
    /**
     * 是否有绑定微信，0未绑定，1绑定
     */
    private Integer isBind = 0;
    /**
     * 微信头像
     */
    private String wxAvatar;
    /**
     * 微信名称
     */
    private String wxName;

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }

    public String getWxAvatar() {
        return wxAvatar;
    }

    public void setWxAvatar(String wxAvatar) {
        this.wxAvatar = wxAvatar;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }
}
