package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.server.exception.NotExitsVideoException;


/**
 * <p>视频抽象</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 9:57
 * </pre>
 */
public interface IdentityVideo extends IInterestPermission {


    /**
     * 根据播放接口返回可播放的信息
     * 播放，如果可以播放则返回视频链接，不能播放返回""
     *
     * @param uid 用户id
     * @return 返回视频的链接
     */
    PlayDto play(Long uid) throws NotExitsVideoException;

    /**
     * 获取分享信息
     *
     * @param uid         分享者
     * @param share       依赖播放接口
     * @param shareReason 分享原因
     * @return 返回视频的链接
     */
    ShareDto share(Long uid, IShare share, String shareReason);

    /**
     * 获取materialUserLink-->id
     *
     * @return 返回materialUserLink-->id
     */
    Long getVideoUserId();


}
