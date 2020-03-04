package cn.mycs.service.material.provider.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.material.provider.bean.dto.CommentDto;
import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.provider.bean.dto.RecommendDto;
import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.provider.bean.dto.VideoDetailDto;
import cn.mycs.service.material.provider.bean.dto.VideoInfoDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>分享接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/4 19:23
 * </pre>
 */
public interface VideoServerShareProvider {
    /**
     * 分享、推荐
     *
     * @param uid             用户id
     * @param videoUserId     视频id
     * @param recommendReason 推荐理由
     * @return 分享、推荐
     */
    @RequestMapping(value = "/videoserver/share", method = RequestMethod.POST, consumes = "application/json")
    JsonResult<ShareDto> share(@RequestParam("uid") Long uid, @RequestParam("videoUserId") Long videoUserId, @RequestParam("recommendReason") String recommendReason);

    /**
     * 请求分享资源
     *
     * @param videoUserId 视频id
     * @return 请求分享资源
     */
    @RequestMapping(value = "/videoserver/share/video/{videoUserId}/info", method = RequestMethod.GET)
    JsonResult<VideoInfoDto> shareVideoInfo(@PathVariable("videoUserId") Long videoUserId);

    /**
     * 视频详情页
     *
     * @param videoUserId videoUserLinkId
     * @return 视频详情页z内容
     */
    @RequestMapping(value = "/videoserver/share/video/{videoUserId}/videoDetail", method = RequestMethod.GET)
    JsonResult<VideoDetailDto> videoDetail(@PathVariable("videoUserId") Long videoUserId);

    /**
     * 视频评论列表
     *
     * @param videoUserId videoUserLinkId
     * @param page        页面
     * @param pageSize    分页大小
     * @return 视频评论列表
     */
    @RequestMapping(value = "/videoserver/share/video/{videoUserId}/comment/list/{page}/{pageSize}", method = RequestMethod.GET)
    JsonResult<List<CommentDto>> videoCommentList(@PathVariable("videoUserId") Long videoUserId, @PathVariable("page") Integer page, @PathVariable("videoUserId") Integer pageSize);

    /**
     * 视频推荐列表
     *
     * @param videoUserId videoUserLinkId
     * @param page        页面
     * @param pageSize    分页大小
     * @return 视频评论列表
     */
    @RequestMapping(value = "/videoserver/share/video/{videoUserId}/recommend/list/{page}/{pageSize}", method = RequestMethod.GET)
    JsonResult<List<RecommendDto>> videoRecommendList(@PathVariable("videoUserId") Long videoUserId, @PathVariable("page") Integer page, @PathVariable("pageSize") Integer pageSize);

    /**
     * 视频播放
     *
     * @param uid         用户id
     * @param videoUserId videoUserId
     * @return 视频播放
     */
    @RequestMapping(value = "/videoserver/video/{uid}/{videoUserId}/play", method = RequestMethod.GET)
    JsonResult<PlayDto> videoPlay(@PathVariable("uid") Long uid, @PathVariable("videoUserId") Long videoUserId);

    /**
     * 分享点击中间处理
     *
     * @param uid     用户id
     * @param shareId 分享id
     * @return 处理
     */
    @RequestMapping(value = "/videoserver/video/share/click/{uid}/{shareId}", method = RequestMethod.GET)
    JsonResult<Long> shareVideoClick(@PathVariable("uid") Long uid, @PathVariable("shareId") String shareId);
}
