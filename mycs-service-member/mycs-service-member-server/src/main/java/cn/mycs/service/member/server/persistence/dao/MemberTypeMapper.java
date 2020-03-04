package cn.mycs.service.member.server.persistence.dao;


import cn.mycs.service.member.server.persistence.model.MemberType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


import java.util.List;

/**
 * <p>
 * 会员类型表 Mapper 接口
 * </p>
 *
 * @author gintamacai
 * @date 2019-09-05 16:16:36
 */
public interface MemberTypeMapper extends BaseMapper<MemberType> {
    /**
     * 查询全部
     *
     * @return 全部会员
     */
    List<MemberType> selectAll();
}
