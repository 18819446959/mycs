package cn.mycs.service.material.server.persistence.dao;

import cn.mycs.service.material.server.persistence.model.Share;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 * 推广分享表 Mapper 接口
 * </p>
 *
 * @author User
 * @date 2019-09-05 18:02:27
 */
public interface ShareMapper extends BaseMapper<Share> {

    /**
     * 该资源有多少个人分享了
     *
     * @param srcId   资源的id
     * @param srcType 资源类型，1：视频
     * @return 分享人数
     */
    Integer howManyShare(@Param("srcId") Long srcId, @Param("srcType") Integer srcType);

    /**
     * 获取最后分享的5个人的id
     *
     * @param srcId   资源的id
     * @param srcType 资源类型，1：视频
     * @return 分享者id
     */
    List<Long> selectFiveShareUid(@Param("srcId") Long srcId, @Param("srcType") Integer srcType);

    /**
     * 获取最后分享的5个人的推荐信息
     *
     * @param srcId   资源的id
     * @param srcType 资源类型，1：视频
     * @return 分享者id
     */
    List<Share> selectFiveShare(@Param("srcId") Long srcId, @Param("srcType") Integer srcType);

    List<Share> selectSharePage(@Param("offset") Integer offset, @Param("limit") Integer limit, @Param("srcId") Long srcId, @Param("srcType") Integer srcType);

}
