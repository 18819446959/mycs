package cn.mycs.service.member.provider.bean.dto;

/**
 * <p>分佣详情Dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 15:55
 * </pre>
 */
public class CommissionBizDto {
    /**
     * 视频用户id
     */
    private Integer videoUserId = 0;
    /**
     * 视频名称
     */
    private String videoName = "";
    /**
     * 购买者
     */
    private String buyer = "";
    /**
     * 分佣订单金额
     */
    private String orderMoney = "";
    /**
     * 分佣比例
     */
    private String proportion = "";
    /**
     * 佣金金额
     */
    private String money = "";
    /**
     * 时间
     */
    private String date = "";

    public Integer getVideoUserId() {
        return videoUserId;
    }

    public void setVideoUserId(Integer videoUserId) {
        this.videoUserId = videoUserId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
