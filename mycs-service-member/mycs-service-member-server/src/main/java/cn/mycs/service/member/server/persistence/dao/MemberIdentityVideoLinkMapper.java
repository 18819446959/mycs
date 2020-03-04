package cn.mycs.service.member.server.persistence.dao;

import cn.mycs.service.member.server.persistence.model.MemberIdentityVideoLink;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员身份视频关系表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:26
 */
public interface MemberIdentityVideoLinkMapper extends BaseMapper<MemberIdentityVideoLink> {

    /**
     * 查询该会员身份下是否有这个视频资源
     *
     * @param memberIdentityId 会员身份id
     * @param videoUserId      视频资源id
     * @return 有 1 无 0
     */
    Integer memberHaveVideo(@Param("memberIdentityId") String memberIdentityId, @Param("videoUserId") Long videoUserId);
}
