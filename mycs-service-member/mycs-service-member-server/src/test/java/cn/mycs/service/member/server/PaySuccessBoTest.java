package cn.mycs.service.member.server;

import cn.mycs.core.base.restful.JsonResult;

import cn.mycs.service.member.provider.bean.dto.PaySuccessDto;
import cn.mycs.service.member.server.base.BaseTest;
import cn.mycs.service.member.server.bo.member.PaySuccessBo;
import org.junit.Test;

/**
 * <p>paySuccessTest</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/12 11:35
 * </pre>
 */
public class PaySuccessBoTest extends BaseTest {
    @Test
    public void testSuccess() {
        JsonResult<PaySuccessDto> success = new PaySuccessBo("47a7aa22eaab4ce5bdd4b46fb5a3eed5").success();
        System.out.println(success);
    }
}
