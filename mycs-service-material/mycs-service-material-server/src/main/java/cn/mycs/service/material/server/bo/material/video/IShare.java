package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.service.material.provider.bean.dto.ShareDto;

/**
 * <p>分享接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 14:00
 * </pre>
 */
public interface IShare {
    /**
     * 视频分享
     *
     * @param uid         分享者
     * @param material       要分享的视频
     * @param shareReason 分享推荐理由
     * @return 分享的结果
     */
    ShareDto share(Long uid, IdentityVideo material, String shareReason);
}
