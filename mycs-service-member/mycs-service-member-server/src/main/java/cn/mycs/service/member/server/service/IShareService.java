package cn.mycs.service.member.server.service;

import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;


/**
 * <p>分享相关服务接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 16:46
 * </pre>
 */
public interface IShareService {
    /**
     * 获取分享对象
     *
     * @param shareId 分享id
     * @return 分享对象
     */
    ShareObjectDto getShareObj(String shareId);

    /**
     * 获取VideoInfoVo
     *
     * @param videoInfoId id
     * @return VideoInfoVo
     */
    VideoInfoVo getVideoInfoVo(Long videoInfoId);
}
