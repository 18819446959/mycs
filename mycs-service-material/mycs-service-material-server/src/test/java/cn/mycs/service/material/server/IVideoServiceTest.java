package cn.mycs.service.material.server;

import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.service.material.server.base.BaseTest;
import cn.mycs.service.material.server.service.IVideoService;
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

public class IVideoServiceTest extends BaseTest {
    @Autowired
    private IVideoService videoService;

    @Test
    public void testGetVideo() {
        Long videoId = 9L;
        VideoVo video = videoService.getVideo(videoId);
        System.out.println(JSON.toJSONString(video));
    }

    @Test
    public void testGetVideoUserLink() {
        Long videoId = 1L;
        VideoInfoVo videoUserLink = videoService.getVideoInfoVo(videoId);
        System.out.println(JSON.toJSONString(videoUserLink));
    }

    @Test
    public void testGetCateNames() {
        Long materialId = 200153989L;
        String cateNames = videoService.getCateNames(materialId);
        System.out.println(cateNames);

    }
}
