package cn.mycs.service.member.server;

import cn.mycs.core.base.restful.JsonResult;

import cn.mycs.service.member.provider.bean.dto.BindWxDto;
import cn.mycs.service.member.server.base.BaseTest;
import cn.mycs.service.member.server.service.IWalletService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>钱包测试类</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/10 19:38
 * </pre>
 */
public class WalletServiceTest extends BaseTest {
    @Autowired
    private IWalletService walletService;

    @Test
    public void testCheckedBind() {
        BindWxDto bindWechat = walletService.isBindWechat(-1L);
        System.out.println(bindWechat);
        bindWechat = walletService.isBindWechat(250091L);
        System.out.println(bindWechat);
    }
    @Test
    public void testWithDrawNotify() {
        JsonResult notify = walletService.withDrawNotify("wpgCs3",4,"提现成功了");
        System.out.println(notify);
    }

}
