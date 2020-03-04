package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>购买成功账单详情Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 16:07
 * </pre>
 */
public class BuyBillBizDto {
    /**
     * 商品
     */
    private String commodity = "";
    /**
     * 有效期
     */
    private String validityPeriod = "";
    /**
     * 支付金额
     */
    private String payMoney = "";
    /**
     * 支付时间
     */
    private String payTime = "";
    /**
     * 流水号
     */
    private String payNum="";
    /**
     * 支付方式
     */
    private String payType="";

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
