package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.service.material.provider.bean.dto.ShareDto;
import cn.mycs.service.material.server.bo.share.ShareBo;

/**
 * <p>二维码分享</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 14:11
 * </pre>
 */
public class QRCodeShare implements IShare {
    @Override
    public ShareDto share(Long uid, IdentityVideo video, String shareReason) {
        /* 通过二维码进行分享
        1、将分享相关的信息插入Share分享表中
        2、将分享相关的信息插入ShareLog分享日志表中
        * */
        ShareBo shareBo = new ShareBo(null);
        return shareBo.createShare(video.getVideoUserId(), uid, shareReason);
    }
}
