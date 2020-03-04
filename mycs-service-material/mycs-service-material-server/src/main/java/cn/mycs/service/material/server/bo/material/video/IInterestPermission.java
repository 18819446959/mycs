package cn.mycs.service.material.server.bo.material.video;

/**
 * <p>权限策略接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 9:47
 * </pre>
 */
public interface IInterestPermission {
    /**
     * 是否可以播放
     * @param uid 用户id
     * @return true 可以播放，false 不可以播放
     */
    boolean canPlay(Long uid);

    /**
     * 是否可以分享
     *
     * @return true 可以分享，false 不可以分享
     */
    boolean canShare();
    /**
     * 是否免费
     *
     * @return true 免费，false 付费
     */
    boolean isFree();
}
