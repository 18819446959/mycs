package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.common.util.RuleUtil;
import cn.mycs.core.constant.CommonConstant;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.core.util.ToolUtil;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.provider.bean.dto.VideoInfoDto;
import cn.mycs.service.material.server.exception.NotExitsVideoException;
import cn.mycs.service.material.server.service.IVideoService;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>会员视频</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 10:11
 * </pre>
 */
public class MemberVideo implements IdentityVideo {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private VideoDetail videoDetail;
    private VideoRecommend videoRecommend;
    /**
     * 视频id
     */
    private Long videoUserId;
    private VideoInfoVo videoInfoVo;
    private IVideoService videoService;


    public MemberVideo(Long videoUserId) {
        this.videoUserId = videoUserId;
        initMapper();
    }

    private void initMapper() {
        this.videoService = SpringContextHolder.getBean("videoServiceImpl");
    }

    private VideoInfoVo getVideoUserLink() {
        if (videoInfoVo == null) {

            videoInfoVo = videoService.getVideoInfoVo(videoUserId);
            if (videoInfoVo == null) {
                videoInfoVo = new VideoInfoVo();
            }
        }
        return videoInfoVo;
    }

    public VideoInfoDto getVideoInfo() {
        VideoInfoVo videoUserLink = getVideoUserLink();
        VideoInfoDto videoInfo = new VideoInfoDto();
        videoInfo.setVideoTitle(videoUserLink.getTitle());
        Integer shareCover = videoUserLink.getShareCover();
        String img = "";
        if (shareCover != null && shareCover != 0) {
            img = RuleUtil.getPhotoUrl(shareCover.longValue(), CommonConstant.NAME_UNIFYDIR);
        } else {
            // 如果没有上传分享海报就用大图
            VideoVo video = videoService.getVideo(videoUserLink.getVideoId());
            if (video != null && video.getVideoId() != null) {
                img = this.getVideoPhotoUrl(video.getStatus(), videoUserLink.getVideoCover(),
                        videoUserLink.getVideoId().intValue(), videoUserLink.getVideoInfoId().intValue(), video.getUploadUid(), videoUserLink.getPicList());
            }
        }
        videoInfo.setVideoPoster(img);
        return videoInfo;
    }

    private String getVideoPhotoUrl(Integer status, Integer videoCover, Integer videoId, Integer id, Long videoCreateBy, String picList) {
        switch (status) {
            case 4:
                return ToolUtil.url(CommonConstant.DOMAINS_STATIC) + "/1.0.0/app/templates/images/transform.jpg";
            default:
                JSONArray pics = JSONArray.parseArray(picList);
                if (videoCover == 1 && !CollectionUtils.isEmpty(pics)) {
                    return RuleUtil.getPhotoUrl(id.longValue(), "video", 1) + "?p=" + pics.get(0);
                } else {
                    return RuleUtil.getVideoPhotoUrl(videoCreateBy, videoId.longValue(), "big");
                }
        }
    }

    @Override
    public PlayDto play(Long uid) throws NotExitsVideoException {
        /* 依赖播放抽象实现*/
        if (uid == null) {
            log.error("播放视频，用户id为空");
            return null;
        }
        if (this.getVideoUserId() == null || this.getVideoUserId() <= 0) {
            log.error("播放视频，视频id为空");
            return null;
        }
        boolean cp = canPlay(uid);

        BasePlay play;
        if (cp) {
            play = new NormalPlay();
        } else {
            play = new PreViewPlay();
        }

        return play.play(uid, this);
    }

    @Override
    public ShareDto share(Long uid, IShare share, String shareReason) {
        /* 依赖分享抽象实现*/
        if (uid == null || uid < 0) {
            return null;
        }
        if (this.getVideoUserId() == null || this.getVideoUserId() < 0) {
            return null;
        }
        return share.share(uid, this, shareReason);
    }

    @Override
    public boolean canPlay(Long uid) {
        /* 现阶段都非免费 */
        /*1、在【member】根据用户id查询当前用户是否是会员，
            如果不是会员身份，直接返回false不能播放
            如果是会员身份-->2
          2、当前会员是否在有效期内，（判断会员的结束时间跟当前时间戳）
            如果该会员已过期，直接返回false不能播放
            如果会员没有过期--->3
          3、取出会员身份id，根据身份id和视频id在member_identity_video_link中查询是否存在记录
            如果不存在记录，直接返回false不能播放
            else 返回true，能播放
        * */

        return videoService.checkedPermission(uid, getVideoUserId());
    }

    @Override
    public boolean canShare() {
        /* 先阶段都是返回true表示能分享 */
        return true;
    }

    @Override
    public boolean isFree() {
        /* 现阶段都是返回false，表示不免费*/
        return true;
    }

    /**
     * 获取视频详情对象
     *
     * @return 返回视频详情对象
     */
    public VideoDetail getVideoDetail() {
        if (videoDetail == null) {
            videoDetail = new VideoDetail(this);
        }
        return videoDetail;
    }

    /**
     * 获取视频推荐对象
     *
     * @return 返回视频推荐对象
     */
    public VideoRecommend getVideoRecommend() {
        if (videoRecommend == null) {
            videoRecommend = new VideoRecommend(this);
        }
        return videoRecommend;
    }

    @Override
    public Long getVideoUserId() {
        return videoUserId;
    }

    public void setVideoUserId(Long videoUserId) {
        this.videoUserId = videoUserId;
    }
}
