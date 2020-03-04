package cn.mycs.service.material.server.bo.share;

import cn.mycs.core.support.DateTimeKit;
import cn.mycs.core.util.CreateIdUtil;
import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.service.material.server.persistence.model.ShareLog;
import cn.mycs.service.material.server.service.IShareLogService;

/**
 * <p>分享日志</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/9 16:23
 * </pre>
 */
public class ShareLogBo {
    private ShareBo share;
    private IShareLogService shareLogService;

    ShareLogBo(ShareBo share) {
        this.share = share;
        this.initMapper();
    }

    private void initMapper() {
        this.shareLogService = SpringContextHolder.getBean("shareLogServiceImpl");
    }

    boolean create(String shareId, Long srcId, Long uid) {
        ShareLog shareLog = new ShareLog();
        shareLog.setShareLogId(CreateIdUtil.createUUID());
        shareLog.setCreateBy(uid);
        shareLog.setShareId(shareId);
        shareLog.setSrcId(srcId);
        shareLog.setAddTime(DateTimeKit.currentTimeSecond());
        shareLog.setShareUid(uid);
        shareLog.setCreateTime(DateTimeKit.currentTimeSecond());
        shareLog.setShareType(1);
        return shareLogService.save(shareLog);

    }
}
