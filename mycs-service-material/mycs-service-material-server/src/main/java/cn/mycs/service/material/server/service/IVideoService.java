package cn.mycs.service.material.server.service;


import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.front.service.course.material.vo.VideoVo;
import cn.mycs.front.service.user.user.protocol.UserProtocol;

import java.util.List;

/**
 * <p>视频服务</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/20 15:30
 * </pre>
 */
public interface IVideoService {

    /**
     * 获取视频信息
     *
     * @param srcId 资源id
     * @return 视频user对象
     */
    VideoInfoVo getVideoInfoVo(Long srcId);

    /**
     * 获取视频
     *
     * @param videoId 视频id
     * @return 视频对象
     */
    VideoVo getVideo(Long videoId);

    /**
     * 获取用户
     *
     * @param uid 用户id
     * @return 用户
     */
    UserProtocol.UserBaseVo getUser(Long uid);

    /**
     * 用户ids获取用户
     *
     * @param longList 用户ids
     * @return 获取用户
     */
    List<UserProtocol.UserBaseVo> getUserByIds(List<Long> longList);

    /**
     * 通过素材id获取素材所有的分类名称，用、隔开
     *
     * @param materialId 素材id
     * @return 分类名称, 用、隔开
     */
    String getCateNames(Long materialId);

    /**
     * 检测用户是否拥有视频的播放权
     *
     * @param uid     用户id
     * @param videoId 视频id
     * @return true可以播放，false，不可以播放
     */
    boolean checkedPermission(Long uid, Long videoId);
}
