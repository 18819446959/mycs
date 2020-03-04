package cn.mycs.service.member.server.persistence.dao;

import cn.mycs.service.member.server.persistence.model.MemberIdentityDuration;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 会员时长卡 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:20
 */
public interface MemberIdentityDurationMapper extends BaseMapper<MemberIdentityDuration> {

    /**
     * 根据身份的id查询身份的价格套餐
     *
     * @param identityId 身份的id
     * @return 该身份下的套餐价格列表
     */
    List<MemberIdentityDuration> selectByIdentityId(@Param("identityId") String identityId);
}
