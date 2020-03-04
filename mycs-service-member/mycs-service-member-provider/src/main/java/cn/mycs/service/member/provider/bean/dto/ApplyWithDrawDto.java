package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>申请提现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/29 17:24
 * </pre>
 */
public class ApplyWithDrawDto {
    private String applyId;
    private String mobile;
    private String realname;
    private String openid;
    /**
     * 微信类型，是公众号还是开放平台
     */
    private String wxType;

    public String getWxType() {
        return wxType;
    }

    public void setWxType(String wxType) {
        this.wxType = wxType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }
}
