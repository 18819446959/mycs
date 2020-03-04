package cn.mycs.service.material.server.bo.share;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;

import cn.mycs.front.service.course.material.vo.VideoInfoVo;
import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.IShareService;
import cn.mycs.service.material.server.service.IVideoService;

/**
 * <p>分享对象</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 16:23
 * </pre>
 */
public class ShareBo {
    private cn.mycs.service.material.server.bo.share.ShareClickLogBo shareClickLog;
    private cn.mycs.service.material.server.bo.share.ShareLogBo shareLog;
    private String shareId;
    private IShareService shareService;
    private IVideoService materialService;

    public ShareBo(String shareId) {
        this.shareId = shareId;
        initMapper();
    }

    Share getShare() {
        return shareService.getShareById(shareId);
    }

    private void initMapper() {
        this.shareService = SpringContextHolder.getBean("shareServiceImpl");
        this.materialService = SpringContextHolder.getBean("materialServiceImpl");
    }

    private String getSrcName(Long srcId) {
        if (srcId == null) {
            return "";
        }
        VideoInfoVo materialInfoVo = materialService.getVideoInfoVo(srcId);
        if (materialInfoVo == null) {
            return "";
        }
        return materialInfoVo.getTitle();
    }

    private int howManyShare(Long srcId) {
        if (srcId == null) {
            return 0;
        }
        // 该资源有多少个人推荐
        Integer count = shareService.howManyShare(srcId, 1);
        return count == null ? 0 : count;
    }

    /**
     * 创建分享对象
     *
     * @param srcId       分享的资源id，目前来说是视频
     * @param uid         分享者id
     * @param shareReason 分享推荐原因
     * @return true 分享成功，false 分享失败
     */
    public ShareDto createShare(Long srcId, Long uid, String shareReason) {
        Share share = new Share();
        String shareId = CreateIdUtil.createUUID();
        share.setShareId(shareId);
        share.setCreateBy(uid);
        share.setCreateTime(DateTimeKit.currentTimeSecond());
        share.setShareTime(DateTimeKit.currentTimeSecond());
        share.setSrcId(srcId);
        share.setShareUid(uid);
        share.setSrcType(1);
        share.setShareReason(shareReason);
        share.setSrcName(getSrcName(srcId));
        // 首先查询有多少人推荐了
        int few = howManyShare(srcId);
        boolean row = shareService.save(share);
        ShareDto shareDto = new ShareDto();
        if (!row) {
            return shareDto;
        }
        row = getShareLog().create(shareId, srcId, uid);
        if (row) {
            return makeShareDto(few, shareId);
        }
        return shareDto;
    }

    private ShareDto makeShareDto(int few, String shareId) {
        ShareDto shareDto = new ShareDto();
        shareDto.setShareIndex(few + 1);
        shareDto.setShareId(shareId);
        return shareDto;
    }

    public cn.mycs.service.material.server.bo.share.ShareLogBo getShareLog() {
        if (shareLog == null) {
            shareLog = new cn.mycs.service.material.server.bo.share.ShareLogBo(this);
        }
        return shareLog;
    }

    public cn.mycs.service.material.server.bo.share.ShareClickLogBo getShareClickLog() {
        if (shareClickLog == null) {
            shareClickLog = new cn.mycs.service.material.server.bo.share.ShareClickLogBo(this);
        }
        return shareClickLog;
    }
}
