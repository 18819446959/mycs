package cn.mycs.service.member.server.bo.member;


import cn.mycs.service.member.server.persistence.model.DistributionConfig;

import java.math.BigDecimal;

/**
 * <p>按比例分销</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 10:40
 * </pre>
 */
public class ProportionCommission extends CalCommissionStrategy {

    @Override
    BigDecimal commissionMoney(DistributionConfig distributionConfig, BigDecimal orderMoney) {
        return orderMoney.multiply(new BigDecimal((distributionConfig.getScale() * 0.01)));
    }
}
