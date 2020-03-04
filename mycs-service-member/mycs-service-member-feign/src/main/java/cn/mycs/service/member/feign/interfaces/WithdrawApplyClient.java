package cn.mycs.service.member.feign.interfaces;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.member.feign.bean.dto.WithdrawApplyDto;
import cn.mycs.service.member.feign.interfaces.hystrixfactory.WithdrawFallbackFactory;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>提现客户端</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/20 15:21
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_COMMON_SERVER,fallbackFactory = WithdrawFallbackFactory.class)
public interface WithdrawApplyClient {
    /**
     * 添加提现申请
     *
     * @param withdrawApplyDto 提现申请
     * @return 提现申请id
     */
    @PostMapping("/member/withdrawApply/add")
    JsonResult<String> addWithdrawApply(@RequestParam("withdrawApply") WithdrawApplyDto withdrawApplyDto);

    /**
     * 提现申请明细
     *
     * @param businessId 业务id
     * @return 提现申请明细
     */
    @GetMapping("/member/withdrawApply/get/{businessId}")
    JsonResult<WithdrawApplyDto> selectById(@PathVariable("businessId") String businessId);

    /**
     * 更新提现申请表状态
     *
     * @param withdrawApplyId 申请表id
     * @param status          要更新的状态
     * @param msg             remark，备注
     * @param arrivalTime     如果是到账状态，则到达时间不为0
     * @return 1成功，0失败
     */
    @PostMapping("/member/withdrawApply/arrival/money")
    JsonResult arrivalMoney(@RequestParam("withdrawApplyId") String withdrawApplyId, @RequestParam("status") Integer status, @RequestParam("msg") String msg, @RequestParam("arrivalTime") int arrivalTime);
}
