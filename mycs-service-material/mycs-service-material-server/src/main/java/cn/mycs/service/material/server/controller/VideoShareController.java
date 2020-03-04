package cn.mycs.service.material.server.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.material.provider.bean.dto.CommentDto;
import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.provider.bean.dto.RecommendDto;
import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.provider.bean.dto.VideoDetailDto;
import cn.mycs.service.material.provider.bean.dto.VideoInfoDto;
import cn.mycs.service.material.provider.interfaces.feign.VideoServerShareProvider;
import cn.mycs.service.material.server.bo.share.ShareBo;
import cn.mycs.service.material.server.bo.share.ShareClickLogBo;
import cn.mycs.service.material.server.bo.material.video.IShare;
import cn.mycs.service.material.server.bo.material.video.MemberVideo;
import cn.mycs.service.material.server.bo.material.video.QRCodeShare;
import cn.mycs.service.material.server.exception.NotExitsVideoException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>视频分享接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 17:16
 * </pre>
 */
@RestController
@RequestMapping
public class VideoShareController implements VideoServerShareProvider {
    @Override
    public JsonResult<ShareDto> share(@RequestParam("uid") Long uid, @RequestParam("videoUserId") Long videoUserId, @RequestParam("recommendReason") String recommendReason) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        IShare share = new QRCodeShare();
        ShareDto shareDto = memberVideo.share(uid, share, recommendReason);
        return JsonResult.success(shareDto);
    }

    @Override
    public JsonResult<VideoInfoDto> shareVideoInfo(@PathVariable("videoUserId") Long videoUserId) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        VideoInfoDto videoInfo = memberVideo.getVideoInfo();
        return JsonResult.success(videoInfo);
    }

    @Override
    public JsonResult<VideoDetailDto> videoDetail(@PathVariable("videoUserId") Long videoUserId) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        VideoDetailDto videoDetail = memberVideo.getVideoDetail().getVideoDetail();
        return JsonResult.success(videoDetail);
    }

    @Override
    public JsonResult<List<CommentDto>> videoCommentList(@PathVariable("videoUserId") Long videoUserId, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        List<CommentDto> commentDtos = memberVideo.getVideoDetail().getCommentList(page, pageSize);
        return JsonResult.success(commentDtos);
    }

    @Override
    public JsonResult<List<RecommendDto>> videoRecommendList(@PathVariable("videoUserId") Long videoUserId, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        List<RecommendDto> recommendDtos = memberVideo.getVideoRecommend().list(page, pageSize);
        return JsonResult.success(recommendDtos);
    }

    @Override
    public JsonResult<PlayDto> videoPlay(@PathVariable("uid") Long uid, @PathVariable("videoUserId") Long videoUserId) {
        MemberVideo memberVideo = new MemberVideo(videoUserId);
        try {
            PlayDto play = memberVideo.play(uid);
            return JsonResult.success(play);
        } catch (NotExitsVideoException e) {
            return JsonResult.fail(e.getMessage());
        }

    }

    @Override
    public JsonResult<Long> shareVideoClick(@PathVariable("uid") Long uid, @PathVariable("shareId") String shareId) {
        ShareBo shareBo = new ShareBo(shareId);
        ShareClickLogBo shareClickLog = shareBo.getShareClickLog();
        Long videoId = shareClickLog.click(uid);
        return JsonResult.success(videoId);
    }
}
