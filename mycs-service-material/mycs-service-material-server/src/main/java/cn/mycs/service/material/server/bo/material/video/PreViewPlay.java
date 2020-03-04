package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.service.material.provider.bean.dto.PlayDto;
import cn.mycs.service.material.server.config.VideoConfig;
import cn.mycs.service.material.server.exception.NotExitsVideoException;

/**
 * <p>预览播放</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 14:08
 * </pre>
 */
public class PreViewPlay extends BasePlay {

    @Override
    public PlayDto play(Long uid, IdentityVideo video) throws NotExitsVideoException {
        /* 预览播放，返回播放链接 */
        PlayInfo playInfo = getPlayUrl(uid, video);
        PlayDto playDto = new PlayDto();
        playDto.setIsPreView(1);
        playDto.setStudyLogId(playInfo.getStudyLogId());
        playDto.setPreViewSecond(VideoConfig.getPreview());
        playDto.setPlayUrl(playInfo.getPlayUrl());
        return playDto;
    }
}
