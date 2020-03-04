package cn.mycs.service.member.server.bo.member;


import cn.mycs.service.member.server.persistence.model.DistributionConfig;

import java.math.BigDecimal;

/**
 * <p>计算佣金策略抽象</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 10:29
 * </pre>
 */
public abstract class CalCommissionStrategy {
    abstract BigDecimal commissionMoney(DistributionConfig distributionConfig, BigDecimal orderMoney);
}
