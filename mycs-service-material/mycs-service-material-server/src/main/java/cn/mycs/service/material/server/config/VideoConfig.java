package cn.mycs.service.material.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>非会员预览时间</p>
 * <pre>
 * @author gitamacai
 * @date 2019/10/8 18:44
 * </pre>
 */
@Component
public class VideoConfig {
    private static Integer preview = 120;

    @Value("${mycs.video.preview}")
    public void setPreview(Integer preview) {
        VideoConfig.preview = preview;
    }

    public static Integer getPreview() {
        return preview;
    }
}
