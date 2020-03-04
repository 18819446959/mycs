package cn.mycs.service.material.server.service.impl;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.material.feign.bean.dto.StudyLogNewDto;
import cn.mycs.service.material.feign.interfaces.feign.StudyLogClient;
import cn.mycs.service.material.server.service.IStudyLogService;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>学习日志接口实现</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 11:46
 * </pre>
 */
@Service("studyLogService")
public class StudyLogServiceImpl implements IStudyLogService {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private StudyLogClient studyLogClient;

    @Override
    public Integer addStudyLog(StudyLogNewDto studyLog) {
        JsonResult<Integer> jsonResult = studyLogClient.addStudyLog(studyLog);
        if (!jsonResult.isSuccess()) {
            log.error("添加学习日志失败，日志内容：{}", JSON.toJSONString(studyLog));
            return 0;
        } else {
            return jsonResult.getData();

        }
    }
}
