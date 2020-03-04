package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.common.util.RuleUtil;
import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.service.material.provider.bean.dto.CommentDto;
import cn.mycs.service.material.provider.bean.dto.RecommendDto;
import cn.mycs.service.material.provider.bean.dto.VideoDetailDto;
import cn.mycs.service.material.feign.bean.dto.CommentSourceDto;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.ICommentService;
import cn.mycs.service.material.server.service.IShareService;
import cn.mycs.service.material.server.service.IVideoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>视频详情</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 10:00
 * </pre>
 */
public class VideoDetail {
    private IdentityVideo video;
    private ICommentService commentService;
    private IVideoService videoService;
    private IShareService shareService;

    VideoDetail(IdentityVideo video) {
        this.video = video;
        initMapper();
    }

    private void initMapper() {
        this.commentService = SpringContextHolder.getBean("commentService");
        this.videoService = SpringContextHolder.getBean("videoServiceImpl");
        this.shareService = SpringContextHolder.getBean("shareServiceImpl");
    }

    /**
     * 获取视频的评论列表
     */
    public List<CommentDto> getCommentList(Integer page, Integer pageSize) {
        /* 获取视频评论列表 从video 对象获取videoUid
         * 从评论表【comment】根据page和pageSize返回评论列表
         */
        Long videoUserId = video.getVideoUserId();
        VideoInfoVo videoUserLink = videoService.getVideoInfoVo(videoUserId);
        if (videoUserLink != null) {
            Integer videoId = videoUserLink.getVideoId().intValue();
            List<CommentSourceDto> commentSourceDtos = commentService.listVideoComment(videoId, page, pageSize);
            int total = commentSourceDtos.size();
            if (commentSourceDtos.size() > 0) {

                //遍历获取用户id
                Set<Long> uidSet = new HashSet<>(total * 2);
                List<Integer> idList = new ArrayList<>(total);

                for (CommentSourceDto comment : commentSourceDtos) {
                    uidSet.add(comment.getFromUid());
                    uidSet.add(Long.parseLong(comment.getReplyId() + ""));
                    idList.add(comment.getId());
                }
                Map<Long, UserProtocol.UserBaseVo> userMap = new HashMap<>();
                if (uidSet.size() > 0) {
                    List<UserProtocol.UserBaseVo> userList = videoService.getUserByIds(new ArrayList<>(uidSet));
                    for (UserProtocol.UserBaseVo user : userList) {
                        userMap.put(user.getUid(), user);
                    }
                }
                CommentDto dto = null;
                List<CommentDto> list = new ArrayList<>(total);
                for (CommentSourceDto comment : commentSourceDtos) {
                    dto = new CommentDto();
                    //评论id
                    dto.setId(comment.getId());
                    //评论内容
                    dto.setContent(comment.getContent());
                    //评论具体时间
                    dto.setTime(DateTimeKit.parseScondTime(comment.getAddTime(), "yyyy-MM-dd HH:mm:ss"));
                    //评论用户id(user表 id)
                    dto.setUid(comment.getFromUid());
                    //回复者id
                    dto.setReplyUid(comment.getReplyUid());
                    UserProtocol.UserBaseVo fromUser = userMap.get(comment.getFromUid());
                    if (fromUser != null) {
                        //评论者名称
                        dto.setName(fromUser.getRealName());
                        //获取评论者头像
                        dto.setPic(fromUser.getPortraitUrl() == null ? "" : fromUser.getPortraitUrl());
                    } else {
                        UserProtocol.UserBaseVo user = videoService.getUser(comment.getFromUid());
                        if (user != null) {
                            userMap.put(user.getUid(), user);
                        }
                    }
                    //获取回复者名称
                    UserProtocol.UserBaseVo replyUser = userMap.get(comment.getReplyUid());
                    if (replyUser != null) {
                        dto.setReplyName(replyUser.getRealName());
                    }
                    if (replyUser == null) {
                        UserProtocol.UserBaseVo user = videoService.getUser(comment.getReplyUid());
                        if (user != null) {
                            dto.setReplyName(user.getRealName());
                            userMap.put(user.getUid(), user);
                        }
                    }
                    list.add(dto);
                }
                return list;
            }
            return Collections.emptyList();
        }
        return Collections.emptyList();
    }

    /**
     * 获取视频的详细信息，即进入视频播放页第一屏信息
     * 如果视频不存在则返回null
     */
    public VideoDetailDto getVideoDetail() {
        /* 从video 对象获取videoUid
            1、video_user_link表中查询，video_user_link对象
            2、如果video_user_link不存在直接返回null;
            3、如果video_user_link存在则获取video_user_link中的video字段到video表查询video信息
            4、根据VideoDetailDto要求拼装返回的dto，并返回
        * */
        Long videoUserId = video.getVideoUserId();
        VideoInfoVo videoInfoVo = videoService.getVideoInfoVo(videoUserId);
        VideoDetailDto detailDto = new VideoDetailDto();
        if (videoInfoVo != null && videoInfoVo.getMaterialId() == null) {

            Long videoId = videoInfoVo.getVideoId();
            VideoVo videoVo = videoService.getVideo(videoId);
            detailDto.setDesc(videoInfoVo.getDescription());
            detailDto.setTitle(videoInfoVo.getTitle());
            // 视频封面
            String videoImg = RuleUtil.getPhotoUrl(videoUserId.longValue(), "video", 1);
            detailDto.setImgSrc(videoImg);
            if (videoVo != null && videoVo.getDuration() != null) {
                detailDto.setDuration(videoVo.getDuration());
                // 播放次数
                detailDto.setPlayCount(videoInfoVo.getClick());
                // 点赞次数
                detailDto.setPraiseCount(videoInfoVo.getUp());
                // 视频来源
                UserProtocol.UserBaseVo user = videoService.getUser(videoVo.getUploadUid());
                if (user != null) {
                    detailDto.setSrc(user.getRealName());
                }
            }
            // 该视频被多少个人分享推荐了
            Integer how = shareService.howManyShare(videoUserId, 1);
            detailDto.setRecommendCount(how);
            if (how > 0) {
                List<Long> longList = shareService.selectFiveShareUid(videoUserId, 1);
                List<UserProtocol.UserBaseVo> users = videoService.getUserByIds(longList);
                List<String> userAvatars = new ArrayList<>();
                for (UserProtocol.UserBaseVo user : users) {
                    userAvatars.add(user.getPortraitUrl() == null ? "" : user.getPortraitUrl());
                }
                detailDto.setRecommendUser(userAvatars);
            }
            // 查询分类名称
            String cateName = videoService.getCateNames(videoInfoVo.getMaterialId());
            detailDto.setCateName(cateName);
            return detailDto;
        }
        return detailDto;
    }

    /***
     * 获取视频的推荐列表:根据视频id在Share表中获取最前的5个分享推荐人
     */
    public List<RecommendDto> getRecommendList() {
        /* 根据视频id在Share表中获取最前的5个分享推荐人
            1、取出推荐人id
            2、根据用户id，换取用户头像
        * */
        List<RecommendDto> lise = new ArrayList<>();
        Long videoUserId = video.getVideoUserId();
        if (videoUserId != null && videoUserId > 0) {
            //查询视频推荐信息
            List<Share> shaerList = shareService.selectFiveShare(videoUserId, 1);
            return RecommendDataHandle.RecommendDataList(shaerList);
        }
        return lise;
    }
}
