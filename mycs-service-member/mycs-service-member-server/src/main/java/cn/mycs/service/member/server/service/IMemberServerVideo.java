package cn.mycs.service.member.server.service;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>支付服务接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:52
 * </pre>
 */
public interface IMemberServerVideo {
    /**
     * 检测用户是否用有某个视频的播放权限
     *
     * @param uid     用户
     * @param videoId 视频id
     * @return true，拥有播放权限，false，没有播放权限
     */
    boolean checkedPermission(@PathVariable("uid") Long uid, @PathVariable("videoId") Long videoId);
}
