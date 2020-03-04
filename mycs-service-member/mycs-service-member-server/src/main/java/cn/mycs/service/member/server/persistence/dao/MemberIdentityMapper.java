package cn.mycs.service.member.server.persistence.dao;

import cn.mycs.service.member.server.persistence.model.MemberIdentity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * <p>
 * 会员身份表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:13
 */
public interface MemberIdentityMapper extends BaseMapper<MemberIdentity> {

    /**
     * 根据会员类型回去会员身份
     *
     * @param memberTypeId 会员类型id
     * @return 会员身份
     */
    List<MemberIdentity> selectByMemberType(String memberTypeId);
}
