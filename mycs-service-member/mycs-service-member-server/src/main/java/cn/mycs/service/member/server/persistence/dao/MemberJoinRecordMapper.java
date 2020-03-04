package cn.mycs.service.member.server.persistence.dao;


import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * <p>
 * 会员开通记录表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:13
 */
public interface MemberJoinRecordMapper extends BaseMapper<MemberJoinRecord> {

    /**
     * 更加会员付款成功状态
     *
     * @param memberJoinRecordId 会员开通记录id
     * @return 影响行数
     */
    int updateJoinSuccess(String memberJoinRecordId);
}
