package cn.mycs.service.material.server;

import cn.mycs.service.material.server.base.BaseTest;
import cn.mycs.service.material.server.bo.material.video.MemberVideo;
import cn.mycs.service.material.server.bo.material.video.VideoDetail;
import cn.mycs.service.material.server.bo.material.video.VideoRecommend;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

/**
 * <p>VideoDetailTest</p>
 * <pre>
 * @author yjy
 * @date 2019/9/11 10:46
 * </pre>
 */
public class VideoDetailTest extends BaseTest {
    @Test
    public void testVideoDetail() {
        // 视频存在
        MemberVideo memberVideo = new MemberVideo(274143L);
        VideoDetail videoDetail = memberVideo.getVideoDetail();
        System.out.println("====" + JSON.toJSONString(videoDetail.getCommentList(1,5)));
    }

    @Test
    public void testVideoDetailGetRecommendList() {
        // 视频存在
        MemberVideo memberVideo = new MemberVideo(22L);
        VideoDetail videoDetail = memberVideo.getVideoDetail();
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoDetail.getRecommendList()));
    }
    @Test
    public void testVideoRecommendList() {
        // 视频存在
        MemberVideo memberVideo = new MemberVideo(22L);
        VideoRecommend videoRecommend = memberVideo.getVideoRecommend();
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(0,0)));
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(1,1)));
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(2,1)));
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(3,1)));
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(4,1)));
        //获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
        System.out.println("====" + JSON.toJSONString(videoRecommend.list(5,1)));
    }
}