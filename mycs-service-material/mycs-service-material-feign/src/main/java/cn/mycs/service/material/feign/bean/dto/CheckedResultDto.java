package cn.mycs.service.material.feign.bean.dto;

import java.io.Serializable;

/**
 * <p>检测权限回传dto</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/30 11:53
 * </pre>
 */
public class CheckedResultDto implements Serializable {
    private boolean buy;
    private boolean free;
    private boolean needVip;

    public boolean isBuy() {
        return buy;
    }

    public void setBuy(boolean buy) {
        this.buy = buy;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public boolean isNeedVip() {
        return needVip;
    }

    public void setNeedVip(boolean needVip) {
        this.needVip = needVip;
    }
}
