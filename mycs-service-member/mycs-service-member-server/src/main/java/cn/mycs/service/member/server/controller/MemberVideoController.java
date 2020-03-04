package cn.mycs.service.member.server.controller;

import cn.mycs.core.base.restful.JsonResult;
import cn.mycs.service.member.provider.interfaces.feign.MemberServerVideoProvider;
import cn.mycs.service.member.server.service.IMemberServerVideo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>会员视频</p>
 * <pre>
 * @author gitamacai
 * @date 2019/9/19 14:52
 * </pre>
 */
@RestController
@RequestMapping
public class MemberVideoController implements MemberServerVideoProvider {
    private Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private IMemberServerVideo memberServerVideo;

    @Override
    public JsonResult<Integer> checkedPermission(@PathVariable("uid") Long uid, @PathVariable("videoId") Long videoId) {
        if (uid == null || uid <= 0) {
            log.error("uid传入错误：uid为：", uid);
            return JsonResult.error("error");
        }
        if (videoId == null || videoId <= 0) {
            log.error("videoId传入错误：videoId为：", videoId);
            return JsonResult.error("error");
        }
        boolean b = memberServerVideo.checkedPermission(uid, videoId);
        int result = b ? 1 : 0;
        return JsonResult.success(result);
    }
}
