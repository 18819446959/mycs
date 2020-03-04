package cn.mycs.service.material.server;

import cn.mycs.service.material.server.base.BaseTest;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.IShareService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>请描述本类的作用</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 17:51
 * </pre>
 */

public class IShareServiceTest extends BaseTest {
    @Autowired
    private IShareService shareService;

    @Test
    public void testGetShareById() {
        String shareId = "0010145899134e7981021e960de311c6";
        Share share = shareService.getShareById(shareId);
        System.out.println(JSON.toJSONString(share));


    }
    @Test
    public void testGetVideo() {
        int  video = shareService.howManyShare(274569L,1);
        System.out.println(JSON.toJSONString(video));
    }
}
