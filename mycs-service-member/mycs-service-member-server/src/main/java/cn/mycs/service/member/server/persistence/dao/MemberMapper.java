package cn.mycs.service.member.server.persistence.dao;


import cn.mycs.service.member.server.persistence.model.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:02
 */
public interface MemberMapper extends BaseMapper<Member> {

    /**
     * 根据用户id查询用户会员
     *
     * @param uid 用户id
     * @return 用户会员信息
     */
    Member selectByUid(Long uid);

}
