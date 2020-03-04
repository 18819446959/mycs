package cn.mycs.service.material.server.service;


import cn.mycs.front.service.user.user.protocol.UserProtocol;
import cn.mycs.service.material.server.persistence.model.Share;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>分享对象服务接口</p>
 * <p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/7 10:23
 * </pre>
 */
public interface IShareService extends IService<Share> {


    /**
     * 多少人推荐了这个视频
     *
     * @param srcId 资源id
     * @param type  类型
     * @return 推荐视频的人数
     */
    Integer howManyShare(Long srcId, int type);

    /**
     * 获取用户
     *
     * @param uid 用户id
     * @return 获取用户
     */
    UserProtocol.UserBaseVo getUser(Long uid);

    /**
     * 查询推荐信息
     *
     * @param offset      偏移量
     * @param limit       分页大小
     * @param videoUserId 视频id
     * @param type        类型
     * @return 推荐信息
     */
    List<Share> selectSharePage(Integer offset, Integer limit, Long videoUserId, int type);

    /**
     * 获取id
     *
     * @param videoUserId
     * @param type
     * @return
     */
    List<Long> selectFiveShareUid(Long videoUserId, int type);

    /**
     * 推荐5人
     *
     * @param videoUserId 推荐视频
     * @param type        类型
     * @return 推荐前5人
     */
    List<Share> selectFiveShare(Long videoUserId, int type);

    /**
     * 分享id
     *
     * @param shareId 分享id
     * @return 分享对象
     */
    Share getShareById(String shareId);
}