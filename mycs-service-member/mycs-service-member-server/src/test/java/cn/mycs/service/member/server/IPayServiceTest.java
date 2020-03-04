package cn.mycs.service.member.server;

import cn.mycs.service.member.server.base.BaseTest;
import cn.mycs.service.member.server.service.IPayService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>IPayServiceTest</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 15:35
 * </pre>
 */
public class IPayServiceTest extends BaseTest {
    @Autowired
    private IPayService payService;
    @Test
    public void testChecked(){
        payService.checkedOrder(0L,"c298f46646af4ad3be9f8ac3e469b0cc");
    }
}
