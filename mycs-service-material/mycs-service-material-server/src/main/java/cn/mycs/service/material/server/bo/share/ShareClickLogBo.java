package cn.mycs.service.material.server.bo.share;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.service.material.server.bo.share.ShareBo;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.persistence.model.ShareClickLog;
import cn.mycs.service.material.server.service.IShareClickLogService;

/**
 * <p>分享点击日志</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 16:24
 * </pre>
 */
public class ShareClickLogBo {

    private ShareBo share;
    private IShareClickLogService shareLogService;

    ShareClickLogBo(ShareBo share) {
        this.share = share;
        initMapper();
    }

    private void initMapper() {
        this.shareLogService = SpringContextHolder.getBean("shareClickLogServiceImpl");
    }

    public Long click(Long clickUid) {
        Share share = this.share.getShare();
        if (share == null) {
            return 0L;
        }
        ShareClickLog shareClickLog = createShareClickLog(clickUid, share);
        shareLogService.save(shareClickLog);
        return share.getSrcId();
    }

    private ShareClickLog createShareClickLog(Long clickUid, Share share) {
        ShareClickLog shareClickLog = new ShareClickLog();
        shareClickLog.setClickLogId(CreateIdUtil.createUUID());
        shareClickLog.setAddTime(DateTimeKit.currentTimeSecond());
        shareClickLog.setShareId(share.getShareId());
        shareClickLog.setSrcId(share.getSrcId());
        shareClickLog.setShareUid(share.getShareUid());
        shareClickLog.setCreateTime(DateTimeKit.currentTimeSecond());
        shareClickLog.setCreateBy(clickUid);
        shareClickLog.setClickUid(clickUid);
        return shareClickLog;

    }
}
