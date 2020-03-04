package cn.mycs.service.member.feign.interfaces;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.member.feign.bean.dto.BizCommissionRecordDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>分佣</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 16:20
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_COMMON_SERVER)
public interface BizCommissionRecordClient {
    /**
     * 添加分佣记录
     *
     * @param commissionRecordDto 分佣记录对象
     * @return 分佣记录id
     */
    @PostMapping("/member/commission/add")
    JsonResult<String> addCommissionRecord(@RequestParam("bizCommissionRecord") BizCommissionRecordDto commissionRecordDto);

    /**
     * 分佣成功，更新分佣状态
     *
     * @param businessId 业务id
     * @return code 0 更新失败，code 1更新成功
     */
    @PostMapping("/member/commission/update/settlement/{businessId}")
    JsonResult updateSettlement(@PathVariable("businessId") String businessId);

    /**
     * 提现分佣明细
     *
     * @param businessId 业务id
     * @return 提现分佣明细
     */
    @PostMapping("/member/commission/get/{businessId}")
    JsonResult<BizCommissionRecordDto> selectById(@PathVariable("businessId") String businessId);
}
