package cn.mycs.service.material.server.bo.material.video;

import cn.mycs.core.util.SpringContextHolder;
import cn.mycs.service.material.provider.bean.dto.RecommendDto;
import cn.mycs.service.material.server.persistence.model.Share;
import cn.mycs.service.material.server.service.IShareService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>视频推荐</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/6 10:00
 * </pre>
 */
public class VideoRecommend {
    private IdentityVideo video;
    private IShareService shareService;

    VideoRecommend(IdentityVideo video) {
        this.video = video;
        initMapper();
    }

    private void initMapper() {
        this.shareService = SpringContextHolder.getBean("shareServiceImpl");
    }

    /**
     * 获取视频的推荐列表
     */
    public List<RecommendDto> list(Integer page, Integer pageSize) {
        /* 根据视频id在Share表中分页获取获取，
            1、取出推荐人id、推荐理由
            2、根据用户id，换取用户头像
        */
        Long videoUserId = video.getVideoUserId();
        if (videoUserId != null && videoUserId > 0) {
            if (page == null || page < 1) {
                page = 1;
            }
            if (pageSize == null || pageSize < 1) {
                pageSize = 10;
            }
            //计算分页
            Integer offset = (page - 1) * pageSize;
            Integer limit = pageSize;
            //查询推荐信息
            List<Share> shaerList = shareService.selectSharePage(offset, limit, videoUserId, 1);
            return RecommendDataHandle.RecommendDataList(shaerList);
        }
        return new ArrayList<>();
    }
}
