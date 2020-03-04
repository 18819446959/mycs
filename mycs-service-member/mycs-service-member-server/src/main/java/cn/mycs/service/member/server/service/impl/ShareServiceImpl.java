package cn.mycs.service.member.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.front.service.course.material.provider.VideoInfoProvider;
import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.service.material.provider.bean.dto.ShareObjectDto;
import cn.mycs.service.member.feign.interfaces.ShareClient;
import cn.mycs.service.member.server.service.IShareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>分享相关服务接口 实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 16:46
 * </pre>
 */
@Service
public class ShareServiceImpl implements IShareService {
    @Autowired
    private ShareClient shareClient;
    @Autowired
    private VideoInfoProvider videoInfoProvider;

    @Override
    public ShareObjectDto getShareObj(String shareId) {
        JsonResult<ShareObjectDto> shareObject = shareClient.getShareObject(shareId);
        if (shareObject.isSuccess()) {
            return shareObject.getData();
        }
        return null;

    }

    @Override
    public VideoInfoVo getVideoInfoVo(Long videoUserId) {
        return videoInfoProvider.detail(videoUserId);
    }
}
