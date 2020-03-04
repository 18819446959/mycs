package cn.mycs.service.material.server.service;

import cn.mycs.service.material.feign.bean.dto.StudyLogNewDto;

/**
 * <p>学习日志</p>
 * <pre>
 * @author gitamacai
 * @date 2019/11/19 11:45
 * </pre>
 */

public interface IStudyLogService {
    /**
     * 添加学习日志，并返回记录id
     *
     * @param studyLog 学习日志
     * @return 记录id
     */
    Integer addStudyLog(StudyLogNewDto studyLog);
}
