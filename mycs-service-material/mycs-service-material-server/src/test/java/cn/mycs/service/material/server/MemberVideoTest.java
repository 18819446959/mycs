package cn.mycs.service.material.server;

import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.provider.bean.dto.VideoDetailDto;
import cn.mycs.service.material.provider.bean.dto.VideoInfoDto;
import cn.mycs.service.material.server.base.BaseTest;
import cn.mycs.service.material.server.bo.material.video.MemberVideo;
import cn.mycs.service.material.server.bo.material.video.QRCodeShare;
import cn.mycs.service.material.server.bo.material.video.VideoDetail;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * <p>MemberVideoTest</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 17:46
 * </pre>
 */
public class MemberVideoTest extends BaseTest {
    @Test
    public void videoInfoExists() {
        // videoUserId 不存在
        VideoInfoDto videoInfo = new MemberVideo(277229L).getVideoInfo();
        System.out.println(JSON.toJSONString(videoInfo));
    }

    @Test
    public void videoInfoNotExists() {
        // videoUserId 存在
        VideoInfoDto videoInfo = new MemberVideo(-1L).getVideoInfo();
        System.out.println(JSON.toJSONString(videoInfo));
    }

    @Test
    public void testVideoShare() {
        // 视频不存在
        MemberVideo memberVideo = new MemberVideo(-1L);

        ShareDto share = memberVideo.share(5617L, new QRCodeShare(), "测试推荐分享视频不存在");
        System.out.println("====" + JSON.toJSONString(share));

        // 视频存在
        memberVideo = new MemberVideo(277229L);

        share = memberVideo.share(5617L, new QRCodeShare(), "测试推荐分享视频存在");
        System.out.println("====" + JSON.toJSONString(share));

    }

    @Test
    public void testVideoDetail() {
        // 视频不存在
        MemberVideo memberVideo = new MemberVideo(-1L);

        VideoDetail videoDetail = memberVideo.getVideoDetail();
        VideoDetailDto videoDetailDto = videoDetail.getVideoDetail();
        System.out.println("====" + JSON.toJSONString(videoDetailDto));

        // 视频存在
        memberVideo = new MemberVideo(277229L);

        videoDetail = memberVideo.getVideoDetail();
        videoDetailDto = videoDetail.getVideoDetail();
        System.out.println("====" + JSON.toJSONString(videoDetailDto));

        memberVideo = new MemberVideo(269253L);

        videoDetail = memberVideo.getVideoDetail();
        videoDetailDto = videoDetail.getVideoDetail();
        System.out.println("====" + JSON.toJSONString(videoDetailDto));

    }
}
