package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>身份时长Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 16:01
 * </pre>
 */
public class IdentityDurationDto{
    /**
     * 身份时长id
     */
    private String identityDurationId;
    /**
     * 身份id
     */
    private String identityId;
    /**
     * 原始价格
     */
    private Float orginalPrice;
    /**
     * 现价
     */
    private Float price;
    /**
     * 有效期
     */
    private Integer limitDate;

    public String getIdentityDurationId() {
        return identityDurationId;
    }

    public void setIdentityDurationId(String identityDurationId) {
        this.identityDurationId = identityDurationId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public Float getOrginalPrice() {
        return orginalPrice;
    }

    public void setOrginalPrice(Float orginalPrice) {
        this.orginalPrice = orginalPrice;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Integer limitDate) {
        this.limitDate = limitDate;
    }
}
