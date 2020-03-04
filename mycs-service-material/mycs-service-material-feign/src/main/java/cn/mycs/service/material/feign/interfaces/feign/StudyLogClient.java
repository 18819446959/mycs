package cn.mycs.service.material.feign.interfaces.feign;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.core.constant.FeignClientConstant;
import cn.mycs.service.material.feign.interfaces.feign.hystrixfactory.StudyLogFallbackFactory;
import cn.mycs.service.material.feign.bean.dto.StudyLogNewDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>学习考核日志记录表 客户端</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 11:38
 * </pre>
 */
@FeignClient(value = FeignClientConstant.Service.MYCS_COMMON_SERVER, fallbackFactory = StudyLogFallbackFactory.class)
public interface StudyLogClient {
    /**
     * 添加学习日志并返回记录id
     *
     * @param studyLogNew 学习日志对象
     * @return 学习日志记录id
     */
    @RequestMapping(value = "/video/study/add/log", method = RequestMethod.POST, consumes = "application/json")
    JsonResult<Integer> addStudyLog(@RequestBody StudyLogNewDto studyLogNew);
}
