package cn.mycs.service.member.server.persistence.dao;

import cn.mycs.service.member.server.persistence.model.MemberInviteRecord;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 会员邀请记录表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:31
 */
public interface MemberInviteRecordMapper extends BaseMapper<MemberInviteRecord> {

    /**
     * 根据邀请者和被邀请者的id进行查找
     *
     * @param inveterUid 邀请者的id
     * @param joinUid    被邀请者的id
     * @return 对象
     */
    MemberInviteRecord selectInveterUidAnduid(@Param("inveterUid") Long inveterUid, @Param("joinUid") Long joinUid);

    /**
     * 根据会员开通的id，查询会员的邀请记录
     * @param memberJoinRecordId 会员开通id
     * @return 会员的邀请记录
     */
    MemberInviteRecord selectMemberJoinId(@Param("memberJoinRecordId") String memberJoinRecordId);
}
