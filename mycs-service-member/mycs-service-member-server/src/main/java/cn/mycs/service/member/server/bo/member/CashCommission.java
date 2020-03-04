package cn.mycs.service.member.server.bo.member;


import cn.mycs.service.member.server.persistence.model.DistributionConfig;

import java.math.BigDecimal;

/**
 * <p>现金分销</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 10:40
 * </pre>
 */
public class CashCommission extends CalCommissionStrategy {

    @Override
    BigDecimal commissionMoney(DistributionConfig distributionConfig, BigDecimal orderMoney) {
        Integer amount = distributionConfig.getAmount();
        return new BigDecimal(amount);
    }
}
