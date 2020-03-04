package cn.mycs.service.member.server.bo.member;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>计算佣金工厂</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 10:32
 * </pre>
 */
public class CalCommissionFactory {

    private static Map<Integer, CalCommissionStrategy> strategyMap = new HashMap<>(16);

    static {
        strategyMap.put(1, new CashCommission());
        strategyMap.put(2, new ProportionCommission());
    }

    public static CalCommissionStrategy getCalStrategy(Integer type) {
        CalCommissionStrategy calCommissionStrategy = strategyMap.get(type);
        if (calCommissionStrategy == null) {
            calCommissionStrategy = strategyMap.get(1);
        }
        return calCommissionStrategy;

    }
}
