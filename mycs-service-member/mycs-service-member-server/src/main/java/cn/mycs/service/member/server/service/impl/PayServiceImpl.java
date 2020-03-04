package cn.mycs.service.member.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.member.server.persistence.dao.MemberJoinRecordMapper;
import cn.mycs.service.member.server.persistence.model.MemberJoinRecord;
import cn.mycs.service.member.server.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>支付检测接口</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:53
 * </pre>
 */
@Service
public class PayServiceImpl implements IPayService {

    @Autowired
    private MemberJoinRecordMapper memberJoinRecordMapper;

    @Override
    public JsonResult checkedOrder(Long uid, String orderId) {
        MemberJoinRecord memberJoinRecord = memberJoinRecordMapper.selectById(orderId);
        if (memberJoinRecord != null && memberJoinRecord.getStatus() == 1) {
            return JsonResult.success("支付成功");
        } else {
            return JsonResult.fail("开通中");
        }
    }

}
